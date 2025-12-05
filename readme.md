# TextBasedCalculator

## Overview

`TextBasedCalculator` is a simple Java console application that walks through user input, validation, and arithmetic. It now offers two modes:

- **Basic mode** – prompt-driven operations (addition, subtraction, multiplication, division, modulus, exponent, square root).
- **Expression mode** – evaluate an entire expression such as `2 + 3 * 4 - 3` with operator precedence and parentheses.

The project reinforces the `Scanner`, control flow, helper methods, expression parsing, and the `Math`/`DecimalFormat` classes.

## Features

- Selectable calculator mode: `basic` or `expression`.
- Basic mode prompts for operands only when needed and supports `+`, `-`, `*`, `/`, `%`, `^`, `sqrt`.
- Expression mode parses a full infix string, honoring precedence, parentheses, and supports `+`, `-`, `*`, `/`, `%`, `^`.
- Validates numeric input, operations, divide/mod-by-zero, negative square roots, and malformed expressions.
- Handles non-numeric input via `try-catch` and formats results to two decimals.

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
Choose calculator mode (basic/expression): basic
Enter first number: 9
Choose operation (+, -, *, /, %, ^, sqrt): sqrt
Result: 3

Choose calculator mode (basic/expression): expression
Enter full expression: 2 + 3 * 4 - 3
Result: 11
```

## Notes

- Expression mode currently supports numbers, parentheses, and the operators shown above (no functions yet).
- Use basic mode for operations such as square roots that require step-by-step prompts.
- Results are rounded for readability via `DecimalFormat`.

## License

This project is provided for educational purposes.