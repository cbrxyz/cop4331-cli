package oop.project.cli;

import java.time.LocalDate;

public class LocalDateParameter extends Parameter<LocalDate>{
    LocalDateParameter(String name, boolean required, int position) {
        super(name, required, position);
    }
    @Override
    public void parse() {
        Token t = InputReader.getInstance().next();
        try {
            this.parsed = LocalDate.parse(t.toString());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Unexpected input: " + t);
        }
    }
}

