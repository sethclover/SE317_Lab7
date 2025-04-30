package controller;

import model.CalculatorModel;
import view.CalculatorView;

/**
 * Controller part of the MVC pattern
 * Handles user interactions and updates the model
 */
public class CalculatorController {
    private CalculatorModel model;
    private CalculatorView view;
    
    public CalculatorController(CalculatorModel model, CalculatorView view) {
        this.model = model;
        this.view = view;
        
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
