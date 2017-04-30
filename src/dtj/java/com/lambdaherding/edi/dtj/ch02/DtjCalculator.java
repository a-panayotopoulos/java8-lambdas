package com.lambdaherding.edi.dtj.ch02;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.UnaryOperator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * There's possibly some simplification possible between UnaryListener and
 * BinaryListerer but the F1 is about to start.
 */
public class DtjCalculator extends JFrame {
	private static final long serialVersionUID = 3809823621528950529L;

	private JTextField display;
	private StringBuilder number = new StringBuilder();
	private Stack<Double> data = new Stack<>();

	public DtjCalculator() {
		super("My Calculator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setLayout(new BorderLayout());

		display = new JTextField();
		display.setEditable(false);
		add(display, BorderLayout.NORTH);

		JPanel buttonPanel = makeButtons();
		add(buttonPanel, BorderLayout.CENTER);

		pack();
	}

	private JPanel makeButtons() {
		JPanel pan = new JPanel();
		pan.setLayout(new GridLayout(4, 5));

		pan.add(inputButton("7"));
		pan.add(inputButton("8"));
		pan.add(inputButton("9"));
		pan.add(binaryActionButton("/", (v1, v2) -> 1 / v1 * v2));
		pan.add(unaryActionButton("%", value -> value / 100));

		pan.add(inputButton("4"));
		pan.add(inputButton("5"));
		pan.add(inputButton("6"));
		pan.add(binaryActionButton("*", (v1, v2) -> v1 * v2));
		pan.add(unaryActionButton("1/x", value -> 1 / value));

		pan.add(inputButton("1"));
		pan.add(inputButton("2"));
		pan.add(inputButton("3"));
		pan.add(binaryActionButton("-", (v1, v2) -> -1 * v1 + v2));
		pan.add(unaryActionButton("+/-", value -> -1 * value));

		pan.add(commaButton());
		pan.add(inputButton("0"));
		pan.add(inputButton("."));
		pan.add(binaryActionButton("+", (v1, v2) -> v1 + v2));
		pan.add(unaryActionButton("\u221a", value -> Math.sqrt(value)));
		return pan;
	}

	public JButton inputButton(String label) {
		JButton button = new JButton(label);
		button.setActionCommand(label);
		button.addActionListener(evt -> {
			addToDisplay(evt.getActionCommand());
			number.append(evt.getActionCommand());
		});
		return button;
	}

	public JButton commaButton() {
		JButton button = new JButton(",");
		button.addActionListener((evt) -> {
			if (doParse()) {
				addToDisplay(",");
			}
		});

		return button;
	}

	private JButton unaryActionButton(String label, UnaryOperator<Double> op) {
		JButton button = new JButton(label);
		UnaryListener listener = new UnaryListener(op);
		button.addActionListener(listener);
		return button;
	}

	private JButton binaryActionButton(String label, BinaryOperator<Double> op) {
		JButton button = new JButton(label);
		BinaryListener listener = new BinaryListener(op);
		button.addActionListener(listener);
		return button;
	}

	private boolean doParse() {
		try {
			data.push(Double.parseDouble(number.toString()));
			return true;
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Number '" + number.toString() + "' is not valid", "Bad number",
					JOptionPane.ERROR_MESSAGE);

			deleteLastNumber();
			return false;
		} finally {
			number = new StringBuilder();
		}
	}

	private void addToDisplay(String str) {
		display.setText(display.getText() + str);
	}

	private void deleteLastNumber() {
		String disp = display.getText();

		if (disp.endsWith(",")) {
			disp = disp.substring(0, disp.length() - 1);
		}

		display.setText(disp.substring(0, disp.lastIndexOf(',') + 1));
	}

	public class UnaryListener implements ActionListener {
		private Function<Double, Double> op;

		public UnaryListener(Function<Double, Double> op) {
			this.op = op;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			if (number.length() > 0) {
				if (!doParse()) {
					return;
				}
			}

			if (data.empty()) {
				JOptionPane.showMessageDialog(null, "No numbers on stack", "Empty stack", JOptionPane.ERROR_MESSAGE);
			} else {
				double result = op.apply(data.pop());
				data.push(result);
				deleteLastNumber();
				addToDisplay(result + ",");
			}
		}
	}

	public class BinaryListener implements ActionListener {
		private BinaryOperator<Double> op;

		public BinaryListener(BinaryOperator<Double> op) {
			this.op = op;
		}

		@Override
		public void actionPerformed(ActionEvent evt) {
			if (number.length() > 0) {
				if (!doParse()) {
					return;
				}
			}

			if (data.size() < 2) {
				JOptionPane.showMessageDialog(null, "Not enough numbers on stack", "Stack too small",
						JOptionPane.ERROR_MESSAGE);
			} else {
				double result = op.apply(data.pop(), data.pop());

				data.push(result);
				deleteLastNumber();
				deleteLastNumber();
				addToDisplay(result + ",");
			}
		}
	}

	public static void main(String[] args) {
		new DtjCalculator().setVisible(true);
	}
}
