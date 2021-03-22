package GUI;

import java.awt.*;

import javax.swing.*;

public class CommandPanel extends JPanel {
	
	/**
	 * Creates JPanel containing a {@link ClearButton} and a {@link SolveButton}
	 * @param gui
	 */
	public CommandPanel(SudokuGUI gui) {
		setLayout(new FlowLayout());
		add(new ClearButton(gui));
		add(new SolveButton(gui));
	}
}
