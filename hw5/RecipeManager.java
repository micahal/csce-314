/*
Micah Alummoottil
CSCE 314
Spring 2025
Homework 5
*/
import java.util.ArrayList;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

//command to compile javac -cp .:/home/micahal/java/hw5/lib/gson-2.12.1.jar RecipeManager.java Recipe.java Ingredients.java
//command to run java -cp .:/home/micahal/java/hw5/lib/gson-2.12.1.jar RecipeManager

//this class reads the recipes from the json and puts them in a arraylist
//and implements search methods to find by ingredient, author, name, method, and id
//we also handle cases where there is no recipes or null for a specific attribute
//unique ids are attained
//we also print total recipes, unique authors, and total ingredients at the end.

public class RecipeManager{

    public static void choice1(String method, ArrayList<Recipe> recipeList) {//option 1 search for recipes that use a method
        Map<String, ArrayList<Integer>> methodIds = new HashMap<>(); // create hashmap to print method then id list 
        for (Recipe recipe : recipeList) {
            if (recipe.getMethod() == null) {
                continue;
            }
            for (String step : recipe.getMethod()) { //loop thru each step in method
                if (step.toLowerCase().contains(method.toLowerCase())) { //ignores the upper/lowercase and checks if the keyword is in it
                    if (!methodIds.containsKey(method.toLowerCase())) { // if this is our first item, we must create a new array
                        methodIds.put(method.toLowerCase(), new ArrayList<Integer>());
                    }
                    //now we add it to the array
                    ArrayList<Integer> recipeIds = methodIds.get(method.toLowerCase());
                    recipeIds.add(recipe.getId());
                    break; // once we see it once we r good
                }
            }
        }
        if (methodIds.size() == 0) {
            System.out.println(method + " has no recipes");
        }
        System.out.println("The IDs that use this method are: " + methodIds);
    }

    public static void choice3(String name, ArrayList<Recipe> recipeList) { //option 3 search recipes by name
        Map<String, Integer> nameIds = new HashMap<>(); // create hashmap to print name then id
        for (Recipe recipe : recipeList) {
            if (recipe.getName().toLowerCase().contains(name)) { //ignores the upper/lowercase and checks if the keyword is in it
                nameIds.put(recipe.getName(), recipe.getId());
            }
        }
        if (nameIds.size() == 0) {
            System.out.println(name + " has no recipes");
        }
        System.out.println(nameIds);
    }

    public static void choice2(int id, ArrayList<Recipe> recipeList) { //searches for a specific id
        for (Recipe recipe : recipeList) {
            if (recipe.getId() == id) {
                System.out.println("Recipe: " + recipe.getName());
                System.out.println("Url: " + recipe.getUrl());
                System.out.println("Description: " + recipe.getDesc());
                System.out.println("Author: " + recipe.getAuthor());
                System.out.println("Ingredients: " + recipe.getIngred());
                System.out.println("Method: " + recipe.getMethod());
                System.out.println("ID: " + recipe.getId());
                return;
            }
        }
        System.out.println("Recipe with ID" + id + " not found"); //error handle if id is valid, but not an id with it
    }

    public static void choice5(ArrayList<Recipe> recipeList) { //getting all recipes with their info
        for (Recipe recipe : recipeList) {
            System.out.println("Recipe: " + recipe.getName());
            System.out.println("Url: " + recipe.getUrl());
            System.out.println("Description: " + recipe.getDesc());
            System.out.println("Author: " + recipe.getAuthor());
            System.out.println("Ingredients: " + recipe.getIngred());
            System.out.println("Method: " + recipe.getMethod());
            System.out.println("ID: " + recipe.getId());
        }
        System.out.println("There are " + Recipe.total + " total recipes");
    }

    public static void choice6(String author, ArrayList<Recipe> recipeList) { //search recipes by author
        Map<String, ArrayList<Integer>> authorIds = new HashMap<>(); // create hashmap to print author then ids
        for (Recipe recipe : recipeList) { //handle if author is null
            if (recipe.getAuthor() != null && recipe.getAuthor().toLowerCase().equalsIgnoreCase(author.toLowerCase())) { //ignores the upper/lowercase and checks for author
                if (!authorIds.containsKey(author.toLowerCase())) { // if this is our first item, we must create a new array
                    authorIds.put(author.toLowerCase(), new ArrayList<Integer>());
                }
                //now we add it to the array
                ArrayList<Integer> recipeIds = authorIds.get(author.toLowerCase());
                recipeIds.add(recipe.getId());
            }
        }
        if (authorIds.size() == 0) {
            System.out.println(author + " has no recipes");
        }
        System.out.println("Recipes by this author are: " + authorIds);;
    }

