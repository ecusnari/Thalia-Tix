package thaliatix;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UnitTests {
	private static BoundaryInterfaceShow showManager = new ShowManager();
	private  BoundaryInterfaceSeating seatingManager = new SeatingManager();
	private static BoundaryInterfaceOrder orderManager = new OrderManager();
	private static BoundaryInterfaceTicket ticketManager = new TicketManager();
	private static BoundaryInterfaceDonation donationManager = new DonationManager(); 
	
	@BeforeEach
	void setUp() throws Exception {
		seatingManager.ConfigureGeneralSeatsLayout();
	}

	//Set up seating layout
	@Test
	void testTheatreSeatLayoutSetUp() {
		assertTrue(seatingManager.getAllSeats().size()>0);
		for(Section section:seatingManager.getAllSeats()) {
			assertTrue(section.getSeating().size()>0);
			for(Seating seating:section.getSeating()) {
				assertTrue(seating.getSeats().size()>0);
				for(Seat seat:seating.getSeats()) {
					assertFalse(seat.isNill());
				}
			}
		}
	}
			
	//Check if gets section detail correct by section ID
	@Test
	void testGetSectionDetail(){
		SectionDetail sectionDetail = seatingManager.getSectionDetail("123");
		assertTrue(!sectionDetail.isNil());
		sectionDetail = seatingManager.getSectionDetail("121");
		assertTrue(sectionDetail.isNil());
		
	}
	//Create seating in seatingManager
	@Test
	void testCreateSeating() {
		BoundaryInterfaceSeating seatingManagerDummy = new SeatingManager();
		Seating s = seatingManagerDummy.createSeating("1", 1, 201);
		assertTrue(s.getSeats().size()==1);
	}
	
	
	@Test
	//Make a show object
	void testNewShow() {
		Show show = new Show("name", "web", "date", "time");
		assertTrue(show.getName().equals("name") && show.getWeb().equals("web") && show.getDate().equals("date")&& show.getTime().equals("time"));
		assertTrue(show.getSections().size()==0);
	}
	
	//Create show and add to show manager
	@Test
	void testCreateShow() {
		Show show = showManager.createShow("name", "web", "date", "time");
		assertTrue(show.getName().equals("name"));
		assertTrue(show.getWeb().equals("web"));
		assertTrue(show.getDate().equals("date"));
		assertTrue(show.getTime().equals("time"));
		List<ShowDetail> listShows = showManager.getAllShows();
	
		assertFalse(showManager.findById(show.getID()).isNil());
	}
	
	@Test
	void testGetShowDetail() {
		Show retrieved = showManager.createShow("name", "web", "date", "time");
		
		SpecificShowDetail show = showManager.getShowDetail(retrieved.getID());
		assertFalse(show.isNil());
	}
	
	@Test
	void testUpdateShow() {
		String wid = showManager.createShow("name", "web", "date", "time").getID();
		Show newShow = new Show("name2","web2", "date2", "time2");
		newShow.setWid(wid);
		showManager.updateShow(wid, newShow);
		Show updatedShow = showManager.findById(wid);
		assertFalse(updatedShow.getName().equals("name"));
		assertFalse(updatedShow.getWeb().equals("web"));
		assertFalse(updatedShow.getDate().equals("date"));
		assertFalse(updatedShow.getTime().equals("time"));
	}
	
	@Test
	void testCreateOrder(){
		Order order = new Order();
		order.setWid("1");
		Show dummyShow = new Show("name", "web", "date", "time");
		ShowInfo show_info = new ShowInfo(dummyShow);
		order.setShow_info(show_info);
		order.setOrder_amount(100);
		order.setNumber_of_tickets(3);
		
		Order retrieved = orderManager.createOrder(order);
		assertFalse(retrieved.isNill());
		assertFalse(orderManager.getOrderById(retrieved.getOid()).isNill());
	}
	
	@Test
	void testGetAllOrders() {
		Order order = new Order();
		order.setWid("1");
		Show dummyShow = new Show("name", "web", "date", "time");
		ShowInfo show_info = new ShowInfo(dummyShow);
		order.setShow_info(show_info);
		order.setOrder_amount(100);
		order.setNumber_of_tickets(3);
		PatronInfo patron_info = new PatronInfo("name", "phone","email","billing", "ccNUM", "ccEXP");
		order.setPatron_info(patron_info);
		assertFalse(orderManager.createOrder(order).isNill());
		
	}
	
	@Test
	void testGetOrderById() {
		String oid = orderManager.createOrder(new Order()).getOid();
		Order order = orderManager.getOrderById(oid);
		assertFalse(order.isNill());
	}
	/*
	@Test
	void testgetOrdersnTimeFrame() {
		Order order = new Order();
		order.setWid("1");
		Show dummyShow = new Show("name", "web", "date", "time");
		ShowInfo show_info = new ShowInfo(dummyShow);
		order.setShow_info(show_info);
		order.setOrder_amount(100);
		order.setNumber_of_tickets(3);
		PatronInfo patron_info = new PatronInfo("name", "phone","email","billing", "1234567891234567", "ccEXP");
		order.setPatron_info(patron_info);
		
		String oid1 = orderManager.createOrder(order).getOid();
		String oid2 = orderManager.createOrder(order).getOid();
		String oid3 = orderManager.createOrder(order).getOid();
		List<DisplayOrder> orders = new ArrayList<DisplayOrder>();
		assertTrue(order.isInTimeFrame("20171125", "20171130"));
		orders.addAll(orderManager.getAllOrdersInTimeFrame("20171125", "20171130"));
		
		int i;
		int cnt = 0;
		for(i=0; i < orders.size();i++) {
			if(orders.get(i).getOid().equals(oid1) || orders.get(i).getOid().equals(oid2) || orders.get(i).getOid().equals(oid3))
				cnt++;
		}
		
		assertTrue(cnt==3);
		
	}*/
	
	
	@Test
	void testCreateSubscription() {
		List<String> tickets = new ArrayList();
		tickets.add("1");
		PatronInfo patron_info = new PatronInfo("name", "phone","email","billing", "1234567891234567", "ccEXP");
		Donation donation = new Donation("1", 2, "pending", tickets, patron_info);
		String did = donationManager.createSubscription(donation).getDid();
		assertFalse(donationManager.getSubscriptionById("1", did) == null);
	}
	
	@Test
	void testAssignDonation() {
		List<String> tickets = new ArrayList<String>();
		tickets.add("2");
		PatronInfo patron_info = new PatronInfo("name", "phone","email","billing", "1234567891234567", "ccEXP");
		Donation donation = new Donation("1", 2, "pending", tickets, patron_info);
		
		String did = donationManager.createSubscription(donation).getDid();
		assertFalse(donationManager.getSubscriptionById("1", did) == null);
		donationManager.assignNewDonations("1");
		
		assertFalse(donation.getTickets().isEmpty());
		//assertTrue(donationManager.getSubscriptionById("1", did).getStatus().equals("assigned"));
				
	
}
	@Test
	void testDisplayOrder() {
		Order order = new Order();
		order.setWid("1");
		Show dummyShow = new Show("name", "web", "date", "time");
		ShowInfo show_info = new ShowInfo(dummyShow);
		order.setShow_info(show_info);
		order.setOrder_amount(100);
		order.setNumber_of_tickets(3);
		PatronInfo patron_info = new PatronInfo("name", "phone","email","billing", "1234567891234567", "ccEXP");
		order.setPatron_info(patron_info);
		
		DisplayOrder displayOrder = new DisplayOrder(order);
		assertTrue(displayOrder.getOid()==order.getOid());
	}	
	
	@Test
	void testNullShow() {
		NullShow nullshow = new NullShow() ;
		assertTrue(nullshow.isNil());		
	}	
	
	@Test
	void testNullSeat() {
		SeatNull nullseat = new SeatNull() ;
		assertTrue(nullseat.isNill());		
	}
	
	@Test
	void testNullOrder() {
		OrderNull nullorder = new OrderNull() ;
		assertTrue(nullorder.isNill());		
	}
	
	@Test
	void testTicket() {
		Show show = new Show("name", "web", "date", "time");
		ShowInfo show_info = new ShowInfo(show);
		PatronInfo patron_info = new PatronInfo("name", "phone","email","billing", "1234567891234567", "ccEXP");
		Section section = new Section("Front center", "123", 0);
		Seating seating = new Seating("1");
		seating.addSeat(1, "201");
		section.addSeating(seating);
		Ticket ticket = new Ticket("1", show_info, patron_info, section) ;
		String tid1 = ticketManager.createTicket(ticket).getTid();
		String tid2 = ticketManager.createTicket(ticket).getTid();
		assertTrue(tid1 != null);
		assertTrue(tid2 != null);
		assertTrue(tid1 != tid2);
	}
	
	@Test
	void testOrderReducedInfo() {
		Order order = new Order();
		order.setWid("1");
		Show dummyShow = new Show("name", "web", "date", "time");
		ShowInfo show_info = new ShowInfo(dummyShow);
		order.setShow_info(show_info);
		order.setOrder_amount(100);
		order.setNumber_of_tickets(3);
		PatronInfo patron_info = new PatronInfo("name", "phone","email","billing", "1234567891234567", "ccEXP");
		order.setPatron_info(patron_info);
		
		OrderReducedInfo reducedOrder = new OrderReducedInfo(order);
		
		
	}
	
	@Test
	void testReducedTicketInfo() {
		Show show = new Show("name", "web", "date", "time");
		ShowInfo show_info = new ShowInfo(show);
		PatronInfo patron_info = new PatronInfo("name", "phone","email","billing", "1234567891234567", "ccEXP");
		Section section = new Section("Front center", "123", 0);
		Seating seating = new Seating("1");
		seating.addSeat(1, "201");
		section.addSeating(seating);
		Ticket ticket = new Ticket("1", show_info, patron_info, section) ;
		ticketManager.createTicket(ticket);
		ReducedTicketInfo ticketReduced = new ReducedTicketInfo(ticket);
		assertTrue(ticketReduced.getTid().equals(ticket.getTid()));
	}
	
	@Test
	void TestReport() {
		Report report = new Report("308", "Theatre occupancy");
	}
	
	@Test 
	void testSeatingInfo(){
		Section section = new Section("Front center", "123", 0);
		SeatingInfo seatingInfo = new SeatingInfo(section);
		assertTrue(seatingInfo.getSid().equals(section.getSid()));
		assertTrue(seatingInfo.getPrice() == section.getPrice());
	}
	
	@Test
	void testSeatRequest() {
		Show show = new Show("name", "web", "date", "time");
		ShowInfo show_info = new ShowInfo(show);
		PatronInfo patron_info = new PatronInfo("name", "phone","email","billing", "1234567891234567", "ccEXP");
		Section section = new Section("Front center", "123", 0);
		Seating seating = new Seating("1");
		seating.addSeat(1, "201");
		section.addSeating(seating);
		
		ShowSectionDetailedInfo showdetail = new ShowSectionDetailedInfo(show, section);
		SeatRequest seatRequest = new SeatRequest(showdetail);
		
		assertTrue(seatRequest.getShow_info().toString().equals("ShowInfo [name=" + show.getName() + ", web=" + show.getWeb() + ", date=" + show.getDate() + ", time=" + show.getTime() + "]"));
	}
	
	@Test
	void testSectionInfo() {
		Section section = new Section("Front center", "123", 0);
		Seating seating = new Seating("1");
		seating.addSeat(1, "201");
		section.addSeating(seating);
		
		SectionInfo sectionInfo = new SectionInfo(section);
		assertTrue(sectionInfo.toString().equals("SectionInfo [sid=" + section.getSid() + ", section_name=" + section.getSection_name() + "]"));
	}
	
	@Test
	void testSpecificShowDetailNull() {
		SpecificShowDetailNull show = new SpecificShowDetailNull();
		assertTrue(show.isNil());
	}
	
	@Test
	void testFindNContiguousSeats() {
		Show show = showManager.createShow("name", "web","date","time");
		Section section = new Section();
		for(Section sections :seatingManager.getAllSeats()) {
			if(sections.getSid().equals("123")) {
				section = sections;
			}
		}
		show.add(section);
		
		SeatRequest seatRequest = showManager.GetRequestedSeats(show.getID(),section.getSid(), 2, "201");
		assertTrue(seatRequest.getStatus().equals("ok"));
		
	}
	
	@Test
	void testSetTicketStatusSold() {
		Show show = showManager.createShow("name", "web","date","time");
		Section section = new Section();
		for(Section sections :seatingManager.getAllSeats()) {
			if(sections.getSid().equals("123")) {
				section = sections;
			}
		}
		show.add(section);
		List<Seat> seats = new ArrayList<Seat>();
		seats.add(new Seat("201", "open"));
		
		showManager.SetTicketStatusToSold(show.getID(), "123", seats);
		
		String status = showManager.findById(show.getID()).getSectionById("123").getSeating().get(0).getSeatByID("201").getStatus();
		assertTrue(status.equals("sold"));
	}


}
