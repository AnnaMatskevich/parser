package expression;

import java.math.BigDecimal;

public class Divide extends BinaryOperation implements SpecialOperation {
    public Divide(MyExpression expression1, MyExpression expression2) {
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
    public int evaluate(int x, int y) {
        return x / y;
    }

}
