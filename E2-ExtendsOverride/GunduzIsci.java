public class GunduzIsci extends Calisanlar {
    public GunduzIsci(String ad, String soyad, int yas, String durum, int maas) {
        super(ad, soyad, yas, durum, maas);
    }
    @Override
    public void bilgiYazdir(){
        super.bilgiYazdir();
        System.out.println("Bu isci gunduz calisiyor.");
    }    
}
