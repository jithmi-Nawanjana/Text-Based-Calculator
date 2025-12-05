# TextBasedCalculator

## Overview

`TextBasedCalculator` is a simple Java console application that walks through user input, validation, and arithmetic. Stage 5 introduces a menu-driven UI:

- **1. Basic Calculator** – prompt-driven operations (addition, subtraction, multiplication, division, modulus, exponent, square root).
- **2. Scientific Calculator** – evaluate an entire expression such as `2 + 3 * 4 - 3` with operator precedence and parentheses.
- **3. Exit** – close the loop cleanly.

The project reinforces the `Scanner`, control flow, helper methods, expression parsing, menu loops, and the `Math`/`DecimalFormat` classes.

## Features

- Menu loop built with `while (true)` for option selection (Basic, Scientific, Exit).
- Basic calculator prompts for operands only when needed and supports `+`, `-`, `*`, `/`, `%`, `^`, `sqrt`.
- Scientific mode parses a full infix string, honoring precedence, parentheses, and supports `+`, `-`, `*`, `/`, `%`, `^`.
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
==== Text-Based Calculator ====
1. Basic Calculator
2. Scientific Calculator
3. Exit
Enter option: 1
Enter first number: 9
Choose operation (+, -, *, /, %, ^, sqrt): sqrt
Result: 3

==== Text-Based Calculator ====
1. Basic Calculator
2. Scientific Calculator
3. Exit
Enter option: 2
Enter full expression: 2 + 3 * 4 - 3
Result: 11

==== Text-Based Calculator ====
1. Basic Calculator
2. Scientific Calculator
3. Exit
Enter option: 3
Goodbye!
```

## Notes

- Scientific mode currently supports numbers, parentheses, and the operators shown above (no functions yet).
- Use basic mode for operations such as square roots that require step-by-step prompts.
- Results are rounded for readability via `DecimalFormat`.

## License

This project is provided for educational purposes.