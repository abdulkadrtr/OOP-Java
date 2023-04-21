public class Oda {
    private Mobilya mobilya;
    public Oda(){
        this.mobilya = new Mobilya();
    }
    @Override
    public String toString(){
        return "Mobilya Turu: " + mobilya.getTur() + "\nMobilya Rengi: " + mobilya.getRenk();
    }
}
