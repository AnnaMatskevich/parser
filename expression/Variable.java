package expression;

import java.math.BigDecimal;
import java.util.Objects;

public class Variable implements MyExpression {
    private final String var;
    private static final int PRIORITY = 0;


    public Variable(String var) {
        this.var = var;
    }

    @Override
    public String toString() {
        return var;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public BigDecimal evaluate(BigDecimal x) {
        return x;
    }

    @Override
    public String toMiniString() {
        return toString();
    }

    @Override
    public boolean equals(Object expression) {
        if (expression == null) {
            return false;
        }
        return expression.getClass() == this.getClass() && var.equals(((Variable) expression).getVar());
    }

    public String getVar() {
        return var;
    }

    @Override
    public int getPriority() {
        return PRIORITY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(), var);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return switch (var) {
            case "x" -> x;
            case "y" -> y;
            case "z" -> z;
            default -> 0;
        };
    }
}
