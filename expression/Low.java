package expression;

import java.math.BigDecimal;

import static java.lang.Math.abs;

public class Low extends UnaryOperation{
    public Low(MyExpression expression) {
        super(expression);
    }
    @Override
    public String toString() {
        return "low(" + expression.toString() + ")";
    }

    @Override
    public String toMiniString() {
        if (expression.getPriority() != 0) {
            return "low(" + expression.toMiniString() + ")";
        }
        return "low " + expression.toMiniString();
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
        int k = 1;
        while ((ans & k) == 0) {
            k = k << 1;
        }
        return k;
    }
}
