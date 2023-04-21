public class Otomobil extends Arac {
    private int kapiSayisi;
    public Otomobil(String marka, String model, int yil, int kapiSayisi){
        super(marka, model, yil);
        this.kapiSayisi = kapiSayisi;
    }
    public int getKapiSayisi(){
        return kapiSayisi;
    }
    public void setKapiSayisi(int kapiSayisi){
        this.kapiSayisi = kapiSayisi;
    }
    public void hareketEt(){
        System.out.println("Otomobil hareket ediyor.");
    }
    @Override
    public String toString(){
        return "Marka: " + marka + "\nModel: " + model + "\nYil: " + yil + "\nKapi Sayisi: " + kapiSayisi;
    }
}
