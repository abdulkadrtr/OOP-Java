import java.io.Serializable;

public class TopExercise implements Serializable {
    private String username; // username
    private double times; // total time
    private double score; // total score
    public TopExercise(ExercisesLog exerciseLog, Exercises exercise){
        this.username = exerciseLog.getUsername();
        int n = exerciseLog.getN();
        int correct = 0;
        double sum = 0.0;
        for(int i = 0; i < n; i++){
            if(exerciseLog.getResult(i) == exercise.getResult(i)){
                correct++;
            }
            sum += exerciseLog.getTimerValue(i);
        }
        this.score = (double)(100.0 / n) * correct;
        this.score = Math.round(this.score * 100.0) / 100.0; // virgulden sonra 2 basamak
        this.times = sum;
    }
    public String getUsername(){
        return this.username;
    }
    public double getTimes(){
        return this.times;
    }
    public String getTimesString(){
        int minutes = (int) (this.times / 60);
        int seconds = (int) (this.times % 60);
        String minutesString = String.format("%02d", minutes);
        String secondsString = String.format("%02d", seconds);
        return minutesString + ":" + secondsString;
    }
    public double getScore(){
        return this.score;
    }
}
