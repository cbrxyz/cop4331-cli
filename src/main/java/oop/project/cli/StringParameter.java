package oop.project.cli;

public class StringParameter extends Parameter<String> {
    StringParameter(String name, boolean required, int position) {
        super(name, required, position);
    }

    @Override
    public void parse() {
        Token t = InputReader.getInstance().next();
        this.parsed = t.toString();
    }
}
