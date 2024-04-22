package oop.project.cli;

public class IntParameter extends Parameter<Integer> {
    IntParameter(String name, boolean required, int position) {
        super(name, required, position);
    }

    @Override
    public void parse() {
        Token t = InputReader.getInstance().next();
        try {
            this.parsed = Integer.parseInt(t.toString());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Unexpected input: " + t);
        }
    }
}
