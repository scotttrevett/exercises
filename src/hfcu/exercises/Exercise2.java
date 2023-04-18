package hfcu.exercises;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * This is the class for exercise 2.
 * It takes a CSV file containing league team stats information and converts it to a data set.
 * It outputs information for the team with the smallest and largest difference in goals for and against.
 * @author Scott Trevett
 *
 */
public class Exercise2 {
	ArrayList<TeamStats> dataSet = new ArrayList<>();

	public Exercise2(String inputFileLocation) throws Exception {
		// read and parse the input file
		readInputFile(inputFileLocation);
		// sort the resulting data set to uncover min and max spreads
		findMinMaxSpread();
	}
	
	/**
	 * Reads in the input CSV file and builds the data set of TeamStats objects.
	 * @param fileName The fully qualified name of the input file.
	 * @throws FileNotFoundException Throws this when the file specified does not exist. 
	 * @throws Exception Throws this when errors occur parsing the input and translating this into a TeamStats object
	 */
	private void readInputFile(String fileName) throws Exception {
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
			TeamStats team = new TeamStats(line);
			dataSet.add(team);
		}
		scan.close();
	}
	
	/**
	 * Traverses through the sorted data set to find all instances that match the minimum and maximum spread. 
	 */
	private void findMinMaxSpread() {
		Collections.sort(dataSet);
		// find minimum spread
		float minSpread = dataSet.get(0).goalDiff;
		float maxSpread = dataSet.get(dataSet.size() - 1).goalDiff;
		for ( TeamStats day : dataSet ) {
			if ( day.goalDiff == minSpread ) {
				System.out.println("Club(s) with smallest goal difference:");
				System.out.println(day.getOutput());
			}
		}
		for ( TeamStats day : dataSet ) {
			if ( day.goalDiff == maxSpread ) {
				System.out.println("Club(s) with largest goal difference:");
				System.out.println(day.getOutput());
			}
		}
	}
}

/**
 * This class defines the object consisting of team stats data
 * @author Scott Trevett
 *
 */
class TeamStats implements Comparable<TeamStats> {
	// fields from input
	String position; // the club position
	String club; // the club name
	int goalsFor; // the number of goals for 
	int goalsAgainst; // the number of goals against
	
	// calculated fields
	int goalDiff; // the absolute difference between goals for and against
	
	public TeamStats (String input) throws Exception {
		// check to see if the input string is null
		if ( input == null) {
			throw new Exception("Input cannot be null");
		}
		String[] parts = input.split(",");
		// check to see if the minimum number of fields was provided
		if (parts.length < 8 ) {
			throw new Exception ("Input does not have the correct number of fields.");
		}
		
		// set fields
		position = parts[0];
		club = parts[1];
		goalsFor = Integer.valueOf(parts[6]);
		goalsAgainst = Integer.valueOf(parts[7]);
		
		// calculate absolute goals difference and set field
		goalDiff = Math.abs(goalsFor - goalsAgainst);
		
	}
	
	/**
	 * Builds output string for object
	 * @return String of relevant object data
	 */
	public String getOutput() {
		StringBuilder output = new StringBuilder();
		output.append("Club position: " + position);
		output.append("\nClub: " + club);
		output.append("\nClub's goals for: " + goalsFor);
		output.append("\nClub's goals against: " + goalsAgainst);
		output.append("\nGoal Difference: " + goalDiff);
		output.append("\n");
		return output.toString();
	}
	
	/**
	 * Compares the goal difference (goalDiff)
	 */
	@Override
	public int compareTo(TeamStats other) {
		return Integer.compare(this.goalDiff, ((TeamStats)other).goalDiff);
	}
}
