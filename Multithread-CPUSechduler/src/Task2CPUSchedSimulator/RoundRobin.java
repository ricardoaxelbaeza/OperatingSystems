package Task2CPUSchedSimulator;

import java.util.Arrays;

public class RoundRobin {
	
	int[] ID;
	int[] arrivaltime;
	int[] burst;
	int[] priority;
	int[] sortedTime;
	
	int[] readyQueue;
	int[] burstCopy;
	int[] endTimes;
	boolean idle = false;

	int[] startTimes;
	int[] processOrder;

	int lastEnd;
	int currentBurst;
	int[] RT;
	
	int[] processEndingOrder;
	int [] responseTimes;
	int timeQuantum; 
	int idleTime;
	int[] TT;
	int [] WT;
	int []sortedEnds;

	public RoundRobin(int[] ID, int[] arrivaltime, int[] burst, int[] priority, int timeQuantum) {
		// TODO Auto-generated constructor stub
		this.ID = ID;
		this.arrivaltime = arrivaltime;
		this.burst = burst;
		this.priority = priority;
		this.timeQuantum = timeQuantum;
	}
	
//	public void orderOfExecution() {
//		sortedTime = Arrays.copyOf(arrivaltime, arrivaltime.length);// copy of arrivaltimes array
//		Arrays.sort(sortedTime); // sorts the arrival times of processes
//		endTimes = new int[sortedTime.length];
//		processEndingOrder = new int[ID.length];
//		startTimes = new int[sortedTime.length];
//		processOrder = new int[ID.length];
//
//		int time = -1; //keeps track of time for process exec & idle time
//		//vars helps us keep track if any burst times are similar and be able to differntiate in code
//		int lastI = -1; 
//		int lastJ = -1;
//		//compares the sorted arrival times to regular arrival times
//		for (int i = 0; i < sortedTime.length; i++) {
//			for (int j = 0; j < arrivaltime.length; j++) {
//				if (sortedTime[i] == arrivaltime[j]) { //if the arrival times are the same, then...
//					//handles idle time from beginning if none of the processes begin at AT 0 
//					if (i == 0) { 
//						if (sortedTime[i] > 0) {
//							idleTime += sortedTime[i];
//							for (int t = 0; t < sortedTime[i]; t++) {
//								time++;
//								System.out.println("Idle " + time + " ms");
//							}
//							idle = true;
//							idleTime = (arrivaltime[j]);
//							lastEnd = sortedTime[i];
//						} // end of if sorted time > 0
//
//						// run the next process after idle
//						//updates info regaring: burst, endTimes array, process order etc.
//						currentBurst = burst[j];
//						endTimes[i] = currentBurst + lastEnd; //populate arr so calc such as TT can be calc
//						processEndingOrder[i] = ID[j]; 
//						lastEnd = endTimes[i];
//
//						startTimes[i] = (time + 1);//keeps track of strt times for RT
//						for (int t = 0; t < currentBurst; t++) {
//							time++;
//							System.out.println("P" + ID[j] + " executing (" + time + " ms)");
//						}
//						System.out.println("P" + ID[j] + " finished executing (" + (time + 1) + " ms)");
//						processOrder[i] = ID[j]; //adds to process order
//
//					} else {//if there was no idle time from the start, then we can execute normally
//						startTimes[i] = (time + 1);
//						boolean repeat = false; 
//
//						if (arrivaltime[j] <= lastEnd) { //if we have any repeated arrival times right after eachother, this handles them, so the code does not confuse them
//							if (lastJ == arrivaltime[j]) {
//								repeat = true;
//							}
//
//							if (repeat == true) { //handles repeat as mentioned earlier
//								System.out.println("repeat entered");
//								processOrder[i] = ID[j];
//								i++;
//								arrivaltime[j] = arrivaltime[j + 1];
//								System.out.println("val j: " + (i));
//								System.out.println("last I in repeat " + lastI);
//
//							}
//							
//							//process will execute
//							currentBurst = burst[j];
//							// process index 1+ execute
//							endTimes[i] = currentBurst + lastEnd;
//							processEndingOrder[i] = ID[j];
//							lastEnd = endTimes[i];
//
//							for (int t = 0; t < currentBurst; t++) {
//								time++;
//								System.out.println("P" + ID[j] + " executing (" + time + " ms)");
//							}
//							System.out.println("P" + ID[j] + " finished executing (" + (time + 1) + " ms)");
//							processOrder[i] = ID[j];
//							
//							//handles if repeat operation
//							lastJ = arrivaltime[j];
//							if (repeat == true) {
//								endTimes[i - 1] = lastEnd;
//								processEndingOrder[i - 1] = ID[j];
//								lastI = lastI + 1;
//							} else {
//								lastI = i;
//
//							}
//
//						} else {// means there will be idle
//
//							int temp = (arrivaltime[j] - lastEnd);
//							idleTime = idleTime + (arrivaltime[j] - lastEnd); 
//
//							lastEnd += (arrivaltime[j] - lastEnd);
//
//							idle = true;
//
//							for (int t = 0; t < temp; t++) {
//								time++;
//								System.out.println("Idle " + time + " ms");
//							}
//							// run process after idle
//							currentBurst = burst[j];
//							endTimes[i] = currentBurst + lastEnd;
//							processEndingOrder[i] = ID[j];
//							lastEnd = endTimes[i];
//
//							startTimes[i] = (time + 1);
//							for (int t = 0; t < currentBurst; t++) {
//								time++;
//								System.out.println("P" + ID[j] + " executing (" + time + " ms)");
//							}
//							System.out.println("P" + ID[j] + " finished executing (" + (time + 1) + " ms)");
//							processOrder[i] = ID[j];
//
//						}
//
//					}
//				}
//			}
//		}
//	}
	
