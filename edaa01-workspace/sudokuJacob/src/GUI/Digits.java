package GUI;

import java.awt.Font;

import javax.swing.*;
import javax.swing.text.*;

public class Digits extends JTextField {
	
	public Digits()	{
		super("");
		((AbstractDocument) this.getDocument()).setDocumentFilter(new InputFilter());
	}
	
	private class InputFilter extends DocumentFilter	{
		InputFilter() {
			super();
			setHorizontalAlignment(JTextField.CENTER);
			setFont(new Font(getFont().getName(), Font.BOLD, 30));
		}
		
		@Override
		public void replace(FilterBypass fb, int offset, int length, String str, AttributeSet attr) throws BadLocationException {
			if ((fb.getDocument().getLength() + str.length() - length) > 1) {
				return;
			}
			if (!str.isEmpty() && (!Character.isDigit(str.charAt(0)) || str.charAt(0) == '0')) {
				return;
			}
			fb.replace(offset, length, str, attr);
		}
	}
	
}
