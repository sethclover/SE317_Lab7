import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Observable;
import java.util.Observer;

/**
 * Main class for the Calculator application
 */
public class Calculator {
    public static void main(String[] args) {
        // Set look and feel to system default
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Create the calculator model
        CalculatorModel model = new CalculatorModel();
        
        // Create the calculator view
        CalculatorView view = new CalculatorView();
        
        // Create the calculator controller
        CalculatorController controller = new CalculatorController(model, view);
        
        // Start the application
        view.setVisible(true);
    }
}

/**
 * Model part of the MVC pattern
 * Contains the calculator logic and notifies observers of changes
 */
class CalculatorModel extends Observable {
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
        inputBuilder = new StringBuilder();
        notifyView();
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
        
        if (isNewInput) {
            inputBuilder = new StringBuilder(digit);
            isNewInput = false;
        } else {
            inputBuilder.append(digit);
        }
        
        updateCurrentValue();
        notifyView();
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
        notifyView();
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
            notifyView();
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
        notifyView();
        
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
        if (!resultDisplayed && inputBuilder.length() > 0) {
            inputBuilder.deleteCharAt(inputBuilder.length() - 1);
            if (inputBuilder.length() == 0 || inputBuilder.toString().equals("-")) {
                inputBuilder = new StringBuilder("0");
                isNewInput = true;
            }
            updateCurrentValue();
            notifyView();
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
        notifyView();
    }
    
    /**
     * Clear the memory
     */
    public void clearMemory() {
        memory = 0;
    }
    
    /**
     * Notify the observer (view) that the model has changed
     */
    private void notifyView() {
        setChanged();
        notifyObservers();
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

/**
 * View part of the MVC pattern
 * Displays the calculator GUI and interacts with the user
 */
class CalculatorView extends JFrame implements Observer {
    private JTextField display;
    private JButton[] digitButtons;
    private JButton decimalButton;
    private JButton equalsButton;
    private JButton addButton;
    private JButton subtractButton;
    private JButton multiplyButton;
    private JButton divideButton;
    private JButton squareButton;
    private JButton sqrtButton;
    private JButton deleteButton;
    private JButton clearButton;
    private JButton memoryAddButton;
    private JButton memorySubtractButton;
    private JButton memoryRecallButton;
    private JButton memoryClearButton;
    
    private CalculatorController controller;
    
    public CalculatorView() {
        // Set up the frame
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(350, 500);
        setLocationRelativeTo(null);
        
        // Initialize components
        initComponents();
        
        // Set the layout
        layoutComponents();
    }
    
    /**
     * Initialize all the components of the calculator
     */
    private void initComponents() {
        // Display field
        display = new JTextField("0");
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        
        // Digit buttons
        digitButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            digitButtons[i] = createButton(String.valueOf(i));
        }
        
        // Operation buttons
        decimalButton = createButton(".");
        equalsButton = createButton("=");
        addButton = createButton("+");
        subtractButton = createButton("-");
        multiplyButton = createButton("×");
        divideButton = createButton("÷");
        squareButton = createButton("x²");
        sqrtButton = createButton("√");
        deleteButton = createButton("DEL");
        clearButton = createButton("C");
        
        // Memory buttons
        memoryAddButton = createButton("M+");
        memorySubtractButton = createButton("M-");
        memoryRecallButton = createButton("MR");
        memoryClearButton = createButton("MC");
    }
    
    /**
     * Helper method to create a button with standard styling
     */
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setFocusPainted(false);
        
        // Set different background colors based on button type
        if (text.matches("[0-9]|\\.")) {
            button.setBackground(new Color(230, 230, 230));
        } else if (text.equals("=")) {
            button.setBackground(new Color(102, 178, 255));
            button.setForeground(Color.WHITE);
        } else if (text.startsWith("M")) {
            button.setBackground(new Color(255, 204, 102));
        } else {
            button.setBackground(new Color(204, 204, 204));
        }
        
        return button;
    }
    
