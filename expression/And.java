package expression;

import java.math.BigDecimal;

public class And extends BinaryOperation{
    public And(MyExpression expression1, MyExpression expression2) {
        super(expression1, expression2);
    }
    @Override
    public int getPriority() {
        return 3;
    }
    @Override
    public String  getOperation() {
        return " & ";
    }
    @Override
    public boolean getCommutativity() {
        return true;
    }

    @Override
    public int evaluate(int x, int y) {
        return x & y;
    }
    @Override
    public BigDecimal evaluate(BigDecimal x) {
        throw new UnsupportedOperationException();
        //return expression1.evaluate(x).add(expression2.evaluate(x));
    }
}
