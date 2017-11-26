package thaliatix;

import java.util.ArrayList;
import java.util.List;

public class OrderManager implements BoundaryInterfaceOrder{
	
	private static List<Order> orders = new ArrayList<Order>();
	
	//Pre-condition: all fields (except OID and Date_ordered) are populated, OID must be null 
	public Order createOrder(Order newOrder) {
		newOrder.setOid();
		newOrder.setDate_ordered();
		orders.add(newOrder);
		
		return newOrder;		
	}
	
	public List<DisplayOrder> getAllOrders(){
		List<DisplayOrder> allOrders = new ArrayList<DisplayOrder>();
		for(Order order:orders) {
			allOrders.add(new DisplayOrder(order));
		}
		return allOrders;
	}

	public Order getOrderById(String oid) {
		for(Order order:orders) {
			if(order.matchesId(oid)) {return order;}
		}
		return null;
	}
	
	public List<DisplayOrder> getAllOrdersInTimeFrame(String start_date, String end_date) {
		List<DisplayOrder> allOrders = new ArrayList<DisplayOrder>();
		for(Order order:orders) {
			if(order.isInTimeFrame(start_date, end_date)) {
				allOrders.add(new DisplayOrder(order));
			}
		}
		return allOrders;
	}
	
	public List<String> getDonatedTickets(String showID, int count){
		List<String> donatedTickets = new ArrayList<String>();
		for(Order order:orders) {
			if(order.getWid().equals(showID)) {
				for(ReducedTicketInfo ticket: order.getTickets()) {
					if(ticket.getStatus().equals("donated") && donatedTickets.size() < count) {
						donatedTickets.add(ticket.getTid());
					}
					
				}
			}
		}
		return donatedTickets;
	}
	public void setTicketStatusDonated(String tid) {
		for(Order order:orders) {
			for(ReducedTicketInfo ticket:order.getTickets()) {
				if(ticket.getTid().equals(tid)) {
					ticket.setStatus("donated");
				}
			}
		}
	}
}
