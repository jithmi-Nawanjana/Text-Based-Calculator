# TextBasedCalculator

## Overview

`TextBasedCalculator` is a simple Java console application that walks through user input, validation, and arithmetic. It now covers addition, subtraction, multiplication, division, modulus, exponentiation, square roots, and formatted output—highlighting the `Scanner`, control flow, helper methods, and the `Math`/`DecimalFormat` classes.

## Features

- Prompts for the first number, then requests a second number only when needed.
- Supports `+`, `-`, `*`, `/`, `%`, `^`, and `sqrt`.
- Validates numeric input, operations, and division/modulus-by-zero attempts.
- Handles non-numeric input via `try-catch`.
- Uses helper methods for input parsing, calculation, and result formatting (rounded to two decimals).

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
Enter first number: 9
Choose operation (+, -, *, /, %, ^, sqrt): sqrt
Result: 3

Enter first number: 10
Choose operation (+, -, *, /, %, ^, sqrt): /
Enter second number: 3
Result: 3.33
```

## Notes

- Operations beyond the listed set are rejected with a helpful message.
- Division and modulus by zero, negative square roots, and missing operands are prevented.
- Results are rounded for readability via `DecimalFormat`.

## License

This project is provided for educational purposes.