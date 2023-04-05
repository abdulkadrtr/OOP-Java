public abstract class AbstractDatabase {
    public void add(){
        System.out.println("Added by abstract class");
    }
    public void delete(){
        System.out.println("Deleted by abstract class");
    }
    public abstract void get();
    public abstract void update();
    //abstract sınıflar içerisinde gövdesi olan veya olmayan metotlar bulunabilir.
}
