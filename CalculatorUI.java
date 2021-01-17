import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;



import javax.swing.JLabel;

public class CalculatorUI extends JFrame {

	JTextArea display = new JTextArea();
	JScrollPane scroll;
	
	//String dis = ; // what is this??
	
	
	String [] disSplit = display.getText().split("\n"); // why is this needed?
	
	//private String number, lastNumber; // is number supposed to be the first number and the lastNumber be the second?
	//private boolean isOperator;
	
	//private String number = disSplit[disSplit.length - 1];
	//private String lastNumber = number;
	
	
	private String num1, num2 = null;
	private char op = ' ';
	
	private static final int FRAME_WIDTH = 200;
	private static final int FRAME_HEIGHT = 300;
	
	public CalculatorUI() {
		//display = new JTextArea();
		//dis =  display.getText();
		// disSplit = dis.split("\\n");
		
		scroll = new JScrollPane(display, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(scroll);
		
		makeClearButtons();
		makeButtons();
		 
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setResizable(false);
		// System.out.println(dis);
		
		// if (!dis.isEmpty()) {
	
	
		
		
		// if (disSplit.length > 1) {
		// 	number = disSplit[disSplit.length - 1];
		// 	lastNumber = disSplit[disSplit.length - 2];
		// }
			
		// }
	}
	
	
	class DigitButtonListener implements ActionListener {
		String digit;
		
		public DigitButtonListener(String d) {
			digit = d;
		}
		
		public void actionPerformed(ActionEvent event) {
			JButton b = (JButton) event.getSource();
			
			if(op == ' ') {
				// number is first one.
				if(num1 == null) {
					num1 = "".concat(b.getText());
			
				} else {
					num1 = num1.concat(b.getText());
				}
			} else {
				// number is second one.
				if(num2 == null) {
					num2 = "".concat(b.getText());
				} else {
					num2 = num2.concat(b.getText());
				}
			}
			
			display.append(b.getText());
			System.out.format("The first Number: %s, The Second Number: %s\n", num1, num2);
			
			// while (number.matches("^[0-9]*\\.[0.9]*$")){
			//	display.setText(display.getText());
			// } // why is this why needed?
			
			// number += digit;
			// display.append(digit);
			
			
			// System.out.println("This is the current number " + number);
			// System.out.println("This was the last number " + lastNumber);
			//display.setText(display.getText() + digit);
		
		}
	}
	
	class OperatorButtonListener implements ActionListener{
		char oper;
		
		public OperatorButtonListener (char op) {
			oper = op;
		}
		
		public void actionPerformed(ActionEvent event) {
			//JButton o = (JButton) event.getSource();
			
			if(op != '=') {
				op = oper;
			} else {
				// equal pressed so do the calc using num1 and num2 and the oper variable.
				// check here for non decimal numbers.
				display.append(String.valueOf(oper) + "\n");
				if(num1.matches("[0-9]+(.[0-9]+)?") && num2.matches("[0-9]+(.[0-9]+)?")) {
					// number is valid
					double result = arithmetic(Double.parseDouble(num1), Double.parseDouble(num2), op);
					String number = String.valueOf(result);
					 display.setText(display.getText() + "\n" + result);
				} else {
					// number invalid.
					display.setText("Invalid numbers entered");
				}
				
			}
			
			display.append(String.valueOf(op));
			
			
		}
			
	
	}
	
	class ClearAllButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			display.setText("");
			num1 = null;
			num2 = null;
		}
	}
	
	class ClearLastButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			display.setText(display.getText().substring(0, display.getText().length() - 1));
		}
	}
	
	// METHODS
	public double arithmetic (double a, double b, char op) {
		switch (op){
			case '+': 
				return a + b;
			case '-':
				return a - b;
			case '*':
				return a * b;
			case '/':
				return a / b;
			default:
				return -1;
		}
	}
	
	// BUTTONS
	
		public JButton DigitButton(String d) {  // Make a digit button
			JButton digitButton = new JButton(d);
			ActionListener digitListener = new DigitButtonListener(d);
			digitButton.addActionListener(digitListener);
			return digitButton;
		
		}
		
		public JButton OperatorButton(String op) {  // Make an operator button 
			JButton OperatorButton = new JButton(op);
			ActionListener OperatorListener = new OperatorButtonListener(op.charAt(0));
			OperatorButton.addActionListener(OperatorListener);
			return OperatorButton;
				
		}
		
		public JButton ClearAllButton() { // Make the Clear All Button
			JButton ClearAllButton = new JButton("Clear All");
			ActionListener ClearAllButtonListener = new ClearAllButtonListener();
			ClearAllButton.addActionListener(ClearAllButtonListener);
			return ClearAllButton;
		}
		
		public JButton ClearLastButton() { // Make the Clear Last Button 
			JButton ClearLastButton = new JButton("Clear Last");
			ActionListener ClearLastButtonListener = new ClearLastButtonListener();
			ClearLastButton.addActionListener(ClearLastButtonListener);
			return ClearLastButton;
		}
		
		private void makeButtons() {
			JPanel buttons = new JPanel();
			buttons.setLayout(new GridLayout(4,4));
			
			buttons.add(DigitButton("7"));
			buttons.add(DigitButton("8"));
			buttons.add(DigitButton("9"));
			buttons.add(OperatorButton("+"));
			
			buttons.add(DigitButton("4"));
			buttons.add(DigitButton("5"));
			buttons.add(DigitButton("6"));
			buttons.add(OperatorButton("-"));
			
			buttons.add(DigitButton("1"));
			buttons.add(DigitButton("2"));
			buttons.add(DigitButton("3"));
			buttons.add(OperatorButton("*"));
			
			buttons.add(DigitButton("0"));
			buttons.add(DigitButton("."));
			buttons.add(OperatorButton("="));
			buttons.add(OperatorButton("/"));
			
			add(buttons, BorderLayout.SOUTH);
			}
		
		private void makeClearButtons() {
			JPanel clearButtons = new JPanel();
			clearButtons.setLayout(new GridLayout(1,2));
			
			clearButtons.add(ClearAllButton());
			clearButtons.add(ClearLastButton());
			
			add(clearButtons, BorderLayout.NORTH);
		}
		
	
	
	
	
	
	}


