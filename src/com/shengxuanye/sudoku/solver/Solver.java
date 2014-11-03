package com.shengxuanye.sudoku.solver;

import java.util.ArrayList;
import com.shengxuanye.sudoku.Sudoku;

/**
 * Solver interface. Each solver takes in a Sudoku and outputs an ArrayList of solutions. 
 * @author Shengxuan Ye
 */

public interface Solver {
	
	public ArrayList<Sudoku> solve(Sudoku s); 

}
