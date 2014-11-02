package com.shengxuanye.sudoku;

import com.shengxuanye.sudoku.solver.DFSSolver;
import com.shengxuanye.sudoku.solver.Solver;

public class Tester {

	public static void main(String[] args) {
//		int[][] data = new int[9][9]; 
//		
//		int dd = 0; 
//		for (int[] l : data) {
//			for (int j = 0; j < l.length; j++) {
//				l[j] = dd;
//				dd++;
//			}
//		}
//		
//		Sudoku s = new Sudoku(data); 
//		s.setSingleItem(0, 0, 1);
//		
//		int[][] units = s.getUnits();
//		
//		printGrid(units); 
//		
//		System.out.println(""+s.isSolved()); 
		
		String fileName = "D:/Workspace/_sandbox/eclipse/Sudoku/data/testdata.txt";
		
		FileIO fio = new FileIO(fileName); 
		
		Sudoku s = fio.readFile(); 
		
		Solver sv = new DFSSolver();
		s = sv.solve(s);
		
		printGrid(s.getData()); 
		
		System.out.println(""+s.isValid()); 

	}
	
	
	
	
	
	public static void printGrid(int a[][])
	{
	   for(int i = 0; i < a.length; i++)
	   {
	      for(int j = 0; j < a[0].length; j++)
	      {
	         System.out.printf("%d\t", a[i][j]);
	      }
	      System.out.println();
	   }
	}

}
