package application;

import solver.Solver;
import GUI.GUI;

public class SudokuApp {
	public static void main(String[] args)	{
	Solver solver = new Solver();
	new GUI(solver);
	}
}
