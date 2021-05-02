package com1032_coursework_rv00138;

import java.util.HashSet; 
import java.util.LinkedList; 
import java.util.Queue; 

public class FIFOMemory {
	
	// Method to calculate page faults using FIFO
    static int pageFaults(int pages[], int n, int capacity) 
    { 
    	/*
    	 * Represents set of current pages
    	 * Use an unordered set so we can quickly
    	 * see if a page is present or not
    	 */
        HashSet<Integer> values = new HashSet<>(capacity); 
       
        // To store the pages in queue manner, FIFO
        Queue<Integer> indexes = new LinkedList<>() ; 
       
        // Start from first page 
        int page_faults = 0; 
        for (int i=0; i<n; i++) 
        { 
            // Check if the set can hold more pages 
            if (values.size() < capacity) 
            { 
                // Insert it into set if not present 
                // already which represents page fault 
                if (!values.contains(pages[i])) 
                { 
                	values.add(pages[i]); 
       
                    // increment page fault 
                    page_faults++; 
       
                    // Push the current page into the queue 
                    indexes.add(pages[i]); 
                } 
            } 
       
            // If the set is full then need to perform FIFO 
            // i.e. remove the first page of the queue from 
            // set and queue both and insert the current page 
            else
            { 
                // Check if current page is not already 
                // present in the set 
                if (!values.contains(pages[i])) 
                { 
                    //Pop the first page from the queue 
                    int val = indexes.peek(); 
       
                    indexes.poll(); 
       
                    // Remove the indexes page 
                    values.remove(val); 
       
                    // insert the current page 
                    values.add(pages[i]); 
       
                    // push the current page into 
                    // the queue 
                    indexes.add(pages[i]); 
       
                    // Increment page faults 
                    page_faults++; 
                } 
            } 
        } 
       
        return page_faults; 
    } 
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int pages[] = {7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2}; 

		int capacity = 4; 
        System.out.println("When there are " + capacity + " frames");
		System.out.println("The number of page faults: " + pageFaults(pages, pages.length, capacity)); 
	}

}
