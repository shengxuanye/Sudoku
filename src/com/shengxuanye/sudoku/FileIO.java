package com.shengxuanye.sudoku;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class FileIO {
	
	private final int SIZE = 9; 
	
	private String filePath;

	public FileIO(String filePath) {
		super();
		this.filePath = filePath;
	} 
	
	public Sudoku readFile() {
		
		ArrayList<int[]> rawData = new ArrayList<int[]>(); 
		try {
		    BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));
		    String line;
		    while((line = br.readLine()) != null){
		    	String[] nums = line.split(","); 
		    	int[] ints = new int[nums.length];
		    	for (int i = 0; i < nums.length; i++) {
		    		ints[i] = Integer.parseInt(nums[i]); 
		    	}
		    	rawData.add(ints);
		    }
		    br.close(); 
		} catch (Exception e) {
			System.err.println("File reading error"); 
			System.exit(-1); 
		}
		
		if (rawData.size() < SIZE) {
			System.err.println("Not enough rows");
			System.exit(-1);
		} else if (rawData.size() > SIZE) {
			System.out.println("Additional lines will be discarded"); 
		}
		
		int[][] parsedData = new int[9][9]; 
		
		for (int i = 0; i < SIZE; i++) {
			if (rawData.get(i).length >= SIZE) {
				for (int j = 0; j < SIZE; j++) {
					parsedData[i][j] = rawData.get(i)[j]; 
				}
				if (rawData.get(i).length > SIZE) System.out.println("Additional columns will be discarded"); 
			} else {
				System.err.println("Not enough columns");
				System.exit(-1);
			}
		}
		
		return new Sudoku(parsedData); 
		
	}
	
}
