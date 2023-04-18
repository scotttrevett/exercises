package hfcu.exercises;

import java.util.ArrayList;

public class Exercise1Redo {

	public Exercise1Redo(String inputFileLocation) throws Exception {
		ArrayList<DataObject> dataSet = Exercise3Shared.readInputFile(inputFileLocation, ",", 3, 2, 3);
		ArrayList<DataObject> minSet = Exercise3Shared.findMinimumDifference(dataSet);
		ArrayList<DataObject> maxSet = Exercise3Shared.findMaximumDifference(dataSet);
		System.out.println("Date(s) with smallest temperature spread:");
		output(minSet);
		System.out.println("Date(s) with largest temperature spread:");
		output(maxSet);
	}
	
	private void output(ArrayList<DataObject> set) throws Exception {
		if ( set == null ) {
			throw new Exception ("Data set cannot be null.");
		}
		for ( DataObject obj : set ) {
			System.out.println("Date: " + obj.getField(0));
			System.out.println("Maximum daily temperature: " + obj.getField(1));
			System.out.println("Minimum daily temperature: " + obj.getField(2));
			System.out.println("Temperature spread: " + obj.fieldDiff);
			System.out.println();
		}

	}
}
