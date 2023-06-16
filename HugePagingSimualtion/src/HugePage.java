import java.util.Scanner;

public class HugePage {

	public static void main(String[] args) {
		
		boolean discord_allocated = false;
		boolean chrome_allocated = false;
		
		Scanner scanner = new Scanner(System.in);
		
//		chrome_allocated = (user_in.equals("malloc(chrome)"));
		int pages;
		int temp;
		int size; //total program size is 4MB
		int num_of_pages;
		
		//Allow user to choose an app
		System.out.println("Choose an Application: (1) discord (2) chrome (3) firefox");
		
		int app_chosen = scanner.nextInt();
		
		switch(app_chosen) {
		case 1: 
			System.out.println("Terminal");
			System.out.println("malloc(discord)"); //we're going to want to allocate memory to discord
			System.out.println("-------------------");
			
			pages = 10000; //each page is 4KB
			temp = pages;
			size = 40; //total program size is 40 MB
			num_of_pages = malloc(pages); //when we want to alloc some mem we call memory allocation method
			
			System.out.println("-------------------------------");
			System.out.println("Discord is ready for allocation");
			System.out.println("-------------------------------");
			
			System.out.println("Original number of pages was: " + temp);
			System.out.println("The number of pages is: " + num_of_pages);
		break;
		case 2: 
			System.out.println("Terminal");
			System.out.println("malloc(chrome)");
			
			pages = 4000; //each page is 4KB
			temp = pages;
			size = 16; //total program size is 16 MB
			num_of_pages = malloc(pages);
			
			System.out.println("-------------------------------");
			System.out.println("Chrome is ready for allocation");
			System.out.println("-------------------------------");
			
			System.out.println("Original number of pages was: " + temp);
			System.out.println("The number of pages is: " + num_of_pages);
		break;
		case 3: 
			System.out.println("Terminal");
			System.out.println("malloc(firefox)");
			System.out.println("-------------------------------");
			
			pages = 20000; //each page is 4KB
			temp = pages;
			size = 80; //total program size is 80 MB
			num_of_pages = malloc(pages);
			
			System.out.println("-------------------------------");
			System.out.println("Firefox is ready for allocation");
			System.out.println("-------------------------------");
			
			System.out.println("Original number of pages was: " + temp);
			System.out.println("The number of pages is: " + num_of_pages);
		break;
		default: 
			System.out.println("Application does not exist");
		break;
		}

	}
	
	static Scanner scan = new Scanner(System.in);

	private static int malloc(int pages) {
		// TODO Auto-generated method stub
		//before we allocate memory to an app, we'll check if huge paging is necessary
		int num_of_pages = pages; 
		if(pages >= 10000) {
			//if huge paging is necessary, we'll enter huge page mode & ask user to 
			//enter how many pages they'd like to combine into one page
			System.out.println("Too Many Pages (" + num_of_pages + " >= " + 10000 + "), Huge Page mode has been entered.");
			System.out.print("Enter desired number of pages in one huge page: "); 
			int desired_num_pages = scan.nextInt(); //gets # of pages desired by the user
			System.out.println("Number of pages in one huge page:" + desired_num_pages);
			num_of_pages = hugepage(pages,desired_num_pages);
		}
		return num_of_pages;
	}

	//performs huge paging calculation
	private static int hugepage(int pages, int desired_num_pages) {
		
		int num_of_pages = pages/desired_num_pages;
		return num_of_pages;
		
	}

}
