import java.io.*;
import java.util.ArrayList;

public class Serialization {
    public static ArrayList<User> readUsersFromFile(String fileName) {
        ArrayList<User> userList = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(fileName);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            userList = (ArrayList<User>) ois.readObject();
            System.out.println("Users deserialization completed.");
        } catch (IOException | ClassNotFoundException e) {
            //e.printStackTrace();
        }
        return userList;
    }

    public static ArrayList<Exercises> readExercisesFromFile(String fileName) {
        ArrayList<Exercises> exercisesList = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(fileName);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            exercisesList = (ArrayList<Exercises>) ois.readObject();
            System.out.println("Exercises deserialization completed.");
        } catch (IOException | ClassNotFoundException e) {
            //e.printStackTrace();
        }
        return exercisesList;
    }
    public static ArrayList<ExercisesLog> readExercisesLogsFromFile(String fileName) {
        ArrayList<ExercisesLog> exercisesLogsList = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(fileName);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            exercisesLogsList = (ArrayList<ExercisesLog>) ois.readObject();
            System.out.println("ExercisesLogs deserialization completed.");
        } catch (IOException | ClassNotFoundException e) {
            //e.printStackTrace();
        }
        return exercisesLogsList;
    }   
    public static void writeUsersToFile(ArrayList<User> users, String fileName) {
        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(users);
            System.out.println("Users serialization completed. The objects are saved as " + fileName);
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }

    public static void writeExercisesToFile(ArrayList<Exercises> exercises, String fileName) {
        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(exercises);
            System.out.println("Exercises serialization completed. The objects are saved as " + fileName);
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }
    public static void writeExercisesLogsToFile(ArrayList<ExercisesLog> exercisesLogs, String fileName) {
        try (FileOutputStream fos = new FileOutputStream(fileName);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(exercisesLogs);
            System.out.println("ExercisesLogs serialization completed. The objects are saved as " + fileName);
        } catch (IOException e) {
            //e.printStackTrace();
        }
    }
}
