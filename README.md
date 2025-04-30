# GUI Calculator

This is a simple GUI calculator application built using Java Swing and the MVC (Model-View-Controller) design pattern. The calculator implements all standard arithmetic operations along with memory functions.

## Features

- Basic arithmetic operations: addition, subtraction, multiplication, division
- Advanced operations: square and square root
- Memory functions: M+, M-, MR, MC
- Delete function to remove the last entered digit
- Clear function to reset the calculator
- User-friendly interface with visual feedback for active operations
- Error handling for invalid operations

## Requirements

- Java Development Kit (JDK) 8 or higher
- Java Runtime Environment (JRE) for running the compiled application

## How to Deploy

### From Source Code

1. Save the provided `Calculator.java` file to your local directory.
2. Open a terminal/command prompt and navigate to the directory containing the file.
3. Compile the Java file:
   ```
   javac Calculator.java
   ```
4. Run the compiled program:
   ```
   java Calculator
   ```

### As a JAR File (if you want to create one)

1. First, compile the program as mentioned above.
2. Create a manifest file (manifest.txt) with the following content:
   ```
   Main-Class: Calculator
   ```
3. Create the JAR file:
   ```
   jar cvfm Calculator.jar manifest.txt *.class
   ```
4. Run the JAR file:
   ```
   java -jar Calculator.jar
   ```

## How to Use

### Basic Arithmetic

1. Enter the first number using the digit buttons (0-9).
2. Press an operation button (+, -, ×, ÷).
3. Enter the second number.
4. Press the equals (=) button to see the result.

### Advanced Operations

- For square (x²): Enter a number and press the "x²" button, then press equals.
- For square root (√): Enter a number and press the "√" button, then press equals.

### Memory Functions

- **M+**: Adds the current displayed result to memory
  - Only works after completing a calculation (pressing =)
- **M-**: Subtracts the current displayed result from memory
  - Only works after completing a calculation (pressing =)
- **MR**: Recalls the number stored in memory and displays it
- **MC**: Clears the memory

### Other Functions

- **DEL**: Deletes the last entered digit or decimal point
- **C**: Clears the calculator, resetting it to initial state

### Error Handling

The calculator will display an error message for invalid operations such as:
- Division by zero
- Square root of a negative number
- Results outside the representable range
- Attempting to add a non-result to memory

## Design Pattern

This calculator implements the MVC (Model-View-Controller) design pattern:

- **Model (CalculatorModel)**: Contains the calculator logic and data
- **View (CalculatorView)**: Displays the calculator GUI
- **Controller (CalculatorController)**: Handles user inputs and updates the model accordingly

A custom implementation of the Observer pattern is used to keep the view updated with changes from the model. This implementation avoids using Java's deprecated Observable class.

## Credits

This calculator was created as part of a lab assignment using the Java Swing library and MVC pattern.