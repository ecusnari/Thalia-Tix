package thaliatix;

import java.util.ArrayList;
import java.util.List;

public class OrderReducedInfo {
	private String oid;
	private String wid;
	private ShowInfo show_info;
	private String date_ordered;
	private int order_amount;
	private List<String> tickets = new ArrayList<String>();
	
	
	public OrderReducedInfo(Order order) {
		this.oid = order.getOid();
		this.wid = order.getWid();
		this.show_info = order.getShow_info();
		this.date_ordered = order.getDate_ordered();
		this.order_amount = order.getOrder_amount();
		for(ReducedTicketInfo ticket: order.getTickets()) {
			this.tickets.add(ticket.getTid());
		}
	}
}
