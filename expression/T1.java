package expression;

import java.math.BigDecimal;

public class T1 extends UnaryOperation{
    public T1(MyExpression expression) {
        super(expression);
    }
    @Override
    public String toString() {
        return "t1(" + expression.toString() + ")";
    }

    @Override
    public String toMiniString() {
        if (expression.getPriority() != 0) {
            return "t1(" + expression.toMiniString() + ")";
        }
        return "t1 " + expression.toMiniString();
    }

    @Override
    public BigDecimal evaluate(BigDecimal x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int evaluateInt(int ans) {
        int k = 0;
        while ((ans & 1) == 1) {
            k ++;
            ans = ans >>> 1;
        }
        return k;
    }
}
