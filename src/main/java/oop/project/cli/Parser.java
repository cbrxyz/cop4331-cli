package oop.project.cli;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Parser {
    ArrayList<Parameter> parameters;
    Map<String, Flag> flags;

    Parser() {
        parameters = new ArrayList<>();
        flags = new HashMap<>();
    }

    void addParam(Parameter param) {
        parameters.add(param);
    }

    void addFlag(Flag flag) {
        flags.put(flag.getName(), flag);
    }

    void parse(String input) {
        // First, split by space
        String[] parts = input.split(" ");

        // Next, associate each part with a parameter or flag
        ArrayList<String> invalidFlags = new ArrayList<>();
        int paramIndex = 0;
        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];
            if (part.startsWith("--")) {
                // This is a flag
                String flagName = part.substring(2);
                Flag flag = flags.get(flagName);
                if (flag == null) {
                    invalidFlags.add(flagName);
                } else {
                    flag.parse(flagName);
                }
            } else {
                // This is a parameter
                if (paramIndex >= parameters.size()) {
                    throw new IllegalArgumentException("Too many parameters");
                }
                Parameter param = parameters.get(paramIndex);
                param.parse(part);
                paramIndex++;
            }
        }
    }
}
