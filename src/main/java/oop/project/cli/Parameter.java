package oop.project.cli;

public abstract class Parameter<T> extends Component<T> {

    private int position;

    Parameter(String name, boolean required, int position) {
        super(name, required);
        this.position = position;
    }

    public int getPosition() {
        return position;
    }
}
