package expression.exceptions;

public class ClosingParenthesisMissException extends Exception {
    public ClosingParenthesisMissException(char ch, int pos) {
        super("close parenthesis expected at position" + pos + " but found " + (ch == '\0' ?  "end of string" : ch));
    }
}
