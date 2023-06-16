package Task1MultiThreadApp;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MultiThreadSorting {

	public static void main(String[] args) throws InterruptedException, FileNotFoundException {
		
		Scanner fileInput = new Scanner(System.in);//scanner for user input
		
		System.out.println("Please enter default .txt file: 'src/unsorted.txt' or enter own .txt (look at format in readme)");
		String userFile = fileInput.next();
		
		File unsortedNums = new File(userFile);
		Scanner fileScan = new Scanner(unsortedNums); //scanner for process file
		int[] list = new int[10];
		
		int index = 0; 
		while(fileScan.hasNextLine()) { //counts how many nums are in the txt file
			list[index] = fileScan.nextInt();
			index++;
		}
	
		System.out.print("Unsorted Array:"); //displays unsorted array
		for(int i = 0; i < 10; i++) {
			System.out.print(list[i] + " ");
			
		}
		
		System.out.println();
		
//		int[] list = {7,12,19,3,18,4,2,6,15,8};
		int length = list.length; 
		int middle = length/2; //find middle of list
		
		//divide array into 2 diff parts
		int [] left = new int[middle];
		int [] right = new int[length-middle];
		//create an empty array to store the sorted array
		int [] sortedList = new int[length];
		
		//fills up left array
		for(int i = 0; i < middle; i++) {
			left[i] = list[i];
		}
		
		//fills up right array
		int leftCounter = 0;
		for(int i = middle; i < length; i++) {
			right[leftCounter] = list[i];
			leftCounter++;
		}
		//create sorting objects 
		Sorting one = new Sorting(left); //sorting obj for left
		Sorting two = new Sorting(right);//sorting obj for right
	
		one.start(); //starts the sorting process for left array
		two.start(); //starts the sorting process for right array
		
		one.join(); //wraps up sorting process for left array
		two.join(); //wraps up sorting process for right array
		
		Merging all = new Merging(left,right,sortedList); //Merging object to merge left and right list
		
		all.start(); //starts the merging process for left & right array
		all.join(); //wraps up merging process for left & right array
		
		System.out.print("Sorted Array:"); //prints sorted array
		for(int i = 0; i < length; i++) {
		System.out.print(sortedList[i] + " ");
	}

		
	}
		

}
