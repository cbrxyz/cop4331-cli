package oop.project.cli;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    ArrayList<Parameter> parameters;
    Map<String, Flag> flags;
    Map<String, NamedParameter> namedParams;

    Parser() {
        parameters = new ArrayList<>();
        flags = new HashMap<>();
        namedParams = new HashMap<>();
    }

    void addParam(Parameter param) {
        parameters.add(param);
    }

    void addFlag(Flag flag) {
        flags.put(flag.getName(), flag);
    }

    void addNamedParam(NamedParameter namedParam) {
        namedParams.put(namedParam.getName(), namedParam);
    }

    public NamedParameter getNamedParam(String name) {
        return namedParams.get(name);
    }

    public <T extends Parameter<?>> T getParam(String name, Class<T> type) {
        for (Parameter p : parameters) {
            if (type.isInstance(p) && name.equals(p.getName())) {
                return (T)p;
            }
        }
        return null;
    }

    void parse(String input) {// base call
        // First, split by space
        String[] parts = input.split(" ");
        new InputReader(parts);
        parse();
    }
    void parse() {
        InputReader reader = InputReader.getInstance();

        // Next, associate each part with a parameter or flag
        ArrayList<String> invalidFlags = new ArrayList<>();
        int paramIndex = 0;
        while (reader.peekNext() != null) {
            String part = reader.peekNext().toString();
            if (part.startsWith("--")) {
                // This is a flag
                String flagName = part.substring(2);
                Flag flag = flags.get(flagName);
                NamedParameter namedP = namedParams.get(flagName);
                if (flag == null && namedP == null) {
                    invalidFlags.add(flagName);
                    reader.next();
                } else if (namedP == null) {
                    flag.parse();
                } else {
                    try {
                        reader.next();
                        namedP.parse();
                    } catch (IllegalArgumentException ignored) {
                    }
                }
            } else {
                // This is a parameter
                if (paramIndex >= parameters.size()) {
                    throw new IllegalArgumentException("Too many parameters");
                }
                parameters.get(paramIndex).parse();
                paramIndex++;
            }
        }
    }
}
