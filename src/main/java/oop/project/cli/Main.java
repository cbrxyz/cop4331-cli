package oop.project.cli;

import java.util.Scanner;

public class Main {

    /**
     * A default implementation of main that can be used to run scenarios.
     */
    public static void main(String[] args) {
        // Testing named parameters
        Parser parser = new ParserBuilder()
                .AddParameter("w", true, 0, ParserBuilder.INT)
                .AddParameter("x", true, 1, ParserBuilder.DOUBLE)
                .AddParameter("y", true, 2, ParserBuilder.INT)
                .AddParameter("z", true, 3, ParserBuilder.INT)
                .AddNamedParameter("method", true, new ParserBuilder()
                        .AddParameter("m", true, 0, ParserBuilder.INT)
                        .build())
                .AddParameter("epoch", true, 4, ParserBuilder.LOCAL_DATE)
                .build();
        parser.parse("10002 20148.12345678 810 381083 --method 2 1999-12-31");
        double sum = 0;
        sum += parser.getParam("w", IntParameter.class).parsed;
        sum += parser.getParam("x", DoubleParameter.class).parsed;
        sum += parser.getParam("y", IntParameter.class).parsed;
        sum += parser.getParam("z", IntParameter.class).parsed;
        int method = parser.getNamedParam("method").parsed.getParam("m", IntParameter.class).parsed;
        switch (method) {
            case 0:
                System.out.println(sum);
                break;
            case 1:
                System.out.println(sum/4);
                break;
            case 2:
                System.out.println("Sum: "+sum+"\nMean: "+sum/4.0f);
                break;
        }
        System.out.println(parser.getParam("epoch", LocalDateParameter.class).parsed);
//        var scanner = new Scanner(System.in);
//        while (true) {
//            var input = scanner.nextLine();
//            if (input.equals("exit")) {
//                break;
//            }
//            try {
//                var result = Scenarios.parse(input);
//                System.out.println(result);
//            } catch (Exception e) {
//                System.out.println("Unexpected exception: " + e.getClass().getName() + ", " + e.getMessage());
//            }
//        }
    }
}
