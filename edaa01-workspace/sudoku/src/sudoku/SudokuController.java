package sudoku;

import javax.swing.*;

/**
 * <code>SudokuController</code> is the controlling part of the MVC-structure.
 * It instantiates the SudokuView and controller and handles the communication inbetween.
 */
public class SudokuController {
    private SudokuView view;
    private SudokuSolver solver;

    public SudokuController(SudokuView view) {
        this.view = view;
        SwingUtilities.invokeLater(() -> initView());
    }

    private void initView() {
        //Set up buttons
        view.getSolveButton().addActionListener(e -> {
            //Try catch block for handling exceptions
            try {
                if (solver == null)
                    this.solver = new Solver(parseGrid());
                solver.setMatrix(parseGrid());

                //If solver returns false
                if (!solver.solve()) {
                    JOptionPane.showMessageDialog(null,
                            "Unsolvable sudoku!",
                            "Error",
                            JOptionPane.ERROR_MESSAGE);
                    //clearGrid();
                    return;
                }
                setGrid(solver.getMatrix());

            } catch (Exception ex) {
                String error = ex.getMessage();
                if (error == null)
                    error = "Inputs must be numbers 1-9 only!";
                JOptionPane.showMessageDialog(null,
                         error,
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }
        });

        //Clear button
        view.getClearButton().addActionListener(e -> {
            //System.out.println("Pressed clear");
            clearGrid();
        });
        //Set default numbers
        // setDefault();

    }

    private void clearGrid() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                JTextField[][] grid = view.getNumberGrid();
                grid[i][j].setText("");
            }
        }
    }

    /**
     * Sets default grid to one of the supplied arrays
     */
    private void setDefault() {
        int[][] sudokuBasic = {
                {8, 6, 0, 0, 2, 0, 0, 0, 0},
                {0, 0, 0, 7, 0, 0, 0, 5, 9},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 6, 0, 8, 0, 0},
                {0, 4, 0, 0, 0, 0, 0, 0, 0},
                {0, 0, 5, 3, 0, 0, 0, 0, 7},
                {0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 2, 0, 0, 0, 0, 6, 0, 0},
                {0, 0, 7, 5, 0, 9, 0, 0, 0}
        };
        int[][] sudokuHard = {
                {0, 0, 0, 0, 0, 0, 0, 0, 2},
                {0, 0, 0, 0, 0, 0, 9, 4, 0},
                {0, 0, 3, 0, 0, 0, 0, 0, 5},
                {0, 9, 2, 3, 0, 5, 0, 7, 4},
                {8, 4, 0, 0, 0, 0, 0, 0, 0},
                {0, 6, 7, 0, 9, 8, 0, 0, 0},
                {0, 0, 0, 7, 0, 6, 0, 0, 0},
                {0, 0, 0, 9, 0, 0, 0, 2, 0},
                {4, 0, 8, 5, 0, 0, 3, 6, 0}
        };
        setGrid(sudokuBasic);
    }


    /**
     * Sets view to display a int[][] grid
     *
     * @param grid to set view to
     */
    public void setGrid(int[][] grid) {
        JTextField[][] viewGrid = view.getNumberGrid();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                viewGrid[i][j].setText(Integer.toString(grid[i][j]));
            }
        }
    }


    /**
     * Tries to parse viewgrid
     *
     * @return int[9][9]
     * @throws Exception if input is invalid
     */
    private int[][] parseGrid() throws Exception {
        JTextField[][] grid = view.getNumberGrid();
        int[][] sudokuGrid = new int[9][9];
        for (int i = 0; i < 9; i++) {
            for (int k = 0; k < 9; k++) {
                if (!grid[i][k].getText().equals("")) {
                    try {
                        sudokuGrid[i][k] = Integer.parseInt(grid[i][k].getText().replaceAll("\\s+", ""));
                        if (sudokuGrid[i][k] == 0)
                            throw new Exception("Inputs must be numbers 1-9 only!");
                    } catch (Exception e) {
                        throw new Exception("Inputs must be numbers 1-9 only!");
                    }
                } else {
                    sudokuGrid[i][k] = 0;
                }
            }

        }
        return sudokuGrid;
    }
}
