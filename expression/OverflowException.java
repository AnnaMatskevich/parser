package expression;

public class OverflowException extends ArithmeticException{
    public OverflowException() {
        super("overflow of integer");
    }
}
