package test;

import static org.junit.jupiter.api.Assertions.*;
import sudoku.*;

import java.util.Arrays;

class SudokuSolverTest {
    private int[][] sudokuBasic = {
            {0,0,8,0,0,9,0,6,2},
            {0,0,0,0,0,0,0,0,5},
            {1,0,2,5,0,0,0,0,0},
            {0,0,0,2,1,0,0,9,0},
            {0,5,0,0,0,0,6,0,0},
            {6,0,0,0,0,0,0,2,8},
            {4,1,0,6,0,8,0,0,0},
            {8,6,0,0,3,0,1,0,0},
            {0,0,0,0,0,0,4,0,0}
    };
    private int[][] sudokuBasicSolved = {
            {5,4,8,1,7,9,3,6,2},
            {3,7,6,8,2,4,9,1,5},
            {1,9,2,5,6,3,8,7,4},
            {7,8,4,2,1,6,5,9,3},
            {2,5,9,3,8,7,6,4,1},
            {6,3,1,9,4,5,7,2,8},
            {4,1,5,6,9,8,2,3,7},
            {8,6,7,4,3,2,1,5,9},
            {9,2,3,7,5,1,4,8,6}
    };
    private int[][] sudokuImpossible = {
            {8,6,8,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0}
    };
    /**
     * SudokuHard taken from
     * http://www.mathsphere.co.uk/downloads/sudoku/10203-hard.pdf
     */
    private int[][] sudokuHard = {
            {0,0,0,0,0,0,0,0,2},
            {0,0,0,0,0,0,9,4,0},
            {0,0,3,0,0,0,0,0,5},
            {0,9,2,3,0,5,0,7,4},
            {8,4,0,0,0,0,0,0,0},
            {0,6,7,0,9,8,0,0,0},
            {0,0,0,7,0,6,0,0,0},
            {0,0,0,9,0,0,0,2,0},
            {4,0,8,5,0,0,3,6,0}
    };
    private int[][] sudokuHardSolved = {
            {6,8,4,1,5,9,7,3,2},
            {7,5,1,8,3,2,9,4,6},
            {9,2,3,6,7,4,1,8,5},
            {1,9,2,3,6,5,8,7,4},
            {8,4,5,2,1,7,6,9,3},
            {3,6,7,4,9,8,2,5,1},
            {2,3,9,7,4,6,5,1,8},
            {5,1,6,9,8,3,4,2,7},
            {4,7,8,5,2,1,3,6,9},
    };
    private SudokuSolver solver;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        solver = new Solver(sudokuBasic);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        solver = null;
    }

    @org.junit.jupiter.api.Test
    void add() {
        solver.add(0,0,1);
        assertEquals(Integer.valueOf(1),solver.get(0,0));
    }

    @org.junit.jupiter.api.Test
    void isValid() {
        solver.add(0,0,1);
        assertEquals(Integer.valueOf(1),solver.get(0,0));
    }

    @org.junit.jupiter.api.Test
    void get() {
        solver.add(4,0,2);
        assertEquals(Integer.valueOf(2), solver.get(4,0));
    }

    @org.junit.jupiter.api.Test
    void remove() {
        solver.remove(1,3);
        assertEquals(Integer.valueOf(0), solver.get(1,3));
    }

    @org.junit.jupiter.api.Test
    void clear() {
        solver.clear();
        assertTrue(Arrays.deepEquals(solver.getMatrix(),new int[9][9]), "Didn't clear properly!");
    }
    
    @org.junit.jupiter.api.Test
    void getMatrix() {
        assertEquals(solver.getMatrix(), sudokuBasic);
    }

    @org.junit.jupiter.api.Test
    void setMatrix() {
        solver.setMatrix(sudokuHard);
        assertEquals(solver.getMatrix(), sudokuHard);
    }
    
    @org.junit.jupiter.api.Test
    void solveEmpty() {
        solver.setMatrix(new int[9][9]);
        assertTrue(Arrays.deepEquals(solver.getMatrix(),new int[9][9]), "Didn't solve correctly!");
    }

    @org.junit.jupiter.api.Test
    void solveBasic() {
        assertTrue(solver.solve(), "Didn't return true after solve!");
        assertTrue(Arrays.deepEquals(solver.getMatrix(),sudokuBasicSolved), "Didn't solve correctly!");
    }

    @org.junit.jupiter.api.Test
    void solveImpossible() {
        solver = new Solver(sudokuImpossible);
        assertFalse(solver.solve(), "Did not return false on impossible sudoku");

    }
}
