package GUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.*;
import javax.swing.text.*;

public class OneDigitField extends JTextField {

	/**
	 * Creates a text field to display only one digit.
	 */
	public OneDigitField() {
		super("");
		((AbstractDocument) this.getDocument())
				.setDocumentFilter(new OneDigitFilter());
	}

	/**
	 * Checks input and hinders it if its not a digit between 1 and 9.
	 */
	private class OneDigitFilter extends DocumentFilter {
		OneDigitFilter() {
			super();
			setHorizontalAlignment(JTextField.CENTER);
			setFont(new Font(getFont().getName(), Font.BOLD, 30));
		}

		public void replace(FilterBypass fb, int offset, int length,
				String str, AttributeSet attr) throws BadLocationException {
			if ((fb.getDocument().getLength() + str.length() - length) > 1) {
				return;
			}
			if (!str.isEmpty()
					&& (!Character.isDigit(str.charAt(0)) || str.charAt(0) == '0')) {
				return;
			}
			fb.replace(offset, length, str, attr);
		}
	}

}
