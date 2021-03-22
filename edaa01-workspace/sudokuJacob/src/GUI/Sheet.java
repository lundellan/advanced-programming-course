package GUI;

import javax.swing.*;

import java.awt.*;

public class Sheet extends JPanel {
	
	public Sheet(Digits[][] input)	{
		setLayout(new GridLayout(9, 9));
		for (int i = 0; i < 81; i++) {
			int x = i % 9;
			int y = i / 9;
			input[y][x] = new Digits();
			if (orange(i)) {
				input[y][x].setBackground(Color.ORANGE);
			}
			this.add(input[y][x]);
		}
	}
	
	private boolean orange(int index) {
		if ((index / 27) % 2 == 0) {
			if ((index / 9) % 2 == 0) {
				if ((index / 3) % 2 == 0) {
					return true;
				}
			} else {
				if ((index / 3) % 2 == 1) {
					return true;
				}
			}
		} else {
			if ((index / 9) % 2 == 0) {
				if ((index / 3) % 2 == 1) {
					return true;
				}
			} else {
				if ((index / 3) % 2 == 0) {
					return true;
				}
			}
		}
		return false;
	}
	
}
