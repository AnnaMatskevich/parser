package expression;

import expression.exceptions.ExpressionParser;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) {
            System.out.println("Error");
            return;
        }
        int x = sc.nextInt();
        final MyExpression example = new Add(
                new Subtract(
                        new Multiply(
                                new Const(2),
                                new Multiply(
                                        new Variable("x"),
                                        new Variable("x")
                                )
                        ),
                        new Multiply(
                                new Const(2),
                                new Variable("x")
                        )
                ),
                new Const(1)
        );
        expression.exceptions.ExpressionParser parser = new ExpressionParser();
    }
}