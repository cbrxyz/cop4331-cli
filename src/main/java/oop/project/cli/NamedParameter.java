package oop.project.cli;

public class NamedParameter extends Component<Parser> {
    boolean given = false;

    public NamedParameter(String name, boolean required, Parser parser) {
        super(name, required);
        parsed = parser;
    }

    @Override
    public void parse() {
        given = true;
        parsed.parse();
    }
}
