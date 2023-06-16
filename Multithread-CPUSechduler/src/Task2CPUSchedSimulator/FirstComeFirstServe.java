package Task2CPUSchedSimulator;

import java.util.Arrays;

public class FirstComeFirstServe {

	int[] ID;
	int[] arrivaltime;
	int[] burst;
	int[] sortedTime;
	int idleTime;
	boolean idle = false;
	int currentBurst;
	int lastEnd;

	int[] endTimes;
	int[] processEndingOrder;
	int[] responseTimes;
	int[] sortedRT;
	int[] processOrder;

	int[] sortedEnds;
	int[] startTimes;

	int[] TT; // array that holds Turn around time for processes
	int[] WT; // array that holds Waiting times for processes
	int[] RT;

	public FirstComeFirstServe(int[] ID, int[] arrivaltime, int[] burst) {
		this.ID = ID;
		this.arrivaltime = arrivaltime;
		this.burst = burst;
	}

	public void orderOfExecution() {
		sortedTime = Arrays.copyOf(arrivaltime, arrivaltime.length);// copy of arrivaltimes array
		Arrays.sort(sortedTime); // sorts the arrival times of processes
		endTimes = new int[sortedTime.length];
		processEndingOrder = new int[ID.length];
		startTimes = new int[sortedTime.length];
		processOrder = new int[ID.length];

		int time = -1; //keeps track of time for process exec & idle time
		//vars helps us keep track if any burst times are similar and be able to differntiate in code
		int lastI = -1; 
		int lastJ = -1;
		//compares the sorted arrival times to regular arrival times
		for (int i = 0; i < sortedTime.length; i++) {
			for (int j = 0; j < arrivaltime.length; j++) {
				if (sortedTime[i] == arrivaltime[j]) { //if the arrival times are the same, then...
					//handles idle time from beginning if none of the processes begin at AT 0 
					if (i == 0) { 
						if (sortedTime[i] > 0) {
							idleTime += sortedTime[i];
							for (int t = 0; t < sortedTime[i]; t++) {
								time++;
								System.out.println("Idle " + time + " ms");
							}
							idle = true;
							idleTime = (arrivaltime[j]);
							lastEnd = sortedTime[i];
						} // end of if sorted time > 0

						// run the next process after idle
						//updates info regaring: burst, endTimes array, process order etc.
						currentBurst = burst[j];
						endTimes[i] = currentBurst + lastEnd; //populate arr so calc such as TT can be calc
						processEndingOrder[i] = ID[j]; 
						lastEnd = endTimes[i];

						startTimes[i] = (time + 1);//keeps track of strt times for RT
						for (int t = 0; t < currentBurst; t++) {
							time++;
							System.out.println("P" + ID[j] + " executing (" + time + " ms)");
						}
						System.out.println("P" + ID[j] + " finished executing (" + (time + 1) + " ms)");
						processOrder[i] = ID[j]; //adds to process order

					} else {//if there was no idle time from the start, then we can execute normally
						startTimes[i] = (time + 1);
						boolean repeat = false; 

						if (arrivaltime[j] <= lastEnd) { //if we have any repeated arrival times right after eachother, this handles them, so the code does not confuse them
							if (lastJ == arrivaltime[j]) {
								repeat = true;
							}

							if (repeat == true) { //handles repeat as mentioned earlier
								System.out.println("repeat entered");
								processOrder[i] = ID[j];
								i++;
								arrivaltime[j] = arrivaltime[j + 1];
								System.out.println("val j: " + (i));
								System.out.println("last I in repeat " + lastI);

							}
							
							//process will execute
							currentBurst = burst[j];
							// process index 1+ execute
							endTimes[i] = currentBurst + lastEnd;
							processEndingOrder[i] = ID[j];
							lastEnd = endTimes[i];

							for (int t = 0; t < currentBurst; t++) {
								time++;
								System.out.println("P" + ID[j] + " executing (" + time + " ms)");
							}
							System.out.println("P" + ID[j] + " finished executing (" + (time + 1) + " ms)");
							processOrder[i] = ID[j];
							
							//handles if repeat operation
							lastJ = arrivaltime[j];
							if (repeat == true) {
								endTimes[i - 1] = lastEnd;
								processEndingOrder[i - 1] = ID[j];
								lastI = lastI + 1;
							} else {
								lastI = i;

							}

						} else {// means there will be idle

							int temp = (arrivaltime[j] - lastEnd);
							idleTime = idleTime + (arrivaltime[j] - lastEnd); 

							lastEnd += (arrivaltime[j] - lastEnd);

							idle = true;

							for (int t = 0; t < temp; t++) {
								time++;
								System.out.println("Idle " + time + " ms");
							}
							// run process after idle
							currentBurst = burst[j];
							endTimes[i] = currentBurst + lastEnd;
							processEndingOrder[i] = ID[j];
							lastEnd = endTimes[i];

							startTimes[i] = (time + 1);
							for (int t = 0; t < currentBurst; t++) {
								time++;
								System.out.println("P" + ID[j] + " executing (" + time + " ms)");
							}
							System.out.println("P" + ID[j] + " finished executing (" + (time + 1) + " ms)");
							processOrder[i] = ID[j];

						}

					}
				}
			}
		}

//		for(int i = 0; i < endTimes.length; i++) {
//			System.out.println(endTimes[i]);
//		}
//		for(int i = 0; i < ID.length; i++) {
//			System.out.println(processEndingOrder[i]);
//		}
//		System.out.println("start times");
//		for(int i = 0; i < ID.length; i++) {
//			System.out.println(startTimes[i]);
//		}	
//		System.out.println("process order");
//		for(int i = 0; i < ID.length; i++) {
//			System.out.println(processOrder[i]);
//		}	

	}// end of order of execution

