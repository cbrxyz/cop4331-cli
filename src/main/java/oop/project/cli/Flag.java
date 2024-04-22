package oop.project.cli;

public class Flag extends Component<Boolean> {
    Flag(String name) {
        super(name, true);
    }

    @Override
    public void parse(String rawInput) {
        if (rawInput.equals(name)) {
            parsed = true;
        } else {
            throw new IllegalArgumentException("Unexpected input: " + rawInput);
        }
    }
}
