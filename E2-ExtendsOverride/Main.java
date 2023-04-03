public class Main{
    public static void main(String[] args){
        Calisanlar[] calisanlar = new Calisanlar[3]; // Calisanlar classindan olusturulan nesneleri tutacak bir dizi(Alt siniflar buraya da dahil edilebilir.)
        Yoneticiler yonetici = new Yoneticiler("Ahmet","T", 4500,"Yonetici",10,7562);
        calisanlar[0] = yonetici;
        //yonetici.bilgiYazdir();
        GunduzIsci gunduzIsci = new GunduzIsci("Ali","T", 4500,"Isci",3876);
        calisanlar[1] = gunduzIsci;
        //gunduzIsci.bilgiYazdir();
        GeceIsci geceIsci = new GeceIsci("Veli","T", 4500,"Isci",4876);
        calisanlar[2] = geceIsci;
        //geceIsci.bilgiYazdir(); 
        for(Calisanlar calisan:calisanlar){
            calisan.bilgiYazdir();
        }
        System.out.println("Yonetici maasi: "+yonetici.getMaas()); // getMaas() methodu Calisanlar classindan miras alinmistir.
        System.out.println("Gunduz isci maasi: "+gunduzIsci.getMaas());
        System.out.println("Gece isci maasi: "+geceIsci.getMaas());
    }
}