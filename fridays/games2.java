public class games2 {
    public static void main(String[] args) {
        String[] dominoes;

        dominoes = MyDominoes2.buildSet(6);

        MyDominoes2.shuffleSet(dominoes);


        for (String tile : dominoes) {
            System.out.println(tile);
        }
    
    }

}
