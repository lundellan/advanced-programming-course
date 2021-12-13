package sudoku;

import javax.swing.*;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;


/**
 * SudokuGrid is a JPanel that implements a 9x9 grid, for sudoku.
 */
public class SudokuGrid extends JPanel {
    private final Color innerBorder = new Color(0, 0, 0, 40);
    private final Color bgColor = new Color(251, 252, 253);
    private JTextField[][] inputFields;
    private JPanel[][] biggerBoxes;

    public SudokuGrid() {
        this.setLayout(new GridLayout(3, 3));

        inputFields = new JTextField[9][9];
        biggerBoxes = new JPanel[3][3];

        for (int i = 0; i < 9; i++) {
            for (int k = 0; k < 9; k++) {
                inputFields[i][k] = new JTextField();
                inputFields[i][k].setHorizontalAlignment(JTextField.CENTER);
                inputFields[i][k].setBackground(Color.WHITE);
                inputFields[i][k].setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));
                inputFields[i][k].setEditable(true);
            }
        }

        //Put smaller boxes in bigger boxes
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                biggerBoxes[i][k] = new JPanel();
                biggerBoxes[i][k].setBorder(BorderFactory.createLineBorder(innerBorder, 1));
                biggerBoxes[i][k].setLayout(new GridLayout(3, 3));
            }
        }

        for (int i = 0; i < 9; i++) {
            for (int k = 0; k < 9; k++) {
                int x = i / 3;
                int y = k / 3;

                //Set each other bigbox to a different color
                if ((x + y) % 2 == 0)
                    inputFields[i][k].setBackground(new Color(255, 165, 0, 255));
                biggerBoxes[x][y].add(inputFields[i][k]);

            }

        }
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                this.add(biggerBoxes[i][k]);
            }
        }

        this.setBorder(BorderFactory.createEmptyBorder(0, 20, 0, 20));
        this.setBackground(bgColor);
    }

    public JTextField[][] getGrid() {
        return inputFields;
    }

    public void clearGrid() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                inputFields[i][j].setText("");
            }
        }
    }

    public void setNumber(int row, int col, int number) {
        inputFields[row][col].setText(Integer.toString(number));
    }


    private static final long serialVersionUID = 1L;

    //Sets aspect ratio to be square
    @Override
    public Dimension getPreferredSize() {
        Dimension d = this.getParent().getSize();
        int newSize = d.width > d.height ? d.height : d.width;
        newSize = newSize == 0 ? 100 : newSize;
        return new Dimension(newSize, newSize);
    }

    //DocumentFilter for 1 char
    public class LimitDocumentFilter extends DocumentFilter {

        private int limit;

        public LimitDocumentFilter(int limit) {
            if (limit <= 0) {
                throw new IllegalArgumentException("Limit can not be <= 0");
            }
            this.limit = limit;
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            int currentLength = fb.getDocument().getLength();
            int overLimit = (currentLength + text.length()) - limit - length;
            if (overLimit > 0) {
                text = text.substring(0, text.length() - overLimit);
            }
            if (text.length() > 0) {
                super.replace(fb, offset, length, text, attrs);
                //.replaceAll("[^1-9\\s]", "")
            }
        }

    }


}
