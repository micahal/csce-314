
public class myUtils {
    public static void getArgs(String[] args) {
        if(args.length == 0) {
            System.out.println("Usage: java test2 -type anything");
        }
        for(String word : args) {
            System.out.println(word);
        }
    }
}
