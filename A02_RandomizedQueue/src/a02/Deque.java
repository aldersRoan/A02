package a02;

import java.util.Iterator; 
import java.util.NoSuchElementException;

/**
 * @author rjayb, Aaron Sadler
 * A double-ended queue or deque (pronounced “deck”) is a generalization of 
 * a stack and a queue that supports adding and removing items from either 
 * the front or the back of the data structure. 
 * 
 * Reference: https://algs4.cs.princeton.edu/code/ "Stack.java, Queue.java, (September 17, 2018)
 * Reference: https://algs4.cs.princeton.edu/13stacks/DoublyLinkedList.java.html (September 21, 2018)
 * @param <Item> 
 *  
 */

public class Deque<Item> implements Iterable<Item> {
	// Fields
	 private Node firstItem;    
	 private Node lastItem; 
	 private int size;
	 
     /**
	 * Done
	 * Inner class that saves item as well as their next/ previous items 
	 * 
	 */
     private class Node { 
        private Item item; // saves item
        private Node next; // save pointer to next node
        private Node previous; // save pointer to previous node
     }
			
	/**
	 * DONE
	 * Constructs an empty Deque object
	 *  
	 */
	public Deque()
	{
		firstItem = new Node();
		lastItem = new Node();
		firstItem.item = null;
		lastItem.item = null;
		size = 0;
	}
	
	/**
	 * Done
	 * Returns true if Deque object is empty, returns false if it is not. 
	 * @return boolean 
	 * 
	 */
	public boolean isEmpty() 
	{
		return size == 0; 
	}
	   
	 /**
	 * Done
	 * Returns the number of items on a Deque. 
	 * @return number of items on Deque
	 * 
	 */
	public int size() 
	{
		return size; 
	}
	  
	 /**
	 * Done
	 * Adds item to the front of a Deque.
	 * @param item
	 * 
	 */
	public void addFirst(Item item)
	{
		nullExceptionCheck(item);
		
		Node oldFirst = firstItem; // create Node (initially firstItem = pointer at first.next)
		firstItem = new Node(); // item being passed is now = firstItem stored by inner class Node
		firstItem.item = item; // Node is now equal to item being added
		firstItem.next = oldFirst; // Sets pointer of first item oldFirst.
		
		if (isEmpty())
        {
        	lastItem = firstItem; // if list is empty the item being passed is the first and last item
        }
		if (!isEmpty())
		{
			oldFirst.previous = firstItem; // making reference for node to previous item
		}
		size++; // Increments size of list
	}

	/**
	 * Done
	 * Adds item to the end of the Deque.
	 * @param item
	 * 
	 */
	public void addLast(Item item) {
		nullExceptionCheck(item);
		
		Node oldLastItem = lastItem; // save item in current last position in Node inner class as oldLast
		lastItem = new Node(); // assign lastItem to new item being passed
		lastItem.item = item; // pass item being added to new Nodes item.
		lastItem.next = null; // Removes pointer to oldLast item
       
		if (isEmpty())
        {
        	firstItem = lastItem; // if list is empty the item being passed is the first and last item
        }
        else
        {
        	oldLastItem.next = lastItem; // point the old last item at the new last item. 
        	lastItem.previous = oldLastItem;
        }
		
        size++; // Increments size of list
     }
	 
	/**
	 * Done
     * Removes and returns the first item on this Deque.
     *
     * @return the item removed from the front of Deque
     * @throws NoSuchElementException if this queue is empty
     * 
     */
    public Item removeFirst() 
    {
    	emptyListCheck(); 
    	
        Item item = firstItem.item;  
        firstItem = firstItem.next;
        if (isEmpty()) lastItem = null;  // to avoid loitering I think I got this from one of Algs4 code in Stack and or Queue
        size--;

        return item;
    }
		
	/**
	 * TODO
	 * Removes item and returns item removed from the back of the Deque. 
	 * @return item removed in the last position
	 * @throws NoSuchElementException if this queue is empty
	 * 
	 */
	public Item removeLast() 
	{
		emptyListCheck();
		
		Item item = lastItem.item; 
		
		if (lastItem.previous != null)
		{
			lastItem = lastItem.previous;
		}        
		
		lastItem.next = null; // to avoid loitering we break connections
        size--;
        
        return item;
	}

