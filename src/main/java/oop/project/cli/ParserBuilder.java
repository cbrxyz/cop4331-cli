package oop.project.cli;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.function.Supplier;

public class ParserBuilder {
    public static Class<IntParameter> INT = IntParameter.class;
    public static Class<DoubleParameter> DOUBLE = DoubleParameter.class;
    public static Class<StringParameter> STRING = StringParameter.class;
    public static Class<LocalDateParameter> LOCAL_DATE = LocalDateParameter.class;

    private static class ParameterToken<T extends Parameter<?>> implements Comparable<ParameterToken<T>> {
        public String name;
        public boolean required;
        public int position;
        Class<T> paramClass;

        public ParameterToken(String name, boolean required, int position, Class<T> paramClass) {
            this.name = name;
            this.required = required;
            this.position = position;
            this.paramClass = paramClass;
        }
        @Override
        public int compareTo(ParameterToken o) {
            return Integer.compare(position, o.position);
        }
    }
    private static class NamedParamToken {
        String name;
        Parser p;
        public NamedParamToken(String name, Parser p) {
            this.p = p;
            this.name = name;
        }
    }
    private final PriorityQueue<ParameterToken<?>> params;
    private final List<String> flags;
    private final List<NamedParamToken> namedParams;

    public ParserBuilder() {
        params = new PriorityQueue<>();
        flags = new ArrayList<>();
        namedParams = new ArrayList<>();
    }

    public <T extends Parameter<?>> ParserBuilder AddParameter(String name, boolean required, int position, Class<T> cls) {
        params.add(new ParameterToken<>(name, required, position, cls));
        return this;
    }

    public ParserBuilder AddBoolFlag(String name) {
        flags.add(name);
        return this;
    }

    public ParserBuilder AddNamedParameter(String name, Parser p) {
        namedParams.add(new NamedParamToken(name, p));
        return this;
    }

    private <T extends Parameter<?>> T makeParam(ParameterToken<T> token) {
        Constructor<T> construct;
        try {
            construct = token.paramClass.getDeclaredConstructor(String.class, boolean.class, int.class);
            return construct.newInstance(token.name,token.required,token.position);
        } catch (ReflectiveOperationException ignored) {
            return null;
        }
    }
    public void reset() {
        params.clear();
        flags.clear();
        namedParams.clear();
    }

    public Parser build() {
        Parser parser = new Parser();
        while (!params.isEmpty()) {
            ParameterToken<?> param = params.poll();
            parser.addParam(makeParam(param));
        }
        for (String flag : flags) {
            parser.addFlag(new Flag(flag));
        }
        for (NamedParamToken npt : namedParams) {
            parser.addNamedParam(new NamedParameter(npt.name, npt.p));
        }
        return parser;
    }
}
