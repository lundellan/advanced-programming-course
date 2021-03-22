package application;

import GUI.SudokuGUI;
import solver.BruteSolver;
import solver.OptimizedSolver;
import solver.Solver;

public class SudokuSolver {
	public static void main(String[] args) {
		Solver solver;
		solver = new BruteSolver();
//		solver = new OptimizedSolver();
		new SudokuGUI(solver);
	}
}
