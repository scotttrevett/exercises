package hfcu.exercises;

import java.util.ArrayList;

/**
 * This is the re-factored class for exercise 2.
 * It takes a CSV file containing league team stats information and converts it to a data set.
 * It outputs information for the team with the smallest and largest difference in goals for and against.
 * @author Scott Trevett
 *
 */
public class Exercise2Redo {
	/**
	 * @param inputFileLocation The fully qualified file name of the input CSV file
	 * @throws Exception
	 */
	public Exercise2Redo(String inputFileLocation) throws Exception {
		// the minimum number of columns is 8
		// the first comparison field is found in column 7
		// the second comparison field is found in column 8
		ArrayList<DataObject> dataSet = Exercise3Shared.readInputFile(inputFileLocation, ",", 8, 7, 8);
		ArrayList<DataObject> minSet = Exercise3Shared.findMinimumDifference(dataSet);
		ArrayList<DataObject> maxSet = Exercise3Shared.findMaximumDifference(dataSet);
		System.out.println("Club(s) with smallest goal difference:");
		output(minSet);
		System.out.println("Club(s) with largest goal difference:");
		output(maxSet);
	}
	
	/**
	 * Prints out relevant information to System.out
	 * @param set The data set to print out
	 * @throws Exception Throws this if the data set is null or empty
	 */
	private void output(ArrayList<DataObject> set) throws Exception {
		if ( set == null || set.size() == 0 ) {
			throw new Exception ("Data set cannot be null.");
		}
		for ( DataObject obj : set ) {
			System.out.println("Club position: " + obj.getField(0));
			System.out.println("Club: " + obj.getField(1));
			// the index for the first field is 6
			System.out.println("Club's goals for: " + obj.getField(6));
			// the index for the second field is 7
			System.out.println("Club's goals against: " + obj.getField(7));
			System.out.println("Goal Difference: " + obj.fieldDiff);
			System.out.println();
		}

	}
}
