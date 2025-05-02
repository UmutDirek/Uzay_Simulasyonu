/**
*
* @author Umut Direk umut.direk@ogr.sakarya.edu.tr
* @since 22.04.2025
* <p>
* Bu sınıf, bir uzay aracının temel özelliklerini ve davranışlarını tanımlar.
* Uzay aracının çıkış ve varış gezegenleri, yolculuk süresi, yolcuları gibi bilgileri içerir.
* </p>
*/
import java.time.LocalDate;
import java.util.*;

public class UzayAraci {
    public String isim;
    public String cikisGezegeni;
    public String varisGezegeni;
    public String cikisTarihi;
    public int mesafeSaat;
    public int hedefeKalanSaat;
    public String hedefVarisTarihi;
    public int kalanBaslangicSaat;
    public List<Kisi> yolcular = new ArrayList<>();

    public UzayAraci(String isim, String cikisGezegeni, String varisGezegeni, String cikisTarihi, int mesafeSaat) {
        this.isim = isim;
        this.cikisGezegeni = cikisGezegeni;
        this.varisGezegeni = varisGezegeni;
        this.cikisTarihi = cikisTarihi;
        this.mesafeSaat = mesafeSaat;
        this.hedefeKalanSaat = mesafeSaat;
    }

    public void yolcuEkle(Kisi k) {
        yolcular.add(k);
    }

    //Araçtaki tüm yolcular ölmüşse imha durumu true döner.
    public boolean imhaMi() {
        for (Kisi k : yolcular) {
            if (k.hayattaMi()) return false;
        }
        return true;
    }
    
    //Eğer araç henüz imha olmamışsa ve hedefe ulaşmamışsa bir saat ilerletir.
    public void ilerle() {
        if (!imhaMi() && hedefeKalanSaat > 0) hedefeKalanSaat--;
    }
    
    public void imhaEt() {
        this.hedefeKalanSaat = -1; // Artık yol almıyor
    }

}
