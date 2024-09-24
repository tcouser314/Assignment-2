/**
 * Athlete.java
 * 
 * KIT107 Assignment 2 -- Athlete Implementation
 * 
 * @author Julian Dermoudy
 * @version	6/9/2024
 * 
 * FILE IS COMPLETE
 */


public class Athlete implements AthleteInterface
{
    protected String name;      // full name
    protected char sex;         // sex
    protected String team;      // country (or similar)
    protected String noc;       // National Olympic Committee
    protected int year;         // year of competition
    protected String city;      // location of Olympic Games
    protected String sport;     // sport of competition
    protected String event;     // event of competition
    protected String result;    // gold, silver, bronze, or no medal

	/**
	 * Constructor
	 * 
	 * @param n String -- athlete's full name
     * @param g char -- athlete's sex
     * @param t String -- athlete's country (or similar)
     * @param a String -- athlete's NOC
     * @param y int -- year of competition
     * @param c String -- city of competition
     * @param s String -- sport of competition
     * @param e String -- event of competition
     * @param r String -- level of medal or "No medal"
	 * 
	 * Precondition: None
	 * Postcondition: The new instance will have its instance variables
     *                  initialised with the relevant parameters.
	 * Informally: Initialise an athlete.
	 */
    public Athlete(String n, char g, String t, String a, int y, String c, String s, String e, String r)
    {
        name=n;
        sex=g;
        team=t;
        noc=a;
        year=y;
        city=c;
        sport=s;
        event=e;
        result=r;
    }

    /**
	 * getName()
	 * 
	 * @return String -- the value in the name field
	 * 
	 * Precondition: None
	 * Postcondition: the current object's name field is returned.
	 * Informally: Get the name field.
	 */
    public String getName()
    {
        return name;
    }

	/**
	 * setName()
	 * 
	 * @param n String -- String to be stored in name field of athlete
	 * 
	 * Precondition: None
	 * Postcondition: the current object's name field is assigned the given
	 * 					value.
	 * Informally: Set the name field.
	 */
    public void setName(String n)
    {
        name=n;
    }

    /**
	 * getSex()
	 * 
	 * @return char -- the value in the sex field
	 * 
	 * Precondition: None
	 * Postcondition: the current object's sex field is returned.
	 * Informally: Get the sex field.
	 */
    public char getSex()
    {
        return sex;
    }

	/**
	 * setSex()
	 * 
	 * @param g char -- char to be stored in sex field of athlete
	 * 
	 * Precondition: None
	 * Postcondition: the current object's sex field is assigned the given
	 * 					value.
	 * Informally: Set the sex field.
	 */
    public void setSex(char g)
    {
        sex=g;
    }

    /**
	 * getTeam()
	 * 
	 * @return String -- the value in the team field
	 * 
	 * Precondition: None
	 * Postcondition: the current object's team field is returned.
	 * Informally: Get the team field.
	 */
    public String getTeam()
    {
        return team;
    }

	/**
	 * setTeam()
	 * 
	 * @param t String -- String to be stored in team field of athlete
	 * 
	 * Precondition: None
	 * Postcondition: the current object's team field is assigned the given
	 * 					value.
	 * Informally: Set the team field.
	 */
    public void setTeam(String t)
    {
        team=t;
    }

    /**
	 * getNOC()
	 * 
	 * @return String -- the value in the noc field
	 * 
	 * Precondition: None
	 * Postcondition: the current object's noc field is returned.
	 * Informally: Get the noc field.
	 */
    public String getNOC()
    {
        return noc;
    }

	/**
	 * setNOC()
	 * 
	 * @param n String -- String to be stored in noc field of athlete
	 * 
	 * Precondition: None
	 * Postcondition: the current object's noc field is assigned the given
	 * 					value.
	 * Informally: Set the noc field.
	 */
    public void setNOC(String n)
    {
        noc=n;
    }

    /**
	 * getYear()
	 * 
	 * @return int -- the value in the year field
	 * 
	 * Precondition: None
	 * Postcondition: the current object's year field is returned.
	 * Informally: Get the year field.
	 */
    public int getYear()
    {
        return year;
    }

	/**
	 * setYear()
	 * 
	 * @param y int -- int to be stored in year field of athlete
	 * 
	 * Precondition: None
	 * Postcondition: the current object's year field is assigned the given
	 * 					value.
	 * Informally: Set the year field.
	 */
    public void setYear(int y)
    {
        year=y;
    }

    /**
	 * getCity()
	 * 
	 * @return String -- the value in the city field
	 * 
	 * Precondition: None
	 * Postcondition: the current object's city field is returned.
	 * Informally: Get the city field.
	 */
    public String getCity()
    {
        return city;
    }

	/**
	 * setCity()
	 * 
	 * @param c String -- String to be stored in city field of athlete
	 * 
	 * Precondition: None
	 * Postcondition: the current object's city field is assigned the given
	 * 					value.
	 * Informally: Set the city field.
	 */
    public void setCity(String c)
    {
        city=c;
    }

    /**
	 * getSport()
	 * 
	 * @return String -- the value in the sport field
	 * 
	 * Precondition: None
	 * Postcondition: the current object's sport field is returned.
	 * Informally: Get the sport field.
	 */
    public String getSport()
    {
        return sport;
    }

	/**
	 * setSport()
	 * 
	 * @param s String -- String to be stored in sport field of athlete
	 * 
	 * Precondition: None
	 * Postcondition: the current object's sport field is assigned the given
	 * 					value.
	 * Informally: Set the sport field.
	 */
    public void setSport(String s)
    {
        sport=s;
    }

    /**
	 * getEvent()
	 * 
	 * @return String -- the value in the event field
	 * 
	 * Precondition: None
	 * Postcondition: the current object's event field is returned.
	 * Informally: Get the event field.
	 */
    public String getEvent()
    {
        return event;
    }

	/**
	 * setEvent()
	 * 
	 * @param e String -- String to be stored in event field of athlete
	 * 
	 * Precondition: None
	 * Postcondition: the current object's event field is assigned the given
	 * 					value.
	 * Informally: Set the event field.
	 */
    public void setEvent(String e)
    {
        event=e;
    }

    /**
	 * getResult()
	 * 
	 * @return String -- the value in the result field
	 * 
	 * Precondition: None
	 * Postcondition: the current object's name result is returned.
	 * Informally: Get the name result.
	 */
    public String getResult()
    {
        return result;
    }

	/**
	 * setResult()
	 * 
	 * @param r String -- String to be stored in result field of athlete
	 * 
	 * Precondition: None
	 * Postcondition: the current object's result field is assigned the given
	 * 					value.
	 * Informally: Set the result field.
	 */
    public void setResult(String r)
    {
        result=r;
    }

	/**
	 * toString()
	 * 
	 * @return String -- printable form of the athlete's data
	 * 
	 * Precondition: None
	 * Postcondition: A printable (String) form of the athlete's data is
     *                  returned.
	 * Informally: Convert an athlete's data to a String.
	 */
    public String toString()
    {
        return year + ": " + name + " (" + noc + ") - " + sport + "; " + event + " -  Result: " + result;
    }
}