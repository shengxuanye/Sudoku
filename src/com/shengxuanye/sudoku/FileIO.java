package com.shengxuanye.sudoku;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * File I/O helper function for Sudoku, including reader and writer. 
 * It reads and writes CSV files with comma delimiter. 
 * The function writes solutions with same path and append "_out_i.txt" to the original input filename, 
 * where i is each of the solution. 
 * @author Shengxuan Ye
 */

public class FileIO {
	
	private int fullSize; 
	private String filePath;

	public FileIO(String filePath, int size) {
		super();
		this.fullSize = size; 
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
		} catch (IOException ioe) {
			System.err.println("File reading error."); 
			System.exit(-1); 
		} catch (NumberFormatException nfe) {
			System.err.println("File content incorrect. (non-numbers?)");
			System.exit(-1);
		}
		
		if (rawData.size() < fullSize) {
			System.err.println("Not enough rows");
			System.exit(-1);
		} else if (rawData.size() > fullSize) {
			System.out.println("Additional lines will be discarded"); 
		}
		
		int[][] parsedData = new int[fullSize][fullSize]; 
		
		for (int i = 0; i < fullSize; i++) {
			if (rawData.get(i).length >= fullSize) {
				for (int j = 0; j < fullSize; j++) {
					parsedData[i][j] = rawData.get(i)[j]; 
				}
				if (rawData.get(i).length > fullSize) System.out.println("Additional columns will be discarded"); 
			} else {
				System.err.println("Not enough columns");
				System.exit(-1);
			}
		}
		
		return new Sudoku(parsedData); 
		
	}
	
	
	public boolean writeFile(ArrayList<Sudoku> al) {
		for (int i = 0; i < al.size(); i++) {
			try {
				BufferedWriter br = new BufferedWriter(new FileWriter(filePath+"_out_"+i+".txt"));
				StringBuilder sb = new StringBuilder();
				for (int[] line : al.get(i).getData()) {
					for (int element : line)
						sb.append(element+",");
					sb.deleteCharAt(sb.length()-1); 
					sb.append(System.getProperty("line.separator"));
				}
				br.write(sb.toString());
				br.close();
				System.out.println("File written at: "+filePath+"_out_"+i+".txt"); 
			} catch (IOException ioe) {
				System.err.println("File writing error"); 
			}
		}
		return true; 
	}
	
}
