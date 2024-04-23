package oop.project.cli;

public class NamedParameter extends Component<Parser> {
    boolean given = false;

    public NamedParameter(String name, Parser parser) {
        super(name, false);
        parsed = parser;
    }

    @Override
    public void parse() {
        given = true;
        try {
            parsed.parse();
        } catch (IllegalArgumentException e) {
            if (!e.getMessage().equals("Too many parameters")) throw e;
        }
    }
}
