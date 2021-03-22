package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import solver.Solver;

public class SolveButton extends JButton implements ActionListener {
	private SudokuGUI gui;

	/**
	 * Creates new button object.
	 */
	public SolveButton(SudokuGUI gui) {
		super("Solve");
		this.gui = gui;
		addActionListener(this);
		this.setToolTipText("Solves the sudoku");
	}

	/**
	 * ActionListener for SolveButton. If called, tries to solve puzzle
	 * currently presented on the puzzle board. If solved, prints solution,
	 * otherwise shows message telling solution is impossible.
	 */
	public void actionPerformed(ActionEvent e) {
		int[][] table = gui.to2DArray();
		Solver solver = gui.getSolver();
		solver.newPuzzle(table);
		if (!solver.solve()) {
			gui.failedMessage();
			return;
		}
		int[][] tableToPrint = solver.getResult();
		gui.fillPuzzle(tableToPrint);
	}
}
