# TextBasedCalculator

## Overview

`TextBasedCalculator` is a simple Java application that allows a user to perform basic arithmetic operations (addition, subtraction, multiplication, and division) via the console. The program demonstrates user input handling and basic control flow in Java.

## Features

- Prompts the user for two numbers.
- Supports four basic operations: `+`, `-`, `*`, `/`.
- Checks for division by zero and invalid operations.
- Displays the calculation result or error messages accordingly.

## How to Run

1. Make sure you have Java installed on your computer.
2. Save the code as `TextBasedCalculator.java`.
3. Open a terminal and compile the program:
    ```
    javac TextBasedCalculator.java
    ```
4. Run the compiled program:
    ```
    java TextBasedCalculator
    ```

## Example

```
Enter first number: 10
Enter second number: 2
Choose operation (+, -, *, /): *
Result: 20.0
```

## Notes

- Only the four operations listed above are supported.
- If an invalid operation is entered, the program will notify the user and exit.
- Division by zero is handled gracefully with an error message.

## License

This project is provided for educational purposes.