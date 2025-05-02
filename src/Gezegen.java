/**
*
* @author Umut Direk umut.direk@ogr.sakarya.edu.tr
* @since 22.04.2025
* <p>
* Bu sınıf, bir gezegenin temel özelliklerini ve simülasyon sürecindeki zaman yönetimini tanımlar.
* Gezegenin başlangıç tarihi, bir günün kaç saat sürdüğü gibi bilgiler burada tutulur.
* </p>
*/
public class Gezegen {
    public String isim;
    public int gunSaat;
    public int toplamSaat = 0;
    public int baslangicGun, baslangicAy, baslangicYil;

    public Gezegen(String isim, int gunSaat, String tarih) {
        this.isim = isim;
        this.gunSaat = gunSaat;
        String[] t = tarih.split("\\.");
        baslangicGun = Integer.parseInt(t[0]);
        baslangicAy = Integer.parseInt(t[1]);
        baslangicYil = Integer.parseInt(t[2]);
    }

    //Gezegenin toplam geçen saate göre anlık tarihini hesaplar.
    public String anlikTarih() {
        return Zaman.tarihHesapla(toplamSaat, gunSaat, baslangicGun, baslangicAy, baslangicYil);
    }
    
    //Gezegenin saatini bir birim artırır. (Simülasyonda zaman ilerlemesi için)
    public void saatGuncelle() {
        toplamSaat++;
    }
}
