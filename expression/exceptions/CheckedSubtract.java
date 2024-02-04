package expression.exceptions;

import expression.MyExpression;

import java.math.BigDecimal;

public class CheckedSubtract extends BinaryOperation {
    public CheckedSubtract(MyExpression expression1, MyExpression expression2) {
        super(expression1, expression2);
    }

    @Override
    public int getPriority() {
        return 2;
    }

    public String getOperation() {
        return " - ";
    }

    public boolean getCommutativity() {
        return false;
    }

    @Override
    public BigDecimal evaluate(BigDecimal x) {
        return expression1.evaluate(x).subtract(expression2.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y) {
        if ((y >= 0 && Integer.MIN_VALUE + y > x) || (y < 0 && Integer.MAX_VALUE + y < x)) {
            throw new OverflowException();
        }
        return x - y;
    }

}
