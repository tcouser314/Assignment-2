/**
 * Noc.java
 * 
 * KIT107 Assignment 2 -- NOC Implementation
 * 
 * @author Thomas Couser 692529
 * @version	<<date of completion>>
 */


public class Noc implements NocInterface
{
    // instance variable declarations
	private Node firstAthlete;
    
	/**
	 * Constructor
	 * 
	 * Precondition: None
	 * Postcondition: The new instance will have its instance variable(s)
     *                  initialised with the relevant parameters.
	 * Informally: Initialise the collection of athletes.
	 */
    public Noc(int year)
    {
		this.firstAthlete = null;
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


    public int getYear()
	/**
	 * getYear()
	 * 
	 * @return int -- the year of competition for all athletes in this cluster
     *                  or 0 if the cluster is empty
	 * 
	 * Precondition: None
	 * Postcondition: the year of the first athlete in the cluster -- or 0 if
     *                  it is empty -- is returned.
	 * Informally: Get the year of competition for the current Games.
	 */
    {
		if (isEmpty()) {
			return 0;
		} else {
			Athlete currentAthlete = (Athlete) firstAthlete.data;
			return currentAthlete.getYear();
		}
    }


    public String getCity()
	/**
	 * getCity()
	 * 
	 * @return String -- the location (city) of competition for this cluster
     *                  or "" if the cluster is empty
	 * 
	 * Precondition: None
	 * Postcondition: the city of the first athlete in the cluster -- or "" if
     *                  it is empty -- is returned.
	 * Informally: Get the city of competition for the current Games.
	 */
    {
		if (isEmpty()) {
			return "";
		} else {
			Athlete currentAthlete = (Athlete) firstAthlete.data;
			return currentAthlete.getCity();
		}
	}


	/**
	 * addAthleteToNOC()
	 * 
	 * @param a Athlete -- the athlete to add to this cluster
	 * 
	 * Precondition: The given Athlete parameter has been constructed
	 * Postcondition: The given Athlete has been added to the cluster of
     *                  athletes preserving the order by name and maintaining
     *                  alphabetical order of NOC.
	 * Informally: Add an athlete to an Olympic Games in order of NOC and
     *                  athlete name.
	 */
	public void addAthleteToNOC(Athlete a)
	{
		Node newNode = new Node(a);
        if (firstAthlete == null) {
            firstAthlete = newNode;
        } else {
            Node current = firstAthlete;
            Node previous = null;
            while (current != null && a.getNOC().compareTo(((Athlete) current.data).getNOC()) > 0) {
			// while (current != null && a.getName().compareTo(((Athlete) current.data).getName()) > 0) {
                previous = current;
                current = current.next;
            }
            if (previous == null) {
                newNode.next = firstAthlete;
                firstAthlete = newNode;
            } else {
                newNode.next = previous.next;
                previous.next = newNode;
            }
        }
	}

	/**
	 * getGoldCount()
	 * 
	 * @param noc String -- the NOC to count the gold medals for
     * 
     * @return int -- the number of gold medals for the given NOC
	 * 
	 * Precondition: The given NOC parameter is not null
	 * Postcondition: The cluster is searched for athletes from the given NOC
     *                  who received gold medals and the count of these is
     *                  returned.
	 * Informally: Count the number of athletes from a given NOC in the
     *                  current Olympic Games who received gold medals.
	 */
	public int getGoldCount(String noc)
	{
		int count = 0;
		Node current = firstAthlete;

		while (current != null) {
			Athlete currentAthlete = (Athlete) current.data;
			if (currentAthlete.getNOC().equals(noc) && currentAthlete.getResult().equals("Gold")) {
				count++;
			}
			current = current.next;
		}
		return count;
	}

	/**
	 * getWinningNOC()
	 * 
	 * @return String -- the NOC with the largest number of gold medals in
     *                      the crrent Olympic Games
	 * 
	 * Precondition: None
	 * Postcondition: A count is made of the number of gold medalists in each
     *                  NOC with the name and number of the most awarded NOC
     *                  remembered and the winning NOC returned.  If the
     *                  cluster is empty then "" is returned, and, in the case
     *                  of a tie, the NOC alphabetically later is returned.
	 * Informally: Count the number of athletes from every NOC in the current
     *                  Olympic Games who received gold medals and return the
     *                  NOC receiving the most.
	 */
	public String getWinningNOC()
	{
		String winningNOC = "";
		int winningCount = 0;
		Node current = firstAthlete;

		while (current != null) {
			Athlete currentAthlete = (Athlete) current.data;
			int goldCount = getGoldCount(currentAthlete.getNOC());
			if (goldCount > winningCount) {
				winningNOC = currentAthlete.getNOC();
				winningCount = goldCount;
			} else if (goldCount == winningCount && currentAthlete.getNOC().compareTo(winningNOC) > 0) {
				winningNOC = currentAthlete.getNOC();
			}
		}
		return "WinningNOC";
	}

    /**
	 * showMedalTally()
	 * 
	 * Precondition: None
	 * Postcondition: The medal table for the current Olympic Games is printed.
	 * Informally: Produce a tally of medals by NOC for the current cluster.
	 */
    public void showMedalTally()
    {
		int year = getYear();
		String city = getCity();
		System.out.println("Medal Tally for " + year + " in " + city);
		String Golds = "";
		String Silvers = "";
		String Bronzes = "";
		int goldCount = 0;
		int silverCount = 0;
		int bronzeCount = 0;
		int total = 0;

		Node current = firstAthlete;

		while (current != null) {
			Athlete currentAthlete = (Athlete) current.data;
		if (currentAthlete.getResult().equals("Gold")) {
			Golds += "G";
			goldCount++;
			total ++;
		}
		else if (currentAthlete.getResult().equals("Silver")) {
			Silvers += "S";
			silverCount++;
			total ++;
		}
		else if (currentAthlete.getResult().equals("Bronze")) {
			Bronzes += "B";
			bronzeCount++;
			total ++;
		}
		current = current.next;
		}
		Athlete firstAthleteAthlete = (Athlete) firstAthlete.data;
		System.out.println(firstAthleteAthlete.getNOC() + "\t|\t" + Golds + Silvers + Bronzes + "\t" + goldCount + " x Gold, " + silverCount + " x Silver, " + bronzeCount + " x Bronze; Total: " + total);
    }

	/**
	 * toString()
	 * 
	 * @return String -- printable form of the cluster of athletes
	 * 
	 * Precondition: None
	 * Postcondition: A printable (String) form of the athletes' data is
     *                  returned, one athlete per line.  If there are no
     *                  athletes then "" is returned.
	 * Informally: Convert the cluster of athletes' data to a multi-line
     *                  String.
	 */
    public String toString()
    {
		String result = "";
		Node current = firstAthlete;
		while (current != null) {
			result += current.data.toString() + "\n";
			current = current.next;
		}
		return result;
    }
}