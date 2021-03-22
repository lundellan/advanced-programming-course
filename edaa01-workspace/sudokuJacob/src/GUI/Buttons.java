package GUI;

import java.awt.*;

import javax.swing.*;

public class Buttons extends JPanel {
	
	public Buttons(GUI gui) {
		setLayout(new FlowLayout());
		add(new ClearButton(gui));
		add(new SolveButton(gui));
	}
	
}