	public void getLength() {
		sortedTime = Arrays.copyOf(arrivaltime, arrivaltime.length);//copy of arrivaltimes array
		Arrays.sort(sortedTime); //sorts the arrival times of processes
		
		//calculates total burst 
		int totalBurst = 0;
		for(int i = 0; i < burst.length;i++) {
			totalBurst += burst[i];
		}

		int timeTracker = 0; //keeps track of when a process has finished executing
		int k = 0; //variable that helps keep track of indexes to store all processes finished execution times
		
		//initialize arrays
		endTimes = new int[ID.length - 1];
		

		
		int temp = 1;//keeps track of time for processes that are still in execution
		//compares the sorted arrival times to regular arrival times
		for(int i = 0; i < sortedTime.length;i++) {
			for(int j = 0; j < arrivaltime.length; j++) {
				if(sortedTime[i] == arrivaltime[j]) { //if the arrival times are the same, then
//					System.out.print("P" + ID[j] + " ");
					//process is now under execution. 
					for(int time = 0; time <= totalBurst; time++) {//keep track of how long the process has been running
						if(time==burst[j]) { //if the time is equal to the burst of the running process, then
							timeTracker += time; //keeps track of time for how long a process ran (this is incremented after each process based on its execution time) which keeps track of when a process ends
//							System.out.println("----------------------------------------");
//							System.out.println("P"+(ID[j])+" finished execution (" + timeTracker +"ms)"); 
//							System.out.println("----------------------------------------");
							endTimes[k] = timeTracker; //keeps track of the processes end of execution time, so that it can be used later of TT
//							processEndingOrder[k] = ID[j];//keeps track of the order processes run
//							totalBurst = totalBurst - time;//decrementing total burst after each process runs
							k++;//increment k so that we can store the ending time of a process in the next iteration
						}

					}

				}//end if statement that determines execution order & time exec
			}//end for loop that goes through OG arrival times
		}//end for loop that goes through sorted arrival times
		
		int length = (int) Math.ceil(((endTimes[4])/timeQuantum)+1);
		endTimes = new int[length];
	
//		for(int i = 0; i < ID.length; i++) {
//			System.out.print(processEndingOrder[i] + " ");
//		}
//		System.out.println();
//		for(int i = 0; i < ID.length; i++) {
//			System.out.print(endTimes[i] + " ");
//		}
	}//end of order of execution
	
