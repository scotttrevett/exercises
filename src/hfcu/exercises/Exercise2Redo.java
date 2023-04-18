package hfcu.exercises;

import java.util.ArrayList;

public class Exercise2Redo {
	public Exercise2Redo(String inputFileLocation) throws Exception {
		ArrayList<DataObject> dataSet = Exercise3Shared.readInputFile(inputFileLocation, ",", 8, 7, 8);
		ArrayList<DataObject> minSet = Exercise3Shared.findMinimumDifference(dataSet);
		ArrayList<DataObject> maxSet = Exercise3Shared.findMaximumDifference(dataSet);
		System.out.println("Club(s) with smallest goal difference:");
		output(minSet);
		System.out.println("Club(s) with largest goal difference:");
		output(maxSet);
	}
	
	private void output(ArrayList<DataObject> set) throws Exception {
		if ( set == null ) {
			throw new Exception ("Data set cannot be null.");
		}
		for ( DataObject obj : set ) {
			System.out.println("Club position: " + obj.getField(0));
			System.out.println("Club: " + obj.getField(1));
			System.out.println("Club's goals for: " + obj.getField(6));
			System.out.println("Club's goals against: " + obj.getField(7));
			System.out.println("Goal Difference: " + obj.fieldDiff);
			System.out.println();
		}

	}
}
