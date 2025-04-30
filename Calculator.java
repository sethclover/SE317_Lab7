import model.CalculatorModel;
import view.CalculatorView;
import controller.CalculatorController;

import javax.swing.*;

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
		new CalculatorController(model, view);

		// Start the application
		view.setVisible(true);
	}
}