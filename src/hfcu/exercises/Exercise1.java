package hfcu.exercises;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * This is the class for exercise 1.
 * It takes a CSV file containing temperature information and converts it to a data set.
 * It outputs information for the day with the smallest and largest temperature spread.
 * @author Scott Trevett
 *
 */
public class Exercise1 {
	ArrayList<DailyTemp> dataSet = new ArrayList<>();

	public Exercise1(String inputFileLocation) throws Exception {
		if ( inputFileLocation == null) {
			throw new FileNotFoundException();
		}
		// read and parse the input file
		readInputFile(inputFileLocation);
		// sort the resulting data set to uncover min and max spreads
		findMinMaxSpread();
	}
	
	// read in input file
	public void readInputFile(String fileName) throws Exception {
		File input = new File(fileName);
		// ensure file exists before attempting to parse
		if ( !input.exists() ) {
			throw new FileNotFoundException();
		}
		Scanner scan = new Scanner(input);
		scan.useDelimiter("\n");
		// for each line of input, create a DailyTemp object and add it to the data set
		while (scan.hasNext()) {
			String line = scan.next();
			DailyTemp day = new DailyTemp(line);
			dataSet.add(day);
		}
		scan.close();
	}
	
	public void findMinMaxSpread() {
		Collections.sort(dataSet);
		// find minimum spread
		int minSpread = dataSet.get(0).tempSpread;
		int maxSpread = dataSet.get(dataSet.size() - 1).tempSpread;
		for ( DailyTemp day : dataSet ) {
			if ( day.tempSpread == minSpread ) {
				System.out.println("Date with smallest temperature spread:");
				System.out.println(day.getOutput());
			}
		}
		for ( DailyTemp day : dataSet ) {
			if ( day.tempSpread == maxSpread ) {
				System.out.println("Date with smallest temperature spread:");
				System.out.println(day.getOutput());
			}
		}
	}
}

class DailyTemp implements Comparable<DailyTemp> {
	// fields from input
	String date;
	int minTemp;
	int maxTemp;
	
	// calculated fields
	int tempSpread;
	
	public DailyTemp (String input) throws Exception {
		// check to see if the input string is null
		if ( input == null) {
			throw new Exception("Input cannot be null");
		}
		String[] parts = input.split(",");
		// check to see if the minimum number of fields was provided
		if (parts.length < 3 ) {
			throw new Exception ("Input does not have the correct number of fields.");
		}
		
		// set fields
		this.date = parts[0];
		this.maxTemp = Integer.valueOf(parts[1]);
		this.minTemp = Integer.valueOf(parts[2]);
		
		// calculate temp spread and set field
		this.tempSpread = maxTemp - minTemp;
		
	}
	
	public String getOutput() {
		StringBuilder output = new StringBuilder();
		output.append("Date: " + date);
		output.append("\nMaximum daily temperature: " + maxTemp);
		output.append("\nMinimum daily temperature: " + minTemp);
		output.append("\nTemperature Spread: " + getTempSpread());
		output.append("\n");
		return output.toString();
	}
	
	public int getTempSpread() {
		return this.tempSpread;
	}

	@Override
	public int compareTo(DailyTemp other) {
		return Integer.compare(this.getTempSpread(), ((DailyTemp)other).getTempSpread());
	}
}
