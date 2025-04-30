package model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CalculatorModelTest {

	private CalculatorModel calculator;

	@Before
	public void setUp() {
		calculator = new CalculatorModel();
	}

	// Basic functionality tests
	@Test
	public void testInitialState() {
		assertEquals("0", calculator.getDisplayValue());
		assertEquals("", calculator.getCurrentOperation());
	}

	@Test
	public void testAddDigit() {
		calculator.addDigit("5");
		assertEquals("5", calculator.getDisplayValue());

		calculator.addDigit("3");
		assertEquals("53", calculator.getDisplayValue());
	}

	@Test
	public void testAddDigitAfterResult() {
		calculator.addDigit("5");
		calculator.setOperation("add");
		calculator.addDigit("3");
		calculator.calculate();
		calculator.addDigit("7");
		assertEquals("7", calculator.getDisplayValue());
	}

	@Test
	public void testAddDecimalPoint() {
		calculator.addDecimalPoint();
		assertEquals("0.", calculator.getDisplayValue());

		calculator.addDigit("5");
		assertEquals("0.5", calculator.getDisplayValue());
	}

	@Test
	public void testMultipleDecimalPoints() {
		calculator.addDigit("5");
		calculator.addDecimalPoint();
		calculator.addDigit("3");
		calculator.addDecimalPoint(); // Second decimal should be ignored
		assertEquals("5.3", calculator.getDisplayValue());
	}

	@Test
	public void testDeleteLastCharacter() {
		calculator.addDigit("1");
		calculator.addDigit("2");
		calculator.addDigit("3");
		calculator.deleteLastCharacter();
		assertEquals("12", calculator.getDisplayValue());

		calculator.deleteLastCharacter();
		calculator.deleteLastCharacter();
		assertEquals("0", calculator.getDisplayValue());
	}

	// Basic arithmetic operation tests
	@Test
	public void testAddition() {
		calculator.addDigit("5");
		calculator.setOperation("add");
		calculator.addDigit("3");
		assertEquals("8", calculator.calculate());
	}

	@Test
	public void testSubtraction() {
		calculator.addDigit("10");
		calculator.setOperation("subtract");
		calculator.addDigit("4");
		assertEquals("6", calculator.calculate());
	}

	@Test
	public void testMultiplication() {
		calculator.addDigit("6");
		calculator.setOperation("multiply");
		calculator.addDigit("7");
		assertEquals("42", calculator.calculate());
	}

	@Test
	public void testDivision() {
		calculator.addDigit("20");
		calculator.setOperation("divide");
		calculator.addDigit("4");
		assertEquals("5", calculator.calculate());
	}

	@Test
	public void testDecimalResult() {
		calculator.addDigit("10");
		calculator.setOperation("divide");
		calculator.addDigit("3");
		assertEquals("3.3333333333333335", calculator.calculate());
	}

	// Advanced operations
	@Test
	public void testSquare() {
		calculator.addDigit("9");
		calculator.setOperation("square");
		assertEquals("81", calculator.calculate());
	}

	@Test
	public void testSquareRoot() {
		calculator.addDigit("16");
		calculator.setOperation("sqrt");
		assertEquals("4", calculator.calculate());
	}

	// Error handling
	@Test
	public void testDivisionByZero() {
		calculator.addDigit("5");
		calculator.setOperation("divide");
		calculator.addDigit("0");
		assertEquals("Error: Division by zero", calculator.calculate());
	}

	@Test
	public void testNegativeSquareRoot() {
		calculator.addDigit("-16");
		calculator.setOperation("sqrt");
		assertEquals("Error: Cannot take square root of negative number", calculator.calculate());
	}

	// Memory operations
	@Test
	public void testMemoryOperations() {
		calculator.addDigit("5");
		calculator.setOperation("add");
		calculator.addDigit("5");
		calculator.calculate();

		assertEquals("Value added to memory", calculator.addToMemory());

		// calculator.reset();
		calculator.recallMemory();
		assertEquals("10", calculator.getDisplayValue());

		calculator.clearMemory();
		// calculator.reset();
		calculator.recallMemory();
		assertEquals("0", calculator.getDisplayValue());
	}

	@Test
	public void testSubtractFromMemory() {
		calculator.addDigit("5");
		calculator.setOperation("add");
		calculator.addDigit("5");
		calculator.calculate();

		assertEquals("Value added to memory", calculator.addToMemory());

		calculator.addDigit("3");
		calculator.calculate();
		assertEquals("Value subtracted from memory", calculator.subtractFromMemory());

		// calculator.reset();
		calculator.recallMemory();
		assertEquals("7", calculator.getDisplayValue());
	}

	// Chained operations
	@Test
	public void testChainedOperations() {
		calculator.addDigit("5");
		calculator.setOperation("add");
		calculator.addDigit("3");
		calculator.setOperation("multiply");
		calculator.addDigit("2");
		assertEquals("16", calculator.calculate());
	}

	@Test
	public void testRepeatedEquals() {
		calculator.addDigit("5");
		calculator.setOperation("add");
		calculator.addDigit("3");
		calculator.calculate();
		calculator.setOperation("add");
		calculator.addDigit("2");
		assertEquals("10", calculator.calculate());
	}
}
