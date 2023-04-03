public class GeceIsci extends Calisanlar {
    public GeceIsci(String ad, String soyad, int yas, String durum, int maas) {
        super(ad, soyad, yas, durum, maas);
    }
    @Override
    public void bilgiYazdir(){
        super.bilgiYazdir();
        System.out.println("Bu isci gece calisiyor.");
    }
    
}
