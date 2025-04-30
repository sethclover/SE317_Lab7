# Scientific Calculator - Comprehensive Test Plan
SE 317: Lab 7 - Interactive GUI System Testing

## 1. Introduction

This document outlines the comprehensive test plan for the Scientific Calculator application, covering both model-level testing and GUI-level testing. The calculator is implemented using the MVC design pattern with the Java Swing library.

## 2. Model Testing

Model testing will be performed using Java test code with simulated function calls to verify the computational logic. Each test case will include the input sequence, expected output, and actual output.

### 2.1 Basic Operations Tests

#### 2.1.1 Addition Tests
| Test ID | Test Case | Input Sequence | Expected Result |
|---------|-----------|----------------|-----------------|
| M-ADD-01 | Basic Addition | 123 + 456 = | 579 |
| M-ADD-02 | Addition with Decimal | 12.34 + 56.78 = | 69.12 |
| M-ADD-03 | Addition with Negative Number | -25 + 10 = | -15 |
| M-ADD-04 | Zero Addition | 0 + 0 = | 0 |

#### 2.1.2 Subtraction Tests
| Test ID | Test Case | Input Sequence | Expected Result |
|---------|-----------|----------------|-----------------|
| M-SUB-01 | Basic Subtraction | 456 - 123 = | 333 |
| M-SUB-02 | Subtraction with Decimal | 56.78 - 12.34 = | 44.44 |
| M-SUB-03 | Subtraction with Negative Result | 25 - 50 = | -25 |
| M-SUB-04 | Subtraction with Zero | 45 - 0 = | 45 |

#### 2.1.3 Multiplication Tests
| Test ID | Test Case | Input Sequence | Expected Result |
|---------|-----------|----------------|-----------------|
| M-MUL-01 | Basic Multiplication | 12 * 34 = | 408 |
| M-MUL-02 | Multiplication with Decimal | 5.5 * 2.0 = | 11.0 |
| M-MUL-03 | Multiplication with Negative Number | -3 * 4 = | -12 |
| M-MUL-04 | Multiplication with Zero | 567 * 0 = | 0 |

#### 2.1.4 Division Tests
| Test ID | Test Case | Input Sequence | Expected Result |
|---------|-----------|----------------|-----------------|
| M-DIV-01 | Basic Division | 100 / 5 = | 20 |
| M-DIV-02 | Division with Decimal | 10 / 4 = | 2.5 |
| M-DIV-03 | Division with Negative Number | -15 / 3 = | -5 |
| M-DIV-04 | Division by Zero | 25 / 0 = | Error |

### 2.2 Advanced Operations Tests

#### 2.2.1 Square Tests
| Test ID | Test Case | Input Sequence | Expected Result |
|---------|-----------|----------------|-----------------|
| M-SQR-01 | Square of Integer | 12 (square) | 144 |
| M-SQR-02 | Square of Decimal | 1.5 (square) | 2.25 |
| M-SQR-03 | Square of Negative Number | -6 (square) | 36 |
| M-SQR-04 | Square of Zero | 0 (square) | 0 |

#### 2.2.2 Square Root Tests
| Test ID | Test Case | Input Sequence | Expected Result |
|---------|-----------|----------------|-----------------|
| M-SQRT-01 | Square Root of Perfect Square | 64 (square root) | 8 |
| M-SQRT-02 | Square Root of Non-Perfect Square | 10 (square root) | 3.16227... |
| M-SQRT-03 | Square Root of Zero | 0 (square root) | 0 |
| M-SQRT-04 | Square Root of Negative Number | -9 (square root) | Error |

### 2.3 Memory Operation Tests

#### 2.3.1 Memory Addition (M+) Tests
| Test ID | Test Case | Input Sequence | Expected Result |
|---------|-----------|----------------|-----------------|
| M-MADD-01 | Add Value to Empty Memory | 125 = M+ | Memory contains 125 |
| M-MADD-02 | Add Value to Existing Memory | 50 = M+, 25 = M+ | Memory contains 75 |
| M-MADD-03 | Add Negative Value to Memory | -30 = M+, 50 = M+ | Memory contains 20 |
| M-MADD-04 | Add Value without Executing Operation | 123 M+ | Error |

