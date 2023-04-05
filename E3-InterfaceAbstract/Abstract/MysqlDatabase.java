public class MysqlDatabase extends AbstractDatabase{
    @Override //abstract metotlar override edilmek zorundadır.
    public void get(){
        System.out.println("Get by Mysql");
    } 
    @Override //abstract metotlar override edilmek zorundadır.
    public void update(){
        System.out.println("Update by Mysql");
    }
}
