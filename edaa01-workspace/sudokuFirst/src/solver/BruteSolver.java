package solver;

import java.util.*;

public class BruteSolver implements Solver {
	private int[][] table;

	/**
	 * Creates new solver object.
	 */
	public BruteSolver() {

	}

	/**
	 * Sets new puzzle for class to handle.
	 * 
	 * @param sudoku
	 *            The new puzzle.
	 */
	public void newPuzzle(int[][] sudoku) {
		table = sudoku;
	}

	/**
	 * Returns the current state of the puzzle.
	 * 
	 * @return Current state of puzzle.
	 */
	public int[][] getResult() {
		return table;
	}

	/**
	 * Initiates solution process in class, first checking for obvious errors
	 * followed by trying to brute force the solution.
	 * 
	 * @return True if the puzzle was solved, otherwise false.
	 */
	public boolean solve() {
		if (checkNoDuplicates()) {
			return solve(0, 0);
		}
		return false;
	}

	/**
	 * Recursive method for solving Sudoku puzzle. If this returns true then the
	 * private variable table will have changed.
	 * 
	 * @param x
	 *            The x coordintate in the puzzle to be handled.
	 * @param y
	 *            The y coordintate in the puzzle to be handled.
	 * 
	 * @return True if there is atleast one solution to the puzzle, otherwise
	 *         false.
	 */
	private boolean solve(int x, int y) {
		if (x == 8 && y == 8) {
			if (table[y][x] > 0) {
				return checkSquare(x, y);
			} else {
				for (int i = 1; i < 10; i++) {
					table[y][x] = i;
					if (checkSquare(x, y)) {
						return true;
					}
				}
				table[y][x] = 0;
				return false;
			}
		} else {
			if (table[y][x] > 0) {
				if (checkSquare(x, y)) {
					int newX = x;
					int newY = y;
					newX += 1;
					if (newX == 9) {
						newX = 0;
						newY += 1;
					}
					return solve(newX, newY);
				} else {
					return false;
				}
			} else {
				for (int i = 1; i < 10; i++) {
					boolean solved = false;
					table[y][x] = i;
					if (checkSquare(x, y)) {
						int newX = x;
						int newY = y;
						newX += 1;
						if (newX == 9) {
							newX = 0;
							newY += 1;
						}
						solved = solve(newX, newY);
						if (solved) {
							return true;
						}
					}
				}
				table[y][x] = 0;
				return false;
			}
		}
	}

	/**
	 * Wrapper method for all controls needing to be done while solving.
	 * 
	 * @param x
	 *            The x coordintate in the puzzle to be handled.
	 * @param y
	 *            The y coordintate in the puzzle to be handled.
	 * 
	 * @return True if the current number in (x,y) doesn't conflict with the
	 *         puzzle, otherwise false.
	 */
	private boolean checkSquare(int x, int y) {
		return checkToXRow(x, y) && checkToYRow(x, y) && checkToBox(x, y);
	}

	/**
	 * Checks current square on sudoku board towards its horizontal row
	 * eliminating all nonpossible alternatives from the possibilities list.
	 * 
	 * @param x
	 *            The x coordinate of the square in the 2D array to be examined.
	 * @param y
	 *            The y coordinate of the square in the 2D array to be examined.
	 *            
	 * @return True if there is no duplicate on same x row, otherwise false  
	 */
	private boolean checkToXRow(int x, int y) {
		for (int xIndex = 0; xIndex < 9; xIndex++) {
			if (x != xIndex) {
				if (table[y][x] == table[y][xIndex]) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Checks current square on sudoku board towards its vertical row
	 * eliminating all nonpossible alternatives from the possibilities list.
	 * 
	 * @param x
	 *            The x coordinate of the square in the 2D array to be examined.
	 * @param y
	 *            The y coordinate of the square in the 2D array to be examined.
	 *            
	 * @return	True if there is no duplicate on same y row, otherwise false
	 */
	private boolean checkToYRow(int x, int y) {
		for (int yIndex = 0; yIndex < 9; yIndex++) {
			if (y != yIndex) {
				if (table[y][x] == table[yIndex][x]) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Checks current square on sudoku board towards its own 3x3 square
	 * eliminating all nonpossible alternatives from the possibilities list.
	 * 
	 * @param x
	 *            The x coordinate of the square in the 2D array to be examined.
	 * @param y
	 *            The y coordinate of the square in the 2D array to be examined.
	 *            
	 * @return True if there is no duplicate in the same 3x3 region, otherwise false
	 */
	private boolean checkToBox(int x, int y) {
		int xStart = ((x / 3) * 3);
		int yStart = ((y / 3) * 3);
		for (int yIndex = yStart; yIndex < yStart + 3; yIndex++) {
			for (int xIndex = xStart; xIndex < xStart + 3; xIndex++) {
				if (x == xIndex && y == yIndex) {
					// Don't want to check vs own square
				} else {
					if (table[y][x] == table[yIndex][xIndex]) {
						return false;
					}
				}
			}
		}
		return true;
	}

	/**
	 * Checks current board for obvious errors. Errors founds are in the nature
	 * of duplicates on x or y rows or in the same board region.
	 * 
	 * @return True if none of these cases appear, otherwise false.
	 */
	private boolean checkNoDuplicates() {
		for (int y = 0; y < 9; y++) {
			Set<Integer> setX = new TreeSet<Integer>();
			Set<Integer> setY = new TreeSet<Integer>();
			for (int x = 0; x < 9; x++) {
				if (table[y][x] != 0) {
					if (!setX.add(table[y][x])) {
						return false;
					}
				}
				if (table[x][y] != 0) {
					if (!setY.add(table[x][y])) {
						return false;
					}
				}
				if (x % 3 == 0 && y % 3 == 0) {
					Set<Integer> setBox = new TreeSet<Integer>();
					for (int i = y; i < y + 3; i++) {
						for (int j = x; j < x + 3; j++) {
							if (table[i][j] != 0) {
								if (!setBox.add(table[i][j])) {
									return false;
								}
							}
						}
					}
				}
			}
		}
		return true;
	}

	/**
	 * Prints current state of puzzle.
	 */
	public void print() {
		for (int y = 0; y < 9; y++) {
			System.out.print("[");
			for (int x = 0; x < 9; x++) {
				System.out.print(table[y][x] + ", ");
			}
			System.out.print("],");
			System.out.println();
		}
	}
}