#### 2.3.2 Memory Subtraction (M-) Tests
| Test ID | Test Case | Input Sequence | Expected Result |
|---------|-----------|----------------|-----------------|
| M-MSUB-01 | Subtract Value from Memory | 100 = M+, 30 = M- | Memory contains 70 |
| M-MSUB-02 | Subtract to Negative Memory | 50 = M+, 75 = M- | Memory contains -25 |
| M-MSUB-03 | Subtract from Empty Memory | 30 = M- | Memory contains -30 |
| M-MSUB-04 | Subtract without Executing Operation | 123 M- | Error |

#### 2.3.3 Memory Recall (MR) Tests
| Test ID | Test Case | Input Sequence | Expected Result |
|---------|-----------|----------------|-----------------|
| M-MR-01 | Recall Memory as First Operand | 50 = M+, MR + 25 = | 75 |
| M-MR-02 | Recall Memory as Second Operand | 10 = M+, 5 + MR = | 15 |
| M-MR-03 | Recall Empty Memory | MR | 0 |
| M-MR-04 | Recall Memory for Unary Operation | 25 = M+, MR (square) | 625 |

#### 2.3.4 Memory Clear (MC) Tests
| Test ID | Test Case | Input Sequence | Expected Result |
|---------|-----------|----------------|-----------------|
| M-MC-01 | Clear Memory | 50 = M+, MC, MR | 0 |
| M-MC-02 | Clear Empty Memory | MC, MR | 0 |

### 2.4 Delete Operation Tests

| Test ID | Test Case | Input Sequence | Expected Result |
|---------|-----------|----------------|-----------------|
| M-DEL-01 | Delete Last Digit | Enter 1234, Delete | 123 |
| M-DEL-02 | Delete Decimal Point | Enter 123.4, Delete | 123 |
| M-DEL-03 | Multiple Delete Operations | Enter 1234.5, Delete (3 times) | 123 |
| M-DEL-04 | Delete All Digits | Enter 123, Delete (3 times) | 0 |

### 2.5 Clear Operation Tests

| Test ID | Test Case | Input Sequence | Expected Result |
|---------|-----------|----------------|-----------------|
| M-CLR-01 | Clear Current Operation | Enter 123, Clear | 0 |
| M-CLR-02 | Clear Memory with Clear | 50 = M+, Clear, MR | 0 |
| M-CLR-03 | Clear in Middle of Operation | 123 + 456, Clear | 0 |

### 2.6 Edge Case Tests

| Test ID | Test Case | Input Sequence | Expected Result |
|---------|-----------|----------------|-----------------|
| M-EDGE-01 | Very Large Numbers | 9999999 * 9999999 = | Error or Correct Result (depends on implementation) |
| M-EDGE-02 | Very Small Decimals | 0.0000001 * 0.0000001 = | 0.00000000000001 or Rounded Result |
| M-EDGE-03 | Multiple Operations | 5 + 5 = * 2 = | 20 |
| M-EDGE-04 | Changing Operations | 5 + (press +) (press -) 3 = | 2 |

## 3. GUI Testing

GUI testing will be performed using the AssertJ-Swing UI testing library. Each test case will include input sequence and expected visual results.

### 3.1 TR1: Test GUI Functions

| Test ID | Test Case | Input Sequence | Expected Result |
|---------|-----------|----------------|-----------------|
| GUI-TR1-01 | Basic Addition | Click 1, 2, +, 3, 4, = | Display shows 46 |
| GUI-TR1-02 | Basic Subtraction | Click 5, 0, -, 2, 5, = | Display shows 25 |
| GUI-TR1-03 | Basic Multiplication | Click 1, 2, *, 5, = | Display shows 60 |
| GUI-TR1-04 | Basic Division | Click 1, 0, 0, /, 2, 0, = | Display shows 5 |
| GUI-TR1-05 | Square Function | Click 9, (square) | Display shows 81 |
| GUI-TR1-06 | Square Root Function | Click 2, 5, (square root) | Display shows 5 |

### 3.2 TR2: Only Operands and Results are Displayed

| Test ID | Test Case | Input Sequence | Expected Result |
|---------|-----------|----------------|-----------------|
| GUI-TR2-01 | Addition Display | Click 1, 1, 1, 1, 1, 1, + | Display shows 111111 (operation not shown) |
| GUI-TR2-02 | Operation Switching | Click 1, 2, 3, +, -, *, / | Display shows 123 (only operand shown) |
| GUI-TR2-03 | Second Operand Display | Click 1, 2, 3, +, 4, 5, 6 | Display shows 456 (first operand replaced) |