    /**
     * Set up the layout of all components
     */
    private void layoutComponents() {
        // Main panel with border layout
        JPanel mainPanel = new JPanel(new BorderLayout(5, 5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Display at the top
        mainPanel.add(display, BorderLayout.NORTH);
        
        // Panel for memory buttons
        JPanel memoryPanel = new JPanel(new GridLayout(1, 4, 5, 5));
        memoryPanel.add(memoryAddButton);
        memoryPanel.add(memorySubtractButton);
        memoryPanel.add(memoryRecallButton);
        memoryPanel.add(memoryClearButton);
        
        // Panel for digit and operation buttons
        JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 5, 5));
        
        // Row 1: Clear, Delete, Square, Square Root
        buttonPanel.add(clearButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(squareButton);
        buttonPanel.add(sqrtButton);
        
        // Row 2: 7, 8, 9, Divide
        buttonPanel.add(digitButtons[7]);
        buttonPanel.add(digitButtons[8]);
        buttonPanel.add(digitButtons[9]);
        buttonPanel.add(divideButton);
        
        // Row 3: 4, 5, 6, Multiply
        buttonPanel.add(digitButtons[4]);
        buttonPanel.add(digitButtons[5]);
        buttonPanel.add(digitButtons[6]);
        buttonPanel.add(multiplyButton);
        
        // Row 4: 1, 2, 3, Subtract
        buttonPanel.add(digitButtons[1]);
        buttonPanel.add(digitButtons[2]);
        buttonPanel.add(digitButtons[3]);
        buttonPanel.add(subtractButton);
        
        // Row 5: 0, Decimal, Equals, Add
        buttonPanel.add(digitButtons[0]);
        buttonPanel.add(decimalButton);
        buttonPanel.add(equalsButton);
        buttonPanel.add(addButton);
        
        // Panel to hold memory panel and button panel
        JPanel contentPanel = new JPanel(new BorderLayout(5, 5));
        contentPanel.add(memoryPanel, BorderLayout.NORTH);
        contentPanel.add(buttonPanel, BorderLayout.CENTER);
        
        // Add the content panel to the main panel
        mainPanel.add(contentPanel, BorderLayout.CENTER);
        
        // Set the main panel as the content pane
        setContentPane(mainPanel);
    }
    
    /**
     * Set the controller for this view
     */
    public void setController(CalculatorController controller) {
        this.controller = controller;
        
        // Add action listeners to buttons
        for (int i = 0; i < 10; i++) {
            final int digit = i;
            digitButtons[i].addActionListener(e -> controller.digitPressed(String.valueOf(digit)));
        }
        
        decimalButton.addActionListener(e -> controller.decimalPressed());
        equalsButton.addActionListener(e -> controller.equalsPressed());
        addButton.addActionListener(e -> controller.operationPressed("add"));
        subtractButton.addActionListener(e -> controller.operationPressed("subtract"));
        multiplyButton.addActionListener(e -> controller.operationPressed("multiply"));
        divideButton.addActionListener(e -> controller.operationPressed("divide"));
        squareButton.addActionListener(e -> controller.operationPressed("square"));
        sqrtButton.addActionListener(e -> controller.operationPressed("sqrt"));
        deleteButton.addActionListener(e -> controller.deletePressed());
        clearButton.addActionListener(e -> controller.clearPressed());
        memoryAddButton.addActionListener(e -> controller.memoryAddPressed());
        memorySubtractButton.addActionListener(e -> controller.memorySubtractPressed());
        memoryRecallButton.addActionListener(e -> controller.memoryRecallPressed());
        memoryClearButton.addActionListener(e -> controller.memoryClearPressed());
    }
    
    /**
     * Update the view to reflect changes in the model
     */
    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof CalculatorModel) {
            CalculatorModel model = (CalculatorModel) o;
            display.setText(model.getDisplayValue());
            
            // Update the visual state of operation buttons
            String currentOp = model.getCurrentOperation();
            resetOperationButtonStyles();
            
            // Set the active operation button style
            switch (currentOp) {
                case "add":
                    setOperationButtonActive(addButton);
                    break;
                case "subtract":
                    setOperationButtonActive(subtractButton);
                    break;
                case "multiply":
                    setOperationButtonActive(multiplyButton);
                    break;
                case "divide":
                    setOperationButtonActive(divideButton);
                    break;
                case "square":
                    setOperationButtonActive(squareButton);
                    break;
                case "sqrt":
                    setOperationButtonActive(sqrtButton);
                    break;
            }
        }
    }
    
    /**
     * Reset all operation buttons to their default style
     */
    private void resetOperationButtonStyles() {
        addButton.setBackground(new Color(204, 204, 204));
        addButton.setFont(new Font("Arial", Font.BOLD, 18));
        
        subtractButton.setBackground(new Color(204, 204, 204));
        subtractButton.setFont(new Font("Arial", Font.BOLD, 18));
        
        multiplyButton.setBackground(new Color(204, 204, 204));
        multiplyButton.setFont(new Font("Arial", Font.BOLD, 18));
        
        divideButton.setBackground(new Color(204, 204, 204));
        divideButton.setFont(new Font("Arial", Font.BOLD, 18));
        
        squareButton.setBackground(new Color(204, 204, 204));
        squareButton.setFont(new Font("Arial", Font.BOLD, 18));
        
        sqrtButton.setBackground(new Color(204, 204, 204));
        sqrtButton.setFont(new Font("Arial", Font.BOLD, 18));
    }
    
    /**
     * Set an operation button to active style
     */
    private void setOperationButtonActive(JButton button) {
        button.setBackground(new Color(255, 153, 0));
        button.setFont(new Font("Arial", Font.BOLD, 20));
    }
    
    /**
     * Show a message to the user
     */
    public void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }
}

/**
 * Controller part of the MVC pattern
 * Handles user interactions and updates the model
 */
class CalculatorController {
    private CalculatorModel model;
    private CalculatorView view;
    
    public CalculatorController(CalculatorModel model, CalculatorView view) {
        this.model = model;
        this.view = view;
        
        // Register the view as an observer of the model
        model.addObserver(view);
        
        // Set this controller to the view
        view.setController(this);
    }
    
    /**
     * Handle digit button press
     */
    public void digitPressed(String digit) {
        model.addDigit(digit);
    }
    
    /**
     * Handle decimal button press
     */
    public void decimalPressed() {
        model.addDecimalPoint();
    }
    
    /**
     * Handle operation button press
     */
    public void operationPressed(String operation) {
        model.setOperation(operation);
    }
    
    /**
     * Handle equals button press
     */
    public void equalsPressed() {
        String result = model.calculate();
        if (result.startsWith("Error")) {
            view.showMessage(result);
        }
    }
    
    /**
     * Handle delete button press
     */
    public void deletePressed() {
        model.deleteLastCharacter();
    }
    
    /**
     * Handle clear button press
     */
    public void clearPressed() {
        model.reset();
    }
    
    /**
     * Handle memory add button press
     */
    public void memoryAddPressed() {
        String result = model.addToMemory();
        if (result.startsWith("Error")) {
            view.showMessage(result);
        }
    }
    
    /**
     * Handle memory subtract button press
     */
    public void memorySubtractPressed() {
        String result = model.subtractFromMemory();
        if (result.startsWith("Error")) {
            view.showMessage(result);
        }
    }
    
    /**
     * Handle memory recall button press
     */
    public void memoryRecallPressed() {
        model.recallMemory();
    }
    
    /**
     * Handle memory clear button press
     */
    public void memoryClearPressed() {
        model.clearMemory();
    }
}