package expression;

import java.math.BigDecimal;
import java.util.Objects;

public class Const implements MyExpression {
    private final Number constant;
    private static final int PRIORITY = 0;

    public Const(int constant) {
        this.constant = constant;
    }

    public Const(BigDecimal constant) {
        this.constant = constant;
    }
    @Override
    public int evaluate(int x) {
        return constant.intValue();
    }
    @Override
    public BigDecimal evaluate(BigDecimal x) {
        return (BigDecimal) constant;
    }

    public int evaluate(int x, int y, int z) {
        return (int)constant;
    }

    @Override
    public String toString() {
        return String.valueOf(constant);
    }

    private Number getConst() {
        return constant;
    }

    @Override
    public String toMiniString() {
        return toString();
    }

    @Override
    public int getPriority() {
        return PRIORITY;
    }

    @Override
    public boolean equals(Object expression) {
        if (expression == null) {
            return false;
        }
        return expression.getClass() == this.getClass() && constant.equals(((Const) expression).getConst());
    }

    @Override
    public int hashCode() {
        return Objects.hash(constant.hashCode(), getClass());
    }

}
