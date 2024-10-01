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
    
    protected Node firstNOC;
    
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
        this.firstNOC = null;
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
        return (firstNOC == null);
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

        boolean found = false;
        Node current = firstNOC;
        Node previous = null;
        Noc currentNoc = null;

        while (current != null && !found) {
            currentNoc = (Noc) current.data;
            if (currentNoc.getYear() == a.year) {
                found = true;
            } else if (currentNoc.getYear() > a.year) {
                break;
            }
            previous = current;
            current = current.getNext();
        }

        if (!found) {
            Node newNode = new Node(new Noc(a.year));
            if (previous == null) {
                newNode.setNext(firstNOC);
                firstNOC = newNode;
            } else {
                newNode.setNext(previous.next);
                previous.setNext(newNode);
            }
            currentNoc = (Noc) newNode.data;
        }
        if (currentNoc != null) {
            currentNoc.addAthleteToNOC(a);
        }
    }


    public void displayAthletes()
    {
        Node current = firstNOC;
        while (current != null) {
            Noc currentNoc = (Noc) current.data;
            System.out.println(currentNoc.toString());
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
        Node current = firstNOC;
        int max = 0;
        int year = 0;
        while (current != null) {
            Noc currentNoc = (Noc) current.data;
            int goldCount = currentNoc.getGoldCount(noc);
            if (goldCount > max) {
                max = goldCount;
                year = currentNoc.getYear();
            }
            current = current.getNext();
        }
        if (year == 0) {
            System.out.println("No data!");
        } else {
            System.out.println("The most successful year for " + noc + " was " + year + " with " + max + " Gold medals!\n");
        }
        return year;
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
        Node current = firstNOC;
        String winningNoc = null;
        boolean found = false;
        
        while (current != null && !found) {
            Noc currentNoc = (Noc) current.data;
            System.out.println("test" + currentNoc.getYear());
            if (currentNoc.getYear() == year) {
                found = true;
                System.out.println("found");
            }
            current = current.getNext();
        }
        if (!found) {
            System.out.println("No data!");
        } else {
            Noc currentNoc = (Noc) current.data;
            winningNoc = currentNoc.getWinningNOC();
            int winningCount = currentNoc.getGoldCount(winningNoc);
            System.out.println("The NOC with the most gold medals in " + year + " was " + winningNoc + " with " + winningCount + " Gold medals!\n");
        }
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

        Node current = firstNOC;
        Noc currentNoc = (Noc) firstNOC.data;

        while (current != null) {
            currentNoc = (Noc) current.data;
            if (currentNoc.getYear() == year) {
                currentNoc.showMedalTally();
            }
            current = current.getNext();
        }
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