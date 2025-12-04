import java.util.Scanner;

public class TextBasedCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter first number: ");
        double firstNumber = scanner.nextDouble();

        System.out.print("Enter second number: ");
        double secondNumber = scanner.nextDouble();

        System.out.print("Choose operation (+, -, *, /): ");
        String operationInput = scanner.next();

        double result;
        boolean validOperation = true;

        switch (operationInput) {
            case "+":
                result = firstNumber + secondNumber;
                break;
            case "-":
                result = firstNumber - secondNumber;
                break;
            case "*":
                result = firstNumber * secondNumber;
                break;
            case "/":
                if (secondNumber == 0) {
                    System.out.println("Cannot divide by zero.");
                    validOperation = false;
                    result = 0;
                } else {
                    result = firstNumber / secondNumber;
                }
                break;
            default:
                System.out.println("Invalid operation selected.");
                validOperation = false;
                result = 0;
        }

        if (validOperation) {
            System.out.println("Result: " + result);
        }

        scanner.close();
    }
}

