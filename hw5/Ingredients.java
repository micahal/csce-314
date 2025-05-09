import java.util.ArrayList;
import java.util.HashMap;


//this class takes in a ingredient and has a hashmap where that ingredient is the key, and that key maps to a arraylist of ints which are the recipe ids that use that ingredients
//this has methods to addingredient, search for recipes with an ingredient, and print the total ingredients

public class Ingredients {
    //private variables
    private String name;
    private static HashMap<String, ArrayList<Integer>> ingredientMap = new HashMap<>(); //hashmap where the ing will be the key
    //and the value will be a array of the ids of recipes that use that ingred
    //add to our hashMap
    public static void addIngred(String name, int id) {
        name = name.toLowerCase();
        ingredientMap.putIfAbsent(name, new ArrayList<>()); //if its a new ingred then we create a new array of ids for it
        if (!ingredientMap.get(name).contains(id)) {
            ingredientMap.get(name).add(id);
        }
        
    }
    //returns ids with that ingred
    public static ArrayList<Integer> getRecipeIds(String ing) {
        ArrayList<Integer> allIds = new ArrayList<>(); //create new array for ids, since data isnt normalized
        String contain = ing.toLowerCase();
        for (String ingredient : ingredientMap.keySet()) { //for each key
            if (ingredient.toLowerCase().contains(contain)) { //if our ing is somewhere in that key (ex: egg in "1 egg yolk")
                allIds.addAll(ingredientMap.get(ingredient)); //add it
            }
        }
        return allIds;
    }
    public static int allIngred() { //returns num of ingredients
        return ingredientMap.size();
    }
}
