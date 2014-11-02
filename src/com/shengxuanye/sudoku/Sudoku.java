package com.shengxuanye.sudoku;

import java.util.HashSet;
import java.util.Set;

public class Sudoku {
	
	private final int SIZE = 9; 
	
	private int[][] data = new int[SIZE][SIZE];		// data [row][col]
	private int[][] units = new int[SIZE*3][SIZE]; 	// this is to store items in row(0-2)-column(3-5)-boxes(6-8), thus each unit only contain 1-9 -> make the things easier

	public Sudoku() {
		super(); 
		for (int[] list : data) {
			for (int i = 0; i < list.length; i++) {
				list[i] = 0; 
			}
		}
		syncUnits(); 
	}
	
	public Sudoku(int[][] data) {
		super();
		this.data = data;
		syncUnits(); 
	} 
	
	public Sudoku(Sudoku s) {
		this.data = s.data;
		syncUnits(); 
	}
	
	
	public boolean isValid() {
		// all list items must 0 - 9
		for (int[] list : data) {
			for (int item : list) {
				if (item <= 9 && item >= 0) {
					continue;
				} else {
					return false; 
				} 
			}
		}	
		
		// all units must be unique or 0 
		for (int i = 0; i < SIZE*3; i++) {
			Set<Integer> uniqueKey = new HashSet<Integer>(); 
			int count = SIZE; 
			for (int j = 0; j < SIZE; j++) {
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
	
	public boolean setSingleItem(int row, int col, int value) {
		if (row >=0 && row <= 8 && col >= 0 && row <= 8 && value >=0 && value <= 9) {
			data[row][col] = value; 
			syncUnits(); 
			return true; 
		}
		return false; 	
	}
	
	public boolean resetSingleItem(int row, int col) {
		return setSingleItem(row, col, 0); 
	}
	
	public boolean isSolved() {
		for (int[] row : data) {
			for (int i : row) {
				if (i == 0) return false;
			}
		}
		return true; 
	}
	
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
	
	private void syncUnits() {
		for (int i = 0; i < SIZE; i++) {
			// row
			units[i] = data[i];
			// column
			for (int j = 0; j < SIZE; j++) {
				units[i+SIZE][j] = data[j][i]; 
			}
		}
		// boxes
		for (int i = 0; i < SIZE; i++) {
			int rStart = i / 3 * 3; 
			int cStart = i % 3 * 3; 
			int l = 0; 
			for (int j = rStart; j < rStart + 3; j++) {
				for (int k = cStart; k < cStart + 3; k++) {
					units[i+SIZE*2][l] = data[j][k]; 
					l++; 
				}
			}
		}
	}
	
	public int[][] getData() {
		return data; 
	}
	
	public int[][] getUnits() {
		return units; 
	}
	

}
