package expression;

import java.math.BigDecimal;

public class High extends UnaryOperation{
    public High(MyExpression expression) {
        super(expression);
    }
    @Override
    public String toString() {
        return "high(" + expression.toString() + ")";
    }

    @Override
    public String toMiniString() {
        if (expression.getPriority() != 0) {
            return "high(" + expression.toMiniString() + ")";
        }
        return "high " + expression.toMiniString();
    }

    @Override
    public BigDecimal evaluate(BigDecimal x) {
        throw new UnsupportedOperationException();
    }

    @Override
    public int evaluateInt(int ans) {
        if (ans == 0) {
            return 0;
        }
        int k = -2147483648;
        while ((ans & k) == 0) {
            k = k >>> 1;
        }
        return k;
    }
}
