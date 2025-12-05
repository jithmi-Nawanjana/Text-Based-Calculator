import java.text.DecimalFormat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.Scanner;

public class TextBasedCalculator {

    private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#.##");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        runMenu(scanner);
        scanner.close();
    }

    private static void runMenu(Scanner scanner) {
        while (true) {
            System.out.println("==== Text-Based Calculator ====");
            System.out.println("1. Basic Calculator");
            System.out.println("2. Scientific Calculator");
            System.out.println("3. Exit");
            System.out.print("Enter option: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    runBasicCalculator(scanner);
                    break;
                case "2":
                    runScientificCalculator(scanner);
                    break;
                case "3":
                    System.out.println("Goodbye!");
                    return;
                default:
                    System.out.println("Invalid option. Please choose 1, 2, or 3.");
            }

            System.out.println();
        }
    }

    private static void runBasicCalculator(Scanner scanner) {
        double firstNumber = readNumber(scanner, "Enter first number: ");
        String operationInput = readOperation(scanner);
        Double secondNumber = null;

        if (requiresSecondOperand(operationInput)) {
            secondNumber = readNumber(scanner, "Enter second number: ");
        }

        try {
            double result = calculate(firstNumber, secondNumber, operationInput);
            System.out.println("Result: " + formatResult(result));
        } catch (IllegalArgumentException | ArithmeticException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void runScientificCalculator(Scanner scanner) {
        while (true) {
            System.out.print("Enter full expression: ");
            String expression = scanner.nextLine();
            if (expression.trim().isEmpty()) {
                System.out.println("Expression cannot be empty.");
                continue;
            }

            try {
                double result = evaluateExpression(expression);
                System.out.println("Result: " + formatResult(result));
                return;
            } catch (IllegalArgumentException | ArithmeticException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static double readNumber(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                return Double.parseDouble(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    private static String readOperation(Scanner scanner) {
        while (true) {
            System.out.print("Choose operation (+, -, *, /, %, ^, sqrt): ");
            String operation = scanner.nextLine().trim().toLowerCase();
            if (operation.equals("+") || operation.equals("-")
                    || operation.equals("*") || operation.equals("/")
                    || operation.equals("%") || operation.equals("^")
                    || operation.equals("sqrt")) {
                return operation;
            }
            System.out.println("Invalid operation selected.");
        }
    }

    private static double calculate(double firstNumber, Double secondNumber, String operation) {
        switch (operation) {
            case "+":
                return firstNumber + requireSecondNumber(secondNumber);
            case "-":
                return firstNumber - requireSecondNumber(secondNumber);
            case "*":
                return firstNumber * requireSecondNumber(secondNumber);
            case "/":
                double divisor = requireSecondNumber(secondNumber);
                if (divisor == 0) {
                    throw new ArithmeticException("Cannot divide by zero.");
                }
                return firstNumber / divisor;
            case "%":
                double modulus = requireSecondNumber(secondNumber);
                if (modulus == 0) {
                    throw new ArithmeticException("Cannot perform modulus by zero.");
                }
                return firstNumber % modulus;
            case "^":
                return Math.pow(firstNumber, requireSecondNumber(secondNumber));
            case "sqrt":
                if (firstNumber < 0) {
                    throw new ArithmeticException("Cannot take the square root of a negative number.");
                }
                return Math.sqrt(firstNumber);
            default:
                throw new IllegalArgumentException("Unsupported operation.");
        }
    }

    private static boolean requiresSecondOperand(String operation) {
        return !operation.equals("sqrt");
    }

    private static double requireSecondNumber(Double secondNumber) {
        if (secondNumber == null) {
            throw new IllegalArgumentException("Second number is required for this operation.");
        }
        return secondNumber;
    }

    private static String formatResult(double value) {
        return DECIMAL_FORMAT.format(value);
    }

    private static double evaluateExpression(String expression) {
        List<String> tokens = tokenizeExpression(expression);
        List<String> postfixTokens = infixToPostfix(tokens);
        return evaluatePostfix(postfixTokens);
    }

    private static List<String> tokenizeExpression(String expression) {
        List<String> tokens = new ArrayList<>();
        StringBuilder numberBuilder = new StringBuilder();
        char[] characters = expression.toCharArray();

        for (char ch : characters) {
            if (Character.isWhitespace(ch)) {
                appendNumberIfNeeded(tokens, numberBuilder);
            } else if (Character.isDigit(ch) || ch == '.') {
                numberBuilder.append(ch);
            } else if (isOperatorChar(ch) || ch == '(' || ch == ')') {
                if ((ch == '-' || ch == '+') && (tokens.isEmpty()
                        || isOperator(tokens.get(tokens.size() - 1))
                        || "(".equals(tokens.get(tokens.size() - 1)))) {
                    numberBuilder.append(ch);
                } else {
                    appendNumberIfNeeded(tokens, numberBuilder);
                    tokens.add(String.valueOf(ch));
                }
            } else {
                throw new IllegalArgumentException("Invalid character in expression: " + ch);
            }
        }

        appendNumberIfNeeded(tokens, numberBuilder);
        return tokens;
    }

    private static void appendNumberIfNeeded(List<String> tokens, StringBuilder numberBuilder) {
        if (numberBuilder.length() == 0) {
            return;
        }

        String numberToken = numberBuilder.toString();
        if (numberToken.equals("+") || numberToken.equals("-")) {
            throw new IllegalArgumentException("Incomplete number near '" + numberToken + "'");
        }

        tokens.add(numberToken);
        numberBuilder.setLength(0);
    }

    private static boolean isOperatorChar(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/'
                || ch == '%' || ch == '^';
    }

    private static boolean isOperator(String token) {
        return token.equals("+") || token.equals("-") || token.equals("*")
                || token.equals("/") || token.equals("%") || token.equals("^");
    }

    private static int precedence(String operator) {
        switch (operator) {
            case "^":
                return 3;
            case "*":
            case "/":
            case "%":
                return 2;
            case "+":
            case "-":
                return 1;
            default:
                return 0;
        }
    }

    private static boolean isLeftAssociative(String operator) {
        return !operator.equals("^");
    }

    private static List<String> infixToPostfix(List<String> tokens) {
        List<String> output = new ArrayList<>();
        Deque<String> operatorStack = new ArrayDeque<>();

        for (String token : tokens) {
            if (isOperator(token)) {
                while (!operatorStack.isEmpty() && isOperator(operatorStack.peek())) {
                    String top = operatorStack.peek();
                    boolean higherPrecedence = precedence(top) > precedence(token);
                    boolean equalPrecedenceAndLeftAssociative = precedence(top) == precedence(token)
                            && isLeftAssociative(token);
                    if (higherPrecedence || equalPrecedenceAndLeftAssociative) {
                        output.add(operatorStack.pop());
                    } else {
                        break;
                    }
                }
                operatorStack.push(token);
            } else if (token.equals("(")) {
                operatorStack.push(token);
            } else if (token.equals(")")) {
                while (!operatorStack.isEmpty() && !operatorStack.peek().equals("(")) {
                    output.add(operatorStack.pop());
                }
                if (operatorStack.isEmpty() || !operatorStack.peek().equals("(")) {
                    throw new IllegalArgumentException("Mismatched parentheses in expression.");
                }
                operatorStack.pop();
            } else {
                output.add(token);
            }
        }

        while (!operatorStack.isEmpty()) {
            String token = operatorStack.pop();
            if (token.equals("(") || token.equals(")")) {
                throw new IllegalArgumentException("Mismatched parentheses in expression.");
            }
            output.add(token);
        }

        return output;
    }

    private static double evaluatePostfix(List<String> postfixTokens) {
        Deque<Double> valueStack = new ArrayDeque<>();

        for (String token : postfixTokens) {
            if (isOperator(token)) {
                if (valueStack.size() < 2) {
                    throw new IllegalArgumentException("Invalid expression.");
                }
                double right = valueStack.pop();
                double left = valueStack.pop();
                valueStack.push(applyOperator(left, right, token));
            } else {
                valueStack.push(Double.parseDouble(token));
            }
        }

        if (valueStack.size() != 1) {
            throw new IllegalArgumentException("Invalid expression.");
        }

        return valueStack.pop();
    }

    private static double applyOperator(double left, double right, String operator) {
        switch (operator) {
            case "+":
                return left + right;
            case "-":
                return left - right;
            case "*":
                return left * right;
            case "/":
                if (right == 0) {
                    throw new ArithmeticException("Cannot divide by zero.");
                }
                return left / right;
            case "%":
                if (right == 0) {
                    throw new ArithmeticException("Cannot perform modulus by zero.");
                }
                return left % right;
            case "^":
                return Math.pow(left, right);
            default:
                throw new IllegalArgumentException("Unsupported operator in expression: " + operator);
        }
    }
}

