package com.shengxuanye.sudoku;

import java.util.ArrayList;
import com.shengxuanye.sudoku.solver.DFSSolver;
import com.shengxuanye.sudoku.solver.Solver;

/**
 * This class gives some examples for the Sudoku solver.
 * Example files are in ./data. Note that the 16x16 Sudoku takes a long time to run.  
 * @author Shengxuan Ye
 */

public class Examples {

	public static void main(String[] args) {
		
		ArrayList<String> fileNames = new ArrayList<String>();
		fileNames.add("./data/case_example.txt"); 
		fileNames.add("./data/case_with_multple_solutions.txt"); 
		fileNames.add("./data/case_with_no_solution.txt"); 
		//fileNames.add("./data/case_large.txt"); 
		
		//int[] sudokuSize = {9, 9, 9, 16}; 
		int[] sudokuSize = {9, 9, 9}; 
		

		for (int i = 0; i < fileNames.size(); i++) {
			String fileName = fileNames.get(i);
			FileIO fio = new FileIO(fileName, sudokuSize[i]); 

			Sudoku s = fio.readFile(); 

			Solver sv = new DFSSolver();
			ArrayList<Sudoku> al = sv.solve(s);

			for (int j = 0; j < al.size(); j++) {
				printGrid(al.get(j).getData()); 
				System.out.println(); 
			}

			fio.writeFile(al); 
		}

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
