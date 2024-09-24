/**
 * YearsInterface.java
 * 
 * KIT107 Assignment 2 -- Years Specification
 * 
 * @author Julian Dermoudy
 * @version	6/9/2024
 * 
 * FILE IS COMPLETE
 */


public interface YearsInterface
{
    //public Years();
    public boolean isEmpty();
    public void addAthleteToYears(Athlete a);
    public int showMostSuccessfulYear(String noc);
    public void showWinningNOC(int year);
    public void showMedalTally(int year);
    public String toString();
    public String toString(int year);
}