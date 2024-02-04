package expression.exceptions;

import expression.MyExpression;

import java.math.BigDecimal;

import static java.lang.Math.min;

public class Min extends BinaryOperation{
    public Min(MyExpression expression1, MyExpression expression2) {
        super(expression1, expression2);
    }
    @Override
    public int getPriority() {
        return 7;
    }
    @Override
    public String  getOperation() {
        return " min ";
    }
    @Override
    public boolean getCommutativity() {
        return true;
    }

    @Override
    public int evaluate(int x,int y) {
        return min(x,  y);
    }
    @Override
    public BigDecimal evaluate(BigDecimal x) {

        throw new RuntimeException();
    }
}
