/**
 * A simple graphical class for square maze elements.
 * Each element will also store its (row, col) position in the overall maze
 * layout on-screen.
 *
 * @author M. Allen
 *
 */
import javax.swing.JComponent;
import java.awt.Graphics;

@SuppressWarnings( "serial" )
public class Square extends JComponent
{
    private int row, col;
    private boolean flag = false;
    
    /**
     * Sets the row value of this Square.
     *
     * @param r Row value of the square.
     */
    public void setRow( int r )
    {
        row = r;
    }
    
    /**
     * Sets the column value of this Square.
     *
     * @param c Column value of the square.
     */
    public void setCol( int c )
    {
        col = c;
    }
    
    /**
     * Returns the row value of this Square.
     *
     * @return Row value of this Square.
     */
    public int getRow()
    {
        return row;
    }
    
    /**
     * Returns the column value of the square.
     * @return Column value of the square.
     */
    public int getCol()
    {
        return col;
    }
    
    /**
     * Sets the flag on a square of whether or not it has been used already.
     * 
     * @param boo True/False if the square in question has been "seen" by solve()
     */
    public void setFlag(boolean boo)
    {
    	flag = boo;
    }
    
    /**
     * Returns Whether or not this square has been seen.
     * 
     * @return True if the square in question has been used by the solve method.
     */
    public boolean getFlag()
    {
    	return flag;
    }
    
    /* Utility method called implictly to draw object on-screen. */
    public void paint( Graphics g )
    {
        g.setColor( getBackground() );
        g.fillRect( 0, 0, getWidth(), getHeight() );
        paintChildren( g );
    }
}