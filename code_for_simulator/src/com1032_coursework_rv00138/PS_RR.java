package com1032_coursework_rv00138;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class PS_RR {

	static File processes = new File("C:\\Users\\Rio\\Documents\\surrey\\first yr\\COM1032\\rv00138_com1032_InputFiles\\Process_details.txt");
	private String s = null;
	private int a = 0;
	private int b = 0;
	private int c = 0;
	private static List<Integer> pid = new ArrayList<Integer>();
	private static List<Integer> expectedRun = new ArrayList<Integer>();
	private static List<Integer> arrivalTime = new ArrayList<Integer>();
	private static TreeMap<Integer, Integer> sortByArrival = new TreeMap<Integer, Integer>();
	private static int[] remTime = new int[5];
	private static int[] wt = new int[5];
	private static int[] ta = new int[5];
	private static int[] finished = new int[5];
	private static int n = 0;
	private static int quantum = 2;
	private static int total_wt = 0;
	private static int total_ta = 0;

	public static void findWaitingTime(int[] remTime, TreeMap<Integer, Integer> sortByArrival, int n,
			 int[] finished) {

		List<Integer> aTimes = new ArrayList<Integer>(sortByArrival.keySet());
		List<Integer> burst = new ArrayList<Integer>(sortByArrival.values());
		
		for (int i = 0; i < n; i++) {
			
			remTime[i] = burst.get(i);
			// i++;
		}

		int k = 0;

		/*
		 * processing until the remaining time of that process (index in remTime) is 0
		 */

		 while (true) {
				boolean done = true;
				for (int i = 0; i < n; i++) {
			
					if (remTime[i] > 0) {
					done = false;
						if (remTime[i] > quantum) {
						k += quantum;
						remTime[i] -= quantum;
					} 	else {
						
						k = k + remTime[i];
						remTime[i] = 0;
						finished[i] = k;
					}
			}
		}if (done == true) {
			break;
		}

		 }

	}

	public static void findTurnAroundTime(TreeMap<Integer, Integer> sortByArrival, int[] ta, 
			int[] wt, int[] finished) {

		List<Integer> aTimes = new ArrayList<Integer>(sortByArrival.keySet());
		List<Integer> burst = new ArrayList<Integer>(sortByArrival.values());

		for (int i = 0; i < n; i++) {
		
			  ta[i] = finished[i] - aTimes.get(i); 
			  wt[i] = ta[i] - burst.get(i);

		}
	}

	public static void findAvgTime(TreeMap<Integer, Integer> sortByArrival, int n, 
			int quantum, int wt[], int ta[]) {
		findWaitingTime(remTime, sortByArrival, n, finished);
		findTurnAroundTime(sortByArrival, ta, wt, finished);

		System.out.println("Processes " + " Arrival Time\t" + " Burst time " + " completion time" + " Turn Around Time "
				+ " Waiting time");

		List<Integer> aTimes = new ArrayList<Integer>(sortByArrival.keySet());
		List<Integer> burst = new ArrayList<Integer>(sortByArrival.values());

		for (int i = 0; i < n; i++) {
			total_wt = total_wt + wt[i];
			total_ta = total_ta + ta[i];

			System.out.printf(" %d", (i + 1));
			System.out.printf("\t\t %d", aTimes.get(i));
			System.out.printf("\t\t%d", burst.get(i));
			System.out.printf("\t\t %d", finished[i]);
			System.out.printf("\t\t %d", ta[i]);
			System.out.printf("\t\t %d \n", wt[i]);

		}

		float avgWT = (float) total_wt / (float) n;
		float avgtotalTAT =(float) total_ta /(float) n;
		System.out.println("Average waiting time = " + avgWT);
		System.out.println("Average turn around time = " + avgtotalTAT);
	}

	public void getProcesses() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(processes));

		try {
			while ((s = br.readLine()) != null) {
				String[] parts = s.split(" ");

				String id = parts[0];
				String runTime = parts[1];
				String arrival = parts[2];

				a = Integer.parseInt(id);
				b = Integer.parseInt(runTime);
				c = Integer.parseInt(arrival);

				pid.add(a);
				expectedRun.add(b);
				arrivalTime.add(c);

				sortByArrival.put(c, b);
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
		
		n = sortByArrival.size();
		
		findAvgTime(sortByArrival, n, quantum, wt, ta);

	}
}
