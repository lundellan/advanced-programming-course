package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ClearButton extends JButton implements ActionListener {
	private GUI gui;
	
	public ClearButton(GUI gui) {
		super("Clear");
		this.gui = gui;
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		gui.sheetClear();
	}
}
