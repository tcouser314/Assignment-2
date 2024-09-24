/**
 * NocInterface.java
 * 
 * KIT107 Assignment 2 -- NOC Specification
 * 
 * @author Julian Dermoudy
 * @version	6/9/2024
 * 
 * FILE IS COMPLETE
 */


public interface NocInterface
{
    //public Noc();
    public boolean isEmpty();
    public int getYear();
    public String getCity();
    public void addAthleteToNOC(Athlete a);
    public int getGoldCount(String noc);
    public String getWinningNOC();
    public void showMedalTally();
    public String toString();
}