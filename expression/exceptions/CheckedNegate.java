package expression.exceptions;

import expression.MyExpression;

import java.math.BigDecimal;

public class CheckedNegate extends UnaryOperation {

    public CheckedNegate(MyExpression expression) {
        super(expression);
    }
    @Override
    public String toString() {
        return "-(" + expression.toString() + ")";
    }

    @Override
    public String toMiniString() {
        if (expression.getPriority() != 0) {
            return "-(" + expression.toMiniString() + ")";
        }
        return "- " + expression.toMiniString();
    }

    @Override
    public BigDecimal evaluate(BigDecimal x) {
        return BigDecimal.ZERO.subtract(expression.evaluate(x));
    }

    @Override
    public int evaluateInt(int x) {
        if (x == Integer.MIN_VALUE) {
            throw new OverflowException();
        }
        return -x;
    }

}
