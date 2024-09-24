/**
 * Years.java
 * 
 * KIT107 Assignment 2 -- Years Implementation
 * 
 * @author Thomas Couser 692529
 * @version	<<date of completion>>
 */


public class Years implements YearsInterface
{
    // instance variable declarations
    
    private Node firstAthlete;
    
	/**
	 * Constructor
	 * 
	 * Precondition: None
	 * Postcondition: The new instance will have its instance variable(s)
     *                  initialised with the relevant parameters.
	 * Informally: Initialise the collection of olympic games.
	 */
    public Years()
    {
        firstAthlete = null;
    }

	/**
	 * isEmpty()
	 * 
	 * @return boolean -- whether the collection is empty
	 * 
	 * Precondition: None
	 * Postcondition: True is returned if the collection is empty; false is
     *                  returned otherwise.
	 * Informally: Check whether the collection is empty.
	 */
    public boolean isEmpty()
    {
        return (firstAthlete == null);
    }

    /**
	 * addAthleteToYears()
	 * 
	 * @param a Athlete -- the athlete to add to this collection
	 * 
	 * Precondition: The given Athlete parameter has been constructed
	 * Postcondition: The given Athlete has been added to the collection and,
     *                  in particular to the appropriate cluster of athletes
     *                  maintaining the increasing year of competition, and,
     *                  within the cluster, the alphabetical order of NOC and
     *                  residualal order of athlete name.
	 * Informally: Add an athlete to the appropriate year (Games) in the
     *                  collection.
	 */
    public void addAthleteToYears(Athlete a) {
        // Check if the athlete's result is a medal
        // if (!a.result.equals("Gold") && !a.result.equals("Silver") && !a.result.equals("Bronze")) {
        if (a.result.equals("No medal")) {
            return;
        }
    
        Node newNode = new Node(a);
    
        // If the list is empty, add the athlete as the first node
        if (isEmpty()) {
            firstAthlete = newNode;
            return;
        }
    
        Node current = firstAthlete;
        Node previous = null;
    
        // Traverse the list to find the correct insertion point
        while (current != null) {
            Athlete currentAthlete = (Athlete) current.data;
    
            // Compare year first, then noc, then name
            int yearComparison = a.year - currentAthlete.year;
            int nocComparison = a.noc.compareTo(currentAthlete.noc);
            int nameComparison = a.name.compareTo(currentAthlete.name);
    
            if (yearComparison < 0 || 
                (yearComparison == 0 && nocComparison < 0) || 
                (yearComparison == 0 && nocComparison == 0 && nameComparison < 0)) {
                break;
            }
    
            previous = current;
            current = current.getNext();
        }
    
        // Insert the new athlete at the correct position
        if (previous == null) {
            // Insert at the beginning
            newNode.setNext(firstAthlete);
            firstAthlete = newNode;
        } else {
            // Insert in the middle or end
            newNode.setNext(current);
            previous.setNext(newNode);
        }
    }
    


    public void displayAthletes()
    {
        Node current = firstAthlete;
        Athlete currentAthlete = (Athlete) current.data;
        Node next = firstAthlete.next;
        Athlete nextAthlete = (Athlete) next.data;
        while (current != null) {
            currentAthlete = (Athlete) current.data;
            nextAthlete = (Athlete) next.data;

            if (currentAthlete.year != nextAthlete.year) {
                System.out.println("");
            }

            System.out.println(currentAthlete);
            current = current.getNext();
            }
        }
        

    /**
	 * showMostSuccessfulYear()
	 * 
	 * @param noc String -- the NOC to search all years for
     * 
     * @return int -- the (most recent) year in which the given NOC received
     *                  the greatest number of gold medals -- or 0 if the NOC
     *                  cannot be found or if the cluster is empty
	 * 
	 * Precondition: The given NOC parameter is not null
	 * Postcondition: The collection is searched for athletes from the given
     *                  NOC who received gold medals and the count of these in
     *                  the year that this is greatest is printed and returned
     *                  -- unless the dataset is empty or the NOC is not found
     *                  in which case 0 is returned and "No data!" is printed.
     *                  The most recent year is returned in the case of a tie.
	 * Informally: Search every Olympic Games in the collection for athletes
     *                  from the given NOC and return the year with the most
     *                  gold medals.
	 */
    public int showMostSuccessfulYear(String noc) {
        Node current = firstAthlete;

        int[] yearGolds = new int[3000];
        int max = 0;
        int maxYear = 0;

        while (current != null) {
            Athlete currentAthlete = (Athlete) current.data;
            if (currentAthlete.noc.contains(noc) && currentAthlete.result.contains("Gold")) {
                yearGolds[currentAthlete.year]++;
            }
            current = current.getNext();
        }
    for (int year = 1850; year <yearGolds.length; year++) {
        if (yearGolds[year] > max) {
            max = yearGolds[year];
            maxYear = year;
        }
    }

    if (max == 0) {
        System.out.println("No data!");
        return 0;
    }
    System.out.println("\nThe most successful year for " + noc + " was " + maxYear + " with " + max + " Gold medals!\n");
    return maxYear;
    }

