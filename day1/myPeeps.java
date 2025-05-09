public class myPeeps {
    String name;
    static int quantity;

    public myPeeps() {
        quantity++;
    }
    public myPeeps(String name) {
        this.name = name;
        quantity++;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public static int getQuantity() {
        return quantity;
    }
}
