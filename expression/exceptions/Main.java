package expression.exceptions;

import expression.TripleExpression;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        ExpressionParser parser = new ExpressionParser();
        TripleExpression expression;
        try {
            expression = parser.parse("1000000*x*x*x*x*x/(x-1)");
        }
        catch (Exception e) {
            System.out.println("illegal string");
            return;
        }
        System.out.println("  x       f");
        for (int i = 0; i < 11; ++i) {
            try {
                System.out.printf("%3d       %s", i, expression.evaluate(i, 0, 0));

            } catch (Exception e) {
                System.out.printf("%3d       %s", i, e.getMessage());
            }
            System.out.println();
        }
    }
}
