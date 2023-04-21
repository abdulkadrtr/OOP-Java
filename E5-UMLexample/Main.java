public class Main{
    public static void main(String[] args){
        //UML Diagram
        // En ust tarafta Class ismi ve Class tipi yer alir.
        // Abstarct class ise italik olarak yazilir.(Eğik yazı)
        // Altinda ise Class icerisindeki degiskenler yer alir. Burada + isareti public oldugunu, - isareti private oldugunu gosterir. 
        //Altı çizili olanlar static oldugunu gosterir.
        // Bir altında metodlar yer alır. Burada + isareti public oldugunu, - isareti private oldugunu gosterir.
        //Extend edilen class ok ile bağlanır.(Düz çizgi) (Inheritance)
        //Implement edilen class ise ok ile bağlanır.(Kesikli çizgi)
        //İçi boş elmas zayıf bağlantıyı gösterir. Elmasın bağlı olduğu sınıf yok edilirse bağlanan sınıf etkilenmez.(aggregation)
        //İçi dolu elmas güçlü bağlantıyı gösterir. Elmasın bağlı olduğu sınıf yok edilirse bağlanan sınıf etkilenir.(yok edilir.) (composition)
        // --------- AGGREGATION ---------
        // Ders sınıfı ve öğrenciler sınıfı bulunur. Ders sınıfı içerisinde öğrenciler sınıfından bir liste bulunur.
        // 2 sınıf arasında zayıf bağlantı vardır. Ders sınıfı yok edilirse öğrenciler sınıfı etkilenmez.
        Ders ders1 = new Ders("Matematik", "MAT101");
        Ogrenci ogrenci1 = new Ogrenci("Ahmet", "Yılmaz");
        Ogrenci ogrenci2 = new Ogrenci("Mehmet", "Kaya");
        Ogrenci ogrenci3 = new Ogrenci("Ali", "Can");
        ders1.ogrenciEkle(ogrenci1);
        ders1.ogrenciEkle(ogrenci2);
        ders1.ogrenciEkle(ogrenci3);
        System.out.println(ders1);
        // --------- COMPOSITION ---------
        // Ev sınıfı , oda sınıfı ve Mobilya sınıfı bulunur.  Ev sınıfı oda sınıfına sahiptir. Oda sınıfı Mobilya sınıfına sahiptir.
        // Sınıflar arasında güçlü bağlantı vardır. Ev sınıfı yok edilirse oda sınıfı etkilenir. Oda sınıfı yok edilirse Mobilya sınıfı etkilenir.
        Ev ev1 = new Ev();
        System.out.println(ev1);
        // --------- INHERITANCE ---------
        //protected ile tanımlanan değişkenler sadece kendi sınıfı ve alt sınıflar tarafından erişilebilir.
        // Arac sınıfı ve Otomobil sınıfı bulunur. Otomobil sınıfı Arac sınıfından türetilmiştir.
        // Arac sınıfı içerisinde protected olarak tanımlanan değişkenler bulunur. Bu değişkenlere Otomobil sınıfından erişilebilir.
        Arac arac1 = new Arac("BMW", "X5", 2010);
        Otomobil otomobil1 = new Otomobil("BMW", "X5", 2010, 5);
        otomobil1.calistir(); //Arac sınıfından gelen metot
        otomobil1.hareketEt(); //Otomobil sınıfına ait metot
        System.out.println(otomobil1);
    }
}