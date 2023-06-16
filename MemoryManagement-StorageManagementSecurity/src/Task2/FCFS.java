package Task2;

public class FCFS {
	//Ricardo Baeza
	int[] queue;
	int init_disk_head;
	int[] sum_queue; //store values here that we will add for total

	public FCFS(int init_disk_head, int[] queue) {
		// TODO Auto-generated constructor stub
		this.init_disk_head = init_disk_head;
		this.queue = queue;
	}

	public void print() {
		// TODO Auto-generated method stub
		System.out.println(init_disk_head);
		for(int i = 0; i < queue.length; i++) {
			System.out.print(queue[i] + " ");
		}
	}
	
	public void HeadMovement() {
		int head_movements = 0;
		int change_directions = 0;
		int last_visited = 0;
		int start = -1;
		 
		int direction = 0;
		
		for(int i = 0; i < queue.length; i++) {
			if(start == -1) {
				int temp = Math.abs(init_disk_head - queue[i]);
				head_movements += temp;
//				change_directions++;
//				
				if(queue[i] < init_disk_head) {
					direction = -1;//left
				}else {
					direction = 1;//right
				}
				start = 1;
				
			}else {
				head_movements += Math.abs(queue[last_visited]- queue[i]);
				
				if(direction == -1) {
					if(queue[i] > queue[last_visited]) {
						change_directions++;
						direction = 1;
					}else {
						direction = -1;
					}
				}else {
					if(queue[i] < queue[last_visited]) {
						change_directions++;
						direction = -1;
					}else {
						direction = 1;
					}
					
				}
				
				last_visited = i;
				
			}
		}
		
		System.out.println("head movements: " + head_movements);
		System.out.println("directions changed: " + change_directions);
	}
	

}
