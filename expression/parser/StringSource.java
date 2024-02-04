package expression.parser;

public class StringSource implements CharSource {
    private final String data;
    private int pos;

    public StringSource(final String data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        return pos < data.length();
    }

    @Override
    public char next() {
        return data.charAt(pos++);
    }

    @Override
    public IllegalArgumentException error(final String message) {
        throw new IllegalArgumentException(pos + ": " + message);
    }

    public int getPos() {
        return pos;
    }

    public void back(int k) {
        pos -=k;
    }
}
