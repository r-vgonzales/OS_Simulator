package com1032_coursework_rv00138;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class PS_FCFS {

	static File processes = new File("C:\\Users\\Rio\\Documents\\surrey\\first yr\\COM1032\\rv00138_com1032_InputFiles\\Process_details.txt");
	private String s = null;
	private int a = 0;
	private int b = 0;
	private static int n = 0;
	private static List<Integer> finalArrival = new ArrayList<Integer>();
	private static List<Integer> expectedRun = new ArrayList<Integer>();
	private static int[] wt = new int[5];
	private static int[] ta = new int [5];
	/*
	 * finding the waiting time for all processes
	 */
	 static void findWaitingTime(List<Integer> finalArrival, int n, 
			 List<Integer> expectedRun, int wt[]) { 
		wt[0] = 0; // for the first process waiting time is 0

		 for (int  i = 1; i < n; i++) { 
			
			 wt[i] = expectedRun.get(i-1) + wt[i-1];

	        } 
	 }
	 
	 /*
	  * calculating the turn around time 
	  */
	 static void findTurnAroundTime(List<Integer> finalArrival, int n, 
			 List<Integer> expectedRun, int wt[], int ta[]) { 
		 
		 // calculate turn around time by adding waiting time and batch time (expected time to run)
		 for (int i = 0; i < n; i++)  {
			 
			 ta[i] = expectedRun.get(i) + wt[i];
		 }
	 }
	 
	 /*
	  * getting the average time
	  */
	public static void findAvgTime(List<Integer> finalArrival, int n,
			List<Integer> expectedRun)  {
	
        int total_wt = 0;
        int total_tat = 0; 
        findWaitingTime(finalArrival,n, expectedRun, wt);
        findTurnAroundTime(finalArrival, n, expectedRun, wt,ta);
		/*
		 * // average waiting time 
		 */
        System.out.println("Processes Burst Time Waiting Time Turn Around Time\n");
        
        //calculate total waiting time and total turn around time
        for (int i = 0; i < n; i++) { 
            total_wt = total_wt + wt[i]; 
            total_tat = total_tat + ta[i]; 
            System.out.printf(" %d ", (i + 1)); 
            System.out.printf("\t\t %d ", expectedRun.get(i));
            System.out.printf("\t %d ", wt[i]); 
            System.out.printf("\t    %d\n", ta[i]); 
        }
        
        float avgWT = (float)total_wt / (float)n;
        int avgtotalTAT = total_tat /n;
        
        System.out.printf("Average waiting time = " + avgWT); 
        System.out.printf("\n"); 
        System.out.printf("Average turn around time = " + avgtotalTAT);
        System.out.println("\n");
        
        
	}
	
	public void getProcesses() throws IOException  {
		BufferedReader br = new BufferedReader(new FileReader(processes));
		
		try {
			while ((s = br.readLine()) != null)  {
				String[] parts = s.split(" ");
				
				String arrival = parts[0];
				String runTime = parts[1];
				
				a = Integer.parseInt(arrival);
				b = Integer.parseInt(runTime);
				
				finalArrival.add(a);
				expectedRun.add(b);
				
			}
		}
		catch (FileNotFoundException e) {
            System.err.println("File not found");
        } catch (IOException e) {
            System.err.println("Unable to read the file.");
    		br.close();
        }
	}
	
	
	public void fcfsSorting() throws IOException  {
		
		  this.getProcesses();
		  n = finalArrival.size();
		  
		  findAvgTime(finalArrival, n, expectedRun);
	  
	  }
	 

}
