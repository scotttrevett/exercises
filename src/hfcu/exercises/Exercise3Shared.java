package hfcu.exercises;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

/**
 * This is the class for exercise 3.
 * This is the shared code between exercises 1 and 2.
 * @author Scott Trevett
 *
 */
public class Exercise3Shared {
	/**
	 * @param fileName The fully qualified name of the input file
	 * @param delimiter The column delimiter used in the input file
	 * @param minNumFields The minimum number of columns needed for input
	 * @param field1 The column number for the first field to use in the comparison when sorting
	 * @param field2 The column number for the second field to use in the comparison when sorting
	 * @return A data set consisting of objects built from the input file.
	 */
	public static ArrayList<DataObject> readInputFile(String fileName, String delimiter, int minNumFields, int field1, int field2) throws Exception {
		ArrayList<DataObject> dataSet = new ArrayList<DataObject>();
		File input = new File(fileName);
		// ensure file exists before attempting to parse
		if ( !input.exists() ) {
			throw new FileNotFoundException ("Input file does not exist.");
		}
		Scanner scan = new Scanner(input);
		scan.useDelimiter("\n");
		// for each line of input, create a DailyTemp object and add it to the data set
		while (scan.hasNext()) {
			String line = scan.next();
			DataObject obj = new DataObject(line, delimiter, minNumFields, field1, field2);
			dataSet.add(obj);
		}
		scan.close();
		return dataSet;
	}
	
	/**
	 * Finds the data subset which has the smallest difference.
	 * @param dataSet The data set to sort and search through.
	 * @return A list of data objects with the smallest difference.
	 */
	public static ArrayList<DataObject> findMinimumDifference(ArrayList<DataObject> dataSet){
		Collections.sort(dataSet);
		ArrayList<DataObject> minimumObjects = new ArrayList<DataObject>();
		int minDiff = dataSet.get(0).fieldDiff;
		for ( DataObject obj : dataSet ) {
			if ( obj.fieldDiff == minDiff ) {
				minimumObjects.add(obj);
			}
		}
		return minimumObjects;
	}
	
	/**
	 * Finds the data subset which has the largest difference.
	 * @param dataSet The data set to sort and search through.
	 * @return A list of data objects with the largest difference.
	 */
	public static ArrayList<DataObject> findMaximumDifference(ArrayList<DataObject> dataSet){
		Collections.sort(dataSet);
		ArrayList<DataObject> maximumObjects = new ArrayList<DataObject>();
		int maxDiff = dataSet.get(dataSet.size()-1).fieldDiff;
		for ( DataObject obj : dataSet ) {
			if ( obj.fieldDiff == maxDiff ) {
				maximumObjects.add(obj);
			}
		}
		return maximumObjects;
	}
}



/**
 * This class defines the generic object from the input data files.
 * @author Scott Trevett
 *
 */
class DataObject implements Comparable<DataObject>{

	String inputLine;
	String[] inputFields;
	int compareField1;
	int compareField2;
	int fieldDiff;
	
	public DataObject (String input, String delimiter, int minNumFields, int field1, int field2) throws Exception{
		// check to see if the input string is null
		if ( input == null) {
			throw new Exception("Input cannot be null");
		}
		inputLine = input;
		inputFields = inputLine.split(delimiter);
		// check to see if the minimum number of fields was provided
		if (inputFields.length < minNumFields ) {
			throw new Exception ("Input does not have the correct number of fields.");
		}
		compareField1 = Integer.valueOf(inputFields[field1-1]);
		compareField2 = Integer.valueOf(inputFields[field2-1]);
		fieldDiff = Math.abs(compareField1 - compareField2);
		
	}
	
	/**
	 * Gets the field 
	 * @param field The index of the field, this is not the same as the column number.
	 * @return The string value of the field.
	 * @throws Exception Throws this when the field value is invalid
	 */
	public String getField(int field) throws Exception {
		if ( field >= inputFields.length || field < 0 ) {
			throw new Exception ("Field index value is invalid.");
		}
		return inputFields[field];
	}
	
	/**
	 * Compares the difference fields. (fieldDiff) 
	 */
	@Override
	public int compareTo(DataObject other) {
		return Integer.compare(fieldDiff, other.fieldDiff);
	}
	
}