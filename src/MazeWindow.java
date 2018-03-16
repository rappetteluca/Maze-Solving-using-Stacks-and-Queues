/**
 * Class that creates GUI interface, and can take loaded maze-file data and turn
 * it into an on-screen maze.
 *
 * @author M. Allen
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.filechooser.*;

@SuppressWarnings( "serial" )
public class MazeWindow extends JFrame implements ActionListener
{
    // array to store maze squares
    private Square[][] maze;
    
    // GUI elements
    private Label info;
    private JPanel panel = new JPanel( null );
    private JButton load, stack, queue, step, toggle;
    private JFileChooser chooser;
    private String mazeFileName;
    private Timer timer;
    private Solver solver;
    
    // constants for graphics layout
    private final int sideBuffer = 10;
    private final int topBuffer = 50;
    private final int gridWidth = 500;
    private final int gridHeight = 500;
    private final int windowWidth = gridWidth + ( 2 * sideBuffer );
    private final int windowHeight = gridHeight + topBuffer + ( 2 * sideBuffer );
    
    /**
     * Constructor for basic window elements.
     */
    public MazeWindow()
    {
        setTitle( "A-maze-in'!" );
        setVisible( true );
        setLayout( null );
        getContentPane().setBackground( new Color( 175, 238, 238 ) );
        setResizable( false );
        setBounds( 50, 50, windowWidth, windowHeight + getInsets().top );
        setDefaultCloseOperation( EXIT_ON_CLOSE );
    }
    
    /**
     * Constructs all the GUI elements and sets up overall program
     * functionality.
     */
    public void makeWindow()
    {
        // chooser for files
        chooser = new JFileChooser();
        chooser.setCurrentDirectory( new java.io.File( "." ) );
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                                                                     "Text Files", "txt" );
        chooser.setFileFilter( filter );
        
        // Timer for animation
        timer = new Timer( 100, this );
        
        // labels/buttons/what have you
        int gap = ( windowWidth - ( 5 * 70 ) ) / 6;
        load = new JButton( "Load" );
        load.setBounds( gap, 10, 70, 25 );
        load.addActionListener( this );
        add( load, 0 );
        stack = new JButton( "Stack" );
        stack.setBounds( 70 + ( 2 * gap ), 10, 70, 25 );
        stack.addActionListener( this );
        add( stack, 0 );
        queue = new JButton( "Queue" );
        queue.setBounds( 140 + ( 3 * gap ), 10, 70, 25 );
        queue.addActionListener( this );
        add( queue, 0 );
        step = new JButton( "Step" );
        step.setBounds( 210 + ( 4 * gap ), 10, 70, 25 );
        step.addActionListener( this );
        add( step, 0 );
        toggle = new JButton( "Run" );
        toggle.setBounds( 280 + ( 5 * gap ), 10, 70, 25 );
        toggle.addActionListener( this );
        add( toggle, 0 );
        
        info = new Label( "No maze loaded." );
        info.setBackground( getContentPane().getBackground() );
        info.setBounds( gap, 40, gridWidth, 20 );
        add( info, 0 );
        
        repaint();
    }
    
    /**
     * Utility method to switch off timer if necessary, and display message;
     * should be called when a search for a path in the maze has failed.
     */
    public void fail()
    {
        if ( timer.isRunning() )
            switchToggle();
        
        info.setText( "Failed.  Goal cannot be reached from start." );
    }
    
    /**
     * Utility method to switch off timer if necessary, and display message;
     * should be called when a search for a path in the maze has succeeded.
     */
    public void succeed()
    {
        if ( timer.isRunning() )
            switchToggle();
        info.setText( "Success!  Goal has been found." );
    }
    
    /* Method to respond to events from GUI elements like buttons. */
    public void actionPerformed( ActionEvent e )
    {
        // load a maze-file
        if ( e.getSource() == load )
        {
            if ( timer.isRunning() )
                switchToggle();
            
            openFile();
        }
        
        // once a maze file is properly loaded, respond to other buttons
        if ( mazeFileName != null )
        {
            if ( e.getSource() == stack )
            {
                if ( timer.isRunning() )
                    switchToggle();
                
                solver.setStack();
                info.setText( "Solver set to use stack-based solution." );
            }
            if ( e.getSource() == queue )
            {
                if ( timer.isRunning() )
                    switchToggle();
                
                solver.setQueue();
                info.setText( "Solver set to use queue-based solution." );
            }
            if ( e.getSource() == step )
            {
                solver.solve();
                repaint();
            }
            if ( e.getSource() == toggle )
            {
                switchToggle();
            }
            
            if ( e.getSource() == timer )
            {
                solver.solve();
            }
            
            repaint();
        }
    }
    
    /**
     * Pre: Maze data should be properly read into input character array.
     * Post: Maze data used to generate on-screen display.
     *
     * @param mz A rectangular array of characters corresponding to maze
     *            layout, loaded from an input file.
     */
    private void buildMaze( char[][] mz )
    {
        // sets dimensions for maze
        int rows = mz.length;
        int cols = mz[0].length;
        int w = gridWidth / cols;
        int h = gridHeight / rows;
        int off = ( gridWidth - ( cols * w ) ) / 2;
        
        // clears existing maze (if any)
        panel.removeAll();
        panel.setBounds( sideBuffer + off, topBuffer + sideBuffer, cols * w,
                        rows * h );
        panel.setBackground( getContentPane().getBackground() );
        add( panel, 0 );
        
        // lays out the new maze
        maze = new Square[rows][cols];
        for ( int r = 0; r < rows; r++ )
            for ( int c = 0; c < cols; c++ )
            {
                maze[r][c] = new Square();
                maze[r][c].setBackground( chooseColor( mz[r][c] ) );
                maze[r][c].setBounds( c * w, r * h, w, h );
                maze[r][c].setRow( r );
                maze[r][c].setCol( c );
                panel.add( maze[r][c] );
            }
        
        repaint();
    }
    
    /* Utility method for setting maze square colors. */
    private Color chooseColor( char c )
    {
        if ( c == 'X' )
            return Color.black;
        if ( c == '_' )
            return Color.white;
        if ( c == 'S' )
            return Color.green;
        if ( c == 'G' )
            return Color.red;
        
        return getBackground();
    }
    
    /* Post: mazeFileName == file chosen by user (if any). */
    private void openFile()
    {
        if ( chooser.showOpenDialog( this ) == JFileChooser.APPROVE_OPTION )
        {
            mazeFileName = chooser.getSelectedFile().getName();
            info.setText( "Maze file " + mazeFileName + " is loaded." );
            
            // load maze and generate Solver object
            MazeMaker maker = new MazeMaker( mazeFileName );
            buildMaze( maker.getMaze() );
            solver = new Solver( maze, this );
        }
    }
    
    /* Helper method to switch the timer and its matching button back & forth. */
    private void switchToggle()
    {
        if ( timer.isRunning() )
        {
            timer.stop();
            toggle.setText( "Run" );
        }
        else
        {
            timer.start();
            toggle.setText( "Pause" );
        }
    }
}
