public class Main{
    public static void main(String[] args){
        AbstractDatabase database = new MysqlDatabase(); //AbstractDatabase referansı ile MysqlDatabase nesnesi oluşturuldu.
        //(abstract sınıflar ile nesne oluşturulamaz.)
        database.add(); //AbstractDatabase sınıfı içerisindeki add metodu çağrıldı.
        database.delete(); //AbstractDatabase sınıfı içerisindeki delete metodu çağrıldı.
        database.get(); //MySQLDatabase sınıfı içerisindeki get metodu çağrıldı.
        MongoDatabase database2 = new MongoDatabase(); //MongoDatabase referansı ile MongoDatabase nesnesi oluşturuldu.
        database2.add(); //AbstractDatabase sınıfı içerisindeki add metodu çağrıldı.
        database2.delete(); //AbstractDatabase sınıfı içerisindeki delete metodu çağrıldı.
        database2.get(); //MongoDatabase sınıfı içerisindeki get metodu çağrıldı.

    }
}