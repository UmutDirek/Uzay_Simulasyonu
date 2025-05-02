/**
*
* @author Umut Direk umut.direk@ogr.sakarya.edu.tr
* @since 23.04.2025
* <p>
* Bu sınıf, gezegenler arası seyahat simülasyonunu başlatır ve yönetir.
* Kişiler, uzay araçları ve gezegen bilgileri dosyalardan okunarak
* simülasyon başlatılır.
* </p>
*/
import java.util.*;


public class Simulasyon {

    public static void main(String[] args) {
        // Kişi, uzay aracı ve gezegen verileri dosyalardan okunuyor
        List<Kisi> kisiler = DosyaOkuma.kisileriOku("Kisiler.txt");
        List<UzayAraci> araclar = DosyaOkuma.araclariOku("Araclar.txt");
        List<Gezegen> gezegenler = DosyaOkuma.gezegenleriOku("Gezegenler.txt");

        // Kişiler ait oldukları uzay araçlarına atanıyor
        for (Kisi kisi : kisiler) {
            for (UzayAraci arac : araclar) {
                if (arac.isim.equals(kisi.uzayAraciAdi)) {
                    arac.yolcuEkle(kisi);
                }
            }
        }

        // Simülasyon başlatılıyor
        simulasyonuBaslat(kisiler, araclar, gezegenler);
    }

    /**
     * Simülasyonu başlatır ve zaman ilerledikçe her şeyi günceller.
     * Uzay araçlarının hareketi, kişilerin yaşlanması, nüfus takibi ve
     * varış bilgileri bu metodla kontrol edilir.
     */
    public static void simulasyonuBaslat(List<Kisi> kisiler, List<UzayAraci> araclar, List<Gezegen> gezegenler) {
        Map<String, Gezegen> gezegenMap = new HashMap<>();
        for (Gezegen g : gezegenler) gezegenMap.put(g.isim, g);

        // Her aracın çıkış ve varış tarihleri hesaplanıyor
        for (UzayAraci arac : araclar) {
            Gezegen cikis = gezegenMap.get(arac.cikisGezegeni);
            Gezegen varis = gezegenMap.get(arac.varisGezegeni);
            arac.kalanBaslangicSaat = Zaman.tarihToSaat(arac.cikisTarihi, cikis.gunSaat, cikis.baslangicGun, cikis.baslangicAy, cikis.baslangicYil);
            int varisSaat = arac.kalanBaslangicSaat + arac.mesafeSaat;
            arac.hedefVarisTarihi = Zaman.tarihHesapla(varisSaat, varis.gunSaat, varis.baslangicGun, varis.baslangicAy, varis.baslangicYil);
        }

        while (true) {
            temizle();            
            for (Kisi k : kisiler) k.yaslan();
            for (Gezegen g : gezegenler) g.saatGuncelle();
            for (UzayAraci a : araclar) {
                if (a.hedefeKalanSaat == 0 && a.imhaMi()) {
                    a.imhaEt();
                }
            }

            boolean devam = false;

            for (UzayAraci a : araclar) {
                Gezegen cikis = gezegenMap.get(a.cikisGezegeni);
                boolean basladi = cikis.toplamSaat >= a.kalanBaslangicSaat;
                if (!a.imhaMi() && basladi && a.hedefeKalanSaat > 0) {
                    a.ilerle();
                }
                if (!a.imhaMi() && (a.hedefeKalanSaat > 0 || !basladi)) {
                    devam = true;
                }
            }

            // Gezegen nüfusları hesaplanır
            Map<String, Integer> nufuslar = new HashMap<>();
            for (Gezegen g : gezegenler) nufuslar.put(g.isim, 0);

            for (UzayAraci a : araclar) {
                Gezegen cikis = gezegenMap.get(a.cikisGezegeni);
                boolean yoldaMi = cikis.toplamSaat >= a.kalanBaslangicSaat;

                for (Kisi k : a.yolcular) {
                    if (k.hayattaMi()) {
                        if (a.hedefeKalanSaat == 0 && !a.imhaMi()) {
                            nufuslar.put(a.varisGezegeni, nufuslar.get(a.varisGezegeni) + 1);
                        } else if (!yoldaMi) {
                            nufuslar.put(a.cikisGezegeni, nufuslar.get(a.cikisGezegeni) + 1);
                        }
                    }
                }
            }

            // Konsola simülasyonun güncel durumu yazdırılır
            System.out.println(" Gezegenler:\n");
            System.out.printf("%-15s", "");
            for (Gezegen g : gezegenler) System.out.printf("%-20s", "--- " + g.isim + " ---");
            System.out.println();

            System.out.printf("%-15s", "Tarih");
            for (Gezegen g : gezegenler) System.out.printf("%-20s", g.anlikTarih());
            System.out.println();

            System.out.printf("%-15s", "Nufus");
            for (Gezegen g : gezegenler) System.out.printf("%-20d", nufuslar.get(g.isim));
            System.out.println();

            System.out.println("\nUzay Araclari:\n");
            System.out.printf("%-12s%-12s%-12s%-12s%-20s%-20s\n", "Arac Adi", "Durum", "Cikis", "Varis", "Kalan Saat", "Varis Tarihi");

            for (UzayAraci a : araclar) {
                Gezegen cikis = gezegenMap.get(a.cikisGezegeni);
                boolean basladi = cikis.toplamSaat >= a.kalanBaslangicSaat;

                String durum = a.imhaMi() ? "IMHA" : (!basladi ? "BEKLİYOR" : (a.hedefeKalanSaat > 0 ? "YOLDA" : "VARIS"));
                String kalan = a.imhaMi() ? "--" : String.valueOf(a.hedefeKalanSaat);
                String tarih = a.imhaMi() ? "--" : a.hedefVarisTarihi;

                System.out.printf("%-12s%-12s%-12s%-12s%-20s%-20s\n", a.isim, durum, a.cikisGezegeni, a.varisGezegeni, kalan, tarih);
            }

            if (!devam) break;
        }

        System.out.println("\nTum uzay araclari hedefine vardi veya imha oldu.");
        System.exit(0); //
    }

    
    // Konsol ekranını temizler.
    public static void temizle() {
        try {
            if (System.getProperty("os.name").contains("Windows"))
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            else
                System.out.print("\033[H\033[2J");
        } catch (Exception e) {
            for (int i = 0; i < 50; i++) System.out.println();
        }
    }
}

