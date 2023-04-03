public class Yoneticiler extends Calisanlar {
    private int yoneticiDerece;
    public Yoneticiler(String ad, String soyad, int yas, String durum, int yoneticiDerece, int maas) {
        super(ad, soyad, yas, durum, maas);
        this.yoneticiDerece = yoneticiDerece;
    }
    public int getYoneticiDerece() {
        return yoneticiDerece;
    }
    public void setYoneticiDerece(int yoneticiDerece) {
        this.yoneticiDerece = yoneticiDerece;
    }
    @Override
    public void bilgiYazdir(){
        super.bilgiYazdir();
        System.out.println("Yonetici derecesi: "+ this.yoneticiDerece);
    }
}
