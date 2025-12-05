import java.util.Scanner;

public class TextBasedCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double firstNumber = readNumber(scanner, "Enter first number: ");
        double secondNumber = readNumber(scanner, "Enter second number: ");
        String operationInput = readOperation(scanner);

        try {
            double result = calculate(firstNumber, secondNumber, operationInput);
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
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
            System.out.print("Choose operation (+, -, *, /): ");
            String operation = scanner.nextLine().trim();
            if (operation.equals("+") || operation.equals("-")
                    || operation.equals("*") || operation.equals("/")) {
                return operation;
            }
            System.out.println("Invalid operation selected.");
        }
    }

    private static double calculate(double firstNumber, double secondNumber, String operation) {
        switch (operation) {
            case "+":
                return firstNumber + secondNumber;
            case "-":
                return firstNumber - secondNumber;
            case "*":
                return firstNumber * secondNumber;
            case "/":
                if (secondNumber == 0) {
                    throw new ArithmeticException("Cannot divide by zero.");
                }
                return firstNumber / secondNumber;
            default:
                throw new IllegalArgumentException("Unsupported operation.");
        }
    }
}

