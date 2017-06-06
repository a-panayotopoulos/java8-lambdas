package com.lambdaherding.edi.axp.ch02;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Stack;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

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
		pan.setLayout( new GridLayout( 4, 5 ) );
		
		pan.add( inputButton( "7" ) );
		pan.add( inputButton( "8" ) );
		pan.add( inputButton( "9" ) );
		pan.add( binaryActionButton( "/", (a, b) -> a / b ) );
		pan.add( unaryActionButton( "%", d -> d / 100 ) );
		
		pan.add( inputButton( "4" ) );
		pan.add( inputButton( "5" ) );
		pan.add( inputButton( "6" ) );
		pan.add( binaryActionButton( "*", (a, b) -> a * b ) );
		pan.add( unaryActionButton( "1/x", d -> 1 / d ) );
		
		pan.add( inputButton( "1" ) );
		pan.add( inputButton( "2" ) );
		pan.add( inputButton( "3" ) );
		pan.add( binaryActionButton( "-", (a, b) -> a - b ) );
		pan.add( unaryActionButton( "+/-", d -> -d ) );

		pan.add( commaButton() );
		pan.add( inputButton( "0" ) );
		pan.add( inputButton( "." ) );
		pan.add( binaryActionButton( "+", (a, b) -> a + b ) );
		pan.add( unaryActionButton( "\u221a", d -> Math.sqrt( d ) ) );
		return pan;
	}
	
	private static JButton button( String label, ActionListener listener ) {
		JButton button = new JButton( label );
		button.addActionListener( listener );
		return button;
	}
	
	public JButton inputButton( String label ) {
		return button( label, e -> {
			addToDisplay( label );
			number.append( label );
		});
	}
	
	public JButton commaButton() {
		return button( ",", e -> {
				if ( doParse() ) {
					addToDisplay( "," );
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
	
	private JButton unaryActionButton( String label, UnaryOperator<Double> unaryFn ) {
		return button( label, e -> {
			if ( number.length() > 0 ) {
				if ( !doParse() ) {
					return;
				}
			}
			
			if ( data.empty() ) {
				JOptionPane.showMessageDialog( null, "No numbers on stack", "Empty stack", JOptionPane.ERROR_MESSAGE );
			}
			else {
				data.push( unaryFn.apply( data.pop() ) );
				deleteLastNumber();
				addToDisplay( data.peek() + "," );
			}
		});
	}
	
	private JButton binaryActionButton( String label, BinaryOperator<Double> binaryFn ) {
		return button( label, e -> {
			if ( number.length() > 0 ) {
				if ( !doParse() ) {
					return;
				}
			}
			
			if ( data.size() < 2 ) {
				JOptionPane.showMessageDialog( null, "Not enough numbers on stack", "Stack too small", JOptionPane.ERROR_MESSAGE );
			}
			else {
				double latter = data.pop();
				data.push( binaryFn.apply( data.pop(), latter ) );
				deleteLastNumber();
				deleteLastNumber();
				addToDisplay( data.peek() + "," );
			}
		});
	}
	
	public static void main( String[] args ) {
		new Calculate().setVisible( true );
	}
}
