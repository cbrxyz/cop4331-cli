package oop.project.cli;

import org.checkerframework.checker.units.qual.N;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

public class Scenarios {

    /**
     * Parses and returns the arguments of a command (one of the scenarios
     * below) into a Map of names to values. This method is provided as a
     * starting point that works for most groups, but depending on your command
     * structure and requirements you may need to make changes to adapt it to
     * your needs - use whatever is convenient for your design.
     */
    public static Map<String, Object> parse(String command) {
        //This assumes commands follow a similar structure to unix commands,
        //e.g. `command [arguments...]`. If your project uses a different
        //structure, e.g. Lisp syntax like `(command [arguments...])`, you may
        //need to adjust this a bit to work as expected.
        var split = command.split(" ", 2);
        var base = split[0];
        var arguments = split.length == 2 ? split[1] : "";
        return switch (base) {
            case "add" -> add(arguments);
            case "sub" -> sub(arguments);
            case "sqrt" -> sqrt(arguments);
            case "calc" -> calc(arguments);
            case "date" -> date(arguments);
            default -> throw new IllegalArgumentException("Unknown command.");
        };
    }

    /**
     * Takes two positional arguments:
     *  - {@code left: <your integer type>}
     *  - {@code right: <your integer type>}
     */

//    private static Map<String, Object> add(String arguments) {
//        //TODO: Parse arguments and extract values.
//        int left = 0; //or BigInteger, etc.
//        int right = 0;
//        return Map.of("left", left, "right", right);
//    }
    private static Map<String, Object> add(String arguments) {
        Parser parser = new Parser();
        IntParameter leftParam = new IntParameter("left", true, 0);
        IntParameter rightParam = new IntParameter("right", true, 1);
        parser.addParam(leftParam);
        parser.addParam(rightParam);
        parser.parse(arguments);
        int left = leftParam.getParsedValue();
        int right = rightParam.getParsedValue();
        return Map.of("left", left, "right", right);
    }

    /**
     * Takes two <em>named</em> arguments:
     *  - {@code left: <your decimal type>} (optional)
     *     - If your project supports default arguments, you could also parse
     *       this as a non-optional decimal value using a default of 0.0.
     *  - {@code right: <your decimal type>} (required)
     */

//    static Map<String, Object> sub(String arguments) {
//        //TODO: Parse arguments and extract values.
//        Optional<Double> left = Optional.empty();
//        double right = 0.0;
//        return Map.of("left", left, "right", right);
//    }
    static Map<String, Object> sub(String arguments) {
        Parser parser = new ParserBuilder()
                .AddNamedParameter("left", false, new ParserBuilder()
                        .AddParameter("num", true, 0, ParserBuilder.DOUBLE)
                        .build())
                .AddNamedParameter("right", false, new ParserBuilder()
                        .AddParameter("num", true, 0, ParserBuilder.DOUBLE)
                        .build())
                .build();
        parser.parse(arguments);

        DoubleParameter leftParam = parser.getNamedParam("left").getParsedValue().getParam("num",ParserBuilder.DOUBLE);
        DoubleParameter rightParam = parser.getNamedParam("right").getParsedValue().getParam("num",ParserBuilder.DOUBLE);
        Double right = rightParam.getParsedValue();
        if (leftParam.getParsedValue() != null) {
            return Map.of("left", leftParam.getParsedValue(), "right", right);
        }
        return Map.of("left", Optional.empty(), "right", right);
    }

    /**
     * Takes one positional argument:
     *  - {@code number: <your integer type>} where {@code number >= 0}
     */
//    static Map<String, Object> sqrt(String arguments) {
//        //TODO: Parse arguments and extract values.
//        int number = 0;
//        return Map.of("number", number);
//    }
    static Map<String, Object> sqrt(String arguments) {
        //TODO: Parse arguments and extract values.
        Parser parser = new Parser();
        IntParameter num = new IntParameter("num", true, 0);
        parser.addParam(num);
        parser.parse(arguments);
        int number = num.getParsedValue();
        return Map.of("number", number);
    }

    /**
     * Takes one positional argument:
     *  - {@code subcommand: "add" | "div" | "sqrt" }, aka one of these values.
     *     - Note: Not all projects support subcommands, but if yours does you
     *       may want to take advantage of this scenario for that.
     */

//    static Map<String, Object> calc(String arguments) {
//        //TODO: Parse arguments and extract values.
//        String subcommand = "";
//        return Map.of("subcommand", subcommand);
//    }
    static Map<String, Object> calc(String arguments) {
        //TODO: Parse arguments and extract values.

        Parser parser = new Parser();
        StringParameter subArg = new StringParameter("subArg", false, 0);
        parser.addParam(subArg);
        parser.parse(arguments);

        Optional<String> subcommand = Optional.empty();
        if (subArg.getParsedValue() != null) {
            subcommand = Optional.of(subArg.getParsedValue());
        }

//        String subcommand = subArg.getParsedValue();
        return Map.of("subcommand", subcommand);
    }

    /**
     * Takes one positional argument:
     *  - {@code date: Date}, a custom type representing a {@code LocalDate}
     *    object (say at least yyyy-mm-dd, or whatever you prefer).
     *     - Note: Consider this a type that CANNOT be supported by your library
     *       out of the box and requires a custom type to be defined.
     */
    static Map<String, Object> date(String arguments) {
        //TODO: Parse arguments and extract values.
        Parser parser = new Parser();
        LocalDateParameter localDate = new LocalDateParameter("localDate", true, 0);
        parser.addParam(localDate);
        parser.parse(arguments);
        LocalDate date = localDate.getParsedValue();
        return Map.of("date", date);
    }

    //TODO: Add your own scenarios based on your software design writeup. You
    //should have a couple from pain points at least, and likely some others
    //for notable features. This doesn't need to be exhaustive, but this is a
    //good place to test/showcase your functionality in context.

}
