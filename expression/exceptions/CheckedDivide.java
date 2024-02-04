package expression.exceptions;

import expression.MyExpression;
import expression.SpecialOperation;

import java.math.BigDecimal;

public class CheckedDivide extends BinaryOperation implements SpecialOperation {
    public CheckedDivide(MyExpression expression1, MyExpression expression2) {
        super(expression1, expression2);
    }

    @Override
    public int getPriority() {
        return 1;
    }

    public String getOperation() {
        return " / ";
    }

    public boolean getCommutativity() {
        return false;
    }

    @Override
    public BigDecimal evaluate(BigDecimal x) {
        return expression1.evaluate(x).divide(expression2.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y) throws DivisionByZeroException {
        if (y == 0) {
            throw new DivisionByZeroException();
        }
        if (x == Integer.MIN_VALUE && y == -1) {
            throw new OverflowException();
        }
        return x / y;
    }

}
