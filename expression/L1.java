package expression;

import java.math.BigDecimal;

public class L1 extends UnaryOperation{
    public L1(MyExpression expression) {
        super(expression);
    }
    @Override
    public String toString() {
        return "l1(" + expression.toString() + ")";
    }

    @Override
    public String toMiniString() {
        if (expression.getPriority() != 0) {
            return "l1(" + expression.toMiniString() + ")";
        }
        return "l1 " + expression.toMiniString();
    }

    @Override
    public BigDecimal evaluate(BigDecimal x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int evaluateInt(int ans) {
        int k = 0;
        while (ans < 0) {
            ans = ans << 1;
            k++;
        }
        return k;
    }

}
