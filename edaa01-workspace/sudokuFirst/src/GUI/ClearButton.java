package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ClearButton extends JButton implements ActionListener {
	private SudokuGUI gui;

	/**
	 * Creates button for clearing puzzle board.
	 * 
	 * @param gui
	 *            The GUI for the button to handle.
	 */
	public ClearButton(SudokuGUI gui) {
		super("Clear");
		this.gui = gui;
		addActionListener(this);
		this.setToolTipText("Clears the sudoku board");
	}

	/**
	 * ActionListener for ClearButton. Clears the puzzle board.
	 */
	public void actionPerformed(ActionEvent e) {
		gui.clearPuzzle();
	}

}
