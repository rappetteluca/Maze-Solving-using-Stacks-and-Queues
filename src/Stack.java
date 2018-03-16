/**
 * A generic stack structure, based on a generic java.util.LinkedList.
 * Implements the generic StackOrQueue interface, so it can be used by the
 * Solver interchangeably with a queue-based implementation.
 *
 * @author M. Allen
 *
 * @param <SomeType> The type of data stored by the list.
 */
import java.util.LinkedList;

public class Stack<SomeType> implements StackOrQueue<SomeType>
{
    private LinkedList<SomeType> list;
    
    /**
     * post: list == an empty LinkedList<SomeType>
     */
    public Stack()
    {
        list = new LinkedList<SomeType>();
    }
        
    /**
     * Stack operation to access front element in LIFO fashion without removing.
     *
     * @return SomeType object currently at top of stack.
     */
    public SomeType top()
    {
        return lookAtFirst();
    }
    
    /**
     * Stack operation to add object to list in LIFO fashion.
     *
     * @param s SomeType object added to top of stack.
     */
    public void push( SomeType s )
    {
        insert(s);
    }
    
    /**
     * Stack operation to remove object in LIFO fashion.
     *
     * @return SomeType object removed from top of stack.
     */
    public SomeType pop()
    {
        return removeFirst();
    }

	@Override
	/**
	 * Stack operation which removes the top object on the stack.
	 * 
	 * @return the SomeType object removed from list.
	 */
	public SomeType removeFirst() 
	{
		return list.remove(0);
	}

	@Override
	/**
	 * Add object to list in Stack fashion (LIFO)
	 * 
	 * @param s SomeType object being inserted
	 * 
	 */
	public void insert(SomeType s) 
	{
		list.addFirst(s);
	}

	@Override
	/**
	 * Checks to see if the size of list is zero.
	 * 
	 * @return true if list is empty and false if list has at least one element.
	 */
	public boolean isEmpty() 
	{
		return list.isEmpty();
	}

	@Override
	/**
	 * Returns the first element in list, but preserves the element's position.
	 * 
	 * @return SomeType object at the top of the stack.
	 */
	public SomeType lookAtFirst() 
	{
		return list.getFirst();
	}
    
}