package oop.project.cli;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class ScenariosTests {

    @Nested
    class Add {

        @ParameterizedTest
        @MethodSource
        public void testAdd(String name, String command, Object expected) {
            test(command, expected);
        }

        public static Stream<Arguments> testAdd() {
            return Stream.of(
                Arguments.of("Add", "add 1 2", Map.of("left", 1, "right", 2)),
                Arguments.of("Missing Argument", "add 1", null),
                Arguments.of("Extraneous Argument", "add 1 2 3", null),
                Arguments.of("Not A Number", "add one two", null),
                Arguments.of("Not An Integer", "add 1.0 2.0", null)
            );
        }

    }

    @Nested
    class Div {

        @ParameterizedTest
        @MethodSource
        public void testSub(String name, String command, Object expected) {
            test(command, expected);
        }

        public static Stream<Arguments> testSub() {
            return Stream.of(
                Arguments.of("Sub", "sub --left 1.0 --right 2.0", Map.of("left", 1.0, "right", 2.0)),
                Arguments.of("Left Only", "sub --left 1.0", null),
                Arguments.of("Right Only", "sub --right 2.0", Map.of("left", Optional.empty(), "right", 2.0)),
                Arguments.of("Missing Value", "sub --right", null),
                Arguments.of("Extraneous Argument", "sub --right 2.0 extraneous", null),
                Arguments.of("Misspelled Flag", "sub --write 2.0", null),
                Arguments.of("Not A Number", "sub --right two", null)
            );
        }

    }

    @Nested
    class Sqrt {

        @ParameterizedTest
        @MethodSource
        public void testSqrt(String name, String command, Object expected) {
            test(command, expected);
        }

        public static Stream<Arguments> testSqrt() {
            return Stream.of(
                Arguments.of("Valid", "sqrt 4", Map.of("number", 4)),
                Arguments.of("Imperfect Square", "sqrt 3", Map.of("number", 3)),
                Arguments.of("Zero", "sqrt 0", Map.of("number", 0)),
                Arguments.of("Negative", "sqrt -1", null)
            );
        }

    }

    @Nested
    class Calc {

        @ParameterizedTest
        @MethodSource
        public void testCalc(String name, String command, Object expected) {
            test(command, expected);
        }

        public static Stream<Arguments> testCalc() {
            return Stream.of(
                Arguments.of("Add", "calc add", Map.of("subcommand", "add")),
                Arguments.of("Sub", "calc sub", Map.of("subcommand", "sub")),
                Arguments.of("Sqrt", "calc sqrt", Map.of("subcommand", "sqrt")),
                Arguments.of("Missing", "calc", null),
                Arguments.of("Invalid", "calc unknown", null)
            );
        }

    }

    @Nested
    class Date {

        @ParameterizedTest
        @MethodSource
        public void testDate(String name, String command, Object expected) {
            test(command, expected);
        }

        public static Stream<Arguments> testDate() {
            return Stream.of(
                Arguments.of("Date", "date 2024-01-01", Map.of("date", LocalDate.of(2024, 1, 1))),
                Arguments.of("Invalid", "date 20240401", null)
            );
        }

    }

    private static void test(String command, Object expected) {
        if (expected != null) {
            var result = Scenarios.parse(command);
            Assertions.assertEquals(expected, result);
        } else {
            //TODO: Update with your specific Exception class or whatever other
            //error handling model you use to check for specific library issues.
            Assertions.assertThrows(Exception.class, () -> {
                Scenarios.parse(command);
            });
        }
    }

}
