public class Arac {
    protected String marka;
    protected String model;
    protected int yil;
    public Arac(String marka, String model, int yil){
        this.marka = marka;
        this.model = model;
        this.yil = yil;
    }
    public void calistir(){
        System.out.println("Arac calistirildi.");
    }
    public String getMarka(){
        return marka;
    }
    public String getModel(){
        return model;
    }
    public int getYil(){
        return yil;
    }
    public void setMarka(String marka){
        this.marka = marka;
    }
    public void setModel(String model){
        this.model = model;
    }
    public void setYil(int yil){
        this.yil = yil;
    }

}
