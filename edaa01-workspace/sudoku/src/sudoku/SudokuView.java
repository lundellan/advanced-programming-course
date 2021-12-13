package sudoku;

import javax.swing.*;

import java.awt.*;


/**
 * <code>SudokuView</code> controls how the view is presented to the user. It handles the Swing components and layout.
 */
public class SudokuView {

    private JButton solve, clear;
    private SudokuGrid grid;
    private JFrame frame;
    private final Color bgColor = new Color(251, 252, 253);

    public SudokuView() {
        SwingUtilities.invokeLater(() -> createWindow("Sudoku Solver", 540, 680));
    }

    private void createWindow(String title, int width, int height) {
        //New JFrame, close when pressing the X button
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(width, height));
        frame.setResizable(true);

        //Mainpanel, sets background and layout
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(bgColor);
        mainPanel.setLayout(new BorderLayout());

        //Title label
        JLabel titleFrame = new JLabel("Sudoku Solver", SwingConstants.CENTER);
        titleFrame.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 36));
        titleFrame.setBackground(bgColor);

        mainPanel.add(titleFrame, BorderLayout.PAGE_START);

        //Add sudokugrid,Gridwrapper for correct aspect ratio of sudokugrid
        JPanel gridWrapper = new JPanel(new GridBagLayout());
        grid = new SudokuGrid();
        gridWrapper.add(grid);
        gridWrapper.setBackground(bgColor);
        
        mainPanel.add(gridWrapper, BorderLayout.CENTER);

        //Solve and clear button with correct layout.
        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.LINE_AXIS));
        solve = new JButton("Solve");
        clear = new JButton("Clear");
        buttons.add(solve);
        buttons.add(Box.createHorizontalGlue());
        buttons.add(clear);
        buttons.setBorder(BorderFactory.createEmptyBorder(32, 32, 32, 32));
        buttons.setBackground(bgColor);
        
        mainPanel.add(buttons, BorderLayout.PAGE_END);

        //Set content pane and pack
        frame.setContentPane(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * @return JButton for the solve button
     */
    public JButton getSolveButton() {
        return solve;
    }

    /**
     * @return JButton for the clear button
     */
    public JButton getClearButton() {
        return clear;
    }

    /**
     * Returns the sudoku grid from the view
     *
     * @return Sudoku grid
     */
    public JTextField[][] getNumberGrid() {
        return grid.getGrid();
    }

}