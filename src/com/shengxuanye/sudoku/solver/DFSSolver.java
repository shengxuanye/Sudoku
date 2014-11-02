package com.shengxuanye.sudoku.solver;

import com.shengxuanye.sudoku.Sudoku;

public class DFSSolver implements Solver {

	private final int SIZE = 9; 
	
	@Override
	public Sudoku solve(Sudoku s) {
		
		if (s == null) {
			return null; 
		}
		
		if (s.isSolved()) {
			return s; 
		} 
		
		if (s.getUnfilledLoc() == null) {
			return null; 
		}
		
		for (int i = 1; i <= SIZE; i++) {
			int[] loc = s.getUnfilledLoc(); 
			s.setSingleItem(loc[0], loc[1], i);
			if (s.isValid()) {
				Sudoku result = solve(s);
				if (result != null) {
					return result; 
				} else {
					s.resetSingleItem(loc[0], loc[1]); 
				}
			} else {
				s.resetSingleItem(loc[0], loc[1]); 
			}
		}
		
		return null;
	}

}
