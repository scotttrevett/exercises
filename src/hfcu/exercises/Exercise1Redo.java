package hfcu.exercises;

import java.util.ArrayList;

/**
 * This is the re-factored class for exercise 1.
 * It takes a CSV file containing temperature information and converts it to a data set.
 * It outputs information for the day with the smallest and largest temperature spread.
 * @author Scott Trevett
 *
 */
public class Exercise1Redo {

	/**
	 * @param inputFileLocation The fully qualified file name of the input CSV file
	 * @throws Exception
	 */
	public Exercise1Redo(String inputFileLocation) throws Exception {
		// the minimum number of columns is 3
		// the first comparison field is found in column 2
		// the second comparison field is found in column 3
		ArrayList<DataObject> dataSet = Exercise3Shared.readInputFile(inputFileLocation, ",", 3, 2, 3);
		ArrayList<DataObject> minSet = Exercise3Shared.findMinimumDifference(dataSet);
		ArrayList<DataObject> maxSet = Exercise3Shared.findMaximumDifference(dataSet);
		System.out.println("Date(s) with smallest temperature spread:");
		output(minSet);
		System.out.println("Date(s) with largest temperature spread:");
		output(maxSet);
	}
	
	/**
	 * Prints out relevant information to System.out
	 * @param set The data set to print out
	 * @throws Exception Throws this if the data set is null or empty
	 */
	private void output(ArrayList<DataObject> set) throws Exception {
		if ( set == null || set.size() == 0) {
			throw new Exception ("Data set cannot be null.");
		}
		for ( DataObject obj : set ) {
			System.out.println("Date: " + obj.getField(0));
			// the index of the maximum temp is 1
			System.out.println("Maximum daily temperature: " + obj.getField(1));
			// the index of the minimum temp is 2
			System.out.println("Minimum daily temperature: " + obj.getField(2));
			System.out.println("Temperature spread: " + obj.fieldDiff);
			System.out.println();
		}

	}
}
