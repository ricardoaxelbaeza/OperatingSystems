package Task1;

public class Offset {
	//Ricardo Baeza
	int offset;
	
	public int offsetCalc(int virtual_address, int page_in_kb) {
		// TODO Auto-generated method stub
		offset = virtual_address % page_in_kb;
		return offset;
	}

} 
