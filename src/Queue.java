/**
 * A generic queue structure, based on a generic java.util.LinkedList.
 * Implements the generic StackOrQueue interface, so it can be used by the
 * Solver interchangeably with a stack-based implementation.
 *
 * @author M. Allen
 * @author YOUR NAME HERE
 *
 * @param <SomeType> The type of data stored by the list.
 */
import java.util.LinkedList;

public class Queue<SomeType> implements StackOrQueue<SomeType>
{
    private LinkedList<SomeType> list;
    
    /**
     * post: list == an empty LinkedList<SomeType>
     */
    public Queue()
    {
        list = new LinkedList<SomeType>();
    }
    
    /**
     * Queue operation to access front element in FIFO fashion without removing.
     *
     * @return SomeType object currently at front of queue.
     */
    public SomeType front()
    {
        return lookAtFirst();
    }
    
    /**
     * Queue operation to add object to list in FIFO fashion.
     *
     * @param s SomeType object added to back of queue.
     */
    public void enqueue( SomeType s )
    {
        insert(s);
    }
    
    /**
     * Queue operation to remove object in FIFO fashion.
     *
     * @return SomeType object removed from front of queue.
     */
    public SomeType dequeue()
    {
        return removeFirst();
    }

	@Override
	/**
	 * Queue operation which removes the top object on the queue.
	 * 
	 * @return the SomeType object removed from list.
	 */
	public SomeType removeFirst() 
	{
		return list.removeFirst();
	}

	@Override
	/**
	 * Add object to list in Queue fashion (FIFO)
	 * 
	 * @param s SomeType object being inserted
	 * 
	 */
	public void insert(SomeType s) 
	{
		list.addLast(s);
	}

	@Override
	/**
	 * Checks to see if the size of list is zero.
	 * 
	 * @return true if list is empty and false if list has at least one element.
	 */
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	/**
	 * Returns the first element in list, but preserves the element's position.
	 * 
	 * @return SomeType object at the top of the stack.
	 */
	public SomeType lookAtFirst() {
		return list.getFirst();
	}
}