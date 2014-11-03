package com.shengxuanye.sudoku.solver;

import java.util.ArrayList;
import com.shengxuanye.sudoku.Sudoku;

/**
 * This is a solver of Sudoku using DFS (recursion method). 
 * @author Shengxuan Ye
 */

public class DFSSolver implements Solver {
	
	@Override
	public ArrayList<Sudoku> solve(Sudoku s) {
		ArrayList<Sudoku> al = new ArrayList<Sudoku>();
		al = solveHelper(s, al); 
		return al; 
	}
	
	public ArrayList<Sudoku> solveHelper(Sudoku s, ArrayList<Sudoku> al) {
		if (s == null) return al; 
		if (s.isSolved() && s.isValid()) {
			al.add(new Sudoku(s));
			return al; 
		}
		
		for (int i = 1; i <= s.getSize(); i++) {
			int[] loc = s.getUnfilledLoc(); 
			s.setSingleItem(loc[0], loc[1], i);
			if (s.isValid()) {
				al = solveHelper(s, al); 
			}
			s.resetSingleItem(loc[0], loc[1]); 
		}
		
		return al; 
	}

}
