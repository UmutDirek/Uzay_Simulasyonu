/**
*
* @author Umut Direk umut.direk@ogr.sakarya.edu.tr
* @since 22.04.2025
* <p>
* Bu sınıf, simülasyonda kullanılacak olan kişi, uzay aracı ve gezegen verilerini 
*  dosyalardan okuyarak ilgili sınıfların nesnelerini oluşturan yardımcı bir sınıftır.
* </p>
*/
import java.io.*;
import java.util.*;

public class DosyaOkuma {

    public static List<Kisi> kisileriOku(String dosyaAdi) {
        List<Kisi> liste = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(dosyaAdi))) {
            String satir;
            while ((satir = br.readLine()) != null) {
                String[] p = satir.split("#");
                liste.add(new Kisi(p[0], Integer.parseInt(p[1]), Integer.parseInt(p[2]), p[3]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return liste;
    }

    public static List<UzayAraci> araclariOku(String dosyaAdi) {
        List<UzayAraci> liste = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(dosyaAdi))) {
            String satir;
            while ((satir = br.readLine()) != null) {
                String[] p = satir.split("#");
                liste.add(new UzayAraci(p[0], p[1], p[2], p[3], Integer.parseInt(p[4])));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return liste;
    }

    public static List<Gezegen> gezegenleriOku(String dosyaAdi) {
        List<Gezegen> liste = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(dosyaAdi))) {
            String satir;
            while ((satir = br.readLine()) != null) {
                String[] p = satir.split("#");
                liste.add(new Gezegen(p[0], Integer.parseInt(p[1]), p[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return liste;
    }
}
