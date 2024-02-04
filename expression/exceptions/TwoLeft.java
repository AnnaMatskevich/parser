package expression.exceptions;

import expression.MyExpression;

import java.math.BigDecimal;

public class TwoLeft extends BinaryOperation{
    public TwoLeft(MyExpression expression1, MyExpression expression2) {
        super(expression1, expression2);
    }
    @Override
    public int getPriority() {
        return 6;
    }
    @Override
    public String  getOperation() {
        return " << ";
    }
    @Override
    public boolean getCommutativity() {
        return false;
    }

    @Override
    public int evaluate(int x,int y) {
        return x << y;
    }
    @Override
    public BigDecimal evaluate(BigDecimal x) {

        throw new RuntimeException();
    }

}
