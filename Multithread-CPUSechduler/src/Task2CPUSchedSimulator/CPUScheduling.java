package Task2CPUSchedSimulator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class CPUScheduling {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);//scanner for user input
		Scanner fileInput = new Scanner(System.in);//scanner for user input
		
		System.out.println("Please enter default .txt file: 'src/new_test.txt' or enter own .txt (look at format in readme)");
		String userFile = fileInput.next();
		
		File processInfo = new File(userFile);
		Scanner fileScan = new Scanner(processInfo); //scanner for process file
		int[] wholeArr = new int[24];
		
		int index = 0; 
		while(fileScan.hasNextLine()) { //counts how many nums are in the txt file
			wholeArr[index] = fileScan.nextInt();
			index++;
		}
	
		System.out.print("Array:");
		for(int i = 0; i < 20; i++) {
			System.out.print(wholeArr[i] + " ");
			
		}
		System.out.println();
		
//		int[] ID = {1,2,3,4,5};
//		int[] arrivaltime = {4,6,0,6,5};
//		int[] burst = {1,2,3,4,5};
//		int[] priority = {3,2,4,5,1};
//		int timeQuantum = 2; 
		
//		int[] ID = {1,2,3,4,5};
//		int[] arrivaltime = {0,4,2,6,3};
//		int[] burst = {10,2,1,5,3};
//		int[] priority = {1,2,3,45};
		
//		int[] wholeArr = {1,2,3,4,5,0,4,2,6,3,10,2,1,5,3,1,2,3,4,5};
		int numInArrays = wholeArr.length/4;
		int[] ID = new int[numInArrays];
		int[] arrivaltime = new int[numInArrays];
		int[] burst = new int[numInArrays];
		int[] priority = new int[numInArrays];
		
		int i = 0;
		int j = 0;
		int k = 0;
		int l = 0;
		int m = 0;
		while(i < wholeArr.length) { //ID
			for(j = 0; j < numInArrays; j++) {
				ID[j] = wholeArr[j];
				
			}
			for(k = 0; k < numInArrays; k++) { //Arrival Time
				arrivaltime[k] = wholeArr[j+k];
				i = i + numInArrays;
			}
			k = k + j;
			
			for(l = 0; l < numInArrays; l++) { //Burst
				burst[l] = wholeArr[k+l];
				i = i + numInArrays;
			}
			l = k + l;

			for(m = 0; m < numInArrays; m++) { //Burst
				priority[m] = wholeArr[l+m];
				i = i + numInArrays;
			}
			i = numInArrays * 4;
		}
		int timeQuantum;
		System.out.println("\nOptions: (1)FCFS, (2)SJF, (3)Preemptive Priority, (4)RR");
		System.out.println("\nPlease enter the number of the Scheduling Algorith you'd like to run:");
		int userOption = scan.nextInt();
		switch(userOption) {
		case 1: 
			System.out.println("First Come First Serve\n");
			FirstComeFirstServe fcfs = new FirstComeFirstServe(ID,arrivaltime,burst);
			//order of process execution
			fcfs.orderOfExecution();
			
//			avg tt calculation
			float avgTT = fcfs.avgTurnaroundTime();
			System.out.println("Average Turnaround Time = "+ avgTT + "ms");
//			
			//avg wt calculation
			float avgWT = fcfs.avgWaitingTime();
			System.out.println("Average Waiting Time = " + avgWT + "ms");
			float avgRT = fcfs.avgResponseTime();
			System.out.println("Average Response Time = " + avgRT + "ms");
			float cputil = fcfs.cpuUtil();
			System.out.println("CPU Utlization = " + cputil);
			break;
		case 2:  
			System.out.println("Shortest Job First\n");
			ShortestJobFirst sjf = new ShortestJobFirst(ID,arrivaltime,burst);
			sjf.orderOfExecution();
			
			//avg tt calculation
			float avegTT = sjf.avgTurnaroundTime();
			System.out.println("Average Turnaround Time = "+ avegTT + "ms");
			//avg wt calculation
			float avegWT = sjf.avgWaitingTime();
			System.out.println("Average Waiting Time = " + avegWT + "ms");
			float avegRT = sjf.avgResponseTime();
			System.out.println("Average Response Time = " + avegRT + "ms");
			float cputili = sjf.cpuUtil();
			System.out.println("CPU Utlization = " + cputili);
			break;
		case 3: 
			System.out.println("Non Preemptive Priority Scheduling\n");
			PreemptivePriority pps = new PreemptivePriority(ID,arrivaltime,burst,priority);
			pps.orderOfExecution();
			//avg tt calculation
			float avrgTT = pps.avgTurnaroundTime();
			System.out.println("Average Turnaround Time = "+ avrgTT + "ms");
			//avg wt calculation
			float avrgWT = pps.avgWaitingTime();
			System.out.println("Average Waiting Time = " + avrgWT + "ms");
			float avrgRT = pps.avgResponseTime();
			System.out.println("Average Response Time = " + avrgRT + "ms");
			float cputiliz = pps.cpuUtil();
			System.out.println("CPU Utlization = " + cputiliz);
			break;
		case 4:
//			System.out.println("Round Robin\n");
//			timeQuantum = 10;
//			RoundRobin rr = new RoundRobin(ID,arrivaltime,burst,priority,timeQuantum);
////			rr.getLength();
//			rr.orderOfExecution();
//			float verageTT = rr.avgTurnaroundTime();
//			System.out.println("Average Turnaround Time = "+ Math.abs(verageTT) + "ms");
//			//avg wt calculation
//			float verageWT = rr.avgWaitingTime();
//			System.out.println("Average Waiting Time = " + Math.abs(verageWT) + "ms");
//			float verageRT = rr.avgResponseTime();
//			System.out.println("Average Response Time = " + verageRT + "ms");
//			float cputilizi = rr.cpuUtil();
//			System.out.println("CPU Utlization = " + cputilizi);
;			break;
		default: 
			System.out.println("Please choose a valid option.");
			break;
		}
		
		
		
		
		
		
		
		
		
	
		
		

