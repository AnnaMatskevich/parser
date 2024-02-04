package expression.exceptions;

import expression.MyExpression;

import java.math.BigDecimal;

import static java.lang.Math.max;

public class Max extends BinaryOperation{
    public Max(MyExpression expression1, MyExpression expression2) {
        super(expression1, expression2);
    }
    @Override
    public int getPriority() {
        return 7;
    }
    @Override
    public String  getOperation() {
        return " max ";
    }
    @Override
    public boolean getCommutativity() {
        return true;
    }

    @Override
    public int evaluate(int x,int y) {
        return max(x, y);
    }
    @Override
    public BigDecimal evaluate(BigDecimal x) {
        throw new RuntimeException();
    }
}
