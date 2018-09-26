package a02;

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

/**
 * A randomized queue is similar to a stack or queue, except that the 
 * item removed is chosen uniformly at random from items in the data structure.
 * @author rjayb
 *
 */
public class RandomizedQueue<Item> {
	private Item[] queue;
	private int size;
	private int first;
	
	 /**
	 * Constructs an empty randomized queue.
	 * 
	 */
	@SuppressWarnings("unchecked")
	public RandomizedQueue() {
		queue = (Item[]) new Object[2];
		size = 0;
		first = 0;
	}
	
	/**
	 * Checks is queue is empty (true) or not (false).
	 * @return boolean
	 * 
	 */
	public boolean isEmpty() {
	   return size == 0; // 
	}
	
	/**
	 * Returns the number of items on the queue.
	 * @return 
	 * 
	 */
	public int size() {
	   return size;  // return the number of items on the queue
	}
	
	/**
	 * Adds an item to queue
	 * @param item
	 */
	public void enqueue(Item item) {
		if(item.equals(null)) {
			throw new NullPointerException();
		}
		if(size == queue.length) {
			resize(queue.length);
		}
		queue[size++] = item;
	   //randomQueue.addFirst(item);
	}
	
	/**
	 * Removes and returns random item from  queue
	 * @return
	 * 
	 */
	public Item dequeue() {
		if(isEmpty()) {
			throw new NoSuchElementException("The queue is Empty.");
		}
		int index = StdRandom.uniform(size);
		Item item = queue[index];
		queue[index] = queue[--size];			//to avoid loitering
		queue[size] = null;
		queue[index] = null;
		size--;
		
		if(size > 0 && size == queue.length/4) {
			resize(queue.length/2);
		}
		return item;
	}
	
	/**
	 * Returns a random item
	 * @return
	 * 
	 */
	public Item sample() {
		if(isEmpty()) {
			throw new NoSuchElementException("The queue is Empty.");
		}
		int index = StdRandom.uniform(size);
		return queue[index];
		// return (but do not delete) a random item
	}
	
	/**
	 * Returns a independent iterator over items in random order
	 * @return
	 * 
	 */
	public Iterator<Item> iterator() {
		return new RandomizedQueueIterator();
		// return an independent iterator over items in random order
	}
	
	private class RandomizedQueueIterator implements Iterator<Item> {
		private int index;
		private int[] clonedIndices;
		
		public RandomizedQueueIterator() {
			clonedIndices = new int[size];
			for(int i = 0; i < queue.length; i++) {
				clonedIndices[index] = i;
			}
			StdRandom.shuffle(clonedIndices);
		}
		@Override
		public boolean hasNext() {
			return index < size;
		}

		@Override
		public Item next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			Item item = queue[clonedIndices[index]];
			index++;
			
			return item;
			
		}
		
		@Override
		public void remove() {
	    	 throw new UnsupportedOperationException("Remove operation not supported"
		    	 		+ " in this version.");  			
		}
		
	}

	
	//Helper Methods
	
	@SuppressWarnings("unchecked")
	private void resize(int capacity) {
		if(capacity >= size) {
			Item[] temp = (Item[]) new Object[capacity];
			for(int i = 0; i < size; i++) {
				temp[i] = queue[(first + i) % queue.length];
			}
			queue = temp;
			first = 0;
		}
	}
}
