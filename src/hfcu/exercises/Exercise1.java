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
	
	/**
	 * Reads in the input CSV file and builds the data set of DailyTemp objects.
	 * @param fileName The fully qualified name of the input file.
	 * @throws FileNotFoundException Throws this when the file specified does not exist. 
	 * @throws Exception Throws this when errors occur parsing the input and translating this into a DailyTemp object
	 */
	public void readInputFile(String fileName) throws FileNotFoundException, Exception {
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
	
	/**
	 * Traverses through the sorted data set to find all instances that match the minimum and maximum spread. 
	 */
	public void findMinMaxSpread() {
		Collections.sort(dataSet);
		// find minimum spread
		int minSpread = dataSet.get(0).tempSpread;
		int maxSpread = dataSet.get(dataSet.size() - 1).tempSpread;
		for ( DailyTemp day : dataSet ) {
			if ( day.tempSpread == minSpread ) {
				System.out.println("Date(s) with smallest temperature spread:");
				System.out.println(day.getOutput());
			}
		}
		for ( DailyTemp day : dataSet ) {
			if ( day.tempSpread == maxSpread ) {
				System.out.println("Date(s) with smallest temperature spread:");
				System.out.println(day.getOutput());
			}
		}
	}
}

/**
 * This class defines the object consisting of daily temperature data
 * @author Scott Trevett
 *
 */
class DailyTemp implements Comparable<DailyTemp> {
	// fields from input
	String date; // the date of the measurements
	int minTemp; // the minimum temperature on the date
	int maxTemp; // the maximum temperature on the date
	
	// calculated fields
	int tempSpread; // calculated difference between the min and max temperatures
	
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
	
	/**
	 * Builds output string for object
	 * @return String of relevant object data
	 */
	public String getOutput() {
		StringBuilder output = new StringBuilder();
		output.append("Date: " + date);
		output.append("\nMaximum daily temperature: " + maxTemp);
		output.append("\nMinimum daily temperature: " + minTemp);
		output.append("\nTemperature Spread: " + tempSpread);
		output.append("\n");
		return output.toString();
	}
	
	/**
	 * Compares the temperature spread field (tempSpread)
	 */
	@Override
	public int compareTo(DailyTemp other) {
		return Integer.compare(this.tempSpread, ((DailyTemp)other).tempSpread);
	}
}
