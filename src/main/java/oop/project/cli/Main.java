package oop.project.cli;

import java.util.Scanner;

public class Main {

    /**
     * A default implementation of main that can be used to run scenarios.
     */
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        while (true) {
            var input = scanner.nextLine();
            if (input.equals("exit")) {
                break;
            }
            try {
                var result = Scenarios.parse(input);
                System.out.println(result);
            } catch (Exception e) {
                System.out.println("Unexpected exception: " + e.getClass().getName() + ", " + e.getMessage());
            }
        }
    }

}
