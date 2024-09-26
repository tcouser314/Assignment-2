/**
 * AssigTwo224.java
 * 
 * KIT107 Assignment 2 -- Harness Class
 * 
 * @author Julian Dermoudy
 * @version	6/9/2024
 * 
 * FILE IS COMPLETE
 */


import java.util.Scanner;


public class AssigTwo224
{
	/**
	 * main() -- entry point
	 * 
	 * @param args String[] -- command line arguments
	 * 
	 * Precondition: None
	 * Postcondition: A title will be displayed, a dataset read and stored,
	 * 					the user prompted for a NOC, that NOC used to
	 * 					determine the year that NOC was most successful,
	 * 					that year used to determine the NOC that was most
	 * 					successful in that year, and the medal tally
	 * 					produced for that year.
	 * Informally: Read the datafile, store it, process it, and produce the
	 * 					results.
	 */
    public static void main(String []args)
    {
		final String FILENAME="data.csv";	// Filename of the file holding the dataset

		Scanner sc=new Scanner(System.in);	// Scanner object for user input
		FileHandler myFile;					// object for A2-related file handling
		Years olympiad;						// collection of data within the program
		String noc;							// user input for chosen NOC
		int year;							// year identified as most successful for the user's NOC

		// produce title
		System.out.println("The Olympic Games");
		System.out.println("=================\n");

		// initialise collection and read in dataset
		olympiad=new Years();
		myFile=new FileHandler(FILENAME);
		myFile.readFile(olympiad);

		olympiad.displayAthletes();

		// System.out.println(olympiad.toString());  // uncomment when debugging

		// seek most successful year for user's provided NOC
		System.out.print("Which NOC would you like to explore? ");
		noc=sc.nextLine();
		year=olympiad.showMostSuccessfulYear(noc);

		// seek most successful NOC for that year
		olympiad.showWinningNOC(year);

		// produce athlete-based medal table for that year
		olympiad.showMedalTally(year);
    }
}