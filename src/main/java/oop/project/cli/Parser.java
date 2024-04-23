package oop.project.cli;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    private final ArrayList<Parameter<?>> parameters;
    private final Map<String, Flag> flags;
    private final Map<String, NamedParameter> namedParams;
    private final ArrayList<Integer> parsedParams;

    Parser() {
        parameters = new ArrayList<>();
        flags = new HashMap<>();
        namedParams = new HashMap<>();
        parsedParams = new ArrayList<>();
    }

    void addParam(Parameter<?> param) {
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

    public void parse(String input) {// base call
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
        for (int i = 0; i < (parameters.size() + flags.size() + namedParams.size()); i++) {
            Token t = reader.peekNext();
            if (t == null) {
                for (int j = 0; j < parameters.size(); j++) {
                    if (!parameters.get(j).isRequired()) continue;
                    boolean found = false;
                    for (int k = 0; k < parsedParams.size(); k++) {
                        if (j == parsedParams.get(k)) {
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        throw new IllegalArgumentException("Expected argument and did not receive it.");
                    }
                }
                for (String name : namedParams.keySet()) {
                    if (namedParams.get(name).isRequired() && !namedParams.get(name).given) {
                        throw new IllegalArgumentException("Expected argument and did not receive it.");
                    }
                }
                return;
            }
            String part = t.toString();
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
                    reader.next();
                    namedP.parse();
                }
            } else {
                // This is a parameter
                if (parameters.size() <= paramIndex) {
                    throw new IllegalArgumentException("Was not expecting positional parameter!");
                }
                parameters.get(paramIndex).parse();
                parsedParams.add(paramIndex);
                paramIndex++;
            }
        }
        if (reader.peekNext() != null) {
            throw new IllegalArgumentException("Too many parameters");
        }
    }
}
