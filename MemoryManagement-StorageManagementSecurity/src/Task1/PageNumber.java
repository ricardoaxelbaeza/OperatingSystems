package Task1;

public class PageNumber {
	int pageNum;
	//Ricardo Baeza
//	public PageNumber(int virtual_address, int page_size) {
//		// TODO Auto-generated constructor stub
//		virtual_address = this.virtual_address;
//		page_size = this.page_size;
//	}

	public int pageNumberCalc(int virtual_address, int page_in_kb) {
		// TODO Auto-generated method stub
		pageNum = virtual_address/page_in_kb;
		return pageNum;
	} 
	
	

}
