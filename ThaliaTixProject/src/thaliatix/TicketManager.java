package thaliatix;

import java.util.ArrayList;
import java.util.List;

public class TicketManager implements BoundaryInterfaceTicket{

	private static List<Ticket> tickets = new ArrayList<Ticket>();
	
	
	public Ticket createTicket(Ticket newTicket) {
		newTicket.setTid();
		tickets.add(newTicket);
		return newTicket;	
	}
	
	
	
}
