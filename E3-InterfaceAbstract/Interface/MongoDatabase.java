public class MongoDatabase implements IDatabase {
    @Override
    public void add() {
        System.out.println("Added by Mongo");
    }
    @Override
    public void delete() {
        System.out.println("Deleted by Mongo");
    }
    @Override
    public void update() {
        System.out.println("Updated by Mongo");
    }
    @Override
    public void get() {
        System.out.println("Got by Mongo");
    }
}