### 3.3 TR3: Operation Button Visual State Changes

| Test ID | Test Case | Input Sequence | Expected Result |
|---------|-----------|----------------|-----------------|
| GUI-TR3-01 | Addition Button State | Click 5, + | + button shows active state |
| GUI-TR3-02 | Operation Button Reset | Click 5, +, 5, = | + button returns to idle state after = |
| GUI-TR3-03 | Operation Switch | Click 5, +, - | + button returns to idle, - button shows active state |

### 3.4 Advanced GUI Tests

#### 3.4.1 Memory Button Tests

| Test ID | Test Case | Input Sequence | Expected Result |
|---------|-----------|----------------|-----------------|
| GUI-MEM-01 | M+ Button Functionality | Click 1, 0, =, M+ | Memory indicator shows active state |
| GUI-MEM-02 | M- Button Functionality | Click 1, 0, =, M+, 5, =, M- | Memory indicator shows value changed |
| GUI-MEM-03 | MR Button Functionality | Click 2, 0, =, M+, Clear, MR | Display shows 20 |
| GUI-MEM-04 | MC Button Functionality | Click 2, 0, =, M+, MC | Memory indicator shows empty state |

#### 3.4.2 Delete and Clear Button Tests

| Test ID | Test Case | Input Sequence | Expected Result |
|---------|-----------|----------------|-----------------|
| GUI-DEL-01 | Delete Button | Click 1, 2, 3, Delete | Display shows 12 |
| GUI-DEL-02 | Multiple Delete | Click 1, 2, 3, ., 4, Delete, Delete | Display shows 123 |
| GUI-CLR-01 | Clear Button | Click 1, 2, 3, +, 4, 5, 6, Clear | Display shows 0 |
| GUI-CLR-02 | Clear Memory | Click 1, 0, =, M+, Clear, MR | Display shows 0 |

#### 3.4.3 Error Handling Tests

| Test ID | Test Case | Input Sequence | Expected Result |
|---------|-----------|----------------|-----------------|
| GUI-ERR-01 | Division by Zero | Click 5, /, 0, = | Display shows "Error" |
| GUI-ERR-02 | Negative Square Root | Click -, 9, (square root) | Display shows "Error" |
| GUI-ERR-03 | Invalid Memory Operation | Click 1, 2, 3, M+ | Display shows "Error" |

## 4. Sample Test Cases with Expected and Actual Results

### 4.1 Model Test Case Sample

#### Test Case: Addition (M-ADD-01)
- Input Sequence: 123 + 456 =
- Expected Result: 579
- Actual Result: 579
- Status: PASS

#### Test Case: Division by Zero (M-DIV-04)
- Input Sequence: 25 / 0 =
- Expected Result: Error
- Actual Result: Error
- Status: PASS

### 4.2 GUI Test Case Sample

#### Test Case: Basic Addition (GUI-TR1-01)
- Input Sequence: Click 1, 2, +, 3, 4, =
- Expected Result: Display shows 46
- Actual Result: Display shows 46
- Status: PASS

#### Test Case: Division by Zero (GUI-ERR-01)
- Input Sequence: Click 5, /, 0, =
- Expected Result: Display shows "Error"
- Actual Result: Display shows "Error"
- Status: PASS

#### Test Case: Operation Button State (GUI-TR3-01)
- Input Sequence: Click 5, +
- Expected Result: + button shows active state
- Actual Result: + button shows active state
- Status: PASS

## 5. Test Environment

- Software: Java Development Kit (JDK) 17
- Libraries: JUnit, Hamcrest, AssertJ-Swing

## 6. Test Execution Plan

1. Execute all model tests using Java test code
2. Capture screenshots of test results
3. Execute all GUI tests using the UI test tool
4. Record test execution videos for GUI tests
5. Document any discrepancies between expected and actual results
6. Fix any identified bugs and retest

## 7. Conclusion

This test plan covers a comprehensive set of test cases for both the model and GUI components of the scientific calculator application. The tests verify basic operations, advanced operations, memory functions, and edge cases to ensure the calculator works correctly according to specifications.

---

*Prepared by: Tyler Gorton (tjgorton@iastate.edu) & Seth Clover (sclover@iastate.edu)*