    public static void choice4(String ingred) {
        System.out.println("The recipe IDS that include this ingredient are: " + Ingredients.getRecipeIds(ingred));
    }

    //public void choice

    public static void menuChoose(ArrayList<Recipe> recipeList, Scanner scanner) { //function to take input and do correct thing
        int choice = 0;
        while (true) {
            System.out.println("==== Recipe Manager Menu ====");
            System.out.println("1. Search recipes by cooking method");
            System.out.println("2. Get a recipe by ID");
            System.out.println("3. Search recipes by name");
            System.out.println("4. Search recipes by ingredient");
            System.out.println("5. Print all recipes");
            System.out.println("6. Search recipe by Author");
            System.out.println("7. Quit");
            //error handle the input
            while (true) {
                System.out.print("Enter your choice: "); 
                if (scanner.hasNextInt()) {
                    choice = scanner.nextInt();
                    scanner.nextLine();
                    break;
                } else {
                    System.out.println("Invalid enter again: ");
                    scanner.nextLine();
                }
            }
            if (choice == 1) { //calls our function to handle searching for method
                System.out.print("Enter your method: ");
                String method = scanner.nextLine();
                choice1(method, recipeList);
            }

            else if (choice == 2) {
                //error handle the id choice
                int id = 0;
                while (true) {
                    System.out.print("Enter your id: "); 
                    if (scanner.hasNextInt()) {
                        id = scanner.nextInt();
                        scanner.nextLine();
                        break;
                    } else {
                        System.out.println("Invalid enter again: ");
                        scanner.nextLine();
                    }
                }
                choice2(id, recipeList); //call our case to get secific recipe
            }

            else if (choice == 3) { //calls our function to search for that keyword
                System.out.print("Enter your name: ");
                String name = scanner.nextLine();
                choice3(name, recipeList);
            }

            else if (choice == 4) {
                System.out.print("Enter your ingredient: ");
                String ingred = scanner.nextLine();
                choice4(ingred);
            }

            //case for if they print all
            else if (choice == 5) {
                choice5(recipeList);
            }

            else if (choice == 6) { //get input and call func to handle searching author
                System.out.print("Enter your author: ");
                String author = scanner.nextLine();
                choice6(author, recipeList);
            }
            //quit case
            else if (choice == 7) {
                System.out.println("Goodbye");
                break;
            } 
            //this means it is a number, but not 1-6
            else {
                System.out.println("Enter a num 1-7");
            }
            //go again if they didnt quit
        }
    }
    public static void main(String[] args){
        //array for recipes
        ArrayList<Recipe> recipeList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in); //open scanner for menuchoose
        //parsing the data using gson (must handle exception even though file exists
        Gson gson = new Gson();
        try (FileReader reader = new FileReader("recipes.json")) {
            recipeList = gson.fromJson(reader, new TypeToken<ArrayList<Recipe>>() {}.getType());

            //id is position in array (start from 1)
            Recipe.process(recipeList); //since gson wont use our constructor we need a method to set ids

        } catch (IOException e) { 
            System.out.println("Error: Unable to read recipes.json - " + e.getMessage());
        }
        for (Recipe recipe : recipeList) { //loop thru ingredients and add them to ingredient class
            int id = recipe.getId();
            for (String ing : recipe.getIngred()) {
                Ingredients.addIngred(ing, id);
            }
        }
        menuChoose(recipeList, scanner);
        System.out.println("There are " + Recipe.total + " total recipes");
        //unique authors
        System.out.println("There are " + Recipe.getTotalAuthors() + " Unique Authors");
        //unique ingredients
        //since data isnt normalized we cant really get unique ingredients, so just call all
        System.out.println("There are " + Ingredients.allIngred() + " Ingredients");

        scanner.close(); //close scanner
        
    }
}