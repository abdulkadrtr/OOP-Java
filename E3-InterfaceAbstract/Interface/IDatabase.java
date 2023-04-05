public interface IDatabase {
    void add();
    void delete();
    void update();
    void get();
}
//Zorunlu olusturulmasi gereken methodlar tanimlanir.
//eger bir class interface'i implement ediyorsa, interface'deki tum methodlari override etmek zorundadir.
//interface'lerde methodlarin body'si olmaz.