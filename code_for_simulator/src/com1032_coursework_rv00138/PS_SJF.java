package com1032_coursework_rv00138;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PS_SJF {

	static File processes = new File("C:\\Users\\Rio\\Documents\\surrey\\first yr\\COM1032\\rv00138_com1032_InputFiles\\Process_details.txt");
	private String s = null;
	private int a = 0;
	private int b = 0;
	private static List<Integer> arrivalTime = new ArrayList<Integer>();
	private static List<Integer> expectedRun = new ArrayList<Integer>();
	private static HashMap<Integer, Integer> sorted = new HashMap<Integer, Integer>(); // arivaltime, burst time
	private static LinkedHashMap<Integer, Integer> sort = new LinkedHashMap<Integer, Integer>();
	private static int[] wt = new int[5];
	private static int[] ta = new int[5];
	private static int n = 0;
	private static float avgwt = 0;
	private static float avgta = 0;
	private static int total_wt = 0;
	private static int total_tat = 0;

	/*
	 * sort the burst times in ascending order, and make 
	 * them the values in a hashmap
	 */
	static void sortProcesses(int n, LinkedHashMap<Integer, Integer> sort) {
		Collections.sort(expectedRun);

		for (int i = 0; i < n ; i++) {
			for (Integer j : sorted.keySet()) {
				sort.put(j, expectedRun.get(i));
				
				  i++; 
				  j++;
				 
			}

		}
		
	}
	
	static void findWaitingTime(LinkedHashMap<Integer, Integer> sort, int n, int wt[]) {
		
		wt[0] = 0;
		/*
		 * takes the burst times and organises them
		 */
			List<Integer>  values = new ArrayList<Integer>(sort.values());
	
			for (int i = 1; i < wt.length; i++)  {
				
				wt[i] = values.get(i - 1) + wt[i-1];
			}

	}

	static void findTurnAroundTime(LinkedHashMap<Integer, Integer> sort, int n, 
			int wt[], int ta[])  {
		
		List<Integer> values = new ArrayList<Integer>(sort.values());

		for (int i=0; i < ta.length; i++)  {
			ta[i] = values.get(i) + wt[i];
		}

	}
	
	public static void findAvgTime(LinkedHashMap<Integer, Integer> sort, int n)  {
		total_wt = 0;
		total_tat = 0;
		
		findWaitingTime(sort, n, wt);
		findTurnAroundTime(sort, n, wt, ta);
	
		
        System.out.println("Processes    Burst Time    Waiting Time    Turn Around Time\n");
        
      //calculate total waiting time and total turn around time
      
        List<Integer> burstValues = new ArrayList<Integer>(sort.values());
        
        for (int i = 0; i < n; i++) { 
            total_wt = total_wt + wt[i]; 
            total_tat = total_tat + ta[i]; 
            System.out.printf(" %d", (i + 1)); 
            System.out.printf("\t\t %d", burstValues.get(i));
            System.out.printf("\t\t %d", wt[i]); 
            System.out.printf("\t\t %d\n", ta[i]); 
        }
        
        avgwt = total_wt / n;
        avgta = total_tat / n;
        
        System.out.printf("Average waiting time = " + avgwt);
        System.out.printf("\n");
        System.out.printf("Average turn around time = " + avgta);
        System.out.println("\n");

	}
	
	public void getProcesses() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(processes));

		try {
			while ((s = br.readLine()) != null) {
				String[] parts = s.split(" ");

				String arrival = parts[0];
				String runTime = parts[1];

				a = Integer.parseInt(arrival);
				b = Integer.parseInt(runTime);

				arrivalTime.add(a);
				expectedRun.add(b);
				sorted.put(a, b);

			}
		} catch (FileNotFoundException e) {
			System.err.println("File not found");
		} catch (IOException e) {
			System.err.println("Unable to read the file.");
			br.close();
		}
		

	}

	public void readyForDisplay() throws IOException {
		this.getProcesses();
		n = arrivalTime.size();
		sortProcesses(n, sort);
	
		findAvgTime(sort, n);
	}
}
