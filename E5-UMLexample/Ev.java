public class Ev {
    private Oda mutfak;
    private Oda salon;
    private Oda yatakOdasi;
    public Ev(){
        this.mutfak = new Oda();
        this.salon = new Oda();
        this.yatakOdasi = new Oda();
    }
    @Override
    public String toString(){
        return "Mutfak:\n" + mutfak.toString() + "\nSalon:\n" + salon.toString() + "\nYatak Odasi:\n" + yatakOdasi.toString();
    }
}
