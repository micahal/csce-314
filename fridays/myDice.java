/*
--ToDo: Fill this in:
Micah Alummoottil
3/28
*/
import java.util.Random;

public class myDice {
    public static int roll() {
        Random random = new Random(); //rand with no in
        int min = 1;
        int max = 6;
        return random.nextInt(max - min + 1) + min;
    }
    
    
    public static int roll(int n) { //rand with in
        Random random = new Random();
        int min = 1;
        int max = n;
        return random.nextInt(max - min + 1) + min;
    }
}
