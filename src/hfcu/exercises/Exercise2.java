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
	
	// read in input file
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
	
	private void findMinMaxSpread() {
		Collections.sort(dataSet);
		// find minimum spread
		float minSpread = dataSet.get(0).goalDiff;
		float maxSpread = dataSet.get(dataSet.size() - 1).goalDiff;
		for ( TeamStats day : dataSet ) {
			if ( day.goalDiff == minSpread ) {
				System.out.println("Club with smallest goal difference:");
				System.out.println(day.getOutput());
			}
		}
		for ( TeamStats day : dataSet ) {
			if ( day.goalDiff == maxSpread ) {
				System.out.println("Club with largest goal difference:");
				System.out.println(day.getOutput());
			}
		}
	}
}

class TeamStats implements Comparable<TeamStats> {
	// fields from input
	String position;
	String club;
	int goalsFor;
	int goalsAgainst;
	
	// calculated fields
	int goalDiff;
	
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
		
		// calculate temp spread and set field
		goalDiff = Math.abs(goalsFor - goalsAgainst);
		
	}
	
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
	
	@Override
	public int compareTo(TeamStats other) {
		return Integer.compare(this.goalDiff, ((TeamStats)other).goalDiff);
	}
}
