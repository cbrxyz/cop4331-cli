package oop.project.cli;

public class IntParameter extends Parameter<Integer> {
    IntParameter(String name, boolean required, int position) {
        super(name, required, position);
    }

    @Override
    public void parse(String rawInput) {
        try {
            this.parsed = Integer.parseInt(rawInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Unexpected input: " + rawInput);
        }
    }
}
