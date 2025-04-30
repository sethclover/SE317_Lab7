package model;

/**
 * Model part of the MVC pattern
 * Contains the calculator logic and notifies observers of changes
 */
public class CalculatorModel {
	private double currentValue;
	private double memory;
	private double firstOperand;
	private String currentOperation;
	private boolean operationActive;
	private boolean resultDisplayed;
	private boolean isNewInput;
	private StringBuilder inputBuilder;

	public CalculatorModel() {
		reset();
	}

	/**
	 * Reset all calculator values
	 */
	public void reset() {
		currentValue = 0;
		memory = 0;
		firstOperand = 0;
		currentOperation = "";
		operationActive = false;
		resultDisplayed = false;
		isNewInput = true;
		inputBuilder = new StringBuilder("0");
	}

	/**
	 * Add a digit to the current input
	 */
	public void addDigit(String digit) {
		if (resultDisplayed) {
			inputBuilder = new StringBuilder();
			resultDisplayed = false;
			isNewInput = true;
		}

		if (isNewInput || inputBuilder.toString().equals("0")) {
			inputBuilder = new StringBuilder(digit);
			isNewInput = false;
		} else {
			inputBuilder.append(digit);
		}

		updateCurrentValue();
	}

	/**
	 * Add decimal point to the current input
	 */
	public void addDecimalPoint() {
		if (resultDisplayed) {
			inputBuilder = new StringBuilder("0.");
			resultDisplayed = false;
			isNewInput = false;
		} else if (isNewInput) {
			inputBuilder = new StringBuilder("0.");
			isNewInput = false;
		} else if (inputBuilder.toString().indexOf('.') == -1) {
			inputBuilder.append('.');
		}

		updateCurrentValue();
	}

	/**
	 * Set an operation to perform
	 */
	public void setOperation(String operation) {
		if (!inputBuilder.toString().isEmpty()) {
			if (operationActive) {
				calculate();
			}

			firstOperand = currentValue;
			currentOperation = operation;
			operationActive = true;
			isNewInput = true;
		}
	}

	/**
	 * Perform a calculation based on the current operation
	 */
	public String calculate() {
		double result = 0;
		String errorMessage = null;

		try {
			// If no second operand is entered for a two-operand operation,
			// use the first operand as the second operand as well
			if (isNewInput && !currentOperation.equals("sqrt") && !currentOperation.equals("square")) {
				currentValue = firstOperand;
			}

			// Perform the appropriate calculation
			switch (currentOperation) {
				case "add":
					result = firstOperand + currentValue;
					break;
				case "subtract":
					result = firstOperand - currentValue;
					break;
				case "multiply":
					result = firstOperand * currentValue;
					break;
				case "divide":
					if (currentValue == 0) {
						throw new ArithmeticException("Division by zero");
					}
					result = firstOperand / currentValue;
					break;
				case "sqrt":
					if (firstOperand < 0) {
						throw new ArithmeticException("Cannot take square root of negative number");
					}
					result = Math.sqrt(firstOperand);
					break;
				case "square":
					result = firstOperand * firstOperand;
					break;
				default:
					result = currentValue;
					break;
			}

			// Check for overflow or other numerical issues
			if (Double.isInfinite(result) || Double.isNaN(result)) {
				throw new ArithmeticException("Result out of range");
			}

			currentValue = result;
			inputBuilder = new StringBuilder(formatOutput(currentValue));

		} catch (ArithmeticException e) {
			errorMessage = "Error: " + e.getMessage();
			reset();
			inputBuilder = new StringBuilder(errorMessage);
		}

		currentOperation = "";
		operationActive = false;
		resultDisplayed = true;
		isNewInput = true;

		return errorMessage == null ? formatOutput(result) : errorMessage;
	}

	/**
	 * Formats the output to avoid showing ".0" for whole numbers
	 */
	private String formatOutput(double value) {
		if (value == (long) value) {
			return String.format("%d", (long) value);
		} else {
			return String.valueOf(value);
		}
	}

	/**
	 * Update the current value from the input builder
	 */
	private void updateCurrentValue() {
		try {
			if (inputBuilder.length() > 0) {
				currentValue = Double.parseDouble(inputBuilder.toString());
			} else {
				currentValue = 0;
			}
		} catch (NumberFormatException e) {
			// This shouldn't happen with controlled input
			currentValue = 0;
		}
	}

	/**
	 * Delete the last character from the input
	 */
	public void deleteLastCharacter() {
		if (inputBuilder.length() > 0) {
			inputBuilder.deleteCharAt(inputBuilder.length() - 1);
			if (inputBuilder.length() == 0 || inputBuilder.toString().equals("-")) {
				inputBuilder = new StringBuilder("0");
				isNewInput = true;
			}
			updateCurrentValue();
		}
	}

	/**
	 * Add the current value to memory
	 */
	public String addToMemory() {
		if (resultDisplayed) {
			memory += currentValue;
			return "Value added to memory";
		} else {
			return "Error: Only results can be added to memory";
		}
	}

	/**
	 * Subtract the current value from memory
	 */
	public String subtractFromMemory() {
		if (resultDisplayed) {
			memory -= currentValue;
			return "Value subtracted from memory";
		} else {
			return "Error: Only results can be subtracted from memory";
		}
	}

	/**
	 * Recall the value from memory
	 */
	public void recallMemory() {
		currentValue = memory;
		inputBuilder = new StringBuilder(formatOutput(memory));
		isNewInput = false;
		resultDisplayed = false;
	}

	/**
	 * Clear the memory
	 */
	public void clearMemory() {
		memory = 0;
	}

	/**
	 * Get the current display value
	 */
	public String getDisplayValue() {
		return inputBuilder.toString();
	}

	/**
	 * Get the current operation
	 */
	public String getCurrentOperation() {
		return currentOperation;
	}
}
