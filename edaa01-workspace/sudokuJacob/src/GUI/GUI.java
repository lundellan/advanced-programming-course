package GUI;

import java.awt.*;

import javax.swing.*;

import solver.Solver;

public class GUI {
	private Solver solver;
	private JFrame frame;
	private Digits[][] inputs;
	private JPanel sheet;
	
	public GUI(Solver solver)	{
		this.solver = solver;
		frame = new JFrame("Sudoku App");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 600);
		frame.setResizable(false);
		
		inputs = new Digits[9][9];
		sheet = new Sheet(inputs);
		frame.add(sheet);
		
		JPanel buttons = new Buttons(this);
		frame.add(buttons, BorderLayout.SOUTH);
		
		frame.setVisible(true);
	}
	
	public Solver getSolver() {
		return solver;
	}
	
	public int[][] sheetInput() {
		int[][] table = new int[9][9];
		for (int i = 0; i < 81; i++) {
			int x = i % 9;
			int y = i / 9;
			if (inputs[y][x].getText().isEmpty()) {
				table[y][x] = 0;
			} else {
				table[y][x] = new Integer(inputs[y][x].getText());
			}
		}
		return table;
	}
	
	public void sheetClear() {
		for (int i = 0; i < 81; i++) {
			int x = i % 9;
			int y = i / 9;
			inputs[y][x].setText("");
		}
		sheet.repaint();
	}
	
	public void sheetFill(int[][] table) {
		for (int i = 0; i < 81; i++) {
			int x = i % 9;
			int y = i / 9;
			inputs[y][x].setText(new Integer(table[y][x]).toString());
		}
	}
	
	public void sheetRepaint() {
		sheet.repaint();
	}
	
	public void sheetFail() {
		JOptionPane.showMessageDialog(frame, "Puzzle is unsolvable!");
	}
}
