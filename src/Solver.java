import java.awt.Color;

/**
 * Maze solving code. Switches between using a stack and a queue to do its work,
 * depending upon what the user chooses. If a stack is used, we get Depth-First
 * search, and if a queue is used, we get breadth-first search.
 *
 * @author M. Allen
 * @author Lucas Rappette
 */

public class Solver
{
	
	private StackOrQueue<Square> list;
	private Square[][] mazeGrid;
	private MazeWindow window;

    /**
     * Initializes the instance variables for the maze and GUI, along with
     * setting the initial boolean array of visited flags.
     *
     * @param mz A rectangular array of Square objects representing a maze.
     * @param win A MazeWindow for displaying GUI elements and messages.
     */
    public Solver( Square[][] mz, MazeWindow win )
    {
    	mazeGrid = mz;
    	window = win;
    }
    
    /**
     * Pre: Maze should already be loaded.
     * Post: Solution list will be set to be a stack, and search is initialized
     * to find starting (green) square in maze.
     */
    public void setStack()
    {
        list = new Stack<Square>();
        for (int i = 0; i < mazeGrid.length; i++)
        {
        	for(int j =0; j < mazeGrid[i].length; j++)
        	{
        		mazeGrid[i][j].setFlag(false);
        		if(mazeGrid[i][j].getBackground() == Color.green)
        		{
        			Square s = mazeGrid[i][j];
        			list.insert(s);
        		}
        		else if(mazeGrid[i][j].getBackground() == Color.RED || 
        				mazeGrid[i][j].getBackground() == Color.BLACK)
        		{
        			//Do nothing
        		}
        		else 
        		{
        			mazeGrid[i][j].setBackground(Color.WHITE);
        		}
        	}
        }
    }
    
    /**
     * Pre: Maze should already be loaded.
     * Post: Solution list will be set to be a queue, and search is initialized
     * to find starting (green) square in maze.
     */
    public void setQueue()
    {
    	list = new Queue<Square>();
        for (int i = 0; i < mazeGrid.length; i++)
        {
        	for(int j =0; j < mazeGrid[i].length; j++)
        	{
        		mazeGrid[i][j].setFlag(false);
        		if(mazeGrid[i][j].getBackground() == Color.green)
        		{
        			Square s = mazeGrid[i][j];
        			list.insert(s);
        		}
        		else if(mazeGrid[i][j].getBackground() == Color.RED || 
        				mazeGrid[i][j].getBackground() == Color.BLACK)
        		{
        			//Do nothing
        		}
        		else 
        		{
        			mazeGrid[i][j].setBackground(Color.WHITE);
        		}
        	}
        }
    }
    
    /**
     * Method to perform a single search iteration. To be called by either the
     * "Step" or "Solve" buttons in the GUI. When repeated, performs search to
     * solve the maze.
     *
     * @return none
     */
    public void solve()
    {
        if(list.isEmpty() || list == null)
        {
        	window.fail();
        	return;
        }
        Square s = list.removeFirst();
        if(s.getFlag())
        {
        	Color col = s.getBackground();
        	if (col != Color.GREEN)
        	s.setBackground(col.darker());
        }
        if (s.getBackground() == Color.RED)
        {
        	window.succeed();
        	return;
        }
        
        if(s.getBackground() == Color.WHITE || s.getBackground() == Color.GREEN)
        {
        	if(s.getBackground() == Color.WHITE)
        	{
        		s.setBackground(Color.CYAN);
        	}
        	s.setFlag(true);
        	
        	Square s1 = null;
        	Square s2 = null;
        	Square s3 = null;
        	Square s4 = null;
        	
        	if (s.getRow() < mazeGrid.length - 1)
        		s1 = mazeGrid[s.getRow() + 1][s.getCol()];
        	if (s.getRow() > 0)
        		s2 = mazeGrid[s.getRow()-1][s.getCol()];
        	if (s.getCol() < mazeGrid[s.getRow()].length - 1)
        		s3 = mazeGrid[s.getRow()][s.getCol() + 1];
        	if (s.getCol() > 0)
        		s4 = mazeGrid[s.getRow()][s.getCol()-1];
        	
        	if (s1 != null)
        	{
        		if(s1.getBackground() != Color.BLACK)
        		{
        			list.insert(s1);	
        		}
        	}
        	if (s2 != null)
        	{
        		if(s2.getBackground() != Color.BLACK)
        		{
        			list.insert(s2);	
        		}
        	}
        	if (s3 != null)
        	{
        		if(s3.getBackground() != Color.BLACK)
        		{
        			list.insert(s3);	
        		}
        	}
        	if (s4 != null)
        	{
        		if(s4.getBackground() != Color.BLACK)
        		{
        			list.insert(s4);	
        		}
        	}
        }
    }
}
