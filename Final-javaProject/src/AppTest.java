import org.junit.*;
import static org.junit.Assert.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class AppTest {
    //Test islemlerinin calismasi icin serverin calismasi gerekmektedir.
    //Login isleminin dogru calisması icin admin kullanicisi 123  sifresiyle onceden oluşturulmuş olmalıdır.
    private String username;
    @Before
    public void setUp(){
        StringBuilder sb = new StringBuilder();
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        Random rnd = new Random();
        for(int i = 0; i < 15; i++)
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        this.username = sb.toString();//http 200 veya 201 için sistemde olmayan bir username oluşturuyoruz.
    }

    @Test
    public void registerTest(){
        String url = "http://localhost:8080/register";
        String data ="{\"username\":\""+ this.username +"\",\"password\":\"123\",\"isAdmin\":false\"}";
        boolean result = sendPost(url, data);
        assertEquals(true, result);
    }
    @Test
    public void loginTest() {
        String url = "http://localhost:8080/login";
        String data = "{\"username\":\"admin\",\"password\":\"123\"}";
        boolean result = sendPost(url, data);
        assertEquals(true, result);
    }
    @Test
    public void createExercise(){
        String url = "http://localhost:8080/createExercise";
        String data = "{\"start\":\"1\",\"end\":\"10\",\"questionCount\":10\"}";
        boolean result = sendPost(url, data);
        assertEquals(true, result);
    }
    @Test
    public void totalExerciseNumber(){
        String url = "http://localhost:8080/totalExerciseNumber";
        String data = "";
        boolean result = sendPost(url, data);
        assertEquals(true, result);
    }

    private boolean sendPost(String url,String requestBody){
        try {
            URL obj = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("Content-Length", String.valueOf(requestBody.length()));
            connection.setRequestProperty("", requestBody);
            int responseCode = connection.getResponseCode();
            if (responseCode == 200 || responseCode == 201)
                return true;
            else
                return false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
