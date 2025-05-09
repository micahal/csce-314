public class MyDominoes2 {

    public static String[] buildSet(int s) {
        
        int tiles = ((s * s +3 * s +2) / 2);
        String[] set = new String[tiles];

        int count = 0;

        for (int i = 0; i <= s; i++) {
            for(int j = i; j <= s; j++){
                set[count++] = (i + " | " + j + " ");
            }
        }

        return set;
    }

    public static void shuffleSet(String[] s) {
        for(int i = 0; i < s.length; i++){
            String first = s[i];
            int rand = (int)(Math.random() * s.length - i ) + i;
            s[i] = s[rand];
            s[rand] = first;
        }
    }

}