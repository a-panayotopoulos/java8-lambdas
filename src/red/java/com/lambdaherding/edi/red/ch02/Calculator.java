package com.lambdaherding.edi.red.ch02;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.Stack;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculator extends JFrame {

	private static final long serialVersionUID = 3809823621528950529L;

	private JTextField display;
	private StringBuilder number = new StringBuilder();
	private Stack<Double> data = new Stack<>();

	public Calculator() {
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
		pan.add( binaryActionButton( "/", (a, b) -> 1 / a * b ) );
		pan.add( unaryActionButton( "%", x -> x / 100 ) );

		pan.add( inputButton( "4" ) );
		pan.add( inputButton( "5" ) );
		pan.add( inputButton( "6" ) );
		pan.add( binaryActionButton( "*", (a, b) -> a * b ) );
		pan.add( unaryActionButton( "1/x", x -> 1 / x ) );

		pan.add( inputButton( "1" ) );
		pan.add( inputButton( "2" ) );
		pan.add( inputButton( "3" ) );
		pan.add( binaryActionButton( "-", (a, b) -> -1 * a + b ) );
		pan.add( unaryActionButton( "+/-", x -> -1 * x ) );

		pan.add( commaButton() );
		pan.add( inputButton( "0" ) );
		pan.add( inputButton( "." ) );
		pan.add( binaryActionButton( "+", (a, b) -> a + b ) );
		pan.add( unaryActionButton( "\u221a", Math::sqrt ) );
		return pan;
	}

	public JButton inputButton( String label ) {
		JButton button = new JButton( label );
		button.setActionCommand( label );
		button.addActionListener( event -> addToDisplay( event.getActionCommand() ) );
		button.addActionListener( event -> number.append( event.getActionCommand() ) );
		return button;
	}

	public JButton commaButton() {
		JButton button = new JButton( "," );
		button.addActionListener( event -> {
			if ( doParse() )
				addToDisplay( "," );
		} );

		return button;
	}

	private JButton unaryActionButton( String label, UnaryOperator<Double> op ) {
		JButton button = new JButton( label );
		button.setActionCommand( label );
		button.addActionListener( event -> onUnaryAction( op ) );
		return button;
	}

	private JButton binaryActionButton( String label, BinaryOperator<Double> op ) {
		JButton button = new JButton( label );
		button.setActionCommand( label );
		button.addActionListener( event -> onBinaryAction( op ) );
		return button;
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

	public void onUnaryAction( UnaryOperator<Double> op ) {
		if ( number.length() > 0 ) {
			if ( !doParse() ) {
				return;
			}
		}

		if ( data.empty() ) {
			JOptionPane.showMessageDialog( null, "No numbers on stack", "Empty stack", JOptionPane.ERROR_MESSAGE );
		}
		else {
			double result = op.apply( data.pop() );
			data.push( result );
			deleteLastNumber();
			addToDisplay( result + "," );
		}
	}

	public void onBinaryAction( BinaryOperator<Double> op ) {
		if ( number.length() > 0 ) {
			if ( !doParse() )
				return;
		}

		if ( data.size() < 2 ) {
			JOptionPane.showMessageDialog( null, "Not enough numbers on stack", "Stack too small",
				JOptionPane.ERROR_MESSAGE );
		}
		else {
			double result = op.apply( data.pop(), data.pop() );
			data.push( result );
			deleteLastNumber();
			deleteLastNumber();
			addToDisplay( result + "," );
		}
	}

	public static void main( String[] args ) {
		new Calculator().setVisible( true );
	}
}
