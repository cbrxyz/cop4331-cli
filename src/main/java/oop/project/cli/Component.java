package oop.project.cli;

public abstract class Component<T> {
    String name;
    boolean required;
    String rawValue;
    T parsed;

    public Component(String name, boolean required) {
        this.name = name;
        this.required = required;
    }

    public String getName() {
        return name;
    }

    public boolean isRequired() {
        return required;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public T getParsedValue() {
        return parsed;
    }

    /**
     * Parse the raw value and set the value field.
     */
    public abstract void parse();
}
