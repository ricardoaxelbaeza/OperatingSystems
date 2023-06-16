package Task2;

import java.util.Arrays;
//Ricardo Baeza
public class CSCAN {
	int[] queue;
	int init_disk_cyl;
	int last_disk_cyl;
	int init_disk_head;
	int previous_head;

	int num_head_mvmt;
	int mvmt1;
	int mvmt2;
	int mvmt3;

	int[] queue_with_head;

	public CSCAN(int init_disk_cyl, int last_disk_cyl, int init_disk_head, int[] queue, int previous_head) {
		// TODO Auto-generated constructor stub
		this.init_disk_cyl = init_disk_cyl;
		this.last_disk_cyl = last_disk_cyl;
		this.init_disk_head = init_disk_head;
		this.queue = queue;
		this.previous_head = previous_head;
	}

	public void num_head_movmt() {
		// TODO Auto-generated method stub

		int head_movements = 0;
		int change_directions = 0;
		int last_visited = -1;
		int start = -1;

		int last_unused_left_index = -1;
		int last_unused_right_index = -1;

		int index_position;
		int direction = 0;
		boolean right_to_left = false;
		boolean left_to_right = false;

		int left = 0;
		int right = 0;

		int remaining_nums_in_queue = -1;

		queue_with_head = queue_with_head(queue, init_disk_head); // creates new array with the head involved
		int head_index = get_head_index(queue_with_head, init_disk_head); // gets index of where the head was stored in
																			// the array

//		System.out.println("head index: " + head_index);
//				System.out.println("array:");
//				for(int i = 0; i < queue_with_head.length; i++) {
//					System.out.print(queue_with_head[i] + " ");
//				}

		index_position = head_index;
		start = 0;

//		System.out.println("initial disk head" + init_disk_head);

//				comparisons		
		for (int i = 0; i < queue_with_head.length + 1; i++) {
			// get index = 2 (53)
			if (start == 0) {
				if (previous_head > init_disk_head) { // going in left direction
					left = Math.abs(init_disk_head - queue_with_head[index_position - 1]);
					right = 999999999;
				} else { // going in right direction
					left = 999999999;
//					System.out.println("right: " + init_disk_head + "-" + queue_with_head[index_position + 1]);
					right = Math.abs(init_disk_head - queue_with_head[index_position + 1]);
				}
				// compare values
				if (right < left) { // we go right
					direction = 1;// right
					head_movements = Math.abs(init_disk_head - queue_with_head[index_position + 1]);
					int temp = index_position + 1;
					last_unused_left_index = index_position - 1;
					last_unused_right_index = index_position;
					index_position = temp;

				} else { // we go left
					direction = -1;// left
					head_movements = Math.abs(init_disk_head - queue_with_head[index_position - 1]);
					int temp = index_position - 1;
					last_unused_right_index = index_position + 1;
					last_unused_left_index = index_position;
					index_position = temp;
				}

				start = 1;

			} else {
				if (direction == -1) { // means we went left

					// from right to right
					if (right_to_left == true) {
						right = Math.abs(init_disk_cyl - queue_with_head[index_position]);
						direction = 1;
						change_directions++;
					} else if (index_position == 0) {
						left = Math.abs(queue_with_head[index_position] - init_disk_cyl);
						last_unused_left_index = 0;
					} else if (index_position == -1) {
						right = Math.abs(init_disk_cyl - last_disk_cyl);
						direction = 1;
						index_position = queue_with_head.length - 1;
						change_directions++;
					} else {
						left = Math.abs(queue_with_head[index_position] - queue_with_head[index_position - 1]);
					}

					if (direction == -1) {// means we have gone left
//						System.out.println("in here");
						direction = -1;
						int temp = index_position - 1;
						head_movements += left;
						index_position = temp;
//						System.out.println("index pos" + index_position);
					}
					if(direction == 1 && right_to_left == true) {
						direction = 1;
						head_movements += right;
					}
					else if (direction == 1) { // means we went right
						direction = 1;
						head_movements += right;
						left_to_right = true;
					} // end of inner if stmt

				} else { // means we went right

					if (left_to_right == true) { //now we're going left though
						System.out.println("left = " + last_disk_cyl + " - " + queue_with_head[index_position]);
						left = Math.abs(last_disk_cyl - queue_with_head[index_position]);
						direction = -1;
						change_directions++;
					} else if (index_position == (queue_with_head.length - 1)) { // getting to last index and performing
																					// operation between last disk head
																					// and last
																					// num in arr
						right = Math.abs(queue_with_head[index_position] - last_disk_cyl);
						last_unused_right_index = (queue_with_head.length - 1);
					} else if (index_position == queue_with_head.length) { // subtracts last disk cylinder with first
																			// disk cylinder
																			// for circular motion
						left = Math.abs(last_disk_cyl - init_disk_cyl);
						direction = -1;
						index_position = init_disk_cyl;
						change_directions++;
					} else {
						right = Math.abs(queue_with_head[index_position] - queue_with_head[index_position + 1]);
					}

					if (direction == 1) { // means we went right
						direction = 1;
						head_movements += right;
						int temp = index_position + 1;
						index_position++;
					} // end of inner if stmt
					
					if(direction == -1 && left_to_right == true) {
						direction = -1;
						head_movements += left;
					}
					else if (direction == -1) { // means we went left
						direction = -1;
						head_movements += left;
						right_to_left = true;

					} // end of inner if stmt
				}

			} // end of right direction operations
		}

//		System.out.println("last left: " + last_unused_left_index);
//		System.out.println("last right: " + last_unused_right_index);
//		System.out.println("current index " + index_position);
		System.out.println("head movements: " + head_movements);
		System.out.println("directions changed: " + change_directions);

	}

	private int[] queue_with_head(int[] queue2, int init_disk_head2) {
		// TODO Auto-generated method stub
		queue_with_head = new int[(queue.length + 1)];

		for (int i = 0; i < queue.length; i++) {
			queue_with_head[i] = queue[i];
		}
		queue_with_head[(queue.length)] = init_disk_head2;

		Arrays.sort(queue_with_head);

		return queue_with_head;
	}

	private int get_head_index(int[] queue_with_head, int init_disk_head) {
		// TODO Auto-generated method stub
		int index = 0;
		for (int i = 0; i < queue_with_head.length; i++) {
			if (queue_with_head[i] == init_disk_head) {
				index = i;
			}
		}
		return index;
	}

}
