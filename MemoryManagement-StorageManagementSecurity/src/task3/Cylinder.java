package task3;

import java.util.Scanner;

public class Cylinder {
	//Ricardo Baeza
	public static void main(String[] args) {
		
		int logical_block_num;
		int num_of_cylinders;
		int num_of_tracks;
		int num_of_sectors;
		
		
		//we're always going to start from the first sector and cylinder, so 0
		int cyl_number = 0;
		int track_number = 0;
		int sector_number = 0;
		
		boolean in_first_platter = true; //we'll start in first platter as well
		
		Scanner user_in = new Scanner(System.in);
		
		//get user input
		System.out.println("Enter a logical block number:");
		logical_block_num = user_in.nextInt();
		
		System.out.println("Enter HD number of cylinders:");
		num_of_cylinders = user_in.nextInt();
		
		System.out.println("Enter HD number of tracks:");
		num_of_tracks = user_in.nextInt();
		
		System.out.println("Enter HD number of sectors:");
		num_of_sectors = user_in.nextInt();
		
		System.out.println("Logical block #: " + logical_block_num);
		System.out.println("# of cylinders: " + num_of_cylinders);
		System.out.println("# of tracks: " + num_of_tracks);
		System.out.println("# of sectors: " + num_of_sectors);
		
		//total sectors = # tracks * # sectors
		//e.g. 
		int track_position = logical_block_num / num_of_sectors;
		track_number = track_position;
		
		//total sectors = # tracks * # sectors (20 * 63) = 1260 sectors
		//1260/63 = 20 (means 20th track) so...
		if(track_number > num_of_tracks) {//means we're not in the first platter's track anymore
			in_first_platter = false;
		}else {
			in_first_platter = true; //still in first platters track
		}
		
		if(in_first_platter == true) { //means we're still in the first platter
			//cylinder remains 0
			//track position was already calculated
			sector_number = calcSector(logical_block_num,num_of_sectors);
			cyl_number = 0;
			
		}else {//not in first platter anymore
			//sectors/ tracks = num of sectors per track
			// position can be a num within 
			cyl_number = calcCylNum(track_position,num_of_tracks);
			track_number = calcTrackNum(track_number,num_of_tracks);
			sector_number = calcSector(logical_block_num,num_of_sectors);
		}
		
		System.out.println("The logical block number " + logical_block_num + " is located at <" + cyl_number + "," + track_number + "," + sector_number + ">");
		
		
		
		
		
		
	}

	private static int calcTrackNum(int track_position, int track_position2) {
		System.out.println(track_position + "-" + track_position2 );
		// TODO Auto-generated method stub
		return track_position - track_position2;
	}

	private static int calcCylNum(int track_position, int num_of_tracks) {
		// TODO Auto-generated method stub
		return track_position/num_of_tracks;
		//if track pos < num of tracks, cyl 0
		//if track pos == num of tracks, cyl 1, etc... keep increasing
	}

	private static int calcSector(int logical_block_num, int num_of_sectors) {
		// TODO Auto-generated method stub
		return logical_block_num % num_of_sectors;
	}

}
