import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.util.Date;
import java.text.SimpleDateFormat;

public class Server {
    private ServerSocket serverSocket;
    private boolean isRunning;
    public final int port = 8080;
    public void startServer(ArrayList<User> users, ArrayList<Exercises> exercises, ArrayList<ExercisesLog> exercisesLogs){
        try{
            //Bu Blok Ana Server Bloğudur.
            //Gelen HTTP isteklerini dinler ve gerekli işlemleri yapar.
            //Gelen isteklerin geldiği URL'e göre gerekli işlemleri yapar.
            //İçerikler JSON formatında alınır ve gönderilir.
            //GUI arayüzü için login.html dosyasını herhangi bir tarayıcıda açarak yazılımı kullanmaya baslayabilirisiniz.
            //JUNIT testinin calismasi icin kesinlikle admin kullanıcısı 123 sifresiyle oluşturulmuş olmalıdır.
            //Server çalışması icin 8080 portu boş olmalıdır.
            //GUI ile server arasındaki bağlantı 8080 portundan sağlanır.
            // CLASSROOM Furkan Cakmak OOP sınıfında sorulan WEB GUI yapılabilir mi sorusuna verilen evet cevabı
            // referans alınarak böyle bir yapı oluşturulmuştur.
            String data,jsonStr,username,dateTimeStr;
            Json json;
            SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            serverSocket = new ServerSocket(port);
            System.out.println("Server started. Listening port: " + port);
            isRunning = true;
            while(isRunning){
                Socket socket = serverSocket.accept();
                InputStream inputStream = socket.getInputStream();
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
                byte[] bytes = new byte[1024];
                int bytesRead = inputStream.read(bytes);
                String request = new String(bytes, 0, bytesRead);
                if(request.startsWith("OPTIONS")){
                    printWriter.println("HTTP/1.1 200 OK");
                    printWriter.println("Access-Control-Allow-Origin: *");
                    printWriter.println("Access-Control-Allow-Methods: POST");
                    printWriter.println("Access-Control-Allow-Headers: Content-Type");
                    printWriter.flush();
                }else if(request.startsWith("POST /login HTTP/1.1")){
                    //Admin veya kullanici girisi buradan yapilir.
                    try{
                        jsonStr = request.split("\n")[17];
                    }catch(Exception e){
                        jsonStr = request.split("\n")[2];
                    }
                    System.out.println(jsonStr);
                    json = new Json();
                    username = json.getValueFromJSON(jsonStr, "username");
                    String password = json.getValueFromJSON(jsonStr, "password");
                    boolean isUserExist = false;
                    int index = 0;
                    while(index<users.size() && !isUserExist){
                        if(users.get(index).getUsername().equals(username) && users.get(index).getPassword().equals(password)){
                            isUserExist = true;
                        }else{
                            index++;
                        }
                    }
                    if(isUserExist && users.get(index).getIsAdmin()){
                        printWriter.println("HTTP/1.1 200 OK");
                        printWriter.println("Access-Control-Allow-Origin: *");
                        printWriter.println("Access-Control-Allow-Methods: POST");
                        printWriter.println("Access-Control-Allow-Headers: Content-Type");
                        printWriter.flush();
                    }else if(isUserExist && !users.get(index).getIsAdmin()){
                        printWriter.println("HTTP/1.1 201 OK");
                        printWriter.println("Access-Control-Allow-Origin: *");
                        printWriter.println("Access-Control-Allow-Methods: POST");
                        printWriter.println("Access-Control-Allow-Headers: Content-Type");
                        printWriter.flush(); 
                    }else{
                        printWriter.println("HTTP/1.1 401 OK");
                        printWriter.println("Access-Control-Allow-Origin: *");
                        printWriter.println("Access-Control-Allow-Methods: POST");
                        printWriter.println("Access-Control-Allow-Headers: Content-Type");
                        printWriter.flush();
                    }
                }else if(request.startsWith("GET /close HTTP/1.1")){
                    printWriter.println("HTTP/1.1 200 OK");
                    printWriter.println("Access-Control-Allow-Origin: *");
                    printWriter.println("Access-Control-Allow-Methods: POST");
                    printWriter.println("Access-Control-Allow-Headers: Content-Type");
                    printWriter.flush();
                    stopServer();
                }else if(request.startsWith("POST /createExercise HTTP/1.1")){
                    //Alistirma olusturulurken bu istek karsilanir.
                    try{
                        jsonStr = request.split("\n")[17];
                    }catch(Exception e){
                        jsonStr = request.split("\n")[2];
                    }
                    json = new Json();
                    int start = Integer.parseInt(json.getValueFromJSON(jsonStr, "start"));
                    int end = Integer.parseInt(json.getValueFromJSON(jsonStr, "end"));
                    int questionCount = Integer.parseInt(json.getValueFromJSON(jsonStr, "questionCount"));
                    Exercises exercise = new Exercises(start, end, questionCount);
                    exercises.add(exercise);
                    printWriter.println("HTTP/1.1 200 OK");
                    printWriter.println("Access-Control-Allow-Origin: *");
                    printWriter.println("Access-Control-Allow-Methods: POST");
                    printWriter.println("Access-Control-Allow-Headers: Content-Type");
                    printWriter.flush();
                    System.out.println("Exercise created. Start: " + start + " End: " + end + " Question Count: " + questionCount);
                    Serialization.writeExercisesToFile(exercises, "src/ser/exercises.ser");
                }else if(request.startsWith("POST /register HTTP/1.1")){
                    //Kayit islemi gerceklestirir.
                    try{
                        jsonStr = request.split("\n")[17];
                    }catch(Exception e){
                        jsonStr = request.split("\n")[2];
                    }
                    json = new Json();
                    username = json.getValueFromJSON(jsonStr, "username");
                    String password = json.getValueFromJSON(jsonStr, "password");
                    boolean isAdmin = Boolean.parseBoolean(json.getValueFromJSON(jsonStr,"isAdmin"));
                    int flag = 0,index = 0;
                    while(flag==0 && users.size()>index){
                        if(users.get(index).getUsername().equals(username)){
                            flag = 1;
                        }else{
                            index++;
                        }
                    }
                    if(flag==0){
                        User user = new User(username, password, isAdmin);
                        users.add(user);
                        printWriter.println("HTTP/1.1 200 OK");
                        printWriter.println("Access-Control-Allow-Origin: *");
                        printWriter.println("Access-Control-Allow-Methods: POST");
                        printWriter.println("Access-Control-Allow-Headers: Content-Type");
                        printWriter.flush();
                        System.out.println("User created. Username: " + username + " Password: " + password + " isAdmin: " + isAdmin);
                        Serialization.writeUsersToFile(users, "src/ser/users.ser");
                    }else{
                        printWriter.println("HTTP/1.1 401 OK");
                        printWriter.println("Access-Control-Allow-Origin: *");
                        printWriter.println("Access-Control-Allow-Methods: POST");
                        printWriter.println("Access-Control-Allow-Headers: Content-Type");
                        printWriter.flush();
                    }
                }else if(request.startsWith("POST /totalExerciseNumber HTTP/1.1")){
                    //Tanimli alistirma sayisi gonderilir.
                    String str = Integer.toString(exercises.size());
                    printWriter.println("HTTP/1.1 200 OK");
                    printWriter.println("Access-Control-Allow-Origin: *");
                    printWriter.println("Access-Control-Allow-Methods: POST");
                    printWriter.println("Access-Control-Allow-Headers: Content-Type");
                    printWriter.println();
                    printWriter.println(str);
                    printWriter.flush();
                }else if(request.startsWith("POST /getSize HTTP/1.1")){
                    //Raporlama sayfasi ilk acildiginda bu istek karsilanarak alistirmayi cozen kac kisinin
                    //oldugu saptanir. Sonrasında her bir kisinin bilgileri gonderilir.(getDatas istegiyle).
                    jsonStr = request.split("\n")[17];
                    json = new Json();
                    int exerciseId = Integer.parseInt(json.getValueFromJSON(jsonStr, "clickedBoxId")) - 1;
                    int size = exercises.get(exerciseId).getTopExercises().size();
                    printWriter.println("HTTP/1.1 200 OK");
                    printWriter.println("Access-Control-Allow-Origin: *");
                    printWriter.println("Access-Control-Allow-Methods: POST");
                    printWriter.println("Access-Control-Allow-Headers: Content-Type");
                    printWriter.println();
                    data =  "{ \"size\": \"" + size + "\"}";
                    printWriter.println(data);
                    printWriter.flush();
                }else if(request.startsWith("POST /getDatas HTTP/1.1")){
                    //Raporlama sayfasinda ilk acildiginda bu istek karsilanir. Alistirmayi cozen kullanicilarin
                    //bilgileri gonderilir.
                    jsonStr = request.split("\n")[17];
                    System.out.println(jsonStr);
                    json = new Json();
                    int exerciseId = Integer.parseInt(json.getValueFromJSON(jsonStr, "clickedBoxId")) - 1;
                    int index = Integer.parseInt(json.getValueFromJSON(jsonStr, "index"));
                    Exercises exercise = exercises.get(exerciseId);
                    TopExercise topExercise = exercise.getTopExercises().get(index);
                    data = "{ \"username\": \"" + topExercise.getUsername() + "\", \"score\": \"" + topExercise.getScore() + "\", \"time\": \"" + topExercise.getTimes() +"\"}";
                    //System.out.println(data);
                    printWriter.println("HTTP/1.1 200 OK");
                    printWriter.println("Access-Control-Allow-Origin: *");
                    printWriter.println("Access-Control-Allow-Methods: POST");
                    printWriter.println("Access-Control-Allow-Headers: Content-Type");
                    printWriter.println();
                    printWriter.println(data);
                    printWriter.flush();
                }else if(request.startsWith("POST /excelExport HTTP/1.1")){
                    //Excel raporlarini olusturmak icin bu istek karsilanir.
                    jsonStr = request.split("\n")[17];
                    json = new Json();
                    int exerciseId = Integer.parseInt(json.getValueFromJSON(jsonStr, "clickedBoxId")) - 1;
                    Exercises exercise = exercises.get(exerciseId);
                    StringBuilder sb = new StringBuilder();
                    data = "Kullanicilar\t";
                    for(int i=0;i<exercise.getN();i++){
                        data += exercise.getA(i) + " x " + exercise.getB(i) + " = " + exercise.getResult(i) + "\t";
                        data += "Sure (sn)\tDegerlendirme\t";
                    }
                    data += "Baslangic Zamani\tBitis Zamani\tDogru Sayisi\tYanlis Sayisi\tToplam Sure\tToplam Puan\n";
                    sb.append(data);
                    ArrayList<TopExercise> topExercises = exercise.getTopExercises();
                    TopExercise topExercise;
                    for(int i=0;i<topExercises.size();i++){
                        topExercise = topExercises.get(i);
                        data = topExercise.getUsername() + "\t";
                        for(int j=0;j<exercisesLogs.size();j++){
                            ExercisesLog exercisesLog = exercisesLogs.get(j);
                            if(exercisesLog.getUsername().equals(topExercise.getUsername()) && exercisesLog.getExerciseNumber() == exerciseId){
                                for(int k=0;k<exercisesLog.getN();k++){
                                    data += exercisesLog.getResult(k) + "\t" + exercisesLog.getTimerValue(k) + "\t";
                                    if(exercisesLog.getResult(k) == exercise.getResult(k)){
                                        data += "1\t";
                                    }else{
                                        data += "0\t";
                                    }
                                }
                                data += exercisesLog.getStart() + "\t" + exercisesLog.getEnd() + "\t" + exercisesLog.getTrueQ() + "\t" + (exercisesLog.getN() - exercisesLog.getTrueQ()) +"\t"+ topExercise.getTimesString() + "\t" + topExercise.getScore() + "\n";
                                sb.append(data);
                            }
                        }
                    }
                    data = "";
                    data = (exerciseId+1) + ".exercise_details.xls";
                    try (FileOutputStream fos = new FileOutputStream(data)) {
                        fos.write(sb.toString().getBytes());
                        System.out.println("Excel export completed.");
                    } catch (IOException e) {
                        System.out.println("Excel export not completed. " + e.getMessage());
                    }
                    printWriter.println("HTTP/1.1 200 OK");
                    printWriter.println("Access-Control-Allow-Origin: *");
                    printWriter.println("Access-Control-Allow-Methods: POST");
                    printWriter.println("Access-Control-Allow-Headers: Content-Type");
                    printWriter.flush();
                }else if(request.startsWith("POST /getValues HTTP/1.1")){
                    //Alistirma sayfasi ilk acildiginda bu istek karsilanir. Kullanici icin log kaydi acilir.
                    //Daha sonrasında bu kaydın icerigi doldurulur.
                    jsonStr = request.split("\n")[17];
                    System.out.println(jsonStr);
                    json = new Json();
                    int exerciseId = Integer.parseInt(json.getValueFromJSON(jsonStr, "clickedBoxId")) - 1;
                    int qNumber = Integer.parseInt(json.getValueFromJSON(jsonStr, "qNumber"));
                    username = json.getValueFromJSON(jsonStr, "username");
                    //System.out.println("Exercise id: " + exerciseId + " Question number: " + qNumber + " Username: " + username);
                    Date now = new Date();
                    dateTimeStr = format.format(now);
                    Exercises exercise = exercises.get(exerciseId);
                    ExercisesLog exerciseLog = new ExercisesLog(exercise.getN(), username, exerciseId,dateTimeStr);
                    for(int i=0;i<exercisesLogs.size();i++){
                        if(exercisesLogs.get(i).getUsername().equals(username) && exercisesLogs.get(i).getExerciseNumber() == exerciseId){
                            exercisesLogs.remove(i); // eski log kaydini sil
                        }
                    }
                    exercisesLogs.add(exerciseLog);
                    printWriter.println("HTTP/1.1 200 OK");
                    printWriter.println("Access-Control-Allow-Origin: *");
                    printWriter.println("Access-Control-Allow-Methods: POST");
                    printWriter.println("Access-Control-Allow-Headers: Content-Type");
                    printWriter.println();
                    data = "{ \"a\": \"" + exercise.getA(qNumber) + "\", \"b\": \"" + exercise.getB(qNumber) + "\", \"totalq\": \"" + exercise.getN() +"\"}";
                    printWriter.println(data);
                    printWriter.flush();
                }else if(request.startsWith("POST /postValue HTTP/1.1")){
                    //Kullanici alistirmayi cozerken her soru icin bu istek gonderilir.
                    jsonStr = request.split("\n")[17];
                    System.out.println(jsonStr);
                    json = new Json();
                    username = json.getValueFromJSON(jsonStr, "username");
                    int exerciseId = Integer.parseInt(json.getValueFromJSON(jsonStr, "clickedBoxId")) - 1;
                    int qNumber = Integer.parseInt(json.getValueFromJSON(jsonStr, "qNumber"));
                    int input = Integer.parseInt(json.getValueFromJSON(jsonStr, "input"));
                    int timerValue = Integer.parseInt(json.getValueFromJSON(jsonStr, "timerValue"));
                    ExercisesLog exerciseLog = null;
                    int i = 0,found=0;
                    while (i < exercisesLogs.size() && found == 0) {
                        String temp = exercisesLogs.get(i).getUsername();
                        int temp2 = exercisesLogs.get(i).getExerciseNumber();
                        if (temp.equals(username) && temp2 == exerciseId) {
                            exerciseLog = exercisesLogs.get(i);
                            found = 1;
                        }
                        i++;
                    }                    
                    if(qNumber > 1){
                        int sum = 0;
                        for(i = 0; i < qNumber - 1; i++){
                            sum += exerciseLog.getTimerValue(i);
                        }
                        exerciseLog.setValue(qNumber - 1 , input, timerValue - sum);
                    }else{
                        exerciseLog.setValue(qNumber - 1 , input, timerValue);
                    }
                    printWriter.println("HTTP/1.1 200 OK");
                    printWriter.println("Access-Control-Allow-Origin: *");
                    printWriter.println("Access-Control-Allow-Methods: POST");
                    printWriter.println("Access-Control-Allow-Headers: Content-Type");
                    Exercises exercise = exercises.get(exerciseId);
                    if(qNumber < exercise.getN()){
                        data = "{ \"a\": \"" + exercise.getA(qNumber) + "\", \"b\": \"" + exercise.getB(qNumber) + "\"}";
                    }else{
                        data = "{ \"a\": \"" + "0"  + "\", \"b\": \"" + "0" + "\", \"totalq\": \"" + "0" +"\"}";
                        TopExercise topExercise = new TopExercise(exerciseLog, exercise);
                        //System.out.println("yapan kisi: " + topExercise.getUsername() + "sure" + topExercise.getTimes() + "score" + topExercise.getScore());
                        //Kullanıcı daha önce bu soruyu çözmüş mü kontrolü. Eğer çözmüşse eski skoru silip yeni skoru ekliyoruz.
                        ArrayList<TopExercise> topExercises = exercise.getTopExercises();
                        for(i = 0; i<topExercises.size(); i++){
                            TopExercise topExercise1 = topExercises.get(i);
                            if(topExercise1.getUsername().equals(username)){
                                topExercises.remove(i);
                            }
                        }
                        exerciseLog.setTqFq(exercise.getMatrix(), exerciseLog.getResults()); //Bu fonksiyon ile doğru ve yanlış sayıları buluyoruz.
                        Date now = new Date();
                        dateTimeStr = format.format(now);
                        exerciseLog.setEnd(dateTimeStr);
                        exercise.addTopExercise(topExercise);
                        Serialization.writeExercisesToFile(exercises, "src/ser/exercises.ser");
                        Serialization.writeExercisesLogsToFile(exercisesLogs, "src/ser/exercisesLogs.ser");
                    }
                    printWriter.println();
                    printWriter.println(data);
                    printWriter.flush();
                }else if(request.startsWith("POST /getResult HTTP/1.1")){
                    //Kullanici alistirmayi bitirdikten sonra finish.html sayfasina yonlendirilir. Bu istek karsilanir.
                    //Ne kadar surede bitirdigi ve kac puan aldigi ve kac sorudan kac tanesini dogru yaptigi gonderilir.
                    jsonStr = request.split("\n")[17];
                    json = new Json();
                    username = json.getValueFromJSON(jsonStr, "username");
                    int exerciseId = Integer.parseInt(json.getValueFromJSON(jsonStr, "clickedBoxId")) - 1;
                    Exercises exercise = exercises.get(exerciseId);
                    int n = exercise.getN();
                    int tQ = 0; // dogru sayisi
                    for(int i=0;i<exercisesLogs.size();i++){
                        if(exercisesLogs.get(i).getUsername().equals(username) && exercisesLogs.get(i).getExerciseNumber() == exerciseId){
                            tQ = exercisesLogs.get(i).getTrueQ();
                        }
                    }
                    ArrayList<TopExercise> topExercises = exercise.getTopExercises();
                    double score = 0.0;
                    double times = 0.0;
                    for (TopExercise topExercise : topExercises) {
                        if(topExercise.getUsername().equals(username)){
                            times = topExercise.getTimes();
                            score = topExercise.getScore();
                        }
                    }
                    printWriter.println("HTTP/1.1 200 OK");
                    printWriter.println("Access-Control-Allow-Origin: *");
                    printWriter.println("Access-Control-Allow-Methods: POST");
                    printWriter.println("Access-Control-Allow-Headers: Content-Type");
                    printWriter.println();
                    data = "{ \"b\": \"" + n + "\", \"c\": \"" + score + "\", \"d\": \"" + times + "\", \"tq\": \"" + tQ +"\" }";
                    printWriter.println(data);
                    printWriter.flush();
                }else{
                    printWriter.println("HTTP/1.1 404 OK");
                    printWriter.println("Access-Control-Allow-Origin: *");
                    printWriter.println("Access-Control-Allow-Methods: POST");
                    printWriter.println("Access-Control-Allow-Headers: Content-Type");
                    printWriter.flush();
                }
                socket.close();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public void stopServer(){
        isRunning = false;
        try{
            serverSocket.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
