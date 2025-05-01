# Scientific Calculator - Comprehensive Test Plan
SE 317: Lab 7 - Interactive GUI System Testing

## 1. Introduction

This document outlines the comprehensive test plan for the Scientific Calculator application, implemented using the MVC design pattern with Java Observer class implementation. The test plan covers both model-level testing (computational logic) and GUI-level testing (user interface functionality). Model tests are performed using JUnit 4, and GUI tests are conducted using AssertJ Swing.

## 2. Model Testing

Model testing is performed using JUnit 4 test code with simulated function calls to verify the computational logic of the `CalculatorModel` class. Each test case includes the input sequence, expected output, and actual output. The tests cover initial state, digit and decimal input, basic arithmetic operations, advanced operations, error handling, memory operations, and chained operations.

### 2.1 Basic Operations Tests

#### 2.1.1 Initial State and Input Tests

| Test ID | Test Case | Input Sequence | Expected Result |
|---------|-----------|----------------|-----------------|
| M-INIT-01 | Initial State | None | Display is "0", Current operation is empty |
| M-INPUT-01 | Add Single Digit | Input "5" | Display is "5" |
| M-INPUT-02 | Add Multiple Digits | Input "5", "3" | Display is "53" |
| M-INPUT-03 | Add Digit After Result | Input "5", set "add", input "3", calculate, input "7" | Display is "7" |
| M-INPUT-04 | Add Decimal Point | Input decimal point | Display is "0." |
| M-INPUT-05 | Add Decimal Digit | Input decimal point, input "5" | Display is "0.5" |
| M-INPUT-06 | Multiple Decimal Points | Input "5", decimal point, "3", decimal point | Display is "5.3" |
| M-INPUT-07 | Delete Last Character | Input "1", "2", "3", delete | Display is "12" |
| M-INPUT-08 | Delete All Characters | Input "1", "2", "3", delete, delete, delete | Display is "0" |

#### 2.1.2 Addition Tests

| Test ID | Test Case | Input Sequence | Expected Result |
|---------|-----------|----------------|-----------------|
| M-ADD-01 | Basic Addition | Input "5", set "add", input "3", calculate | Result is "8" |

#### 2.1.3 Subtraction Tests

| Test ID | Test Case | Input Sequence | Expected Result |
|---------|-----------|----------------|-----------------|
| M-SUB-01 | Basic Subtraction | Input "10", set "subtract", input "4", calculate | Result is "6" |

#### 2.1.4 Multiplication Tests

| Test ID | Test Case | Input Sequence | Expected Result |
|---------|-----------|----------------|-----------------|
| M-MUL-01 | Basic Multiplication | Input "6", set "multiply", input "7", calculate | Result is "42" |

#### 2.1.5 Division Tests

| Test ID | Test Case | Input Sequence | Expected Result |
|---------|-----------|----------------|-----------------|
| M-DIV-01 | Basic Division | Input "20", set "divide", input "4", calculate | Result is "5" |
| M-DIV-02 | Decimal Division | Input "10", set "divide", input "3", calculate | Result is "3.3333333333333335" |

### 2.2 Advanced Operations Tests

#### 2.2.1 Square Tests

| Test ID | Test Case | Input Sequence | Expected Result |
|---------|-----------|----------------|-----------------|
| M-SQR-01 | Square of Integer | Input "9", set "square", calculate | Result is "81" |

#### 2.2.2 Square Root Tests

| Test ID | Test Case | Input Sequence | Expected Result |
|---------|-----------|----------------|-----------------|
| M-SQRT-01 | Square Root of Perfect Square | Input "16", set "sqrt", calculate | Result is "4" |

### 2.3 Memory Operation Tests

#### 2.3.1 Memory Addition (M+) Tests

| Test ID | Test Case | Input Sequence | Expected Result |
|---------|-----------|----------------|-----------------|
| M-MADD-01 | Add Value to Memory | Input "5", set "add", input "5", calculate, M+ | Message is "Value added to memory", Recall shows "10" |

#### 2.3.2 Memory Subtraction (M-) Tests

| Test ID | Test Case | Input Sequence | Expected Result |
|---------|-----------|----------------|-----------------|
| M-MSUB-01 | Subtract Value from Memory | Input "5", set "add", input "5", calculate, M+, input "3", calculate, M- | Message is "Value subtracted from memory", Recall shows "7" |

#### 2.3.3 Memory Recall (MR) and Clear (MC) Tests

| Test ID | Test Case | Input Sequence | Expected Result |
|---------|-----------|----------------|-----------------|
| M-MR-01 | Recall Memory | Input "5", set "add", input "5", calculate, M+, recall | Display is "10" |
| M-MC-01 | Clear Memory | Input "5", set "add", input "5", calculate, M+, clear memory, recall | Display is "0" |

### 2.4 Delete Operation Tests

