package solver;

import java.util.*;

public class Solver implements SudokuSolver {
	private int[][] numbers;
	
	
	public Solver()	{
		numbers = new int[9][9];
	}

	@Override
	public void setNumber(int row, int col, int number) {
		numbers[row][col] = number;
	}

	@Override
	public boolean trySetNumber(int row, int col, int number) {
		return validateRows(row, col, number) && validateCols(row, col, number) && validateBox(row, col, number);
	}

	@Override
	public int getNumber(int row, int col) {
		return numbers[row][col];
	}

	@Override
	public void removeNumber(int row, int col) {
		// TODO Auto-generated method stub
	}

	@Override
	public void clear() {
		numbers = new int[9][9];
	}

	@Override
	public boolean solve() {
		if (checkNoDuplicates()) {
			return solve(0, 0);
		}
		return false;
	}
	
	private boolean solve(int x, int y) {
		if (x == 8 && y == 8) {
			if (this.numbers[y][x] > 0) {
				return trySetNumber(x, y);
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

	@Override
	public int[][] getNumbers() {
		return numbers;
	}

	@Override
	public void setNumbers(int[][] numbers) {
		this.numbers = numbers;
	}
	
	private boolean validateCols(int row, int col, int number) {
		for (int i = 0; i < 9; i++) {
			if (col != i) {
				if (number == this.numbers[row][i]) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean validateRows(int row, int col, int number) {
		for (int i = 0; i < 9; i++) {
			if (row != i) {
				if (number == this.numbers[i][col]) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean validateBox(int row, int col, int number) {
		int rowStart = ((row / 3) * 3);
		int colStart = ((col / 3) * 3);
		for (int i = rowStart; i < rowStart + 3; i++) {
			for (int j = colStart; j < colStart + 3; j++) {
				if (col == j && row == j) {
				} else {
					if (number == this.numbers[i][j]) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	private boolean checkNoDuplicates() {
		for (int y = 0; y < 9; y++) {
			Set<Integer> setX = new TreeSet<Integer>();
			Set<Integer> setY = new TreeSet<Integer>();
			for (int x = 0; x < 9; x++) {
				if (this.numbers[y][x] != 0) {
					if (!setX.add(this.numbers[y][x])) {
						return false;
					}
				}
				if (this.numbers[x][y] != 0) {
					if (!setY.add(this.numbers[x][y])) {
						return false;
					}
				}
				if (x % 3 == 0 && y % 3 == 0) {
					Set<Integer> setBox = new TreeSet<Integer>();
					for (int i = y; i < y + 3; i++) {
						for (int j = x; j < x + 3; j++) {
							if (this.numbers[i][j] != 0) {
								if (!setBox.add(this.numbers[i][j])) {
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
	
	
}
