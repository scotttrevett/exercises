# Programming exercises

## Exercise 1
This exercise `Exercise1.java` takes in the `data\Exercise1.csv` CSV file as input. It will find and output to System.out information for the day with the minimum and the day with the maximum temperature difference.

#### Input file
The input file is a CSV file that contains the following required columns in order (any additional columns will be ignored)
1. Date
2. Maximum Temperature
3. Minimum Temperature

#### Output
Here is an example of the output:

```
Date(s) with smallest temperature spread:
Date: 8/20/2022
Maximum daily temperature: 82
Minimum daily temperature: 74
Temperature Spread: 8
```

```
Date(s) with smallest temperature spread:
Date: 6/16/2022
Maximum daily temperature: 111
Minimum daily temperature: 72
Temperature Spread: 39
```

## Exercise 2
This exercise `Exercise2.java` takes in the `data\Exercise2.csv` CSV file as input. It will find and output to System.out information for the English Premier League club teams with the smallest and largest differences between the goals for and goals against.

### Input file
The input file is a CSV file that contains the following required columns in order (fields 3-6 are unused for this exercise and any additional columns will be ignored)
1. Position
2. Club
3. <unused>
4. <unused>
5. <unused>
6. <unused>
7. Goals For
8. Goals Against

### Output
Below is an example of the output. It will output all clubs that share the same difference.

```
Club(s) with smallest goal difference:
Club position: 6
Club: Aston Villa
Club's goals for: 41
Club's goals against: 40
Goal Difference: 1

Club(s) with smallest goal difference:
Club position: 10
Club: Fulham
Club's goals for: 39
Club's goals against: 40
Goal Difference: 1
```

```
Club(s) with largest goal difference:
Club position: 2
Club: Manchester City
Club's goals for: 75
Club's goals against: 27
Goal Difference: 48
```

## Exercise 3
This exercise `Exercise3.java`, `Exercise1Redo.java`, and `Exercise2Redo.java` re-factored the code in Exercise 1 and 2 to take advantage of shared code.
The inputs and outputs are the same as previous exercises.
