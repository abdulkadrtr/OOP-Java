import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;
import java.util.Scanner;
import java.io.*;


public class Users {
    private int id;
    private String name;
    private String email;
    private String password;

    public static void dataRegister(String csvMetin) {
        String dosyaAdi = "database/database.csv";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(dosyaAdi, true));
            writer.write(csvMetin);
            writer.close();
            System.out.println("Kullanıcı kaydı başarılı!");
        } catch (IOException e) {
            System.out.println("CSV dosyası yazılırken hata oluştu: " + e.getMessage());
        }
    }

    public static String dataFormat(String s1, String s2, String s3) {
        return String.format("%s,%s,%s\n", s1, s2, s3);
    }
    public static void login(Scanner scanner){
        System.out.print("E-posta adresinizi girin: ");
        String email = scanner.nextLine();
        System.out.print("Şifrenizi girin: ");
        String password = scanner.nextLine();
        password = hashString(password);
        if (isUserValid(email, password)){
            System.out.println("Giriş başarılı!");
        } else {
            System.out.println("Giriş başarısız!");
        }
    }

    public static boolean isUserValid(String email, String password) {
        String csvFile = "database/database.csv";
        String line = "";
        String cvsSplitBy = ",";
        boolean userFound = false;

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] user = line.split(cvsSplitBy);
                if (email.equals(user[1]) && password.equals(user[2])) {
                    userFound = true;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return userFound;
    }
    public static void register(Scanner scanner){
        System.out.print("Adınızı girin: ");
        String name = scanner.nextLine();
        System.out.print("E-posta adresinizi girin: ");
        String email = scanner.nextLine();
        System.out.print("Şifrenizi girin: ");
        String password = scanner.nextLine();
        Users user = new Users();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        String data = dataFormat(user.name, user.email, user.password);
        dataRegister(data);
    }

    public static String hashString(String input){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(input.getBytes());
            Formatter formatter = new Formatter();
            for (byte b : hashBytes) {
                formatter.format("%02x", b);
            }
            return formatter.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getPassword() {
        return password;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = hashString(password);
    }
}
