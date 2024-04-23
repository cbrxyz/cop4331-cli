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
//        parser.addParam(new DoubleParameter("x", true, 1));
//        parser.addParam(new IntParameter("y", true, 2));
//        parser.addParam(new IntParameter("z", true, 3));
//        Parser nested = new Parser();
//        nested.addParam(new IntParameter("m",true,0));
//        parser.addNamedParam(new NamedParameter("method", true, nested));
//        parser.addParam(new LocalDateParameter("epoch", true, 4));
//        parser.parse("10002 20148.12345678 810 381083 --method 2 1999-12-31");
//        double sum = 0;
//        sum += parser.getParam("w", IntParameter.class).parsed;
//        sum += parser.getParam("x", DoubleParameter.class).parsed;
//        sum += parser.getParam("y", IntParameter.class).parsed;
//        sum += parser.getParam("z", IntParameter.class).parsed;
//        int method = parser.getNamedParam("method").parsed.getParam("m", IntParameter.class).parsed;
//        switch (method) {
//            case 0:
//                System.out.println(sum);
//                break;
//            case 1:
//                System.out.println(sum/4);
//                break;
//            case 2:
//                System.out.println("Sum: "+sum+"\nMean: "+sum/4.0f);
//                break;
//        }
//        System.out.println(parser.getParam("epoch", LocalDateParameter.class).parsed);
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