| Test ID | Test Case | Input Sequence | Expected Result |
|---------|-----------|----------------|-----------------|
| M-DEL-01 | Delete Last Character | Input "1", "2", "3", delete | Display is "12" |
| M-DEL-02 | Delete All Characters | Input "1", "2", "3", delete, delete, delete | Display is "0" |

### 2.5 Edge Case Tests

| Test ID | Test Case | Input Sequence | Expected Result |
|---------|-----------|----------------|-----------------|
| M-EDGE-01 | Division by Zero | Input "5", set "divide", input "0", calculate | Result is "Error: Division by zero" |
| M-EDGE-02 | Negative Square Root | Input "-16", set "sqrt", calculate | Result is "Error: Cannot take square root of negative number" |
| M-EDGE-03 | Chained Operations | Input "5", set "add", input "3", set "multiply", input "2", calculate | Result is "16" |
| M-EDGE-04 | Repeated Equals | Input "5", set "add", input "3", calculate, set "add", input "2", calculate | Result is "10" |

## 3. GUI Testing

GUI testing is performed using AssertJ Swing, a UI testing tool for Java Swing applications. Each test case includes the input sequence (button clicks) and the expected visual result (display text or button state). The tests cover basic operations, advanced operations, memory functions, display behavior, button states, error handling, and clear/delete operations.

### 3.1 TR1: Test GUI Functions

| Test ID | Test Case | Input Sequence | Expected Result |
|---------|-----------|----------------|-----------------|
| GUI-TR1-01 | Basic Addition | Click 1, 2, +, 3, 4, = | Display shows "46" |
| GUI-TR1-02 | Basic Subtraction | Click 5, 0, -, 2, 0, = | Display shows "30" |
| GUI-TR1-03 | Basic Multiplication | Click 7, *, 8, = | Display shows "56" |
| GUI-TR1-04 | Basic Division | Click 1, 0, 0, /, 2, 5, = | Display shows "4" |
| GUI-TR1-05 | Square Function | Click 5, x², = | Display shows "25" |
| GUI-TR1-06 | Square Root Function | Click 1, 6, √, = | Display shows "4" |

### 3.2 TR2: Only Operands and Results are Displayed

| Test ID | Test Case | Input Sequence | Expected Result |
|---------|-----------|----------------|-----------------|
| GUI-TR2-01 | Display Digits | Click 1 | Display shows "1" |
| GUI-TR2-02 | Display Digits (Multiple) | Click 1, 2 | Display shows "12" |
| GUI-TR2-03 | Display Digits (Multiple) | Click 1, 2, 3 | Display shows "123" |
| GUI-TR2-04 | Display During Addition (First Operand) | Click 2 | Display shows "2" |
| GUI-TR2-05 | Display During Addition (After Operator) | Click 2, + | Display shows "2" |
| GUI-TR2-06 | Display During Addition (Second Operand) | Click 2, +, 3 | Display shows "3" |
| GUI-TR2-07 | Display During Addition (Result) | Click 2, +, 3, = | Display shows "5" |
| GUI-TR2-08 | Display Decimal | Click 4 | Display shows "4" |
| GUI-TR2-09 | Display Decimal (Point) | Click 4, . | Display shows "4." |
| GUI-TR2-10 | Display Decimal (Complete) | Click 4, ., 5 | Display shows "4.5" |

### 3.3 TR3: Operation Button Visual State Changes

| Test ID | Test Case | Input Sequence | Expected Result |
|---------|-----------|----------------|-----------------|
| GUI-TR3-01 | Addition Button State (Active) | Click Add | Add button background is orange (RGB: 255, 153, 0), Subtract button is gray (RGB: 204, 204, 204) |
| GUI-TR3-02 | Addition Button State (Reset) | Click Add, = | Add button background is gray (RGB: 204, 204, 204) |
| GUI-TR3-03 | Subtraction Button State (Active) | Click Subtract | Subtract button background is orange (RGB: 255, 153, 0), Add button is gray (RGB: 204, 204, 204) |
| GUI-TR3-04 | Subtraction Button State (Reset) | Click Subtract, = | Subtract button background is gray (RGB: 204, 204, 204) |
| GUI-TR3-05 | Square Button State (Active) | Click x² | x² button background is orange (RGB: 255, 153, 0), √ button is gray (RGB: 204, 204, 204) |
| GUI-TR3-06 | Square Button State (Reset) | Click x², = | x² button background is gray (RGB: 204, 204, 204) |

### 3.4 Advanced GUI Tests

#### 3.4.1 Memory Button Tests

| Test ID | Test Case | Input Sequence | Expected Result |
|---------|-----------|----------------|-----------------|
| GUI-MEM-01 | Memory Add and Recall | Click 2, +, 3, =, M+, DEL, MR | Display shows "5" |
| GUI-MEM-02 | Memory Subtract | Click 5, +, 5, =, M+, 2, +, 2, =, M-, MR | Display shows "6" |
| GUI-MEM-03 | Memory Add Non-Result | Click 1, 2, 3, M+ | Display shows "Error: Only results can be added to memory" |

