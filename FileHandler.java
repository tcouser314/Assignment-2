/**
 * FileHandler.java
 * 
 * KIT107 Assignment 2 -- FileHandler Implementation
 * 
 * @author Julian Dermoudy
 * @version	6/9/2024
 * 
 * FILE IS COMPLETE
 */


import java.io.*;


public class FileHandler implements FileHandlerInterface
{
    protected BufferedReader input; // input stream
    protected String fileName;      // name of datafile

	/**
	 * Constructor
	 * 
	 * @param FILENAME final String -- filename for dataset
	 * 
	 * Precondition: None
	 * Postcondition: The new instance will have its fileName field assigned
	 * 					the given value and its input field assigned null.
	 * Informally: Initialise a FileHandler.
	 */
    public FileHandler(final String FILENAME)
    {
        fileName=FILENAME;
        input=null;
    }

	/**
	 * openFile()
	 * 
	 * Precondition: fileName has been initialised to the name/path of an
     *                  existing, readable file.
	 * Postcondition: The nominated file is open for reading and the
     *                  BufferedReader object is assigned to the input
     *                  instance variable.
	 * Informally: Open the datafile.
	 */
    public void openFile()
    {
        try
    {
		input = new BufferedReader(new FileReader(fileName));
		}
    catch (IOException e)
		{
		System.err.println("Error opening " + fileName + " for loading");
		System.exit(1);
		}
    }    
    
	/**
	 * closeFile()
	 * 
	 * Precondition: input refers to an open BufferedReader object.
	 * Postcondition: The file referred to by the input instance variable is
     *                  closed.
	 * Informally: Close the datafile.
	 */
    public void closeFile()
    {
        try
		{
			input.close();
		}
		catch (IOException e)
		{
			System.err.println("Error closing " + fileName + " -- " + e.toString());
		}
    }    
    
	/**
	 * readLine()
	 * 
     * @return String -- the next line of data from the text file
     * 
	 * Precondition: a CSV (comma-separated values) text file has been
     *                  opened for reading and its reference assigned to the
     *                  input instance variable.
	 * Postcondition: the next (non-comment) line from the text file is
     *                  returned (or null if end-of-file is reached).
	 * Informally: Get the next line of data from the datafile.
	 */
	public String readLine()
	{
   		String line;    // line from file

	line=null;

	try
	{
        // get line from the file, skipping lines beginning with #
	line=input.readLine();
   			while ((line != null) && (line.indexOf('#') == 0)) // skip the comments
			{
				line=input.readLine();
			}
		}
		catch (IOException e)
		{
            System.err.println("Error in " + fileName + " data");
            System.exit(1);
		}
		return line;
	}    

	/**
	 * readFile()
	 * 
     * @param olympiad Years -- the collection of athletes to be constructed
     * 
	 * Precondition: a CSV (comma-separated values) text file exists and the
     *                  program has read permission for it, the file's name
     *                  has been stored in the fileName instance variable,
     *                  and the given Years parameter has been properly
     *                  constructed.
	 * Postcondition: the file is opened, read -- filling the collection
     *                  referred to on the parameter list -- and closed.
	 * Informally: Read the data from the datafile and store the data within
     *                  the given parameter.
	 */
    public void readFile(Years olympiad)
    {
        final int LIMIT = 500;    // number of athletes to process; reduce to, e.g., 25 when debugging
        
        String line;        // line of data read from file
        String []tokens;    // line of data broken into fields
        Athlete athlete;    // athlete created from the line of data
        int count;          // count of number of athletes read from file

        // prepare for reading
        count=0;
        openFile();

        // until end-of-file or LIMIT reached, read athlete's data, tokenise, and store in collection
        line=readLine();
		while ((line != null) && (count < LIMIT))
        {
            count++;
            // System.out.println("Line " + count + " of " + LIMIT + " read\n\t" + line + "\n"); // uncomment when debugging
            
            tokens=line.split(",");
            athlete=new Athlete(tokens[0],tokens[1].charAt(0),tokens[2],tokens[3],Integer.parseInt(tokens[4]),tokens[5],tokens[6],tokens[7],tokens[8]);
            
			olympiad.addAthleteToYears(athlete);
            line=readLine();
        }

        // close the file
        closeFile();
    }
}