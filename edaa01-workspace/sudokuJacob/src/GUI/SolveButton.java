package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import solver.Solver;

public class SolveButton extends JButton implements ActionListener {
	private GUI gui;

	public SolveButton(GUI gui) {
		super("Solve");
		this.gui = gui;
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		int[][] table = gui.sheetInput();
		Solver solver = gui.getSolver();
		solver.newPuzzle(table);
		if (!solver.solve()) {
			gui.sheetFail();
			return;
		}
		int[][] resultTable = solver.getResult();
		gui.sheetFill(resultTable);
	}

}
