package com.shengxuanye.sudoku;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Sudoku implements a Sudoku of any size with functions for the solver. 
 * Note that the size must be perfect square (4,9,16,25,etc).
 * 
 * @author Shengxuan Ye
 * 
 */

public class Sudoku {
	
	private int fullSize; 	// size of the Sudoku 
	private int sqrtSize; 	// sqrt size of the Sudoku
	
	private int[][] data; 	// data in [row][col]
	private int[][] units; 	// this is to store items in row(0-2)-column(3-5)-boxes(6-8), 
							// and each unit only contain 0 to fullSize integers

	
	/**
	 * Sudoku constructs: the object can be set up using default (9 by 9 with all 0s), or
	 * by copying a 2D array, or by copying another object
	 */
	
	public Sudoku() {
		super();
		
		fullSize = 9;
		sqrtSize = (int) Math.sqrt(fullSize); 
		
		data = new int[fullSize][fullSize];		
		units = new int[fullSize*sqrtSize][fullSize]; 
		
		for (int[] list : data) {
			for (int i = 0; i < list.length; i++) {
				list[i] = 0; 
			}
		}
		syncUnits(); 
	}
	
	
	public Sudoku(int[][] data) {
		super();
		
		this.data = deepCopy(data);
		
		fullSize = data.length; 
		sqrtSize = (int) Math.sqrt(fullSize);
		if (sqrtSize - Math.sqrt(fullSize) > 0.001) {
			System.err.println("Sudoku size error.");
			System.exit(-1);
		}
		
		units = new int[fullSize*sqrtSize][fullSize]; 
		syncUnits(); 
	} 
	
	
	public Sudoku(Sudoku s) {
		this.data = deepCopy(s.data);
		fullSize = s.data.length; 
		sqrtSize = (int) Math.sqrt(fullSize);
		units = new int[fullSize*sqrtSize][fullSize]; 
		syncUnits(); 
	}
	
	
	/**
	 * The following two method set a single cell to specific value, or reset a single cell. 
	 */
	
	public boolean setSingleItem(int row, int col, int value) {
		if (row >=0 && row <= fullSize-1 && col >= 0 && row <= fullSize-1 && value >=0 && value <= fullSize) {
			data[row][col] = value; 
			syncUnits(); 
			return true; 
		}
		return false; 	
	}
	
	
	public boolean resetSingleItem(int row, int col) {
		return setSingleItem(row, col, 0); 
	}
	
	
	/**
	 * The following method check the Sudoku is valid / is solved. 
	 * Note that is solved does not mean it is valid - it is simply all filled. 
	 */
	
	public boolean isValid() {
		// all list items must 0 - fullSize
		for (int[] list : data) {
			for (int item : list) {
				if (item <= fullSize && item >= 0) {
					continue;
				} else {
					return false; 
				} 
			}
		}	
		// all units must be unique or 0 
		for (int i = 0; i < fullSize*sqrtSize; i++) {
			Set<Integer> uniqueKey = new HashSet<Integer>(); 
			int count = fullSize; 
			for (int j = 0; j < fullSize; j++) {
				if (units[i][j] == 0) 
					count--;
				else 
					uniqueKey.add(units[i][j]);
			}
			if (uniqueKey.size() != count) {
				return false; 
			}
		}
		
		return true; 
	}
	
	
	public boolean isSolved() {
		for (int[] row : data) {
			for (int i : row) {
				if (i == 0) return false;
			}
		}
		return true; 
	}
	
	/**
	 * Getters and setters. 
	 * getUnfilledLoc() return the first cell that is 0; 
	 * getSize() return the size of the Sudoku. 
	 */
	
	public int[] getUnfilledLoc() {
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[0].length; j++) {
				if (data[i][j] == 0) {
					int[] r = {i, j};
					return r; 
				}; 
			}
		}
		return null; 
	}
	
	public int getSize() {
		return fullSize; 
	}
	
	public int[][] getData() {
		return data; 
	}
	
	public int[][] getUnits() {
		return units; 
	}
	
	
	/**
	 * Private methods 
	 * syncUnits() basically sync "data" with "units". Units is used to check whether the sudoku is valid. 
	 * Each unit has to have all unique values or 0s. 
	 * deepCopy() used to deep copy an array. 
	 */
	
	private void syncUnits() {
		for (int i = 0; i < fullSize; i++) {
			// row
			units[i] = data[i];
			// column
			for (int j = 0; j < fullSize; j++) {
				units[i+fullSize][j] = data[j][i]; 
			}
		}
		// boxes
		for (int i = 0; i < fullSize; i++) {
			int rStart = i / sqrtSize * sqrtSize; 
			int cStart = i % sqrtSize * sqrtSize; 
			int l = 0; 
			for (int j = rStart; j < rStart + sqrtSize; j++) {
				for (int k = cStart; k < cStart + sqrtSize; k++) {
					units[i+fullSize*(sqrtSize-1)][l] = data[j][k]; 
					l++; 
				}
			}
		}
	}
	
	private static int[][] deepCopy(int[][] original) {
	    if (original == null) {
	        return null;
	    }

	    int[][] result = new int[original.length][];
	    for (int i = 0; i < original.length; i++) {
	        result[i] = Arrays.copyOf(original[i], original[i].length);
	    }
	    return result;
	}
	

}
