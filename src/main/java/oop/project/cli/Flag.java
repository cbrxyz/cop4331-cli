package oop.project.cli;

public class Flag extends Component<Boolean> {
    Flag(String name) {
        super(name, true);
        parsed = false;
    }

    @Override
    public void parse() {
        Token t = InputReader.getInstance().next();
        if (t.toString().equals(name)) {
            parsed = true;
        } else {
            throw new IllegalArgumentException("Unexpected input: " + t);
        }
    }
}
