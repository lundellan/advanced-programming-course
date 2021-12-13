package sudoku;

import javax.swing.*;

public class SudokuApplication {
    private static SudokuController controller;
    private static SudokuView view;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {

        }

        view = new SudokuView();
        controller = new SudokuController(view);
    }
}
