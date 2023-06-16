package Task2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class DiskSchedulingRun {
	//Ricardo Baeza
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		Scanner user_init_head = new Scanner(System.in);
		Scanner option = new Scanner(System.in);
		Random random = new Random();
		
		int[] queue = null;
		int previous_head;
		int init_disk_head;
		int total_disk_cylinders = 5000;
		int init_disk_cyl = 0;
		int last_disk_cyl = 4999;
		
		int num_head_mvmt;
		
		int option_type; //can choose automatic or manual .txt
		int algo_selected;// can choose for algo selected
		
		//Populates queue array
		System.out.println("Please choose an option: (1)Automatic Genration (2)Input .txt File");
		option_type = option.nextInt();
		
		switch(option_type) {
		case 1: 
			queue = new int[5];
			queue = generate_rand_queue(queue.length);
			System.out.print("queue: ");
			for(int i = 0; i < queue.length; i++) {
				System.out.print(queue[i] + " ");
			}
			System.out.println();
;		break;
		case 2: 
			int queue_length = get_txt_length();//gets length of txt file
			queue = new int[queue_length]; //creates array of size queue_length
			queue = populate_queue(queue_length);
			
//			System.out.println("queue length: " + queue_length);
			System.out.print("queue: ");
			for(int i = 0; i < queue_length; i++) {
				System.out.print(queue[i] + " ");
			}
			System.out.println();
		break;
		default: 
			System.out.println("Invalid Please Restart Program Again");
		}
		 
		//Acquiring Disk Head from user
		System.out.println("Please enter a Initial Disk Head:");
		init_disk_head = user_init_head.nextInt();
		
		System.out.println("Disk Head = " + init_disk_head);
		
		//User selection for algo
		System.out.println("Please select a sorting algorithm: (1)FCFS (2)SSTF (3)SCAN (4)CSCAN");
		algo_selected = option.nextInt();
		switch(algo_selected) {
		case 1: 
			FCFS fcfs = new FCFS(init_disk_head,queue);
			fcfs.HeadMovement();
		break;
		case 2:
			System.out.println("SSTF Disk Algorithm");
			SSTF sstf = new SSTF(init_disk_cyl,last_disk_cyl,init_disk_head,queue);
			sstf.num_head_movmt();
		break;
		case 3: 
			System.out.println("SCAN Disk Algorithm");
			previous_head = previous_head();
			SCAN scan = new SCAN(init_disk_cyl,last_disk_cyl,init_disk_head,queue,previous_head);
			scan.num_head_movmt();
			
		break;
		case 4: 
			System.out.println("CSCAN Disk Algorithm");
			previous_head = previous_head();
			CSCAN cscan = new CSCAN(init_disk_cyl,last_disk_cyl,init_disk_head,queue,previous_head);
			cscan.num_head_movmt();
		}
		
		
		
		
//		
//		fcfs.print();
//		fcfs.HeadMovement();
		
		
		
		
		
		
		
		
	}


	static Random random = new Random();
	static Scanner fileInput = new Scanner(System.in);//scanner for user input
	static Scanner user_input = new Scanner(System.in);
	
	private static int get_txt_length() throws FileNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("Please enter default .txt file: 'src/queue.txt' or enter own .txt (look at format in readme)");
		String userFile = fileInput.next();
	
		File queue_info = new File(userFile);
		
		Scanner fileScan = new Scanner(queue_info); //scanner for process file
		
		int index = 0; 
		while(fileScan.hasNextLine()) { //counts how many nums are in the txt file
			fileScan.nextInt(); //goes through the txt file
			index++;
		}
		
		return index;
		
	}
	
	private static int[] populate_queue(int queue_length) throws FileNotFoundException {
		// TODO Auto-generated method stub
		int[] queue = new int[queue_length];
		
		System.out.println("Please enter .txt file again: 'src/queue.txt' or own .txt");
		String userFile = fileInput.next();
		
		File queue_info = new File(userFile);
		
		Scanner fileScan = new Scanner(queue_info); //scanner for process file
		
		int index = 0; 
		while(fileScan.hasNextLine()) { //counts how many nums are in the txt file
			queue[index] = fileScan.nextInt();
			index++;
		}
	
		return queue;
	}
	
	private static int previous_head() {
		// TODO Auto-generated method stub
		int previous_head;
		System.out.println("Please enter the previous head's position: ");
		previous_head = user_input.nextInt();
		System.out.println("Previous Head: " + previous_head);
		return previous_head;
	}

	private static int[] generate_rand_queue(int queue_length) {
		int[] queue = new int[queue_length];
		for(int i = 0; i < queue_length; i++) {
			queue[i] = random.nextInt(100);
		}
		return queue;
	}

}
