package expression.exceptions;

public class UnknownChar extends Exception {
    public UnknownChar(char ch, int pos) {
        super("unexpected char met at position " + pos + " but found " + (ch == '\0' ?  "end of string" : ch));
    }

}
