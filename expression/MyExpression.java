package expression;

public interface MyExpression extends Expression, TripleExpression, BigDecimalExpression {
    int getPriority();
}
