package sudoku;

public interface SudokuSolver {
	/**
	 * Solves the sudoku
	 * @return returns true if sudoku is solvable false if not
	 * 
	 */
	boolean solve();

	/**
	 * Puts digit in the box row, col.
	 * 
	 * @param row   The row
	 * @param col   The column
	 * @param digit The digit to insert in box row, col
	 * @throws IllegalArgumentException if row, col or digit is outside the range
	 *                                  [0..9]
	 */
	void add(int row, int col, int digit);

	/**
	 *Removes number at position row, col
	 * 
	 * @param row		The row
	 * @param col		The column
	 * @throws IllegalArgumentException if number is outside [1..9] or row or col is
	 *                                  outside [0..8]
	 */
	void remove(int row, int col);

	/**
	 *Gets number at position row, col
	 *
	 * @param row		The row
	 * @param col		The column
	 * @throws IllegalArgumentException if number is outside [1..9] or row or col is
	 *                                  outside [0..8]
	 */
	int get(int row, int col);

	/**
	 * Checks that all filled in digits follows the the sudoku rules.
	 */
	boolean isValid(int row, int col, int number);

	/**
	 * Clears the entire sudoku grid
	 *
	 */
	void clear();

	/**
	 * Fills the grid with the digits in m. The digit 0 represents an empty box.
	 * 
	 * @param m the matrix with the digits to insert
	 * @throws IllegalArgumentException if m has the wrong dimension or contains
	 *                                  values outside the range [0..9]
	 */
	void setMatrix(int[][] m);

	/**
	 *	@return returns the current sudoku grid
	 */
	int[][] getMatrix();
}