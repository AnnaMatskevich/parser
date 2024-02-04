package expression.exceptions;

import expression.*;
import expression.parser.*;

import java.util.List;
import java.util.Set;

public class ExpressionParser implements TripleParser {
    private static boolean hadWhileSpase = false;
    private static boolean hadParenthesis = false;
    private final static Set<String> VARIABLES = Set.of(
            "x", "y", "z"
    );
    private final static List<List<String>> priorityAndOperations = List.of(
            List.of("t1", "l1", "low", "high"),
            List.of("/", "*"),
            List.of("+", "-"),
            List.of("&"),
            List.of("^"),
            List.of("|"),
            List.of(">>>", "<<", ">>"),
            List.of("min", "max")
    );
    private final static int MAXPRIORITY = priorityAndOperations.size() - 1;

    public ExpressionParser() {

    }

    @Override
    public TripleExpression parse(String expression) throws ClosingParenthesisMissException, UnknownChar, UnexpectedEndOfString, EndOfStringExpected {
        return new ParserForExpressions(new StringSource(expression)).parse();
    }

    private static final class ParserForExpressions extends BaseParser {
        public ParserForExpressions(final StringSource source) {
            super(source);
        }

        public MyExpression parse() throws IllegalArgumentException, ClosingParenthesisMissException, UnknownChar, UnexpectedEndOfString, EndOfStringExpected {
            MyExpression expression = parsePriorityLevel(MAXPRIORITY);

            skipWhiteSpace();
            if (!eof()) {
                throw new EndOfStringExpected(take());
            }
            return expression;
        }

        private MyExpression getCompose(String operation, MyExpression expression1, MyExpression expression2) {
            return switch (operation) {
                case "+" -> new CheckedAdd(expression1, expression2);
                case "-" -> new CheckedSubtract(expression1, expression2);
                case "*" -> new CheckedMultiply(expression1, expression2);
                case "/" -> new CheckedDivide(expression1, expression2);
                case "&" -> new And(expression1, expression2);
                case "|" -> new Or(expression1, expression2);
                case "^" -> new Xor(expression1, expression2);
                case ">>" -> new TwoRight(expression1, expression2);
                case "<<" -> new TwoLeft(expression1, expression2);
                case ">>>" -> new TreeRight(expression1, expression2);
                case "min" -> new Min(expression1, expression2);
                case "max" -> new Max(expression1, expression2);
                default -> throw error("no such operation");
            };

        }

        private MyExpression getCompose(String operation, MyExpression expression) {
            return switch (operation) {
                case "l1" -> new L1(expression);
                case "t1" -> new T1(expression);
                case "high" -> new High(expression);
                case "low" -> new Low(expression);
                default -> throw error("no such operation");
            };

        }

        private MyExpression parsePriorityLevel(int level) throws ClosingParenthesisMissException, UnknownChar, UnexpectedEndOfString {
            if (level == 0) {
                hadWhileSpase = false;
                hadParenthesis = false;
            }
            if (level == 0) {
                return zeroLevel();
            }
            MyExpression expression1 = parsePriorityLevel(level - 1);
            while (true) {
                hadWhileSpase = hadWhileSpase || take(' ');
                if (level == 7) {
                    if (!hadWhileSpase && !eof() && !hadParenthesis) {
                        return expression1;
                    }
                }

                skipWhiteSpace();
                int skipped = 0;
                for (String operation : priorityAndOperations.get(level)) {
                    if (take(operation)) {
                        MyExpression expression2 = parsePriorityLevel(level - 1);
                        expression1 = getCompose(operation, expression1, expression2);
                        break;
                    }
                    skipped++;
                }
                if (skipped == priorityAndOperations.get(level).size()) {
                    break;
                }
            }
            return expression1;
        }

        private MyExpression zeroLevel() throws ClosingParenthesisMissException, UnexpectedEndOfString, UnknownChar {
            skipWhiteSpace();
            MyExpression ans;
            int minCount = 0;
            while (take('-')) {
                minCount++;
            }
            if (minCount % 2 == 1) {
                if (between('1', '9')) {
                    StringBuilder sb = new StringBuilder("-");
                    takeDigits(sb);
                    return new Const(Integer.parseInt(sb.toString()));
                }
                return new CheckedNegate(parsePriorityLevel(0));
            }
            if (take('(')) {
                ans = parsePriorityLevel(MAXPRIORITY);
                skipWhiteSpace();
                if (!take(')')) {
                    throw new ClosingParenthesisMissException(take(), getPos());
                }
                hadParenthesis = true;
                return ans;
            }
            for (String operation : priorityAndOperations.get(0)) {
                if (take(operation)) {
                    MyExpression expression2 = parsePriorityLevel(0);
                    return getCompose(operation, expression2);
                }
            }
            StringBuilder sb = new StringBuilder();
            if (take('-')) {
                sb.append('-');
            }
            if (take('0')) {
                sb.append('0');
            } else {
                if (between('1', '9')) {
                    takeDigits(sb);
                }
            }
            if (sb.isEmpty()) {
                for (String probablyVariable : VARIABLES) {
                    if (take(probablyVariable)) {
                        return new Variable(probablyVariable);
                    }
                }
                throw new UnknownChar(take(), getPos());
            }
            return new Const(Integer.parseInt(sb.toString()));
        }

        private void takeDigits(final StringBuilder sb) {
            while (between('0', '9')) {
                sb.append(take());
            }
        }


    }
}
