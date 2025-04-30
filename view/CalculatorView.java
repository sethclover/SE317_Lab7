package view;

import controller.CalculatorController;
import model.CalculatorModel;
import model.ModelObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * View part of the MVC pattern
 * Displays the calculator GUI and interacts with the user
 */
public class CalculatorView extends JFrame implements ModelObserver {
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
    public void update(CalculatorModel model) {
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
