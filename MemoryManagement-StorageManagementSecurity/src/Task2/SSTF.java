package Task2;

import java.util.Arrays;
//Ricardo Baeza
public class SSTF {
	int[] queue;
	int init_disk_cyl;
	int last_disk_cyl;
	int init_disk_head;

	int num_head_mvmt;
	int mvmt1;
	int mvmt2;

	int[] queue_with_head;

	public SSTF(int init_disk_cyl, int last_disk_cyl, int init_disk_head, int[] queue) {
		// TODO Auto-generated constructor stub
		this.init_disk_cyl = init_disk_cyl;
		this.last_disk_cyl = last_disk_cyl;
		this.init_disk_head = init_disk_head;
		this.queue = queue;

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
 
		int left = 0;
		int right = 0;

		int remaining_nums_in_queue = -1;

		queue_with_head = queue_with_head(queue, init_disk_head); // creates new array with the head involved
		int head_index = get_head_index(queue_with_head, init_disk_head); // gets index of where the head was stored in
																			// the array

//		System.out.println("head index: " + head_index);
//		System.out.println("array:");
//		for(int i = 0; i < queue_with_head.length; i++) {
//			System.out.print(queue_with_head[i] + " ");
//		}

		index_position = head_index;
		start = 0;

//		comparisons		
		for (int i = 0; i < (queue_with_head.length - 1); i++) {
			// get index = 2 (53)
			if (start == 0) {
				int temp_right = index_position + 1;
				int temp_left = index_position - 1;
				if(temp_right > (queue_with_head.length - 1)) { //means we're going entirely left (nothing is aside current index)
					left = Math.abs(init_disk_head - queue_with_head[index_position - 1]);
					right = 9999999;
				}else if(temp_left < 0){ //means we're going entirely right
					right = Math.abs(init_disk_head - queue_with_head[index_position + 1]);
					left = 9999999;
				}
				
				else {
				// get left value of head
				left = Math.abs(init_disk_head - queue_with_head[index_position - 1]);
				// get right value of head
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

				if (direction == -1) { // left

					int temp_left = index_position - 1;
					if (temp_left < 0) { // when we reach the lefts end and there's no left to subtract
						direction = 1;
						right = Math.abs(queue_with_head[last_unused_right_index] - queue_with_head[index_position]);
						left = 100000000;
						last_unused_left_index -= 1;
//						System.out.println("right: " + queue_with_head[last_unused_right_index]);
					}
//					else if (temp_left == 0){//when we go left then right (left, left, left ,right, right)
//						left = Math.abs(queue_with_head[index_position] - queue_with_head[index_position - 1]);
//						right = 100000000;
//					}
					else if(last_unused_right_index == queue_with_head.length) {
						left = Math.abs(queue_with_head[index_position] - queue_with_head[index_position - 1]);
						right = 100000000;
					}
//					else if (index_position + 1 == last_unused_left_index) { // means we went left (straight up) right
//																				// from the beginning
//						direction = -1;
//						left = Math.abs(queue_with_head[index_position] - queue_with_head[index_position - 1]);
//						right = 100000000;
//						last_unused_left_index -= 1;
//					} 
					else {
//						System.out.println("entered");
						left = Math.abs(queue_with_head[index_position] - queue_with_head[index_position - 1]);
//						System.out.println("right: " + queue_with_head[index_position] + " - " + queue_with_head[last_unused_right_index]);
						right = Math.abs(queue_with_head[index_position] - queue_with_head[last_unused_right_index]);
					}

					if (right < left) { // means we went right
						direction = 1;
						head_movements += right;
						last_unused_left_index = index_position - 1;
						index_position = last_unused_right_index;
						change_directions++;
					} else {// means we have gone left
						direction = -1;
						head_movements += left;
//						last_unused_right_index += 1;
						index_position -= 1;
					}

				} else { // dir. 1 (right)
					int temp_right = index_position + 1;
//					System.out.println("temp rigt" + temp_right);
//					System.out.println("index pos" + index_position);
					if (temp_right >= queue_with_head.length) { // when we reach the right end and there's no right to
																// subtract
						direction = -1; // means we're going left now..
						left = Math.abs(queue_with_head[last_unused_left_index] - queue_with_head[index_position]);
//						System.out.println("left calc" + left);
						right = 100000000;
						last_unused_right_index -= 1;
//						System.out.println("left: " + queue_with_head[last_unused_left_index]);
					}
//						else if (queue_with_head[index_position] > queue_with_head[index_position - 1]){//when we go left then right (left, left, left ,right, right)
//						System.out.println("init");
//						right = Math.abs(queue_with_head[index_position] - queue_with_head[index_position + 1]);
//						left = 100000000;
//					}
					else if(last_unused_left_index == -1) {//no more left to go through, so just right
						right = right = Math.abs(queue_with_head[index_position] - queue_with_head[index_position + 1]);
						left = 999999999;
					} 
					else {
						left = Math.abs(queue_with_head[last_unused_left_index] - queue_with_head[index_position]);
						right = Math.abs(queue_with_head[index_position] - queue_with_head[index_position + 1]);
					}
					if (right < left) { // means we went right
						direction = 1;
						head_movements += right;
						int temp = index_position + 1;
						index_position++;
					} else {// means we have gone left
						direction = -1;
						head_movements += left;
						last_unused_right_index = index_position + 1;
						index_position = last_unused_left_index;
						change_directions++;

					} // end of inner if stmt

				} // end of outer if stmt

			}

//		System.out.println(left);
//		System.out.println(right);

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