#### 3.4.2 Delete and Clear Button Tests

| Test ID | Test Case | Input Sequence | Expected Result |
|---------|-----------|----------------|-----------------|
| GUI-DEL-01 | Delete Single Digit | Click 1, 2, 3, 4, DEL | Display shows "123" |
| GUI-DEL-02 | Delete Multiple Digits | Click 1, 2, 3, 4, DEL, DEL | Display shows "12" |
| GUI-CLR-01 | Clear Operation | Click 1, +, 2, =, C | Display shows "0", Add button background is gray (RGB: 204, 204, 204) |

#### 3.4.3 Error Handling Tests

| Test ID | Test Case | Input Sequence | Expected Result |
|---------|-----------|----------------|-----------------|
| GUI-ERR-01 | Division by Zero | Click 1, 0, ÷, 0, = | Display shows "Error: Division by zero" |
| GUI-ERR-02 | Negative Square Root | Click 2, -, 5, =, √, = | Display shows "Error: Cannot take square root of negative number" |

## 4. Sample Test Cases with Expected and Actual Results

### 4.1 Model Test Case Sample

#### Test Case: Initial State (M-INIT-01)
- Input Sequence: None
- Expected Result: Display is "0", Current operation is empty
- Actual Result: [To be filled after execution]
- Status: [To be filled after execution]

#### Test Case: Basic Addition (M-ADD-01)
- Input Sequence: Input "5", set "add", input "3", calculate
- Expected Result: Result is "8"
- Actual Result: [To be filled after execution]
- Status: [To be filled after execution]

#### Test Case: Division by Zero (M-EDGE-01)
- Input Sequence: Input "5", set "divide", input "0", calculate
- Expected Result: Result is "Error: Division by zero"
- Actual Result: [To be filled after execution]
- Status: [To be filled after execution]

#### Test Case: Memory Operations (M-MADD-01, M-MR-01)
- Input Sequence: Input "5", set "add", input "5", calculate, M+, recall
- Expected Result: Message is "Value added to memory", Recall shows "10"
- Actual Result: [To be filled after execution]
- Status: [To be filled after execution]

### 4.2 GUI Test Case Sample

#### Test Case: Basic Addition (GUI-TR1-01)
- Input Sequence: Click 1, 2, +, 3, 4, =
- Expected Result: Display shows "46"
- Actual Result: [To be filled after execution]
- Status: [To be filled after execution]

#### Test Case: Division by Zero (GUI-ERR-01)
- Input Sequence: Click 1, 0, ÷, 0, =
- Expected Result: Display shows "Error: Division by zero"
- Actual Result: [To be filled after execution]
- Status: [To be filled after execution]

#### Test Case: Addition Button State (GUI-TR3-01)
- Input Sequence: Click Add
- Expected Result: Add button background is orange (RGB: 255, 153, 0), Subtract button is gray (RGB: 204, 204, 204)
- Actual Result: [To be filled after execution]
- Status: [To be filled after execution]

#### Test Case: Memory Add and Recall (GUI-MEM-01)
- Input Sequence: Click 2, +, 3, =, M+, DEL, MR
- Expected Result: Display shows "5"
- Actual Result: [To be filled after execution]
- Status: [To be filled after execution]

## 5. Test Environment

- **Testing Tools**:
  - Model Testing: JUnit 4
  - GUI Testing: AssertJ Swing 3.17.1
- **Dependencies**:
  - Model: `junit-4.13.2.jar`, `hamcrest-core-1.3.jar`
  - GUI: `assertj-core-3.27.3.jar`, `assertj-swing-3.17.1.jar`, `assertj-swing-junit-3.17.1.jar`, `opentest4j-1.3.0.jar`, `fest-reflect-1.4.1.jar`

## 6. Test Execution Plan

1. Configure the test environment with JDK 17 and required dependencies.
2. Add JARs to the project classpath:
4. Execute model tests using the JUnit 4 test runner (`CalculatorModelTest.java`).
5. Execute GUI tests using the JUnit 5 test runner (`CalculatorGUITest.java`).
6. Capture screenshots of test results for failed tests.
7. Document any discrepancies between expected and actual results.
8. Fix any identified bugs and retest.

## 7. Conclusion

This test plan covers a comprehensive set of test cases for both the model and GUI components of the Scientific Calculator application. Model tests verify the computational logic, including basic operations, advanced operations, memory functions, and edge cases. GUI tests ensure the user interface functions correctly, providing appropriate visual feedback for operations, display behavior, button states, and error handling. The tests collectively ensure the calculator works according to specifications.

---

*Prepared by: Tyler Gorton (tjgorton@iastate.edu) & Seth Clover (sclover@iastate.edu)*