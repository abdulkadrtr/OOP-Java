class DatabaseManager{ //Database yöneticisi
    public void addDatabase(IDatabase database){
        database.add();
    }
    public void deleteDatabase(IDatabase database){
        database.delete();
    }
    public void updateDatabase(IDatabase database){
        database.update();
    }
    public void getDatabase(IDatabase database){
        database.get();
    }
}

public class Main{
    public static void main(String[] args){
        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.addDatabase(new MysqlDatabase());
        databaseManager.addDatabase(new MongoDatabase());
        databaseManager.deleteDatabase(new MysqlDatabase());
        databaseManager.deleteDatabase(new MongoDatabase());
    }
}