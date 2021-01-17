



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
import javax.swing.JScrollPane;

public class CalculatorFrame extends JFrame {

	public JTextArea display;
	private boolean newValue; 
	JScrollPane scroll;
	
	private double lastNumber;
	private String lastOperator = new String("");
	private String curVal= new String("");
	 
	private static final int FRAME_WIDTH = 200;
	private static final int FRAME_HEIGHT = 300;
	
	//if (newValue = true) {
	//	System.out.println();
	//}
 
	// LISTENERS
	
	class DigitButtonListener implements ActionListener {
		
		private String digit;
		
		public DigitButtonListener (String d) {
			digit = d;
		}
		
		public void actionPerformed(ActionEvent event) {
			
			curVal += digit;
			System.out.printf("Digit is : %s; Value in the text area is %s, Cur Val = %s \n", digit, display.getText(), curVal);
			
			display.setText(display.getText() + digit);
		}
	}
	
	
	class OperationButtonListener implements ActionListener{
		private String mOper;
		
		public OperationButtonListener(String op) {
			mOper = op;
		}
		
		public void actionPerformed(ActionEvent event) {
				// Check if Last operator is empty or calculate
				if (lastOperator.isEmpty()) {
					//move current value to array
					lastNumber = Double.parseDouble(curVal);
				}
				else {
					lastNumber = calclateWithOper(lastNumber,Double.parseDouble(curVal),lastOperator);
					if (!mOper.equals("=")){
					display.setText(display.getText() +"=\n"+lastNumber);	
					lastOperator = "";						
					}

					//display.setText(display.getText() + "\n"+lastNumber);
					//curVal = String.valueOf(lastNumber);
				}
				
				//Set Current value to empty
				if (mOper.equals("=")) {
				   display.setText(display.getText() + mOper+"\n"+lastNumber +"\n");
				   lastOperator = "";
				   //curVal = "0";

				}
				else {
					display.setText(display.getText() + mOper+"\n");
					lastOperator = mOper;	

					}
				
				
				curVal = "0";
				
		}
	}
	
	
	class ClearAllButtonListener implements ActionListener{
		
		public void actionPerformed(ActionEvent event) {
			display.setText("");
			lastNumber = 0;
			lastOperator = "";
			curVal= "0";
		}
	}
	
	class ClearLastButtonListener implements ActionListener{
		
		public void actionPerformed (ActionEvent event) {
			
			int length = display.getText().length();
			int num = display.getText().length() - 1;
			String sto;
			lastOperator = "";
			if (length > 0) {
				StringBuilder back = new StringBuilder(display.getText());
				// check if the last element is new line
				if ('\n' == back.charAt(num)) {
					// This only happens if the last character was an operator
					back.deleteCharAt(num);
					num = num - 1;
					// Because the last value is an operator replace curValue with lastValue
					// And set last value to 0;
					curVal = String.valueOf(lastNumber);
					lastNumber = 0;
				}
				back.deleteCharAt(num);
				sto = back.toString();
				//curVal = sto;
				display.setText(sto);
			}
		}
	}
	
	// BUTTONS 
	public JButton DigitButton(String d) {
		JButton digitButton = new JButton(d);
		ActionListener digitListener = new DigitButtonListener(d);
		digitButton.addActionListener(digitListener);
		return digitButton;
		
	}
	public double calclateWithOper(double a, double b, String oper) {
		double result = 0;
		switch(oper) {
		case "+":
			result = a+b;
			break;
		case "-":
			result = a-b;
			break;
		case "/":
			// check for divide by 0
			if (b == 0) {
				display.append("\n ERROR! \n");
			}
			else
			result = a/b;
			break;
		case "*":
			result = a*b;
			break;
		case "=":
			result = a ; // A is the last number
			break;
		case ".":
			{
				if(display.getText().isEmpty())
				{
					// SHOW ERROR ! CANT BE EMPty !
				}
				else
				{
					int lengthOfdisplay = display.getText().length();
					if(lengthOfdisplay > 1)
					{
						char theLastchar = display.getText().charAt(lengthOfdisplay-1);
						char oneBeforelastchar = display.getText().charAt(lengthOfdisplay-2);
						if(!Character.isDigit(theLastchar))
						{
							display.append("\n ERROR! \n");
							// DO THIS ....
						}
						else
						{
							// ADD THE DOT ( . )s
							display.append(".");
						}
					}
					else
					{
						// OK if length is not bigger than 1 that means we could have number in display so
						// we add the dot
						// ADD THE DOT
						// for other signs you should have something like this always check if display is empty
					}
				}


			}
		}
		return result;
	}
	// BUTTONS 
	public JButton OperatorButton(String op) {
		JButton OperatorButton = new JButton(op);
		ActionListener OperatorListener = new OperationButtonListener(op);
		OperatorButton.addActionListener(OperatorListener);
		return OperatorButton;
			
	}
	
	public JButton ClearAllButton() {
		JButton clearAllButton = new JButton("Clear All");
		ActionListener clearAllListener = new ClearAllButtonListener();
		clearAllButton.addActionListener(clearAllListener);
		return clearAllButton;
	}
	
	public JButton ClearLastButton() {
		JButton clearLastButton = new JButton("Clear Last");
		ActionListener clearLastListener = new ClearLastButtonListener();
		clearLastButton.addActionListener(clearLastListener);
		return clearLastButton;
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
		buttons.add(OperatorButton("."));
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
	
	public CalculatorFrame() {
		
		makeClearButtons();
		makeButtons();
		
		newValue = true;
		
		display = new JTextArea(""); 
		//display.setAlignmentX(JTextArea.RIGHT_ALIGNMENT);
		//display.setAlignmentY(JTextArea.BOTTOM_ALIGNMENT);
		add(display, BorderLayout.CENTER);
		scroll = new JScrollPane(display, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		add(scroll);
		
		 
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setResizable(false);
		
	}
		//public static void main(String [] args) {
		
		//JFrame frame = new CalculatorFrame();
	    //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    //frame.setTitle("Calculator");
	    //frame.setVisible(true);
	//}
	}