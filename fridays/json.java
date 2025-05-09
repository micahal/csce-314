import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;


/*
 * Robert Lightfoot
 * CSCE 314
 * In Class starter code
 * spring 2025
 * 
 * Your name: Micah Alummoottil
 * Your UIN: 933008069
 */

 /*
  * The following starter code reads in a json file of people with other attributes.
  * Then, it prints them to the screen.
  * Your goal is to replace the printing each record to the screen with adding them to an
  * array list of authors.
  * Authors should be a class type with attributes: name, language, id, bio, version. There should 
  * also be counter of how many authors.
  * Next, you will print out a summary, showing how many authors you have.
  * finally, create a list of unique languages, print out the languages, and the number of languages.
  */
public class json {
    public static void main(String[] args) {
        String filename = "bios.txt";
        Set<String> languages = new HashSet<>();
        ArrayList<Author> totalAuthors = new ArrayList<>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            StringBuilder jsonContent = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                jsonContent.append(line.trim()).append(" ");
            }
            
            String json = jsonContent.toString().trim();
            json = json.substring(json.indexOf("[") + 1, json.lastIndexOf("]")).trim();
            String[] records = json.split("},");
            
            for (String record : records) {
                record = record.replace("{", "").replace("}", "").trim();
                String[] pairs = record.split(",");
                String name = "", language = "", id = "", bio = "";
                double version = 0.0;
                
                for (String pair : pairs) {
                    String[] keyValue = pair.split(":", 2);
                    if (keyValue.length < 2) continue;
                    
                    String key = keyValue[0].trim().replace("\"", "");
                    String value = keyValue[1].trim().replace("\"", "");
                    
                    switch (key) {
                        case "name": name = value; break;
                        case "language": language = value; break;
                        case "id": id = value; break;
                        case "bio": bio = value; break;
                        case "version": 
                            try {
                                version = Double.parseDouble(value);
                            } catch (NumberFormatException e) {
                                System.err.println("Warning: Invalid version number for record: " + name);
                                version = -1.0; // Default or error value
                            }
                            break;
                    }
                }

                // To Do: convert this to building instances of an Author in a class called Author.java
                // then comment out this printing, print out the number of authors, a list of unique languages, and the number of languages.

                //3168 authors
                // 10 lang
                
                Author toAdd = new Author(name, language, id, bio, version);
                totalAuthors.add(toAdd);
                languages.add(language);
                
                /*
                System.out.println("Name: " + toAdd.getName());
                System.out.println("Language: " + toAdd.getLanguage());
                System.out.println("ID: " + toAdd.getID());
                System.out.println("Bio: " + toAdd.getBIO());
                System.out.println("Version: " + toAdd.getVersion());
                System.out.println("----------------------------");
                */

                
        

                
            }
            System.out.println("Total Authors: " + Author.getAuthors());
            System.out.println("Unique Languages: " + languages);
            System.out.println("Number of Languages: " + languages.size());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
