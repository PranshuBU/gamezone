/*
 * Input.java
 *
 * This class is mainly for helper methods to run user inputs for the program.
 * There are methods for reading different types of inputs (integer, string, characters), and has basic input validation.
 */


import java.util.Scanner;

public class Input {
    private static Scanner scanner = new Scanner(System.in);

    //for integer inputs (in game)
    public static int getIntInput(String prompt) {
        System.out.print(prompt);
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input: Please enter a valid number!");
            scanner.next(); // Clear invalid input
        }
        return scanner.nextInt();
    }

    //for string inputs (for UI/setup questions)
    public static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.next().trim();
    }

    // for character inputs
    public static char getCharInput(String prompt) {
        System.out.print(prompt);
        String input = scanner.next().trim().toUpperCase();
        return input.charAt(0);
    }


}
