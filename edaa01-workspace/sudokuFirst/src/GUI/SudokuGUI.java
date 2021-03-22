package GUI;

import java.awt.*;

import javax.swing.*;

import solver.Solver;

public class SudokuGUI {
	private JPanel gamePanel;
	private Solver solver;
	private JFrame frame;
	private OneDigitField[][] box;

	/**
	 * Creates new gui object.
	 */
	public SudokuGUI(Solver solver) {
		this.solver = solver;
		frame = new JFrame("Sudoku");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 600);
		frame.setResizable(false);
		box = new OneDigitField[9][9];

		gamePanel = new GamePanel(box);
		frame.add(gamePanel);
		
		JPanel buttonField = new CommandPanel(this);
		frame.add(buttonField, BorderLayout.SOUTH);

		frame.setVisible(true);
	}

	/**
	 * Reads current input on puzzle board and stores in a 2D array.
	 * 
	 * @return 2D array containing current board state.
	 */
	public int[][] to2DArray() {
		int[][] table = new int[9][9];
		for (int i = 0; i < 81; i++) {
			int x = i % 9;
			int y = i / 9;
			if (box[y][x].getText().isEmpty()) {
				table[y][x] = 0;
			} else {
				table[y][x] = new Integer(box[y][x].getText());
			}
		}
		return table;
	}

	/**
	 * Clears entire board.
	 */
	public void clearPuzzle() {
		for (int i = 0; i < 81; i++) {
			int x = i % 9;
			int y = i / 9;
			box[y][x].setText("");
		}
		gamePanel.repaint();
	}

	/**
	 * Fills current board with numbers in supplied 2D array.
	 * 
	 * @param table
	 *            Representation of puzzle boards numbers
	 */
	public void fillPuzzle(int[][] table) {
		for (int i = 0; i < 81; i++) {
			int x = i % 9;
			int y = i / 9;
			box[y][x].setText(new Integer(table[y][x]).toString());
		}
	}

	/**
	 * Repaints panel containing game board
	 */
	public void repaint() {
		gamePanel.repaint();
	}

	/**
	 * Returns puzzle solver
	 * 
	 * @return Returns puzzle solver
	 */
	public Solver getSolver() {
		return solver;
	}

	/**
	 * Shows message informing that the puzzle is unsolvable.
	 */
	public void failedMessage() {
		JOptionPane.showMessageDialog(frame, "Puzzle is unsolvable!");
	}
}
