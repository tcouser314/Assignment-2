/**
 * Noc.java
 * 
 * KIT107 Assignment 2 -- NOC Implementation
 * 
 * @author Thomas Couser 692529
 * @version	2/10/2024
 * 
 * a) 
 * I would use a list to store the cluster of athletes within a single year. 
 * The functionality allows for the addition of athletes to the list in a specific
 * order, checking if the list is empty and interating through the list and 
 * retrieving details. A list provides the flexibility for ordered insertion. 
 * 
 * b)
 * I would use a linked list as the underlying data structure for the list of
 * athletes within a single year. This is because linked lists allow for quick
 * insertion of elements in order, which is important for the NOC class. Linked
 * lists can also grow easily which is important since we do not know the number
 * of athletes that competed in any given year.
 * 
 */


public class Noc implements NocInterface
{
    // instance variable declarations
	private Node firstAthlete; // the first athlete in the NOC
    
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
        Athlete currentAthlete = null; // set currentAthlete to the first athlete in the cluster

		if (isEmpty()) 
        {
			return 0;
		} else 
        {
			currentAthlete = (Athlete) firstAthlete.data; // set currentAthlete to the first athlete in the cluster
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
        Athlete currentAthlete = null; // set currentAthlete to the first athlete in the cluster

		if (isEmpty()) 
        {
			return "";
		} else 
        {
			currentAthlete = (Athlete) firstAthlete.data; // set currentAthlete to the first athlete in the cluster
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
		Node newNode = new Node(a); // create a new node with the given athlete 
        Node current = firstAthlete; // current NOC in the list
        Node previous = null; // previous NOC in the list

        if (firstAthlete == null) 
        {
            firstAthlete = newNode;
        } else 
        {
            
            while (current != null && a.getNOC().compareTo(((Athlete) current.data).getNOC()) > 0) 
            {
                previous = current;
                current = current.next;
            }
            if (previous == null) 
            {
                newNode.next = firstAthlete;
                firstAthlete = newNode;
            } else 
            {
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
		int count = 0; // number of gold medals for the given NOC
		Node current = firstAthlete; // current NOC in the list
        Athlete currentAthlete = null; // current athlete being searched as an Athlete object instead of a Node

		while (current != null) 
        {
			currentAthlete = (Athlete) current.data;
			if (currentAthlete.getNOC().equals(noc) && currentAthlete.getResult().equals("Gold")) 
            {
				count++;
			}
			current = current.next;
		}
		return count;
	}

// 	/**
// 	 * getWinningNOC()
// 	 * 
// 	 * @return String -- the NOC with the largest number of gold medals in
//      *                      the crrent Olympic Games
// 	 * 
// 	 * Precondition: None
// 	 * Postcondition: A count is made of the number of gold medalists in each
//      *                  NOC with the name and number of the most awarded NOC
//      *                  remembered and the winning NOC returned.  If the
//      *                  cluster is empty then "" is returned, and, in the case
//      *                  of a tie, the NOC alphabetically later is returned.
// 	 * Informally: Count the number of athletes from every NOC in the current
//      *                  Olympic Games who received gold medals and return the
//      *                  NOC receiving the most.
// 	 */
public String getWinningNOC() {
    final int SIZE = 100; // maximum number of NOCs set to arbitrary value but cannot be too small
    Node current = firstAthlete; // current NOC being searched
    String[] nocs = new String[SIZE]; // array of NOCs
    int[] goldCount = new int[SIZE]; // array of gold medal counts
    int nocIndex = 0; // number of NOCs found
    int year = getYear(); // year of the current Olympic Games
    Athlete athlete = null; // current athlete being searched as an Athlete object instead of a Node
    boolean nocFound = false; // whether the current NOC has been found in the array of NOCs
    String winningNOC = ""; // NOC with the most gold medals
    int winningCount = 0; // number of gold medals for the winning NOC

    while (current != null) 
    {
        athlete = (Athlete) current.data;

        if (athlete.year == year) 
        {
            nocFound = false;
            for (int i = 0; i < nocIndex; i++) 
            {
                if (nocs[i].equals(athlete.noc)) 
                {
                    nocFound = true;
                    if (athlete.result.equals("Gold")) 
                    {
                        goldCount[i]++;
                    }
                }
            }

            if (!nocFound) 
            {
                nocs[nocIndex] = athlete.noc;
                if (athlete.result.equals("Gold")) 
                {
                    goldCount[nocIndex]++;
                }
                nocIndex++;
            }
        }
        current = current.getNext();
    }

    for (int i = 0; i < nocIndex; i++) 
    {
        if (goldCount[i] > winningCount || (goldCount[i] == winningCount && nocs[i].compareTo(winningNOC) > 0)) 
        {
            winningNOC = nocs[i];
            winningCount = goldCount[i];
        }
    }

    // return the winning NOC
    if (nocIndex == 0) 
    {
        return "";
    } else 
    {
        return winningNOC;
    }
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
	
		final int SIZE = 100; // maximum number of NOCs set to arbitrary value but cannot be too small
        Node current = firstAthlete; // current NOC being searched
        String[] nocs = new String[SIZE]; // array of NOCs
        int[] goldCount = new int[SIZE]; // array of gold medal counts
        int[] silverCount = new int[SIZE]; // array of silver medal counts  
        int[] bronzeCount = new int[SIZE]; // array of bronze medal counts
        int[] medalCount = new int[SIZE]; // array of total medal counts
        int nocIndex = 0; // number of NOCs found
        String city = getCity(); // city of the current Olympic Games
		int year = getYear(); // year of the current Olympic Games
        Athlete athlete = (Athlete) current.data; // current athlete being searched as an Athlete object instead of a Node
        boolean nocFound = false; // whether the current NOC has been found in the array of NOCs
        char result = ' '; // result of the current athlete
        String tallyString = ""; // string of medals (in "GGSSSSBB format") for the current NOC
        String tally = ""; // final string to print

        while (current != null) 
        {
            athlete = (Athlete) current.data;
            
            if (athlete.year == year) 
            {
                nocFound = false;
                
                for (int i = 0; i < nocIndex; i++) 
                {
                    if (nocs[i].equals(athlete.noc)) 
                    {
                        nocFound = true;
                        result = athlete.result.charAt(0);
                        medalCount[i]++;
                        if (result == 'G') 
                        {
                            goldCount[i]++;
                        } else if (result == 'S') 
                        {
                            silverCount[i]++;
                        } else 
                        {
                            bronzeCount[i]++;
                        }
                    }
                }
        if (!nocFound) 
        {
            nocs[nocIndex] = athlete.noc;
            result = athlete.result.charAt(0);
            medalCount[nocIndex]++;
            if (result == 'G') 
            {
                goldCount[nocIndex]++;
            } else if (result == 'S') 
            {
                silverCount[nocIndex]++;
            } else 
            {
                bronzeCount[nocIndex]++;
            }
            nocIndex++;
        }
        }
        current = current.getNext();
    }

    if (nocIndex == 0) 
    {
        System.out.println("No data!");
        return;
    }
    
    System.out.println("Medal Tally for " + year + " Olympic Games in " + city + "\n");

    for (int i = 0; i < nocIndex; i++) 
    {
        tallyString = ""; // string of medals (in "GGSSSSBB format") for the current NOC
		tallyString += "G".repeat(goldCount[i]) + "S".repeat(silverCount[i]) + "B".repeat(bronzeCount[i]);
        
        tally = nocs[i] + "\t" + tallyString + " "+goldCount[i]+" x Gold, "+silverCount[i]+" x Silver, "+bronzeCount[i]+" x Bronze, Total: "+medalCount[i]; // final string to print
        System.out.println(tally);
    }
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
		String result = ""; // string to return
		Node current = firstAthlete; // current Athlete in the list

		while (current != null) 
        {
			result += current.data.toString() + "\n";
			current = current.next;
		}
		return result;
    }
}