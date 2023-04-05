public class MongoDatabase extends AbstractDatabase {
    @Override
    public void get() {
        System.out.println("Get by Mongo");
    }
    @Override
    public void update() {
        System.out.println("Update by Mongo");
    }
}
