/**
*
* @author Umut Direk umut.direk@ogr.sakarya.edu.tr
* @since 22.04.2025
* <p>
* Bu sınıf, simülasyonda uzay araçlarıyla yolculuk eden kişilerin özelliklerini ve durumlarını tutar.
* Her bir kişi için isim, yaş, kalan ömür ve ait olduğu uzay aracı bilgileri yer alır.
* </p>
*/
public class Kisi {
    public String isim;
    public int yas;
    public int kalanOmur;
    public String uzayAraciAdi;

    public Kisi(String isim, int yas, int kalanOmur, String uzayAraciAdi) {
        this.isim = isim;
        this.yas = yas;
        this.kalanOmur = kalanOmur;
        this.uzayAraciAdi = uzayAraciAdi;
    }

    public boolean hayattaMi() {
        return kalanOmur > 0;
    }

    public void yaslan() {
        if (hayattaMi()) kalanOmur--;
    }
}
