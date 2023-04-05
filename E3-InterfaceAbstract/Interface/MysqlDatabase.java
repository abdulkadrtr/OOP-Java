public class MysqlDatabase implements IDatabase {
    //MysqlDatabase class'i IDatabase interface'ini implement eder.
    //bu sayede MysqlDatabase class'i IDatabase interface'indeki tum methodlari override etmek zorundadir.
    @Override
    public void add() {
        System.out.println("Added by Mysql");
    }
    @Override
    public void delete() {
        System.out.println("Deleted by Mysql");
    }
    @Override
    public void update() {
        System.out.println("Updated by Mysql");
    }
    @Override
    public void get() {
        System.out.println("Got by Mysql");
    }
}
