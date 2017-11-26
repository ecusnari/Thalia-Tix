package thaliatix;

import java.util.List;

public interface BoundaryInterfaceOrder {

	public Order createOrder(Order newOrder);
	public List<DisplayOrder> getAllOrders();
	public Order getOrderById(String oid);
	public List<DisplayOrder> getAllOrdersInTimeFrame(String start_date, String end_date);
	public List<String> getDonatedTickets(String showID, int count);
	public void setTicketStatusDonated(String tid);
}
