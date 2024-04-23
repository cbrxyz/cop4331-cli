package oop.project.cli;

public class DoubleParameter extends Parameter<Double> {
    DoubleParameter(String name, boolean required, int position) {
        super(name, required, position);
    }

    @Override
    public void parse() {
        Token t = InputReader.getInstance().next();
        try {
            this.parsed = Double.parseDouble(t.toString());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Unexpected input: " + t);
        }
    }
}