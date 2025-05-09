public class Author {
    String name;
    String language;
    String ID;
    String Bio;
    double version;
    static int authors;
    


    public Author(String name, String language, String ID, String Bio, double version) {
        this.name = name;
        this.language = language;
        this.ID = ID;
        this.Bio = Bio;
        this.version = version;
        authors++;

    }
    public void setName(String name) {
        this.name = name;
    }
    public void setLanguage(String language) {
        this.language = language;
    }
    public void setID(String ID) {
        this.ID = ID;
    }
    public void setBIO(String Bio) {
        this.Bio = Bio;
    }
    public void setVersion(double version) {
        this.version = version;
    }
    public String getName() {
        return this.name;
    }
    public String getLanguage() {
        return this.language;
    }
    public String getID() {
        return this.ID;
    }
    public String getBIO() {
        return this.Bio;
    }
    public double getVersion() {
        return this.version;
    }

    public static int getAuthors() {
        return authors;
    }
}
