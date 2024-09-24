/**
 * AthleteInterface.java
 * 
 * KIT107 Assignment 2 -- Athlete Specification
 * 
 * @author Julian Dermoudy
 * @version	6/9/2024
 * 
 * FILE IS COMPLETE
 */


public interface AthleteInterface
{
    //public Athlete(String n, char g, String t, String a, int y, String c, String s, String e, String r);
    public String getName();
    public void setName(String n);
    public char getSex();
    public void setSex(char g);
    public String getTeam();
    public void setTeam(String t);
    public String getNOC();
    public void setNOC(String n);
    public int getYear();
    public void setYear(int y);
    public String getCity();
    public void setCity(String c);
    public String getSport();
    public void setSport(String s);
    public String getEvent();
    public void setEvent(String e);
    public String getResult();
    public void setResult(String r);
    public String toString();
}