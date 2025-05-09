// Define the Planet interface
//Micah Alummoottil 4/4/25
import java.util.ArrayList;
import java.util.List;

interface Planet {

    double getDistanceFromSun(); // in million km

    double getDiameter();         // in km

    double getRevolutionsPerEarthYear(); // 1 for Earth, different for others

    String getColor();            // Return the planet's main color

    void printInfo();              // Prints all details about the planet
}

// Implement the Earth class
class Earth implements Planet {
    private double distSun;
    private double diam;
    private double rev; //variables
    private String color;

    public Earth(double distSun, double diam, double rev, String color) {
        this.distSun = distSun;
        this.diam = diam;
        this.rev = rev;
        this.color = color; //construtor
    }

    @Override
    public double getDistanceFromSun() {
        return distSun;
    }
    @Override
    public double getDiameter() {
        return diam;
    }
    @Override
    public double getRevolutionsPerEarthYear() { //getters
        return rev;
    }
    @Override
    public String getColor() {
        return color;
    }
    @Override
    public void printInfo() {
        System.out.println("Planet Earth");
        System.out.println("Distance from sun: " + distSun + " million km"); //print infp
        System.out.println("Diameter: " + diam + " km");
        System.out.println("Revolutions: " + rev + " earth years");
        System.out.println("Color: " + color);
    }
    
}

// Implement the Mars class
class Mars implements Planet {
    private double distSun;
    private double diam; //variables
    private double rev;
    private String color;

    public Mars(double distSun, double diam, double rev, String color) {
        this.distSun = distSun;
        this.diam = diam;
        this.rev = rev; //constructor
        this.color = color;
    }

    @Override
    public double getDistanceFromSun() {
        return distSun;
    }
    @Override
    public double getDiameter() {
        return diam;
    }
    @Override
    public double getRevolutionsPerEarthYear() { //getters
        return rev;
    }
    @Override
    public String getColor() {
        return color;
    }
    @Override
    public void printInfo() {
        System.out.println("Planet Mars");
        System.out.println("Distance from sun: " + distSun + " million km"); //print infp
        System.out.println("Diameter: " + diam + " km");
        System.out.println("Revolutions: " + rev + " earth years");
        System.out.println("Color: " + color);
    }
    
}

class Mercury implements Planet {
    private double distSun;
    private double diam; //variables
    private double rev;
    private String color;

    public Mercury(double distSun, double diam, double rev, String color) {
        this.distSun = distSun;
        this.diam = diam;
        this.rev = rev;
        this.color = color; //cnstructor
    }

    @Override
    public double getDistanceFromSun() {
        return distSun;
    }
    @Override
    public double getDiameter() {
        return diam;
    }
    @Override
    public double getRevolutionsPerEarthYear() { //getters
        return rev;
    }
    @Override
    public String getColor() {
        return color;
    }
    @Override
    public void printInfo() {
        System.out.println("Planet Mercury");
        System.out.println("Distance from sun: " + distSun + " million km"); //print infp
        System.out.println("Diameter: " + diam + " km");
        System.out.println("Revolutions: " + rev + " earth years");
        System.out.println("Color: " + color);
    }
}

// SolarSystem class to manage planets

class SolarSystem {
    private List<Planet> planets = new ArrayList<>();
    
    public void addPlanet(Planet planet) {
        planets.add(planet);
    }
    
    public void printAllPlanets() {
        System.out.println("Welcome to the Solar System!");
        for (Planet planet : planets) {
            planet.printInfo();
        }
    }
}

// Main class to run the program
public class SolarSystemSimulation {
    public static void main(String[] args) {
        SolarSystem solarSystem = new SolarSystem();
        Earth earth = new Earth(149.6, 12742.0, 1.0, "Blue & Green"); //add plants & create the,
        solarSystem.addPlanet(earth);
        Mars mars = new Mars(227.9, 6779.0, 1.88, "Reddish");
        solarSystem.addPlanet(mars);
        Mercury mercury = new Mercury(57.9, 4880.0, 0.24, "Gray");
        solarSystem.addPlanet(mercury);
        // Add planets
        
        // Print all planet details
        solarSystem.printAllPlanets(); //print all
    }
}