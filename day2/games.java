
public class games {
    public static void main(String[] args) {
        String[] dominoes;
        dominoes = myDominoes.buildSet(9);

        for (String tile : dominoes) {
            System.out.println(tile);
        }
    }

}
