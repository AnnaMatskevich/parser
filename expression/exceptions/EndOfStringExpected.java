package expression.exceptions;

public class EndOfStringExpected extends Exception {
    public EndOfStringExpected(char ch) {
        super("unexpected char, end of string expected but found " + ch);
    }
}
