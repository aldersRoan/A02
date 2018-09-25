package a02;

import java.util.Deque;
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
	private int last;
	
	//Deque<Item> randomQueue;
	
	 /**
	 * Constructs an empty randomized queue.
	 * 
	 */
	@SuppressWarnings("unchecked")
	public RandomizedQueue() {
		queue = (Item[]) new Object[2];
		size = 0;
		first = 0;
		last = 0;
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
	 * @return int 
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
		int index = randomQueueIndex();
		Item element = queue[index];
		queue[index] = queue[--size];
		queue[size] = null;
		queue[index] = null;
		size--;
		
		if(size > 0 && size == queue.length/4) {
			resize(queue.length/2);
		}
		return element;
	}
	
	/**
	 * Returns a random item
	 * @return
	 * 
	 */
	public Item sample() {
		return null; // TODO
		// return (but do not delete) a random item
	}
	
	/**
	 * Returns a independent iterator over items in random order
	 * @return
	 * 
	 */
	public Iterator<Item> iterator() {
		return null; //TODO
		// return an independent iterator over items in random order
	}

	/**
	 * RandomizedQueue Test Client
	 * @param args
	 */
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
			last = size;
		}
	}
	
	private int randomQueueIndex() {
		return StdRandom.uniform(0, size);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
