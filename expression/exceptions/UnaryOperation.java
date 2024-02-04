package expression.exceptions;

import expression.MyExpression;

import java.util.Objects;

public abstract class UnaryOperation implements MyExpression {
    protected final MyExpression expression;
    public UnaryOperation(MyExpression expression) {
        this.expression = expression;
    }

    @Override
    public int getPriority() {
        return 0;
    }
    @Override
    public int hashCode() {
        return Objects.hash(getClass(), expression);
    }

    abstract int evaluateInt(int x);
    @Override
    public int evaluate(int x) {
        return evaluateInt(expression.evaluate(x));
    }
    @Override
    public int evaluate(int x, int y, int z) {
        return evaluateInt(expression.evaluate(x, y, z));
    }

}
