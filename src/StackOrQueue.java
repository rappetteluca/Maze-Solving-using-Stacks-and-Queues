/**
 * A generic interface for a list structure that can behave either like a stack
 * or like a queue.
 * 
 * @author M. Allen
 * 
 * @param <SomeType> The type of data stored by the list.
 */
public interface StackOrQueue<SomeType>
{
    /**
     * Removes the first element of the list, whether it is a stack or a queue.
     * 
     * @return An item of SomeType, the first element in the list.
     */
    public SomeType removeFirst();

    /**
     * Adds an element to the list, in order that depends upon whether the
     * implementing class is a stack or queue.
     * 
     * @param s An object of SomeType to be added to list.
     */
    public void insert( SomeType s );

    /**
     * @return True if and only if there are no data elements in the list.
     */
    public boolean isEmpty();

    /**
     * Returns the first element of the list, without removing it.
     * 
     * @return An item of SomeType, the first element in the list.
     */
    public SomeType lookAtFirst();
}
