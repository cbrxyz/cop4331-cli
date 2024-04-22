package oop.project.cli;

import java.util.Scanner;

public class Main {

    /**
     * A default implementation of main that can be used to run scenarios.
     */
    public static void main(String[] args) {
//        // Testing named parameters
//        Parser parser = new Parser();
//        parser.addParam(new IntParameter("w", true, 0));
//        parser.addParam(new IntParameter("x", true, 1));
//        parser.addParam(new IntParameter("y", true, 2));
//        parser.addParam(new IntParameter("z", true, 3));
//        Parser nested = new Parser();
//        nested.addParam(new IntParameter("m",true,0));
//        parser.addNamedParam(new NamedParameter("method", true, nested));
//        parser.parse("10002 20148 810 381083 --method 2");
//        int sum = 0;
//        sum += parser.getIntParam("w").parsed;
//        sum += parser.getIntParam("x").parsed;
//        sum += parser.getIntParam("y").parsed;
//        sum += parser.getIntParam("z").parsed;
//        int method = parser.getNamedParam("method").parsed.getIntParam("m").parsed;
//        switch (method) {
//            case 0:
//                System.out.println(sum);
//                break;
//            case 1:
//                System.out.println(sum/4.0f);
//                break;
//            case 2:
//                System.out.println("Sum: "+sum+"\nMean: "+sum/4.0f);
//                break;
//        }
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
