import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.ArrayList;

public class App{
    public static void main(String[] args) {
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        PrintStream printStream = new PrintStream(new CustomOutputStream(textArea));
        System.setOut(printStream);
        JFrame frame = new JFrame("SERVER UYGULAMASI");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JScrollPane scrollPane = new JScrollPane(textArea);
        frame.getContentPane().add(scrollPane);
        JButton button = new JButton("ARAYUZ AC");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Tuşa tıklandığında açılacak eylem
                String filePath = "src/gui/login.html";
                try {
                    File htmlFile = new File(filePath);
                    Desktop.getDesktop().open(htmlFile);
                } catch (IOException ex) {
                    System.out.println("Dosya açma hatası: " + ex.getMessage());
                }
            }
        });
        frame.add(button, BorderLayout.NORTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        System.out.println("Arayuz icin ARAYUZ AC tusuna tiklayiniz.");
        System.out.println(" \"Server started. Listening port: 8080\" mesajı gelene kadar lütfen bekleyiniz.\n");
        System.out.println("Mesaj geldikten sonra uygulamayı kullanmaya baslayabilirsiniz.\n");
        System.out.println("Mesaj gelmemesi durumunda 8080 portu dolu oldugundan uygulama calisamaz.\n");
        System.out.println("Boyle bir durumda 8080 portunu bosaltmak icin beniOkuOnemli.txt dosyasini okuyunuz.\n\n");
        //Programı calistirdiktan sonra kullanmaya baslamak icin lutfen login.html 
        //dosyasını herhangi bir tarayıcıda açınız.
        //JUINIT paketi haricinde herhangi bir paket kullanılmamıştır.
        //Kodların tamamı java standart kütüphanesi kullanılarak yazılmıştır.
        //.ser dosyalarını okuyup içeriklerini arraylistlere atıyoruz.
        ArrayList<User> users = Serialization.readUsersFromFile("src/ser/users.ser");
        ArrayList<Exercises> exercises = Serialization.readExercisesFromFile("src/ser/exercises.ser");
        ArrayList<ExercisesLog> exercisesLogs = Serialization.readExercisesLogsFromFile("src/ser/exercisesLogs.ser");
        //server başlatma kodları
        //Gelen HTTP isteklerini dinler ve gerekli işlemleri yapar.
        //Gelen isteklerin geldiği URL'e göre gerekli işlemleri yapar.
        //İçerikler JSON formatında alınır ve gönderilir.
        //GUI arayüzü için login.html dosyasını herhangi bir tarayıcıda açarak yazılımı kullanmaya baslayabilirisiniz.
        //JUNIT testinin calismasi icin kesinlikle admin kullanıcısı 123 sifresiyle oluşturulmuş olmalıdır.
        //Server çalışması icin 8080 portu boş olmalıdır.
        //GUI ile server arasındaki bağlantı 8080 portundan sağlanır.
        //CLASSROOM Furkan Cakmak OOP sınıfında sorulan WEB GUI yapılabilir mi sorusuna verilen evet cevabı
        //referans alınarak böyle bir yapı oluşturulmuştur.
        //detaylı excel raporları proje dizininde oluşur. Ayrıca excel ile oluşturulan dosya uyarı vererek açılır
        //uyarı görmezden gelinip dosya açılırsa içerikler görülebilir.
        Server server = new Server();
        server.startServer(users, exercises,exercisesLogs);   


    }
    public static class CustomOutputStream extends OutputStream {
        private JTextArea textArea;

        public CustomOutputStream(JTextArea textArea) {
            this.textArea = textArea;
        }

        @Override
        public void write(int b) {
            textArea.append(String.valueOf((char) b));
        }

        @Override
        public void write(byte[] b, int off, int len) {
            textArea.append(new String(b, off, len));
        }
    }
}