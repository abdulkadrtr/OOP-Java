import java.util.ArrayList;
import java.util.List;

public class Ders {
    private String dersAdi;
    private String dersKodu;
    private List<Ogrenci> ogrenciler;
    
    public Ders(String dersAdi, String dersKodu){
        this.dersAdi = dersAdi;
        this.dersKodu = dersKodu;
        this.ogrenciler = new ArrayList<>();
    }
    public void ogrenciEkle(Ogrenci ogrenci){
        ogrenciler.add(ogrenci);
    }
    public void ogrenciSil(Ogrenci ogrenci){
        ogrenciler.remove(ogrenci);
    }
    @Override
    public String toString(){
        //dersin adı ve dersi alan öğrencilerin adı ve soyadı
        String ogrenciListesi = "";
        for(Ogrenci ogrenci : ogrenciler){
            ogrenciListesi += ogrenci.getAd() + " " + ogrenci.getSoyad() + "\n";
        }
        return dersAdi + " " + dersKodu + "\nKayitli Ogrenciler:\n" + ogrenciListesi;
    }
}
