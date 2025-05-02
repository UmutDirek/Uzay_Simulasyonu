/**
*
* @author Umut Direk umut.direk@ogr.sakarya.edu.tr
* @since 22.04.2025
* <p>
* Bu sınıf, simülasyonda zaman hesaplamalarını gerçekleştirmek için yardımcı metotlar sağlar.
* Saat cinsinden geçen zamanı tarih formatına çevirme veya bir tarihi saat cinsinden hesaplama işlemleri yapılır.
* Aylar 30 gün, yıllar 12 ay üzerinden varsayılmıştır.
* </p>
*/
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Zaman {

	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d.M.yyyy");

    // Başlangıç tarihi ve toplam geçen saat bilgisi verildiğinde yeni tarihi hesaplar
    public static String tarihHesapla(int toplamSaat, int gunSaat, int basGun, int basAy, int basYil) {
        LocalDate baslangic = LocalDate.of(basYil, basAy, basGun);
        int toplamGun = toplamSaat / gunSaat;
        LocalDate yeniTarih = baslangic.plusDays(toplamGun);
        return yeniTarih.format(formatter);
    }

    // Verilen tarih stringini saat cinsine çevirir
    public static int tarihToSaat(String tarih, int gunSaat, int basGun, int basAy, int basYil) {
        LocalDate baslangic = LocalDate.of(basYil, basAy, basGun);
        LocalDate verilenTarih = LocalDate.parse(tarih, formatter);
        int toplamGun = (int) java.time.temporal.ChronoUnit.DAYS.between(baslangic, verilenTarih);
        return toplamGun * gunSaat;
    }
}
