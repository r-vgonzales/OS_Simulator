package com1032_coursework_rv00138;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;

public class Shell {
	static String dir = System.getProperty("user.dir"); // initial value

	public static void main(String[] args) throws java.io.IOException, ParseException {
		String a;
		String commandLine;
		ArrayList<String> history = new ArrayList<String>();
		BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

		Path currentRelativePath = Paths.get("");
		dir = currentRelativePath.toAbsolutePath().toString();

		//we break out with <control><C>
		while (true) {
			// read what they entered
			System.out.print("jsh>");
			commandLine = console.readLine();
			// if they entered a return, just loop again
			if (commandLine.equals(""))
				continue;
			else if (commandLine.equals("boot")) {
				Boot b = new Boot();
				b.initialiseOS();
				history.add(commandLine);
			}
				// to run first come first serve scheduling
			else if (commandLine.equals("fcfs"))  {
				PS_FCFS f = new PS_FCFS();
				f.fcfsSorting();
				history.add(commandLine);
			}
			//to run shortest job first scheduling
			else if (commandLine.equals("sjf"))  {
				PS_SJF s = new PS_SJF();
				s.readyForDisplay();
				history.add(commandLine);
			}
			//to run round robin scheduling
			else if (commandLine.equals("rr"))  {
				PS_RR r = new PS_RR();
				r.readyForDisplay();
				history.add(commandLine);
			}
			// to run least recently used page replacement
			else if (commandLine.equals("lru on memory"))  {
				LRUMemory.main(args);
				history.add(commandLine);
			}
			// to run first in first out page replacement
			else if (commandLine.equals("fifo on memory"))  {
				FIFOMemory.main(args);
				history.add(commandLine);
			}
			// check for history commands here, otherwise, fork a new process
			if (commandLine.equals("history")) {
				for (int i = 0; i < history.size(); i++)
					System.out.println(i + " " + history.get(i));
				continue;
			} else if (commandLine.equals("!!")) {
				if (history.size() > 0) {
					commandLine = history.get(history.size() - 2);
					System.out.println("will fork a process for last command " + commandLine);
				} else  {
					System.out.println("History is empty!");
				}
			} else if (commandLine.startsWith("!")) {
				int comandIndex = Integer.parseInt(commandLine.substring(1, 2));
				if (history.size() > comandIndex) {
					commandLine = history.get(comandIndex);
				} else  {
					System.out.println("History command at index " + comandIndex + " does not exist!");
			}
			forkProcess(commandLine);

			history.add(commandLine);
		}
		}
	}
		
		public static void forkProcess(String commandLine) {
			if (commandLine.equals(""))
				return;
			// parse the input to obtain the command
			ArrayList<String> command = new ArrayList<String>();
			for (String token : commandLine.split("\\s+")) // will match one or more whitespace charcters
				command.add(token);
			// (2) create a ProcessBuilder object
			ProcessBuilder pb = new ProcessBuilder(command);
			Process process = null;
		}
}
	

