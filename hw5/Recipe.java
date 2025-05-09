/*
Micah Alummoottil
CSCE 314
Spring 2025
Homework 5
*/
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.google.gson.annotations.SerializedName;


//this class creates our recipe objects, has getters and setters for each attribute, and creates our id for that recupe, and tracks our total recipes, and tracks our unique authors in a set

public class Recipe {
    //private variables
    @SerializedName("Name")
    private String name;
    private String url;
    @SerializedName("Description")
    private String desc;
    @SerializedName("Author")
    private String author;
    @SerializedName("Ingredients")
    private ArrayList<String> ingred;
    @SerializedName("Method")
    private ArrayList<String> method;
    private int id;
    static int total = 0;
    static Set<String> totalAuthors = new HashSet<>(); // for unique authors

    //constructor
    public Recipe(String name, String url, String desc, String author, ArrayList<String> ingred, ArrayList<String> method) {
        setName(name);
        setUrl(url);
        setDesc(desc);
        setAuthor(author);
        setIngred(ingred);
        setMethod(method);
        
    }


    //getters
    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
    
    public String getDesc() {
        return desc;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public ArrayList<String> getIngred() {
        return ingred;
    }

    public ArrayList<String> getMethod() {
        return method;
    }

    public int getId() {
        return id;
    }

    public static int getTotalAuthors() {
        return totalAuthors.size();
    }

    //setters
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
    
    public void setIngred(ArrayList<String> ingred) {
        this.ingred = ingred;
    }

    public void setMethod(ArrayList<String> method) {
        this.method = method;
    }

    public static void process(ArrayList<Recipe> recipeList) {
        total = 0;
        for (int i = 0; i < recipeList.size(); i++) {
            Recipe recipe = recipeList.get(i);
            total++;
            recipe.setId(total); //set id and increment total

            String author = recipe.getAuthor();
            if (author != null) { // add to unique author set
                Recipe.totalAuthors.add(author);
            }
        }
    }

    
    
    
    
    

}
