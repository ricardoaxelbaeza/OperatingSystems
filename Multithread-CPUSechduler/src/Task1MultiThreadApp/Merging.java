package Task1MultiThreadApp;

import java.util.Arrays;

public class Merging extends Thread {

	int totalLength;
	int left[];
	int right[];
	private int[] list;
	
	public Merging(int[] left, int[] right,int[] sortedList) { ////constructor for the passed lists and array to store sorted list
		 totalLength = left.length + right.length;
		 this.left = left;
		 this.right = right;
		 this.list = sortedList;
	}
	
	public void run() {//merges left and right list together
//		list = new int[totalLength]; //create array to store left & right array
		
		for(int i = 0; i < left.length; i++) {
			list[i] = left[i];
		}
		
		int j=0;
		for(int i = totalLength - left.length; i < totalLength; i++) {
			list[i] = right[j];
			j++;
		}
		
		Arrays.sort(list);//sorts list of left and right combined
		
	}

}
