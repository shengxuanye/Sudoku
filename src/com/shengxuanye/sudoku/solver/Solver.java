package com.shengxuanye.sudoku.solver;

import java.util.ArrayList;
import com.shengxuanye.sudoku.Sudoku;

/**
 * Solver interface. 
 * @author Shengxuan
 */

public interface Solver {
	
	public ArrayList<Sudoku> solve(Sudoku s); 

}
