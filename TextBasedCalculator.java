import java.text.DecimalFormat;
import java.util.Scanner;

public class TextBasedCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

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

        scanner.close();
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
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return decimalFormat.format(value);
    }
}

