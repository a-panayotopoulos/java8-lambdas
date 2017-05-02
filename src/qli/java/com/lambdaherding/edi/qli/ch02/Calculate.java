package com.lambdaherding.edi.qli.ch02;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Stack;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * This stack-based calculator can take a sequence of numbers and apply simple operations
 * to them.
 * 
 * E.g. entering "45.7,6,4*-" would give 45.7 - ( 6 * 4 ) = 21.7
 * 
 * Can we simplify this with the use of lambdas?
 */
public class Calculate extends JFrame {
	private static final long serialVersionUID = 3809823621528950529L;
	
	private JTextField display;
	private StringBuilder number = new StringBuilder();
	private Stack<Double> data = new Stack<>();

	public Calculate() {
		super( "My Calculator" );
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		setLayout( new BorderLayout() );
		
		display = new JTextField();
		display.setEditable( false );
		add( display, BorderLayout.NORTH );
		
		JPanel buttonPanel = makeButtons();
		add( buttonPanel, BorderLayout.CENTER );
		
		pack();
	}

	private JPanel makeButtons() {
		JPanel pan = new JPanel();
		pan.setLayout(new GridLayout(5, 5));

		Arrays.asList( 
				inputButton("7"), inputButton("8"), inputButton("9"),
				binaryActionButton("/", (x, y) -> x / y), unaryActionButton("%", x -> x / 100),
				inputButton("4"), inputButton("5"), inputButton("6"), 
				binaryActionButton("*", (x, y) -> x * y), unaryActionButton("1/x", x -> 1 / x),
				inputButton("1"), inputButton("2"), inputButton("3"), 
				binaryActionButton("-", (x, y) -> x - y), unaryActionButton("+/-", x -> -x),
				commaButton(), inputButton("0"), inputButton("."), 
				binaryActionButton("+", (x, y) -> x + y), unaryActionButton("\u221a", x -> Math.sqrt(x)),
				clearAll("ClearAll")
				).forEach( b -> pan.add(b) );		
		return pan;
	}

	public JButton jButton(String label, ActionListener l) {
		JButton button = new JButton(label);
		button.setActionCommand(label);
		button.addActionListener(l);
		return button;
	}

	public JButton clearAll(String label) {
		return jButton(label, (evt) -> {
			display.setText("");
			data.clear();
			number = new StringBuilder();
		});
	}
	
	public JButton inputButton(String label) {
		return jButton(label, (evt) -> {
			addToDisplay(evt.getActionCommand());
			number.append(evt.getActionCommand());
		});
	}

	public JButton commaButton() {
		return jButton(",", evt -> {
			if (doParse()) {
				addToDisplay(",");
			}
		});
	}

	private JButton unaryActionButton(String label, UnaryOperator<Double> unaryOp) {
		return jButton(label, evt -> {
			if (number.length() > 0) {
				if (!doParse()) {
					return;
				}
			}

			if (data.empty()) {
				JOptionPane.showMessageDialog(null, "No numbers on stack",
						"Empty stack", JOptionPane.ERROR_MESSAGE);
			} else {
				double result = unaryOp.apply(data.pop());
				data.push(result);
				deleteLastNumber();
				addToDisplay(result + ",");
			}
		});
	}
	
	private JButton binaryActionButton(String label, BinaryOperator<Double> binaryOp) {
		return jButton(label, evt -> {
			if (number.length() > 0) {
				if (!doParse()) {
					return;
				}
			}

			if (data.size() < 2) {
				JOptionPane.showMessageDialog(null, "Not enough numbers on stack", "Stack too small", JOptionPane.ERROR_MESSAGE);
			} else {
				double result = binaryOp.apply(data.pop(), data.pop());
				data.push(result);
				deleteLastNumber();
				deleteLastNumber();
				addToDisplay(result + ",");
			}
		});
	}
	
	private boolean doParse() {
		try {
			data.push( Double.parseDouble( number.toString() ) );
			return true;
		}
		catch ( NumberFormatException e ) {
			JOptionPane.showMessageDialog( null, "Number '" + number.toString() + "' is not valid",
					"Bad number", JOptionPane.ERROR_MESSAGE );
			
			deleteLastNumber();
			return false;
		}
		finally {
			number = new StringBuilder();
		}
	}

	private void addToDisplay( String str ) {
		display.setText( display.getText() + str );
	}
	
	private void deleteLastNumber() {
		String disp = display.getText();
		
		if ( disp.endsWith( "," ) ) {
			disp = disp.substring( 0, disp.length() - 1 );
		}
		
		display.setText( disp.substring( 0, disp.lastIndexOf( ',' ) + 1 ) );
	}
	
	public static void main( String[] args ) {
		new Calculate().setVisible( true );
	}
}
