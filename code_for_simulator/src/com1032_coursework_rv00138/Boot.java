package com1032_coursework_rv00138;

import java.io.*;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.nio.file.*;
import java.text.ParseException;
import java.net.*;

public class Boot {
	static File file = new File("C:\\Users\\Rio\\Documents\\surrey\\first yr\\COM1032\\rv00138_com1032_InputFiles\\os_initialise.txt");
	private List<String> list = new ArrayList<String>();
	private int NumOfProcessors = 0;
	private int RAMSizeMMU = 0;
	private int DeskSize = 0;
	private List<String> input = new ArrayList<String>();
	private List<String> output = new ArrayList<String>();
	private List<String> pScheduling = new ArrayList<String>();

	public void initialiseOS() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String a = null;
		
		try {
			while ((a = br.readLine()) != null) {
				list.add(a);
			}

		} 
		catch (FileNotFoundException e) {
			System.err.println("File not found");
		} 
		catch (IOException e) {
			System.err.println("Unable to read the file.");
		}
		
		br.close();

		/*
		 * Number of processor this simulator will use
		 */
		this.NumOfProcessors = Integer.parseInt(list.get(0));
		/*
		 * Size of the RAM for the MMU
		 */
		this.RAMSizeMMU = Integer.parseInt(list.get(1));
		/*
		 * Desk Size for the File Manager
		 */
		this.DeskSize = Integer.parseInt(list.get(2));
		/*
		 * Adds all input devices into an ArrayList
		 */
		this.input.add(list.get(3));
		this.input.add(list.get(4));
		/*
		 * Adds all output devices into an ArrayList
		 */
		this.output.add(list.get(5));
		this.output.add(list.get(6));
		/*
		 * Stores all the scheduling algorithms the simulator is capable of
		 */
		this.pScheduling.add(list.get(7));
		this.pScheduling.add(list.get(8));
		this.pScheduling.add(list.get(9));

		
		System.out.println("OS initialised");

	}

}
