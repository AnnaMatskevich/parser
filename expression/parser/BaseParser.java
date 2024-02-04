package expression.parser;

public class BaseParser {
    private static final char END = '\0';
    private final StringSource source;
    private char ch = 0xffff;

    protected BaseParser(final StringSource source) {
        this.source = source;
        take();
    }

    protected int getPos(){
        return source.getPos();
    }

    protected char take() {
        final char result = ch;
        ch = source.hasNext() ? source.next() : END;
        return result;
    }

    protected void skipWhiteSpace() {
        while (Character.isWhitespace(ch)) {
            take();
        }
    }
    protected boolean test(final char expected) {
        return ch == expected;
    }

    protected boolean take(final char expected) {
        if (test(expected)) {
            take();
            return true;
        }
        return false;
    }
    protected boolean take(String expected) {
        for (int pos = 0; pos < expected.length(); ++pos) {
            if (take(expected.charAt(pos))){
                continue;
            }
            if (!eof()) {
                source.back(pos + 1);
                take();
            }
            else {
                source.back(pos);
                take();
            }
            return false;
        }
        return true;
    }

    protected void expect(final char expected) {
        if (!take(expected)) {
            throw error("Expected '" + expected + "', found '" + ch + "'");
        }
    }

    protected void expect(final String value) {
        for (final char c : value.toCharArray()) {
            expect(c);
        }
    }

    protected boolean eof() {
        return take(END);
    }

    protected IllegalArgumentException error(final String message) {
        throw  source.error(message);
    }

    protected boolean between(final char from, final char to) {
        return from <= ch && ch <= to;
    }
}
