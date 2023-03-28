import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Seçim yapın:");
            System.out.println("1 - Kayıt Ol");
            System.out.println("2 - Giriş Yap");
            System.out.println("3 - Çıkış Yap");
            int choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) {
                Users.register(scanner);
            } else if (choice == 2) {
                Users.login(scanner);
            } else if (choice == 3) {
                System.out.println("Çıkış yapılıyor...");
                break;
            } else {
                System.out.println("Geçersiz seçim!");
            }
        }
        scanner.close();
    }
}
