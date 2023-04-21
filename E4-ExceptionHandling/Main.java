import java.util.*;

public class Main{
    public static void main(String[] args){
        System.out.println("Exception Handling in Java");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = sc.nextLine();
        System.out.print("Enter your age: ");
        int age = sc.nextInt();
        try{
            Person p = new Person(name, age); //Burada hata olursa catch'e gider.
            System.out.println(p);
        }catch (ImpossibleInfo e){ 
            System.out.println(e.getMessage()); //Burada hata mesajı yazdırılır.
        }
        finally { //Hata olsun olmasın çalışır.
            sc.close();
        }
    }
} 