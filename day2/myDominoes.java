
public class myDominoes {
    public static String[] buildSet(int s) {
        int tiles = ((s * s + 3 * s + 2) / 2);
        String[] set = new String[tiles];

        int count = 0;

        for (int i = 0; i <= s; i++) {
            for (int j = i; j <= s; j++) {
                set[count++] = (i + " | " + j + " ");
            }
        }

        return set;
    }
}
