import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Exercises implements Serializable {
    private int a;
    private int b;
    private int N;
    private int[][] matrix;
    private ArrayList<TopExercise> topExercises;
    public Exercises(int a, int b, int N){
        this.a = a;
        this.b = b;
        this.N = N;
        this.topExercises = new ArrayList<TopExercise>();
        generateMatrix(a,b,N);
    }
    public void addTopExercise(TopExercise topExercise){
        this.topExercises.add(topExercise);
    }
    private void generateMatrix(int a, int b, int N) {
        this.matrix = new int[N][3];
        Random random = new Random();
        
        for (int i = 0; i < N; i++) {
            int firstNumber = random.nextInt(b - a + 1) + a;
            int secondNumber = random.nextInt(b - a + 1) + a;
            this.matrix[i][0] = firstNumber;
            this.matrix[i][1] = secondNumber;
            this.matrix[i][2] = firstNumber * secondNumber;
        }
    }
    public ArrayList<TopExercise> getTopExercises(){
        return this.topExercises;
    }
    public void setTopExercises(ArrayList<TopExercise> topExercises){
        this.topExercises = topExercises;
    }
    public void addTopExercises(TopExercise topExercise){
        this.topExercises.add(topExercise);
    }
    public int[][] getMatrix(){
        return this.matrix;
    }
    public int getN(){
        return this.N;
    }
    public int getA(int index){
        return this.matrix[index][0];
    }
    public int getB(int index){
        return this.matrix[index][1];
    }
    public int getResult(int index){
        return this.matrix[index][2];
    }
}
