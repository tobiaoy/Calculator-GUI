import javax.swing.*;
import java.awt.*;

public class CalculatorUIView {

	public static void main (String[] args) {
		JFrame Calculator = new CalculatorFrame();
		Calculator.setTitle("Calculator");
		Calculator.setVisible(true);
		
		Calculator.setDefaultCloseOperation(Calculator.EXIT_ON_CLOSE);
	}
}