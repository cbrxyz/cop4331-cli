package oop.project.cli;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class InputReader {
    static InputReader instance = null;
    ArrayList<Token> tokens;
    int currToken = 0;

    public InputReader(String[] args) {
        tokens = new ArrayList<>();
        int index = 0;
        Map<Character, Character> closingOf = new HashMap<>();
        closingOf.put(' ', ' ');
        closingOf.put('"', '"');
        closingOf.put('(', ')');
        closingOf.put('[', ']');
        closingOf.put('{', '}');
        closingOf.put('<', '>');
        String fullInput = String.join(" ", args);
        if (!closingOf.containsKey(fullInput.charAt(0))) {
            fullInput = " " + fullInput;
        }
        while (index < fullInput.length()-1) {
            int finished = fullInput.indexOf(closingOf.get(fullInput.charAt(index)), index+1);
            if (finished == -1) finished = fullInput.length();
            tokens.add(new Token(fullInput.substring(index+1, finished)));
            index = finished;
        }
        instance = this;
    }

    public Token next() {
        if (currToken < tokens.size()) {
            return tokens.get(currToken++);
        }
        return null;
    }

    public Token peekNext() {
        if (currToken < tokens.size()) {
            return tokens.get(currToken);
        }
        return null;
    }

    public void restart() {
        currToken = 0;
    }

    public static InputReader getInstance() {
        return instance;
    }
}
