package expression.exceptions;

import expression.MyExpression;

import java.math.BigDecimal;

public class CheckedAdd extends BinaryOperation {
    public CheckedAdd(MyExpression expression1, MyExpression expression2) {
        super(expression1, expression2);
    }

    @Override
    public int getPriority() {
        return 2;
    }

    @Override
    public String getOperation() {
        return " + ";
    }

    @Override
    public boolean getCommutativity() {
        return true;
    }

    @Override
    public int evaluate(int x, int y) throws OverflowException {
        if ((x > 0 && Integer.MAX_VALUE - x < y) || (x <= 0 && Integer.MIN_VALUE - x > y)) {
            throw new OverflowException();
        }
        return x + y;
    }

    @Override
    public BigDecimal evaluate(BigDecimal x) {
        return expression1.evaluate(x).add(expression2.evaluate(x));
    }
}
