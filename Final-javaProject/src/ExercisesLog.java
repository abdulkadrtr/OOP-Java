import java.io.Serializable;

public class ExercisesLog implements Serializable {
    private int N; // soru sayisi
    private String username; // alistirma yapan kisinin adi
    private int exerciseNumber; // alistirma numarasi
    private int[] results; // alistirma sonuclari
    private double[] times; // alistirma zamanlari
    private String start; // start time date
    private String end; // end time date
    private int trueQ; // dogru sayisi
    public ExercisesLog(int N, String username, int exerciseNumber, String start){
        results = new int[N];
        this.username = username;
        this.times = new double[N + 1];
        this.times[0] = 0.0;
        this.exerciseNumber = exerciseNumber;
        this.N = N;
        this.start = start;
    }
    public void setValue(int index, int value, double time){
        results[index] = value;
        times[index] = time;
    }
    public double getTimerValue(int index){
        return times[index];
    }
    public String getUsername(){
        return this.username;
    }
    public int getN(){
        return this.N;
    }
    public int getExerciseNumber(){
        return this.exerciseNumber;
    }
    public int getResult(int index){
        return this.results[index];
    }
    public void setStart(String start){
        this.start = start;
    }
    public void setEnd(String end){
        this.end = end;
    }
    public String getStart(){
        return this.start;
    }
    public String getEnd(){
        return this.end;
    }
    public int[] getResults(){
        return this.results;
    }
    public void setTqFq(int[][] matrix,int[] results){
        int trueQ = 0;
        for(int i = 0; i < matrix.length; i++){
            if(matrix[i][2] == results[i]){
                trueQ++;
            }
        }
        this.trueQ = trueQ;
    }
    public int getTrueQ(){
        return this.trueQ;
    }
}