	public float avgTurnaroundTime() {
		TT = new int[burst.length]; // create a TT array to store TT times
		sortedEnds = new int[ID.length]; // create array to store sorted End times

		for (int i = 0; i < ID.length; i++) {// sorting the ordered ID (in terms of execution order) to OG ID array
			for (int j = 0; j < ID.length; j++) {
				if (ID[i] == processEndingOrder[j]) {// done by comparing regular IDs and in order IDs
					sortedEnds[i] = endTimes[j];
				}
			}
		} // by doing so, we're able to get the corresponding execution end times
			// associated with the original ID order so we can actually compute TT

		for (int i = 0; i < burst.length; i++) {
			TT[i] = sortedEnds[i] - arrivaltime[i];// Stores TT times from sorted ends(respective to ID og order)
													// subtracted by the respective arrival time
		}

		float TottalTT = 0;
		for (int i = 0; i < burst.length; i++) {
			TottalTT += TT[i]; // adds all of the content in the TT array together
		}

		return (float) TottalTT / ID.length; // returns the AVg TT time
	}

	public float avgWaitingTime() {
		WT = new int[TT.length]; // creates new array to store Waiting Time values
		for (int i = 0; i < TT.length; i++) {
			WT[i] = TT[i] - burst[i]; // takes in Turnaround time values & subtracts them from the respective Waiting
										// times
		}
		float totalWT = 0;
		for (int i = 0; i < WT.length; i++) {
			totalWT += WT[i];// add all values in WT
		}
		return totalWT / (WT.length);// return average Waiting Time
	}

	public float avgResponseTime() {
		RT = new int[ID.length];

		for (int i = 0; i < ID.length; i++) {
			for (int j = 0; j < ID.length; j++) {
				if (ID[i] == processOrder[j]) {
					RT[i] = startTimes[j] - arrivaltime[i];
				}
			}
		}

		float totalResponseTimes = 0;
		// all of the content of each response time for each process is done here
		for (int i = 0; i < RT.length; i++) {
			totalResponseTimes += RT[i];// add all values in responseTimes
		}

		return totalResponseTimes / (ID.length);// return average of response times

	}

//	
	public float cpuUtil() {
		int totaltime = endTimes[(ID.length - 1)];
		if (idle == true) {
			float cpuTil = totaltime - idleTime;
			cpuTil = cpuTil / totaltime;
			cpuTil = cpuTil * 100;

			return cpuTil;
		} else {
			return (float) 100.00;

		}
	}

}
