package oop.project.cli;

public class Token {
    private final String content;

    public Token(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return content;
    }
}
