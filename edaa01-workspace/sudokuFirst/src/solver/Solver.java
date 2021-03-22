package solver;

public interface Solver {
	public boolean solve();
	
	public void newPuzzle(int[][] sudoku);
	
	public void print();
	
	public int[][] getResult();
}
