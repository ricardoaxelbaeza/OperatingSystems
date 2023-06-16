package Task1;

import java.util.Scanner;

public class AddressTranslation {
	//Ricardo Baeza
	public static void main(String[] args) {
		int virtual_address;
		int page_size;
		int page_number;
		int offset;
		int kb_size = 1024;
		 
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Enter a page size:");
		page_size = scan.nextInt(); //stores users entered page size into page_size
		
		System.out.println("Enter a virtual address:");
		virtual_address = scan.nextInt(); //stores users entered virtual address into virtual_address
		
//		System.out.println("virtual address:" + virtual_address);
//		System.out.println("page size:" + page_size);

		int page_in_kb = page_size * kb_size;
//		System.out.println("page in kb:" + page_in_kb);
		
		PageNumber pn = new PageNumber(); //page_size * kb_size to get kb
		Offset off = new Offset();
//		
		page_number = pn.pageNumberCalc(virtual_address,(page_in_kb));
		offset = off.offsetCalc(virtual_address,(page_in_kb));
		
		System.out.println("The address " + virtual_address + " contains: page number = " + page_number + 
							", offset = " + offset + ".");

	}

}
