package thaliatix;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.annotations.Expose;

public class Order {
	
	@Expose private String oid;
	private String wid;
	private ShowInfo show_info;
	private String date_ordered;
	private int order_amount;
	private int number_of_tickets;
	private PatronInfo patron_info;
	private List<ReducedTicketInfo> tickets = new ArrayList<ReducedTicketInfo>();
	
	public Order() {

	}
	
	public boolean isNill() {
		return false;
	}
	public String getOid() {
		return oid;
	}
	public void setOid() {
		this.oid = Integer.toString(UniqueOIDGenerator.getUniqueID());
	}
	public String getWid() {
		return wid;
	}
	public void setWid(String wid) {
		this.wid = wid;
	}
	public ShowInfo getShow_info() {
		return show_info;
	}
	public void setShow_info(ShowInfo show_info) {
		this.show_info = show_info;
	}
	public String getDate_ordered() {
		return date_ordered;
	}
	public void setDate_ordered() {
		//"2017-10-28 18:24
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date date = new Date();
		this.date_ordered = dateFormat.format(date);
	}
	public int getOrder_amount() {
		return order_amount;
	}
	public void setOrder_amount(int order_amount) {
		this.order_amount = order_amount;
	}
	public int getNumber_of_tickets() {
		return number_of_tickets;
	}
	public void setNumber_of_tickets(int number_of_tickets) {
		this.number_of_tickets = number_of_tickets;
	}
	public PatronInfo getPatron_info() {
		return patron_info;
	}
	public void setPatron_info(PatronInfo patron_info) {
		this.patron_info = patron_info;
	}
	
	public void addTicket(Ticket ticket) {
		this.tickets.add(new ReducedTicketInfo(ticket));
	}
	
	public List<ReducedTicketInfo> getTickets(){
		return this.tickets;
	}
	
	public boolean matchesId(String oid) {
		return(oid.equals(this.oid));
	}
	
	@Override
	public String toString() {
		return "Order [oid=" + oid + ", wid=" + wid + ", show_info=" + show_info + ", date_ordered=" + date_ordered
				+ ", order_amount=" + order_amount + ", number_of_tickets=" + number_of_tickets + ", patron_info="
				+ patron_info + "]";
	}
	
	public boolean isInTimeFrame(String start_date, String end_date) {
		try {
			SimpleDateFormat dateFormatOrder = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dayFormat = new SimpleDateFormat("yyyyMMdd");
			Date orderDate = dateFormatOrder.parse(this.getDate_ordered().substring(0, 10));
			Date startDate = dayFormat.parse(start_date);
			Date endDate = dayFormat.parse(end_date);
			
			if(orderDate.after(startDate) && orderDate.before(endDate)||orderDate.equals(startDate)||orderDate.equals(endDate)) {
				return true;
			}else {return false;}
			
		}catch(Exception e) {
			return false;
		}
	}
	
	

}





