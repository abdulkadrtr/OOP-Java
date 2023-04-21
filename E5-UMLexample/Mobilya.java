public class Mobilya {
    private String tur;
    private String renk;
    public Mobilya(){
        this.tur = "Masa";
        this.renk = "Kahverengi";
    }
    public String getTur(){
        return tur;
    }
    public String getRenk(){
        return renk;
    }
    public void setTur(String tur){
        this.tur = tur;
    }
    public void setRenk(String renk){
        this.renk = renk;
    }
}
