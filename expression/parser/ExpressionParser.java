package expression.parser;

import expression.*;

import java.util.List;
import java.util.Set;

public class ExpressionParser implements TripleParser {
    private static final Set<String> VARIABLES = Set.of("x", "y", "z");
    private static final List<List<String>> PRIORITY_AND_OPERATIONS = List.of(
            List.of("t1", "l1", "low", "high"),
            List.of("/", "*"),
            List.of("+", "-"),
            List.of("&"),
            List.of("^"),
            List.of("|")
    );
    private static final int MAX_PRIORITY = PRIORITY_AND_OPERATIONS.size() - 1;

    public ExpressionParser() {

    }

    @Override
    public TripleExpression parse(String expression) {
        return new ParserForExpressions(new StringSource(expression)).parse();
    }

    private static final class ParserForExpressions extends BaseParser {
        public ParserForExpressions(final StringSource source) {
            super(source);
        }

        public MyExpression parse() {
            MyExpression expression = parsePriorityLevel(MAX_PRIORITY);

            skipWhiteSpace();
            if (!eof()) {
                throw error("end of string expected");
            }
            return expression;
        }

        private MyExpression getCompose(String operation, MyExpression expression1, MyExpression expression2) {
            return switch (operation) {
                case "+" -> new Add(expression1, expression2);
                case "-" -> new Subtract(expression1, expression2);
                case "*" -> new Multiply(expression1, expression2);
                case "/" -> new Divide(expression1, expression2);
                case "&" -> new And(expression1, expression2);
                case "|" -> new Or(expression1, expression2);
                case "^" -> new Xor(expression1, expression2);
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

        private MyExpression parsePriorityLevel(int level) {
            if (level != 0) {
                MyExpression expression1 = parsePriorityLevel(level - 1);
                while (true) {
                    skipWhiteSpace();
                    int skipped = 0;
                    for (String operation : PRIORITY_AND_OPERATIONS.get(level)) {
                        if (take(operation)) {
                            MyExpression expression2 = parsePriorityLevel(level - 1);
                            expression1 = getCompose(operation, expression1, expression2);
                            break;
                        }
                        skipped++;
                    }
                    if (skipped == PRIORITY_AND_OPERATIONS.get(level).size()) {
                        break;
                    }
                }
                return expression1;
            }
            skipWhiteSpace();
            MyExpression ans;
            int minCount = 0;
            while (take('-')) {
                minCount++;
            }
            if (minCount % 2 == 1) {
                if (between('1', '9')) {
                    StringBuilder sb = new StringBuilder("-");
                    while (between('0', '9')) {
                        sb.append(take());
                    }
                    return new Const(Integer.parseInt(sb.toString()));
                }
                return new Negate(parsePriorityLevel(0));
            }
            if (take('(')) {
                ans = parsePriorityLevel(MAX_PRIORITY);
                skipWhiteSpace();
                expect(')');
                return ans;
            }
            for (String operation : PRIORITY_AND_OPERATIONS.get(level)) {
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
                while (between('0', '9')) {
                    sb.append(take());
                }
            }
            if (sb.isEmpty()) {
                for (String probablyVariable : VARIABLES) {
                    if (take(probablyVariable)) {
                        return new Variable(probablyVariable);
                    }
                }
                throw new RuntimeException("no such variable");
            }
            return new Const(Integer.parseInt(sb.toString()));
        }

    }
}
