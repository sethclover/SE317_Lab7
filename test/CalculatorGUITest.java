import org.assertj.swing.fixture.FrameFixture;
import org.assertj.swing.fixture.JButtonFixture;
import org.assertj.swing.junit.testcase.AssertJSwingJUnitTestCase;
import org.junit.Test;
import java.awt.Color;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.swing.finder.WindowFinder.findFrame;
import static org.assertj.swing.launcher.ApplicationLauncher.application;
import static org.assertj.swing.core.matcher.JButtonMatcher.withText;

public class CalculatorGUITest extends AssertJSwingJUnitTestCase {
	private FrameFixture window;

	@Override
	public void onSetUp() {
		// Launch the calculator application
		application("Calculator").start();
		// Find the frame by title
		window = findFrame("Calculator").using(robot());
		window.show();
	}

	// Helper method to check button background color
	private void checkButtonBackground(JButtonFixture button, Color expectedColor) {
		assertThat(button.target().getBackground()).isEqualTo(expectedColor);
	}

	@Test
	public void testAddition() {
		window.button(withText("1")).click();
		window.button(withText("2")).click();
		window.button("Add").click();
		window.button(withText("3")).click();
		window.button(withText("4")).click();
		window.button("Equals").click();
		window.textBox().requireText("46");
	}

	@Test
	public void testSubtraction() {
		window.button(withText("5")).click();
		window.button(withText("0")).click();
		window.button("Subtract").click();
		window.button(withText("2")).click();
		window.button(withText("0")).click();
		window.button("Equals").click();
		window.textBox().requireText("30");
	}

	@Test
	public void testMultiplication() {
		window.button(withText("7")).click();
		window.button("Multiply").click();
		window.button(withText("8")).click();
		window.button("Equals").click();
		window.textBox().requireText("56");
	}

	@Test
	public void testDivision() {
		window.button(withText("1")).click();
		window.button(withText("0")).click();
		window.button(withText("0")).click();
		window.button("Divide").click();
		window.button(withText("2")).click();
		window.button(withText("5")).click();
		window.button("Equals").click();
		window.textBox().requireText("4");
	}

	@Test
	public void testSquare() {
		window.button(withText("5")).click();
		window.button(withText("x²")).click();
		window.button(withText("=")).click();
		window.textBox().requireText("25");
	}

	@Test
	public void testSquareRoot() {
		window.button(withText("1")).click();
		window.button(withText("6")).click();
		window.button(withText("√")).click();
		window.button(withText("=")).click();
		window.textBox().requireText("4");
	}

	@Test
	public void testDisplayDigits() {
		window.button(withText("1")).click();
		window.textBox().requireText("1");
		window.button(withText("2")).click();
		window.textBox().requireText("12");
		window.button(withText("3")).click();
		window.textBox().requireText("123");
	}

	@Test
	public void testDisplayDuringAddition() {
		window.button(withText("2")).click();
		window.textBox().requireText("2");
		window.button("Add").click();
		window.textBox().requireText("2"); // First operand remains
		window.button(withText("3")).click();
		window.textBox().requireText("3"); // Second operand shown
		window.button(withText("=")).click();
		window.textBox().requireText("5"); // Result shown
	}

	@Test
	public void testDisplayDecimal() {
		window.button(withText("4")).click();
		window.textBox().requireText("4");
		window.button("Decimal").click();
		window.textBox().requireText("4.");
		window.button(withText("5")).click();
		window.textBox().requireText("4.5");
	}

	@Test
	public void testAdditionButtonState() {
		window.button("Add").click();
		checkButtonBackground(window.button("Add"), new Color(255, 153, 0));
		checkButtonBackground(window.button("Subtract"), new Color(204, 204, 204));
		window.button(withText("=")).click();
		checkButtonBackground(window.button("Add"), new Color(204, 204, 204));
	}

	@Test
	public void testSubtractionButtonState() {
		window.button("Subtract").click();
		checkButtonBackground(window.button("Subtract"), new Color(255, 153, 0));
		checkButtonBackground(window.button("Add"), new Color(204, 204, 204));
		window.button(withText("=")).click();
		checkButtonBackground(window.button("Subtract"), new Color(204, 204, 204));
	}

	@Test
	public void testSquareButtonState() {
		window.button(withText("x²")).click();
		checkButtonBackground(window.button(withText("x²")), new Color(255, 153, 0));
		checkButtonBackground(window.button(withText("√")), new Color(204, 204, 204));
		window.button(withText("=")).click();
		checkButtonBackground(window.button(withText("x²")), new Color(204, 204, 204));
	}

	@Test
	public void testMemoryAddAndRecall() {
		window.button(withText("2")).click();
		window.button("Add").click();
		window.button(withText("3")).click();
		window.button(withText("=")).click();
		window.textBox().requireText("5");
		window.button(withText("M+")).click();
		window.button(withText("DEL")).click();
		window.textBox().requireText("0");
		window.button(withText("MR")).click();
		window.textBox().requireText("5");
	}

	@Test
	public void testMemorySubtract() {
		window.button(withText("5")).click();
		window.button("Add").click();
		window.button(withText("5")).click();
		window.button(withText("=")).click();
		window.button(withText("M+")).click(); // Memory = 10
		// window.button(withText("C")).click();
		window.button(withText("2")).click();
		window.button("Add").click();
		window.button(withText("2")).click();
		window.button(withText("=")).click();
		window.button(withText("M-")).click(); // Memory = 10 - 4 = 6
		// window.button(withText("C")).click();
		window.button(withText("MR")).click();
		window.textBox().requireText("6");
	}

	@Test
	public void testMemoryAddNonResult() {
		window.button(withText("1")).click();
		window.button(withText("2")).click();
		window.button(withText("3")).click();
		window.button(withText("M+")).click();
		window.textBox().requireText("Error: Only results can be added to memory");
	}

	@Test
	public void testDivisionByZero() {
		window.button(withText("1")).click();
		window.button(withText("0")).click();
		window.button(withText("÷")).click();
		window.button(withText("0")).click();
		window.button(withText("=")).click();
		window.textBox().requireText("Error: Division by zero");
	}

	@Test
	public void testSquareRootOfNegative() {
		window.button(withText("2")).click();
		window.button("Subtract").click();
		window.button(withText("5")).click();
		window.button(withText("=")).click();
		window.textBox().requireText("-3");
		window.button(withText("√")).click();
		window.button(withText("=")).click();
		window.textBox().requireText("Error: Cannot take square root of negative number");
	}

	@Test
	public void testDelete() {
		window.button(withText("1")).click();
		window.button(withText("2")).click();
		window.button(withText("3")).click();
		window.button(withText("4")).click();
		window.textBox().requireText("1234");
		window.button(withText("DEL")).click();
		window.textBox().requireText("123");
		window.button(withText("DEL")).click();
		window.textBox().requireText("12");
	}

	@Test
	public void testClear() {
		window.button(withText("1")).click();
		window.button("Add").click();
		window.button(withText("2")).click();
		window.button(withText("=")).click();
		window.textBox().requireText("3");
		window.button(withText("C")).click();
		window.textBox().requireText("0");
		checkButtonBackground(window.button("Add"), new Color(204, 204, 204));
	}
}