package expression.exceptions;

import expression.MyExpression;
import expression.SpecialOperation;
import expression.ToMiniString;

import java.util.Objects;

public abstract class BinaryOperation implements MyExpression, ToMiniString {
    protected final MyExpression expression1;
    protected final MyExpression expression2;

    protected BinaryOperation(MyExpression expression1, MyExpression expression2) { // NOTE: It's bad to assign fields in heir constructors, why not pass them here? And why package-private?
        this.expression1 = expression1;
        this.expression2 = expression2;
    }


    @Override
    public String toString() {
        return "(" + expression1.toString() + this.getOperation() + expression2.toString() + ")";
    }
    abstract String getOperation();


    @Override
    public boolean equals(Object expression) {
        if (expression == null) {
            return false;
        }
        return (expression.getClass() == this.getClass()
                && ((BinaryOperation) expression).getExpression1().equals(this.expression1)
                && ((BinaryOperation) expression).getExpression2().equals(this.expression2));
    }

    protected MyExpression getExpression1() {
        return expression1;
    }

    protected MyExpression getExpression2() {
        return expression2;
    }
    abstract boolean getCommutativity();
    @Override
    public int evaluate(int x) {
        return evaluate(expression1.evaluate(x), expression2.evaluate(x));
    }
    abstract int evaluate(int x, int y) throws ArithmeticException;
    protected boolean overflow (long value) {
        return value > Integer.MAX_VALUE || value < Integer.MIN_VALUE;
    }


    protected String convertExpression(MyExpression expression, boolean second) {
        if (expression.getPriority() > getPriority()
                || second && expression.getPriority() == getPriority()
                && (!getCommutativity() || expression instanceof SpecialOperation)
                || (second && getClass()!=expression.getClass() && getPriority()==expression.getPriority() && getPriority()==7)
        ) {
            return "(" + expression.toMiniString() + ")";
        }
        return expression.toMiniString();
    }

    @Override
    public String toMiniString() {
        return convertExpression(expression1, false) + getOperation() +
                convertExpression(expression2, true);
    }
    @Override
    public int evaluate(int x, int y, int z) throws ArithmeticException{
        return evaluate(expression1.evaluate(x, y, z) , expression2.evaluate(x, y, z));
    }

    @Override
    public int hashCode() {
        return Objects.hash(expression1, expression2, getClass());
    }
}
