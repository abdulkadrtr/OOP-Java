public class Calisanlar {
    private String ad;
    private String soyad;
    private int yas;
    private String durum;
    private int maas;
    public int getMaas() {
        return maas;
    }
    public void setMaas(int maas) {
        this.maas = maas;
    }
    public String getAd() {
        return ad;
    }
    public void setAd(String ad) {
        this.ad = ad;
    }
    public String getSoyad() {
        return soyad;
    }
    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }
    public int getYas() {
        return yas;
    }
    public void setYas(int yas) {
        this.yas = yas;
    }
    public String getDurum() {
        return durum;
    }
    public void setDurum(String durum) {
        this.durum = durum;
    }
    public Calisanlar(String ad,String soyad,int yas,String durum,int maas){
        this.ad = ad;
        this.soyad = soyad;
        this.yas = yas;
        this.durum = durum;
        this.maas = maas;
    }
    public void bilgiYazdir(){
        System.out.println("Bilgiler:"+this.ad+" "+this.soyad+" "+this.yas+" "+this.durum+" "+this.maas);
    }
}
