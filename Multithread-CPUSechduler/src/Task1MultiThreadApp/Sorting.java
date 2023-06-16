package Task1MultiThreadApp;

import java.util.Arrays;

public class Sorting extends Thread{
	
	private int[] list;
	
	public Sorting(int[] passedList) { //constructor for the passed list (left or right)
		list = passedList;
		int listLength = list.length;
	}

	public void run() { //sorts the passed list (left or right)
		Arrays.sort(list);
	}
	


	
	
}
