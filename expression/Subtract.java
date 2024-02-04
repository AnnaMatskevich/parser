package expression;

import java.math.BigDecimal;

public class Subtract extends BinaryOperation {
    public Subtract(MyExpression expression1, MyExpression expression2) {
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
        return x - y;
    }

}
