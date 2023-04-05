## Nesne Tabanlı Programlama Çalışmaları

- E1-registerLogin içerisinde Users sınıfı oluşturulmuştur. Bu sınıfa yeni kayıt eklenebilir veya giriş yap seçeneği kullanılabilir. Program database.csv dosyasını
veritabanı olarak kullanır. Kullanıcı şifreleri hashlenerek veritabanına yazılır. 

- E2-ExtendsOverride içerisinde extends ve Override örnekleri bulunmaktadır. Yoneticiler , GeceIsci ve GunduzIsci sınıfları Calisanlar sınıfından kalıtılmıştır. 

- E3-InterfaceAbstract örneğinde, Interface ve Abstract Class'ın kullanımı gösterilmiştir. Interface, kendisini implemente eden her sınıfın tanımlaması zorunlu olan metotlarını tanımlar. İmplemente edilen her sınıf, Interface içerisinde tanımlanan her metodu @Override etmek zorundadır. Yani, Interface içinde tanımlanan bir metodu implemente eden bir sınıf, bu metodu kendi içinde yeniden tanımlamalıdır. Bu sayede, Interface tarafından belirlenen davranışlar, her bir implemente eden sınıf tarafından garanti edilir.
Abstract Class ise, soyut sınıfların temel özelliklerini tanımlayan ve alt sınıflar tarafından uygulanmak üzere bazı metotlar tanımlayan bir sınıftır. Abstract Class'ın alt sınıfları, Abstract Class içinde tanımlanan soyut metotları (abstract methods) implemente etmek veya yeniden tanımlamak zorundadır. Ayrıca, Abstract Class içinde normal metotlar da tanımlanabilir ve alt sınıflar bu normal metotları override edebilir.
Interface içerisinde yalnızca metod tanımı bulunurken Abstract Class içerisinde metod tanımı ve gövdeli metodlar bulunabilir.
