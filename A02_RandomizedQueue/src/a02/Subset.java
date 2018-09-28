package a02;

import edu.princeton.cs.algs4.StdOut;

public class Subset {
	/**
	 * 
	 * Prints Random Subset in respects to int k passed to method randomQueSubsetPrint
	 * @param args
	 */
	public static void main(String[] args)
	{
		System.out.println("Subset, size(3)");
		printRandomSample(3);

		System.out.println("Subset, size(3)");
		printRandomSample(3);

		System.out.println("Subset, size(8)");
		printRandomSample(8);
				
	}


	/**
	 * Prints Random k Strings form Subset
	 */
	private static void printRandomSample(int k) {
		String[] inputData = {"AA","BB","BB","BB","BB","BB","CC","CC"};		
		RandomizedQueue<String> randomQueue = new RandomizedQueue<String>();
		
		for(String el: inputData) {
			randomQueue.enqueue(el);
		}
		
		for (int i= 0; i < k; i++)
		{
			StdOut.println(randomQueue.dequeue());
		}
		StdOut.println();
		
	}
}
