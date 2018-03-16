import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Converts a text-file input to an array of characters that can be used to
 * generate a graphical image of a maze.
 *
 * @author M. Allen
 * @author Lucas Rappette
 */

public class MazeMaker
{
    // an array of character values corresponding to the maze
    private char[][] maze;
    
    /**
     * Constructor that converts the input file into a character-array.
     * Input file must be in /makeFiles/
     *
     * @param mazeFileName Text file containing maze data.
     */
    public MazeMaker( String mazeFileName )
    {
    	try
    	{
    		File mazeFile = new File("./mazeFiles/" + mazeFileName);
    		FileInputStream fis = new FileInputStream(mazeFile);
    		Scanner input = new Scanner(fis);
    		int lineCount = 0;
    		BufferedReader lineReader = 
    				new BufferedReader(new FileReader(mazeFile));
    		
    		//No easy way to preemptively determine the number of rows 
    		//in a text file, BufferedReader is a simple workaround.
    		while(lineReader.readLine() != null)
    			lineCount++;
    		
    		lineReader.close();
    		maze = new char[lineCount][];
    		lineCount = 0;
    		
    		while(input.hasNext())
    		{
    			String s = input.next();
    			char[] chars = s.toCharArray();
    			maze[lineCount] = chars;
    			lineCount++;
    		}
    		input.close();
    	}
    	catch (FileNotFoundException e)
    	{
    		System.out.println("Invalid Maze selected ");
    		maze = new char[0][0];
    	} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * Returns the character array corresponding to the maze.
     * 
     * @return Rectangular array of characters that correspond to input
     *         maze-file.
     */
    public char[][] getMaze()
    {
        return maze;
    }
}