//		//Turn around time
//		int[] TT = new int[burst.length];
//		
//		for(int i = 0; i < burst.length; i++) {
//			TT[i] = burst[i] - arrivaltime[i];
//		}
//		
//		for(int i = 0; i < burst.length; i++) {
//			System.out.println(TT[i]);
//		}
//		
//		float TottalTT = 0;
//		for(int i = 0; i < burst.length; i++) {
//			TottalTT += TT[i];
//		}
//		
//		float avgTT = TottalTT/ID.length;
//		System.out.println("avg:" + avgTT);
		
		
		
//		int [] sortedTime = Arrays.copyOf(arrivaltime, arrivaltime.length);//done
//		
//		
//		Arrays.sort(sortedTime);//done
//		
//		System.out.print("FCFS Order:");
//		for(int i = 0; i < sortedTime.length;i++) {
//			for(int j = 0; j < arrivaltime.length; j++) {
//				if(sortedTime[i] == arrivaltime[j]) {
////					System.out.print("P" + ID[j] + " ");
//					for(int time = 0; time <= burst[j]; time++) {
//						if(time==burst[j]) {
//							System.out.println("P"+i+" finished execution (" + time +"ms)");
//						}else {
//							System.out.println("P"+i+" still running (" + time + "ms)");
//						}
//					}
//				}//end if statement that determines execution order & time exec
//			}//end for loop that goes through OG arrival times
//		}//end for loop that goes through sorted arrival times
//		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		for(int i = 0; i < burst.length; i++) {
//			
//		}
		
		//process execution update
//		for(int i = 0; i < sortedTime[i]; i++) {
//			
//		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
//		File file = new File("");
//		Scanner scan = new Scanner(file);
//		
//		int stopper = 4;
//		if(scan.hasNext() && stopper > 0) {
//		
//		}else if(stopper == 0){
//			
//		}else {
//			
//		}
		
//		String str = "PId:30";
//		str = str.replaceAll("\\D", "");
//		System.out.println(str);
		
		
		

	}

}