	/**
     * Returns an iterator that iterates over the items in this Deque from front and back.
     * Reference: https://algs4.cs.princeton.edu/13stacks/DoublyLinkedList.java.html
     * 
     * @return an iterator that iterates over the items in this Deque.
     */
	 public Iterator<Item> iterator()  
	 {
	        return new DequeIterator();  
	 }

	 //@SuppressWarnings("hiding")
	 private class DequeIterator implements Iterator<Item> 
	 {
		 private Node current = firstItem.next;
		 
		 
		 public boolean hasNext()  
	     { 
	       	return current != null;                     
	     }
	       
	     public void remove()      
	     { 
	    	 throw new UnsupportedOperationException("Remove operation not supported"
	    	 		+ " in this version.");  
	     }

	     public Item next() 
	     {
	    	 if (!hasNext()) 
	    		 throw new NoSuchElementException();
	            
	    	 Item item = current.item;
	         current = current.next; 
	          
	         return item;
	     }
	 } 
	 
     /**
     * Checks for null exceptions, throws illegalArgumentException
     * @param item being passed
     * 
     */
    private void nullExceptionCheck(Item item)
    {
		if (item == null) throw new java.lang.IllegalArgumentException ("Cannot be null. ");
	}
    
	/**
	 * Checks if list is empty throws NoSuchElementException.
	 * 
	 */
	private void emptyListCheck() 
	{
		if (isEmpty()) throw new NoSuchElementException("List is empty: ");
	}
    

     
///////////////////////////////////////////Helper Methods//////////////////////////////////////////////////////////
    /**
     * Returns Item in front of list
     *
     * @return the item in front of the list
     * @throws NoSuchElementException if this queue is empty
     */
   
     public Item peekFirst() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return (Item) firstItem.item;
    }
    
    /**
	 * Prints a string representation of the Deque list
	 * 
	 */
    @Override
	public String toString() 
    {
		StringBuilder sb = new StringBuilder();
		Node current = firstItem;
		
		while(current != null) 
		{
			sb.append(current.item).append(" ");
			current = current.next;
		}
		
		return sb.toString();
	}
   
    /**
	 * Test Client
	 * @param args
	 */
	public static void main(String[] args) {
		
		Deque<String> testDeque = new Deque<String>();
		
		// Expected true
		System.out.println("isEmpty before adding: " + testDeque.isEmpty());
		
		//Adding items to front of list
		testDeque.addFirst("Hello"); // is back
		testDeque.addFirst("Hola");
		testDeque.addFirst("Bonjour"); // is front
		
		// print list
		System.out.println("list: " + testDeque);
		
		// Expected false
		System.out.println("isEmpty after adding: " + testDeque.isEmpty()); 
//		
		// Expected Bonjour
		System.out.println("Last item added to front of list: " +testDeque.peekFirst());

		// Expected: Bonjour
		System.out.println("Remove First: " + testDeque.removeFirst()); 
		System.out.println("list: " + testDeque);
		//expected Hola
		System.out.println("Remove First: " + testDeque.removeFirst()); 
		// expected Hello
		System.out.println("Remove First: " + testDeque.removeFirst());
		//List should be empty now. 
		System.out.println("isEmpty: " + testDeque.isEmpty()); 
		System.out.println();
		
///////////////////////////Breaks when running tests together///////////////////////////////
		// Expected true
		System.out.println("isEmpty before adding: " + testDeque.isEmpty());
		
		//Adding items to back of list
		testDeque.addLast("Hello"); // is front
		testDeque.addLast("Hola");
		testDeque.addLast("Bonjour"); // is back
		
		System.out.println("list: " + testDeque);
		
		// Expected false
		System.out.println("isEmpty after adding: " + testDeque.isEmpty()); 
		
		// Expected Hello
		System.out.println("Item at front: " +testDeque.peekFirst());

		// Expected: Bonjour
		System.out.println("Remove last: " + testDeque.removeLast()); 
		// Front of list expected (Hello)
		System.out.println("Same item at front: " + testDeque.peekFirst());
		
		// Last item (Hola)
		testDeque.iterator();
		
		System.out.println("list: " + testDeque);
		// expected Hola
		System.out.println("Remove Last: " + testDeque.removeLast()); 
		System.out.println("list: " + testDeque);
		
		// expected Hello
		System.out.println("Remove Last: " + testDeque.removeLast());
		//List should be empty now. 
		System.out.println("isEmpty: " + testDeque.isEmpty()); 
		
		System.out.println();	
	}// Main End
}// Deque Class End