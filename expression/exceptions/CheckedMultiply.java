package expression.exceptions;

import expression.MyExpression;

import java.math.BigDecimal;

public class CheckedMultiply extends BinaryOperation {
    public CheckedMultiply(MyExpression expression1, MyExpression expression2) {
        super(expression1, expression2);
    }

    @Override
    public int getPriority() {
        return 1;
    }

    public String getOperation() {
        return " * ";
    }

    public boolean getCommutativity() {
        return true;
    }

    @Override
    public BigDecimal evaluate(BigDecimal x) {
        return expression1.evaluate(x).multiply(expression2.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y) {
        if (x == 0 || y == 0) {
            return 0;
        }
        if ((x >= 0 && y >= 0  && (x > Integer.MAX_VALUE / y || y > Integer.MAX_VALUE / x))
                || (x <= 0 && y <= 0 && (x < Integer.MAX_VALUE / y || y < Integer.MAX_VALUE / x))
                || (x >= 0 && y <= 0 && (y < Integer.MIN_VALUE / x))
                || (x <= 0 && y >= 0 && (x < Integer.MIN_VALUE / y))) {
            throw new OverflowException();
        }
        return x * y;
    }
}
