package expression;

import java.math.BigDecimal;

public class Multiply extends BinaryOperation {
    public Multiply(MyExpression expression1, MyExpression expression2) {
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
        return x * y;
    }
}
