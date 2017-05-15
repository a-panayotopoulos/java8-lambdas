package com.lambdaherding.edi.ch02;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

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
		pan.setLayout( new GridLayout( 4, 5 ) );
		
		pan.add( inputButton( "7" ) );
		pan.add( inputButton( "8" ) );
		pan.add( inputButton( "9" ) );
		pan.add( binaryActionButton( "/" ) );
		pan.add( unaryActionButton( "%" ) );
		
		pan.add( inputButton( "4" ) );
		pan.add( inputButton( "5" ) );
		pan.add( inputButton( "6" ) );
		pan.add( binaryActionButton( "*" ) );
		pan.add( unaryActionButton( "1/x" ) );
		
		pan.add( inputButton( "1" ) );
		pan.add( inputButton( "2" ) );
		pan.add( inputButton( "3" ) );
		pan.add( binaryActionButton( "-" ) );
		pan.add( unaryActionButton( "+/-" ) );

		pan.add( commaButton() );
		pan.add( inputButton( "0" ) );
		pan.add( inputButton( "." ) );
		pan.add( binaryActionButton( "+" ) );
		pan.add( unaryActionButton( "\u221a" ) );
		return pan;
	}
	
	private ActionListener inputListener = new ActionListener() {
		@Override
		public void actionPerformed( ActionEvent evt ) {
			addToDisplay( evt.getActionCommand() );
			number.append( evt.getActionCommand() );
		}
	};
	
	public JButton inputButton( String label ) {
		JButton button = new JButton( label );
		button.setActionCommand( label );
		button.addActionListener( inputListener );
		return button;
	}
	
	public JButton commaButton() {
		JButton button = new JButton( "," );
		button.addActionListener( new ActionListener() {
			@Override
			public void actionPerformed( ActionEvent evt ) {
				if ( doParse() ) {
					addToDisplay( "," );
				}
			}
		});
		
		return button;
	}
	
	private JButton unaryActionButton( String label ) {
		JButton button = new JButton( label );
		button.setActionCommand( label );
		button.addActionListener( unaryOperationListener );
		return button;
	}
	
	private JButton binaryActionButton( String label ) {
		JButton button = new JButton( label );
		button.setActionCommand( label );
		button.addActionListener( binaryOperationListener );
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
	
	private ActionListener unaryOperationListener = new ActionListener() {
		@Override
		public void actionPerformed( ActionEvent evt ) {
			if ( number.length() > 0 ) {
				if ( !doParse() ) {
					return;
				}
			}
			
			if ( data.empty() ) {
				JOptionPane.showMessageDialog( null, "No numbers on stack", "Empty stack", JOptionPane.ERROR_MESSAGE );
			}
			else {
				double result;
				
				switch ( evt.getActionCommand() ) {
				case "%":
					result = data.pop() / 100;
					break;
				case "1/x":
					result = 1 / data.pop();
					break;
				case "+/-":
					result = -1 * data.pop();
					break;
				case "\u221a":
					result = Math.sqrt( data.pop() );
					break;
				default:
					throw new IllegalStateException( "Don't know how to deal with operator '" + evt.getActionCommand() + '\'' );
				}
				
				data.push( result );
				deleteLastNumber();
				addToDisplay( result + "," );
			}
		}
	};
	
	private ActionListener binaryOperationListener = new ActionListener() {
		@Override
		public void actionPerformed( ActionEvent evt ) {
			if ( number.length() > 0 ) {
				if ( !doParse() ) {
					return;
				}
			}
			
			if ( data.size() < 2 ) {
				JOptionPane.showMessageDialog( null, "Not enough numbers on stack", "Stack too small", JOptionPane.ERROR_MESSAGE );
			}
			else {
				double result;
				
				switch ( evt.getActionCommand() ) {
				case "+":
					result = data.pop() + data.pop();
					break;
				case "-":
					result = -1 * data.pop() + data.pop();
					break;
				case "*":
					result = data.pop() * data.pop();
					break;
				case "/":
					result = 1 / data.pop() * data.pop();
					break;
				default:
					throw new IllegalStateException( "Don't know how to deal with operator '" + evt.getActionCommand() + '\'' );
				}
				
				data.push( result );
				deleteLastNumber();
				deleteLastNumber();
				addToDisplay( result + "," );
			}
		}
	};
	
	public static void main( String[] args ) {
		new Calculate().setVisible( true );
	}
}