    /**
	 * showWinningNOC()
	 * 
	 * @param int year -- the year to produce results for
     * 
     * Precondition: None
	 * Postcondition: The collection is searched for the given year and, if
     *                  found, that year's cluster of athletes is searched to
     *                  find the NOC with the greatest number of gold medals.
     *                  If the year does not exist then "No data!" is printed,
     *                  otherwise the result is displayed.  In the case of a
     *                  tie, the NOC occurring latest alphabetically is chosen
     *                  as the answer.
	 * Informally: Search every Olympic Games in the collection for a Games
     *                  from the given year and then find the NOC in that
     *                  year's Games with the most gold medals.
	 */
    public void showWinningNOC(int year)
    {
        Node current = firstAthlete;
        
        String[] nocs = new String[200];
        int[] goldCount = new int[200];
        int nocIndex = 0;

        while (current != null) {
            Athlete currentAthlete = (Athlete) current.data;
            if (currentAthlete.year == year && currentAthlete.result.contains("Gold")) {
                boolean found = false;
                for (int i = 0; i < nocIndex; i++) {
                    if (nocs[i].equals(currentAthlete.noc)) {
                        goldCount[i]++;
                        found = true;
                    }
                }
                if (!found) {
                    nocs[nocIndex] = currentAthlete.noc;
                    goldCount[nocIndex]++;
                    nocIndex++;
                }
            }
        current = current.getNext();
        }
    if (nocIndex == 0) {
        System.out.println("No data!");
        return;
    }
    String winningnoc = nocs[0];
    int max = goldCount[0];

    for (int i = 1; i < nocIndex; i++) {
        if (goldCount[i] > max) {
            max = goldCount[i];
            winningnoc = nocs[i];
        }
    }
    System.out.println("The most successful country in " + year + " was " + winningnoc + " with " + max + " Gold medals!\n");
    }

    /**
	 * showMedalTally()
	 * 
	 * @param int year -- the year to produce results for
     * 
     * Precondition: None
	 * Postcondition: The collection is searched for the given year and, if
     *                  found, that year's medal table is printed with a line
     *                  for each NOC, as a 'horizonal histogram' in which a G
     *                  is printed for each gold medal, an S for each silver,
     *                  and a B for each bronze, before a summary is printed
     *                  for the NOC.  If the year does not exist then "No
     *                  data!" is printed.
	 * Informally: Print the athlete-basaed medal tally for the Olympic Games
     *                  of the given year.
	 */
    public void showMedalTally(int year)
    {
        final int SIZE = 500;
        Node current = firstAthlete;
        String[] nocs = new String[SIZE];
        int[] goldCount = new int[SIZE];
        int[] silverCount = new int[SIZE];
        int[] bronzeCount = new int[SIZE];
        int[] medalCount = new int[SIZE];
        int nocIndex = 0;
        String city = null;

        while (current != null) {
            Athlete athlete = (Athlete) current.data;
            
            if (athlete.year == year) {
                boolean nocFound = false;
                if (city == null) {
                    city = athlete.city;
                }
                
                for (int i = 0; i < nocIndex; i++) {
                    if (nocs[i].equals(athlete.noc)) {
                        nocFound = true;
                        char result = athlete.result.charAt(0);
                        medalCount[i]++;
                        if (result == 'G') {
                            goldCount[i]++;
                        } else if (result == 'S') {
                            silverCount[i]++;
                        } else {
                            bronzeCount[i]++;
                        }
                    }
            }
        if (!nocFound) {
            nocs[nocIndex] = athlete.noc;
            char result = athlete.result.charAt(0);
            medalCount[nocIndex]++;
            if (result == 'G') {
                goldCount[nocIndex]++;
            } else if (result == 'S') {
                silverCount[nocIndex]++;
            } else {
                bronzeCount[nocIndex]++;
            }
            nocIndex++;
        }
        }
        
        current = current.getNext();
    }

    if (nocIndex == 0) {
        System.out.println("No data!");
        return;
    }
    
    System.out.println("Medal Tally for " + year + " Olympic Games in " + city + "\n\n");

    for (int i = 0; i < nocIndex; i++) {
        String tallyString = "";
        for (int j = 0; j < goldCount[i]; j++) {
            tallyString += "G";
        }
        for (int j = 0; j < silverCount[i]; j++) {
            tallyString += "S";
        }
        for (int j = 0; j < bronzeCount[i]; j++) {
            tallyString += "B";
        }
        
        String tally = nocs[i] + "\t" + tallyString + " "+goldCount[i]+" x Gold, "+silverCount[i]+" x Silver, "+bronzeCount[i]+" x Bronze, Total: "+medalCount[i];
        System.out.println(tally);
    }
}

    public void showAthletes() {
        Node current = firstAthlete;
        int num = 0;
        while (current != null) {
            Athlete currentAthlete = (Athlete) current.data;
            System.out.println(num+1+". "+currentAthlete.toString());
            num++;
            current = current.getNext();
        }
    }

    public boolean compare(String a, String b) {
        return a.compareTo(b) < 0;
    }

	/**
	 * toString()
	 * 
	 * @return String -- printable form of the collection of Olympic Games
	 * 
	 * Precondition: None
	 * Postcondition: A printable (String) form of the Olympic Games data is
     *                  returned, one athlete per line, with each Games data
     *                  separated by a blank line.  If there are no athletes
     *                  then "" is returned.
	 * Informally: Convert the collection of Olympic Games data to a multi-
     *                  line String.
	 */
    public String toString()
    {
        return "";
    }

	/**
	 * toString()
	 * 
     * @param year int -- the year to print the cluster of athletes for
     * 
	 * @return String -- printable form of the cluster of athletes for an
     *                      Olympic Games of the given year
	 * 
	 * Precondition: None
	 * Postcondition: A printable (String) form of the Olympic Games data for
     *                  the given year is returned, one athlete per line.  If
     *                  the year does not exist, of if there are no athletes
     *                  for that year, then "" is returned.
	 * Informally: Convert the cluster of Olympic Games data to a multi-line
     *                  String.
	 */
    public String toString(int year)
    {
        return "";
    }
}