package thaliatix;

public class DisplayOrder {
	private String oid;
	private String wid;
	private ShowInfo show_info;
	private String date_ordered;
	private int order_amount;
	private int number_of_tickets;
	private PatronInfo patron_info;
	
	public DisplayOrder(Order order) {
		this.oid = order.getOid();
		this.wid = order.getWid();
		this.show_info = order.getShow_info();
		this.date_ordered = order.getDate_ordered();
		this.order_amount = order.getOrder_amount();
		this.number_of_tickets = order.getNumber_of_tickets();
		this.patron_info = order.getPatron_info();
		String displayCCNum = MaskCCNum(this.patron_info.getCc_number());
		this.patron_info.setCc_number(displayCCNum);
	}
	
	private String MaskCCNum(String cc_num) {
		int len = cc_num.length();
		String Mask = "";
		int i;
		for(i=0;i<len-4;i++) {
			Mask = Mask.concat("x");
			
		}
		Mask = Mask.concat(cc_num.substring(len-4));
		
		return Mask;
	}
	
	public String getOid() {
		return this.oid;
	} 
}
