package GUI;

import javax.swing.*;

import java.awt.*;

public class GamePanel extends JPanel {

	/**
	 * Creates JPanel containing a 9x9 grid of {@link OneDigitField}.
	 * 
	 * @param box
	 *            A empty 9x9 2D array to be filled with {@link GamePanel}
	 */
	public GamePanel(OneDigitField[][] box) {
		setLayout(new GridLayout(9, 9));
		for (int i = 0; i < 81; i++) {
			int x = i % 9;
			int y = i / 9;
			box[y][x] = new OneDigitField();
			if (isColored(i)) {
				box[y][x].setBackground(Color.LIGHT_GRAY);
			}
			this.add(box[y][x]);
		}
	}

	/**
	 * Calculates if the indexed square should be colored on graphical puzzle
	 * board.
	 * 
	 * @param index
	 *            The index to check the square against.
	 * 
	 * @return True if square should be colored, otherwise false.
	 */
	private boolean isColored(int index) {
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