	public void orderOfExecution() {
		sortedTime = Arrays.copyOf(arrivaltime, arrivaltime.length);//copy of arrivaltimes array
		Arrays.sort(sortedTime); //sorts the arrival times of processes
		
		//calculates total burst 
		int totalBurst = 0;
		for(int i = 0; i < burst.length;i++) {
			totalBurst += burst[i];
		}

		int timeTracker = 0; //keeps track of when a process has finished executinh
		int k = 0; //variable that helps keep track of indexes to store all processes finished execution times
		
		//initialize arrays
//		endTimes = new int[ID.length];
		processEndingOrder = new int[ID.length];
		int temp = 1;
		//keeps track of time for processes that are still in execution
		System.out.print("FCFS Order:\n");
		//compares the sorted arrival times to regular arrival times
		for(int i = 0; i < sortedTime.length;i++) {
			for(int j = 0; j < arrivaltime.length; j++) {
				if(sortedTime[i] == arrivaltime[j]) { //if the arrival times are the same, then
//					System.out.print("P" + ID[j] + " ");
					//process is now under execution. 
					for(int time = 0; time < timeQuantum; time++) {//keep track of how long the process has been running
						if(time==burst[j]) { //if the time is equal to the burst of the running process, then
							timeTracker += time; //keeps track of time for how long a process ran (this is incremented after each process based on its execution time) which keeps track of when a process ends
							System.out.println("----------------------------------------");
							System.out.println("P"+(ID[j])+" finished execution (" + (temp - 1) +"ms)"); 
							System.out.println("----------------------------------------");
							endTimes[k] = timeTracker; //keeps track of the processes end of execution time, so that it can be used later of TT
							processEndingOrder[k] = ID[j];//keeps track of the order processes run
							totalBurst = totalBurst - time;//decrementing total burst after each process runs
							k++;//increment k so that we can store the ending time of a process in the next iteration
						}
						else {//if a process time is less than total burst, it means its still under execution
							if(time < burst[j]) {//so we print the process is under execution and its time each ms
							System.out.println("P"+(ID[j])+" still executing (" + temp +"ms)");
							temp++;
							}
						}
					}

					

				}//end if statement that determines execution order & time exec
			}//end for loop that goes through OG arrival times
		}//end for loop that goes through sorted arrival times
//		for(int i = 0; i < ID.length; i++) {
//			System.out.print(processEndingOrder[i] + " ");
//		}
//		System.out.println();
//		for(int i = 0; i < ID.length; i++) {
//			System.out.print(endTimes[i] + " ");
//		}
	}//end of order of execution
	
	public float avgTurnaroundTime() {
		TT = new int[burst.length]; //create a TT array to store TT times
		sortedEnds = new int[ID.length]; //create array to store sorted End times
		
		for(int i = 0; i < ID.length; i++) {//sorting the ordered ID (in terms of execution order) to OG ID array
			for(int j = 0; j < ID.length; j++) {
				if(ID[i] == processEndingOrder[j]) {//done by comparing regular IDs and in order IDs
					sortedEnds[i] = endTimes[j];
				}
			}
		}//by doing so, we're able to get the corresponding execution end times associated with the original ID order so we can actually compute TT
//		System.out.println("sorted ends: ");
//		for(int i = 0; i < ID.length; i++) {
//			System.out.print(sortedEnds[i] + " ");
//		}
//		System.out.println("arrival times");
//		for(int i = 0; i < ID.length; i++) {
//			System.out.print(arrivaltime[i] + " ");
//		}
		
		
		for(int i = 0; i < burst.length; i++) {
			TT[i] = sortedEnds[i] - arrivaltime[i];//Stores TT times from sorted ends(respective to ID og order) subtracted by the respective arrival time
		}
		

		float TottalTT = 0;
		for(int i = 0; i < burst.length; i++) {
			TottalTT += TT[i]; //adds all of the content in the TT array together
		}
//		System.out.println("TT Times: ");
//		for(int i = 0; i < burst.length; i++) {
//			System.out.print(TT[i] + " ");
//		}
//		System.out.println("Burst Times: ");
//		for(int i = 0; i < burst.length; i++) {
//			System.out.print(burst[i] + " ");
//		}
		
		return (float) TottalTT/ID.length; //returns the AVg TT time
	}
	
	public float avgWaitingTime() {
		WT = new int[TT.length]; //creates new array to store Waiting Time values
		for(int i = 0; i < TT.length; i++) {
			WT[i] = TT[i] - burst[i]; //takes in Turnaround time values & subtracts them from the respective Waiting times
		}
		float totalWT = 0;
		for(int i = 0; i < WT.length; i++) {
		totalWT += WT[i];//add all values in WT
		}
		return totalWT/(WT.length );//return average Waiting Time
	}
	
	public float avgResponseTime() {
		responseTimes = new int[(ID.length)];
		responseTimes[0] = 0;
		
		//endtimes are modified so that they are in the form of start time
		int[] updatedEndTime = new int[ID.length]; //update the endtimes so they are actually the arrival times of the processes
		updatedEndTime[0] = 0;
		
		int tempy = 0;
		for(int i = 1; i < ID.length; i++) {
			updatedEndTime[i] = endTimes[tempy];
			tempy++;
		}
		
		//the actual operation to get the response times for each process is done here
		for(int i = 0; i < ID.length; i++) {
			responseTimes[i] = (updatedEndTime[i] - arrivaltime[i]) ;
		}
		
		float totalResponseTimes= 0;
		//all of the content of each response time for each process is done here
		for(int i = 0; i < responseTimes.length; i++) {
			totalResponseTimes += responseTimes[i];//add all values in responseTimes
			}
		return totalResponseTimes/(ID.length );//return average of response times
		
	}
	
	public float cpuUtil() {
		int totaltime = endTimes[4];
		if (idle == false) {
			return (float) 100.00;
		}else {
			float cpuTil = (totaltime-idleTime)/totaltime;
			return cpuTil;
		}
	}
	
	
}
