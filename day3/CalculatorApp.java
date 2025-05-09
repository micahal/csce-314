/*
 * Micah Alummoottil 4/2/25
 * 
 */

 //define the calculator interface

import java.util.Scanner;

interface Calculator {
    void add(double value);
    void subtract(double value);
    void multiply(double value);
    void divide(double value);
    void mod(double value);
    double getResults();
}
//implement calculator interface
class simpleCalculator implements Calculator {
    private double result;

    public simpleCalculator() {
        this.result = 0;
    }

    @Override
    public void add(double value) {
        result += value;
    }

    @Override
    public void subtract(double value) {
        result -= value;
    }

    @Override
    public void multiply(double value) {
        result *= value;
    }
    @Override
    public void mod(double value) {
        if (value != 0) {
            result %= value;
        }
        else {
            System.out.println("Cannot do mod by 0");
        }
    }

    @Override
    public void divide(double value) {
        if (value != 0) {
            result /= value;
        }
        else {
            System.out.println("Cannot divide by 0");
        }
    }

    @Override
    public double getResults() {
        return result;
    }
}
//main class to run
public class CalculatorApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        simpleCalculator calculator = new simpleCalculator();
        boolean running = true;

        System.out.println("Calculator - Type operations (+, -, *, /, %) or = to display result & exit");

        while (running) {
            System.out.println("Enter operation ");
            String input = scanner.next();
            if (input.equals("=")) {
                System.out.println("Final result " + calculator.getResults());
                running = false;
            }
            else {
                double value = scanner.nextDouble();
                switch (input) {
                    case "+":
                        calculator.add(value);
                        break;
                    case "-":
                        calculator.subtract(value);
                        break;
                    case "*":
                        calculator.multiply(value);
                        break;
                    case "/":
                        calculator.divide(value);
                        break;
                    case "%":
                        calculator.mod(value);
                        break;
                    default:
                        System.out.println("Please enter a valid operation (+, -, *, /, %, =)");
                        break;
                }
                System.out.println("Current result is " + calculator.getResults());
            }
        }
    }
}
