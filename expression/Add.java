package expression;

import java.math.BigDecimal;

public class Add extends BinaryOperation {
    public Add(MyExpression expression1, MyExpression expression2) {
        super(expression1, expression2);
    }
    @Override
    public int getPriority() {
        return 2;
    }
    @Override
    public String  getOperation() {
        return " + ";
    }
    @Override
    public boolean getCommutativity() {
        return true;
    }

    @Override
    public int evaluate(int x,int y) {
        return x + y;
    }
    @Override
    public BigDecimal evaluate(BigDecimal x) {
        return expression1.evaluate(x).add(expression2.evaluate(x));
    }
}
