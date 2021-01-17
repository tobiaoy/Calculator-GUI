package Atwo;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;



import javax.swing.JLabel;

public class Calculator extends JFrame{

	private JTextArea display;
	
	private double lastNumber; //last number 
	private String lastOperator = ""; // last operator
	private String curVal = ""; // current number 
	
	private static final int FRAME_WIDTH = 200;
	private static final int FRAME_HEIGHT = 300;

// LISTENERS 
	
	class DigitButtonListener implements ActionListener{
		
		private String digit;
		
		public DigitButtonListener (String d) {
			digit = d;
		}
			public void actionPerformed(ActionEvent event) {
			curVal += digit;  
			//if ("\n") {
			//	
			//}
			display.setText(display.getText() + digit);
		
		}
	} 
	
	class OperatorButtonListener implements ActionListener{
		private String op;
	
		public OperatorButtonListener (String oper) {
			op = oper;
			lastOperator = op;
			
			
			
			}
	
		public void actionPerformed(ActionEvent event) {
			if (lastOperator.isEmpty()) {
				//move current value to array
				lastNumber = Double.parseDouble(curVal);
			}
			else {
				lastNumber = arithmetic(lastNumber,Double.parseDouble(curVal),lastOperator);	
				//display.setText(display.getText() + "\n"+lastNumber);
				//curVal = String.valueOf(lastNumber);
			}
			
			//Set Current value to empty
			if (lastOperator == "=" ) {
			   display.setText(display.getText() + op +"\n"+lastNumber);

			}
			else {
				display.setText(display.getText() + op +"\n");	
			  
				 if (!(lastOperator.equals("="))&& curVal== "0") {
					display.setText(display.getText() +"=\n"+lastNumber);

				}
				//else {
				//}
			}
			lastOperator = op;
			curVal = "0";
			
	}
		
	}
	
	class ClearAllButtonListener implements ActionListener{
		public void actionPerformed(ActionEvent event) {
			display.setText("");
			lastNumber = 0;
			lastOperator = "";
			curVal = "";
		}
	}
	
	class ClearLastButtonListener implements ActionListener{
		public void actionPerformed (ActionEvent event) {
			int length = display.getText().length();
			int num = display.getText().length() - 1;
			String sto;
			
			if (length > 0) {
				
				StringBuilder back = new StringBuilder(display.getText());
				
				
				if ('\n' == back.charAt(num)) {
					
					num-=1;
					
					if ('=' == back.charAt(num - 1)) {
						display.setText(display.getText());
					}
					
					back.deleteCharAt(num);
				}
				
				back.deleteCharAt(num);
				sto = back.toString();
				
				//int curValLength = curVal.length();
				int curValnum = curVal.length() - 1;
				StringBuilder curVals = new StringBuilder(curVal);
				curVals.deleteCharAt(curValnum); 
				
				curVal = curVals.toString();
				
			}
		}
	}
	
	// METHODS 
	public double arithmetic(double a, double b, String op) {
		double ans = a;
		switch (op) {
		
		case "+":  // Addition
			ans = 	a + b;
			break;
		
		case "-":  // Subtraction
			ans = a - b;
			break;
			
		case "*":  // Multiplication
			ans = a * b;
			break;
			
		case "/":  // Division
			ans = a / b;
			if (b == 0) {  // If 'someone' tries to divide by zero
				display.setText(display.getText() + "\n Math Error \n");
			}
			break;
			
		case "=":
			ans = a;
			break;
		}
		return ans;
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
		ActionListener OperatorListener = new OperatorButtonListener(op);
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
	
	// CONSTRUCTOR
	public Calculator() {
		
		makeClearButtons();
		makeButtons();
		
		display = new JTextArea("");
		add(display, BorderLayout.CENTER);
		
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
	}
	
}

