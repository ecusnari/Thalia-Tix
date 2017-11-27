package thaliatix;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;




@Path("/thalia")
public class REST_controller {
	private BoundaryInterfaceShow showManager = new ShowManager();
	private BoundaryInterfaceSeating seatingManager = new SeatingManager();
	private BoundaryInterfaceOrder orderManager = new OrderManager();
	private BoundaryInterfaceTicket ticketManager = new TicketManager();
	private BoundaryInterfaceDonation donationManager = new DonationManager();
	
	@PostConstruct
    public void postConstruct() {
        //Initialize theater seating
    	
		if(seatingManager.getAllSeats().size()==0) {
			seatingManager.ConfigureGeneralSeatsLayout();
		}
		
	}
    
    
    //CREATE SHOWS
	@Path("/shows")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response makeShow(String json) {
		try {
			//Get data from POST Json input
	        Gson gson = new Gson();
	        JsonObject obj = gson.fromJson(json, JsonObject.class);
			JsonElement showJSON = obj.get("show_info");
			JsonArray seatJSON = obj.getAsJsonArray("seating_info");
			
			String showString = showJSON.toString();
			String seatString = seatJSON.get(0).toString();
			
			System.out.println(showString);
			System.out.println(seatString);
			
	        Show s = gson.fromJson(showString, Show.class); 
	   
	        
	        try{
	        	if(s.getName().isEmpty() || s.getDate().isEmpty() || s.getTime().isEmpty()) {
	        		System.out.println("Missing data.\n");}
	        		
	        }catch(Exception e) {
	        	return Response.status(Response.Status.BAD_REQUEST).entity("Data from show_info is missing.").build();
	        }
	        
	        Show ns = showManager.createShow(s.getName(), s.getWeb(), s.getDate(), s.getTime());
	        
	        BoundaryInterfaceSeating newSeatingLayout = new SeatingManager();
	        newSeatingLayout.ConfigureGeneralSeatsLayout();
	        
	        for(int i = 0; i < seatJSON.size(); i++)
	        {
	        		JsonObject seatJSONobj = seatJSON.get(i).getAsJsonObject();
	        		Section sec = gson.fromJson(seatJSONobj, Section.class);
	        		newSeatingLayout.updateSectionPrice(sec.getSid(), sec.getPrice());
	        }	
	        
		    for(Section section: newSeatingLayout.getAllSeats()) {
		    	if(section.getPrice()!=0) {
		    		ns.add(section);
		    	}
		    }
	        
		    Gson gsonb = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
		    String st = gsonb.toJson(ns);
		    
		    
		    return Response.ok(st, MediaType.APPLICATION_JSON).status(201).build(); 
		}catch(Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
     
    }
    
    //UPDATE SHOWS
    @Path("/shows/{id}")
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateShow(@PathParam("id") String showID, String json) {
    	
        
    	SpecificShowDetail sIn = showManager.getShowDetail(showID);
        
        if (sIn.isNil()) {
            // return a 404
            return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for ID: " + showID).build();
        }
        else {
        
	        try {
				//Get data from PUT JSON input
		        Gson gson = new Gson();
		        JsonObject obj = gson.fromJson(json, JsonObject.class);
				JsonElement showJSON = obj.get("show_info");
				JsonArray seatJSON = obj.getAsJsonArray("seating_info");
				
				String showString = showJSON.toString();
				String seatString = seatJSON.get(0).toString();
				
				System.out.println(showString);
				System.out.println(seatString);
				
				//Show object retrieved from PUT JSON
		        Show s = gson.fromJson(showString, Show.class);      
		        
		        //Check if PUT JSON has the basic fields
		        try{
		        	if(s.getName().isEmpty() || s.getDate().isEmpty() || s.getTime().isEmpty()) {
		        		System.out.println("Missing data.\n");}
		        		
		        }catch(Exception e) {
		        	return Response.status(Response.Status.BAD_REQUEST).entity("Data from show_info is missing.").build();
		        }
		        
		        
		        //Add to s object the section information from PUT JSON
		        System.out.println("Seating manager set=up for new show(PUT)");
		        BoundaryInterfaceSeating newSeatingLayout = new SeatingManager();
		        newSeatingLayout.ConfigureGeneralSeatsLayout();
		        
		        for(int i = 0; i < seatJSON.size(); i++)
		        {
		        		JsonObject seatJSONobj = seatJSON.get(i).getAsJsonObject();
		        		Section sec = gson.fromJson(seatJSONobj, Section.class);
		        		newSeatingLayout.updateSectionPrice(sec.getSid(), sec.getPrice());
		        }	
		        
			    for(Section section: newSeatingLayout.getAllSeats()) {
			    	if(section.getPrice()!=0) {
			    		s.add(section);
			    	}
			    }	
			      
		        s.setWid(showID);
		        
        		showManager.updateShow(showID, s);
        		Gson gsonb = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    		    String st = gsonb.toJson(s);
        		return Response.status(Response.Status.ACCEPTED).build();
		}catch(Exception e) {
			e.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
        
        }
     
    }
    
    
  //VIEW SHOWS by ID
    @Path("/shows/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSpecificShow(@PathParam("id") String lid) {
        // call the "Get Show Detail" use case
        SpecificShowDetail s = showManager.getShowDetail(lid);
        if (s.isNil()) {
            // return a 404
            return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for ID: " + lid).build();
        } else {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String st = gson.toJson(s);
            return Response.ok(st).build();
        }
    }
    
    //VIEW ALL SHOWS
    @Path("/shows")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllShows() {
        // calls the "Get All Shows" use case
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String s = gson.toJson(showManager.getAllShows());
        return Response.status(Response.Status.OK).entity(s).build();
    }

    //VIEW SHOW if given ID and view all it's sections
    @Path("/shows/{id}/sections")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSectionsForShowID(@PathParam("id") String  wid) {
    	
    	//calls the "Get Show Sections" use case
    	Gson gson = new GsonBuilder().setPrettyPrinting().create();
    	String s = gson.toJson(showManager.getShowSections(wid));
    	System.out.println(s);
    	return Response.status(Response.Status.OK).entity(s).build();
    	
    }
    
    //VIEW SHOW if given wid and VIEW particular sections if given sid
    @Path("shows/{id}/sections/{sid}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getExactSectionForShowID(@PathParam("id") String showID, @PathParam("sid") String secID) {
		
    	ShowSectionDetailedInfo s = showManager.getShowSectionDetail(showID, secID);
        if (s == null) {
            // return a 404
            return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for ID: " + showID).build();
        } else {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String st = gson.toJson(s);
            return Response.ok(st).build();
        }
    	
    	    	
    }
    
    
    
    ///////////////////////////////SEATING//////////////////////////////
    
    //VIEW ALL SEATING
    @Path("seating")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    //Two use cases covered:
    //1. Get all seatings
    //2. Get requested seats
    public Response getAllSeats(@QueryParam("show") String showID, @QueryParam("section") String sectionID, @QueryParam("count") int count, @QueryParam("starting_seat_id") String start_cid) {
        // calls the "Get All Seats" use case
    	System.out.println("wid: " + showID + ", sid:" + sectionID + ", count:" + count + " start id: " + start_cid);
    	if(showID==null || sectionID == null || count == 0) {
    		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();
            String s = gson.toJson(seatingManager.getAllSeats());
            return Response.status(Response.Status.OK).entity(s).build();
    	}
    	else if(showID != null && sectionID != null && count != 0) {
    		SeatRequest jsonSeatsRequested =  showManager.GetRequestedSeats(showID, sectionID, count, start_cid);
    		if(jsonSeatsRequested == null) {
    			return Response.status(Response.Status.BAD_REQUEST).entity("The start id does not exist in the chosen section").build();
    		}
    		Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String st = gson.toJson(jsonSeatsRequested);
            return Response.ok(st).build();
    	}
		return Response.status(Response.Status.BAD_REQUEST).entity("Request Path does not match any resourse for /seating").build();
        
    }
    
    //VIEW SPECIFIC SECTION
    @Path("/seating/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSpecificSection(@PathParam("id") String lid) {
        // call the "Get Show Detail" use case
        SectionDetail s = seatingManager.getSectionDetail(lid);
        if (s.isNil()) {
            // return a 404
            return Response.status(Response.Status.NOT_FOUND).entity("Entity not found for ID: " + lid).build();
        } else {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String st = gson.toJson(s);
            return Response.ok(st).build();
        }
    }

    ///////////////////////////////ORDERS///////////////////////////////
    
    //CREATE ORDER
    @Path("/orders")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response makeOrder(String json) {
		
    	try {
    		//Get data from POST Json input
	        Gson gson = new Gson();
	        JsonObject obj = gson.fromJson(json, JsonObject.class);
			
	        JsonElement widJSON = obj.get("wid");
	        JsonElement sidJSON = obj.get("sid");
			JsonArray seatsJSON = obj.getAsJsonArray("seats");
			JsonElement patronInfoJson = obj.get("patron_info");
			
			String widString = widJSON.getAsString();
			String sidString = sidJSON.getAsString();
			String patronInfoString = patronInfoJson.toString();
			
			System.out.println(widString);
			System.out.println(sidString);
			System.out.println(patronInfoString);
			
			try{
				if(widString.isEmpty()|| sidString.isEmpty()||patronInfoString.isEmpty()) {
					System.out.println("Missing data in the input JSON.");
				}
			}catch(Exception e) {
				return Response.status(Response.Status.BAD_REQUEST).entity("Data from input JSON is missing.").build();
			}
			
			
			String start_cid;
			List<Seat> seats = new ArrayList<Seat>();
			try {
				for(int i = 0; i < seatsJSON.size();i++) {
					JsonObject seatJsonobj = seatsJSON.get(i).getAsJsonObject();
					Seat seatRetrieved = gson.fromJson(seatJsonobj, Seat.class);
					seats.add(seatRetrieved);
				}
				start_cid = seats.get(0).getCid();
			}catch(Exception e){
				return Response.status(Response.Status.BAD_REQUEST).entity("Data from input JSON is missing for 'seats'.").build();
			}
			
			//Check Requested Seats Availability
			boolean seatsAvailable = showManager.CheckSeatsAvailability(widString, sidString, seats);
			if(!seatsAvailable) {return Response.status(Response.Status.BAD_REQUEST).entity("Seats requested are unavailable").build();}
				        
			Order newOrder = new Order();
			PatronInfo newPatron = gson.fromJson(patronInfoString, PatronInfo.class);
			ShowInfo showInfo = new ShowInfo(showManager.findById(widString));
			newOrder.setWid(Integer.toString((Integer.parseInt(widString))));
			System.out.println(newOrder.getWid());
			newOrder.setShow_info(showInfo);
			System.out.println(newOrder.getShow_info().toString());
			newOrder.setNumber_of_tickets(seats.size());
			newOrder.setPatron_info(newPatron);
			
			//Update Status of seats to "sold"
			showManager.SetTicketStatusToSold(widString, sidString, seats);
			
			//Create Tickets and add to Ticket Manager
			int price = showManager.GetSectionPriceForShow(widString, sidString);
			
			for(Seat availableSeat:seats) {
				Section getSectionInfoForTicket = showManager.getSectionInfoForSeat(widString, sidString, availableSeat.getCid());
								
				Ticket newTicket = new Ticket(widString, showInfo, newPatron, getSectionInfoForTicket);
				Ticket ticketCompleted = ticketManager.createTicket(newTicket);
				//Add Ticket to Order
				newOrder.addTicket(ticketCompleted);
				
			}
			
			//Add order_amount to newOrder
			newOrder.setOrder_amount(price*seats.size());
			
	        
	        Order orderCompleted = orderManager.createOrder(newOrder);
	        
	        
		    Gson gsonO = new GsonBuilder().setPrettyPrinting().create();
		    String st = gsonO.toJson(new OrderReducedInfo(orderCompleted));		    
		    return Response.ok(st, MediaType.APPLICATION_JSON).status(201).build();
    		
    	}catch(Exception e){
    		e.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST).build();
    	}
    	
    	
    }

    
    //GET ALL ORDERS and ALL ORDERS BETWEEN TWO DATES
    @Path("/orders")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllOrders(@QueryParam("start_date") String start_date, @QueryParam("end_date") String end_date) {
    	
    	if(start_date == null || end_date==null) {
    		Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String s = gson.toJson(orderManager.getAllOrders());
            return Response.status(Response.Status.OK).entity(s).build();
    	}else {
    		try {
    			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
    			Date startDate = dateFormat.parse(start_date);
    			Date endDate = dateFormat.parse(end_date);
    		}catch(Exception e) {
    			return Response.status(Response.Status.BAD_REQUEST).entity("Start or End date have the wrong format").build();
    		}
    		
    		Gson gson = new GsonBuilder().setPrettyPrinting().create();
    		String s = gson.toJson(orderManager.getAllOrdersInTimeFrame(start_date, end_date));
    		return Response.status(Response.Status.OK).entity(s).build();
    	}
    	
    }
    
    //GET ORDER BY ID
    @Path("/orders/{oid}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrderById(@PathParam("oid") String orderID) {
    	Gson gson = new GsonBuilder().setPrettyPrinting().create();
    	Order getOrder = orderManager.getOrderById(orderID);
    	if(getOrder.isNill()) {
    		return Response.status(Response.Status.NOT_FOUND).entity("Order not found for ID: " + orderID).build();
    	}
        String s = gson.toJson(getOrder);
        return Response.status(Response.Status.OK).entity(s).build();
    }
    

    //SUBSCRIBE
    @Path("/shows/{wid}/donations")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response subscribeForDonation(@PathParam("wid") String showID, String json) {
    	try {
    		//Get data from POST Json input
	        Gson gson = new Gson();
	        JsonObject obj = gson.fromJson(json, JsonObject.class);
			
	        JsonElement widJSON = obj.get("wid");
	        JsonElement countJSON = obj.get("count");
			JsonElement patronInfoJson = obj.get("patron_info");
			
			String widString = widJSON.getAsString();
			int countInt = countJSON.getAsInt();
			String patronInfoString = patronInfoJson.toString();
			
			if(!widString.equals(showID)) {return Response.status(Response.Status.BAD_REQUEST).entity("Mismatch in url show ID and POST 'wid'.").build();}
			
			System.out.println("Donation POST");
			System.out.println(widString);
			System.out.println(countInt);
			System.out.println(patronInfoString);
			
			try{
				if(widString.isEmpty()||patronInfoString.isEmpty()) {
					System.out.println("Missing data in the input JSON.");
				}
			}catch(Exception e) {
				return Response.status(Response.Status.BAD_REQUEST).entity("Data from input JSON is missing.").build();
			}
			
			PatronInfo newPatron = gson.fromJson(patronInfoString, PatronInfo.class);
			List<String> ticketsList = orderManager.getDonatedTickets(showID, countInt);
			
			String status;
			
			if(ticketsList.size()==0) {
				status = "pending";
			}else {
				status = "assigned";
				
			}
			
			Donation newSubscription = new Donation(showID, countInt, status, ticketsList, newPatron);
			System.out.println("newDonation: " + newSubscription );
			
			Gson gsonD = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
		    String st = gsonD.toJson(donationManager.createSubscription(newSubscription));		    
		    return Response.ok(st, MediaType.APPLICATION_JSON).status(201).build();
			
    	}catch(Exception e){
    		return Response.status(Response.Status.BAD_REQUEST).entity("Data from input JSON is missing.").build();
    	}
    	
    	
    }
    
    //GET SUBSCRIPTION BY ID
    @Path("/shows/{wid}/donations/{did}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSubscriptionInfo(@PathParam("wid") String showID, @PathParam("did") String donationID) {
    	
    	
    	Donation foundSubscription = donationManager.getSubscriptionById(showID, donationID);
    	if(foundSubscription == null) {
    		return Response.status(Response.Status.BAD_REQUEST).entity("No such subscription found for wid " +showID + " and did "+donationID).build();
    	}else {
    		Gson gsonO = new GsonBuilder().setPrettyPrinting().create();
    		String st = gsonO.toJson(foundSubscription);		    
    	    return Response.ok(st, MediaType.APPLICATION_JSON).status(201).build();
    	}
	    
    	
    } 
    
    //DONATE TICKETS
    @Path("/tickets/donations")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response donateTicket(String json) {
    	Gson gson = new Gson();
        JsonObject obj = gson.fromJson(json, JsonObject.class);
		
        
		JsonElement ticketsJson = obj.get("tickets");
		String ticketsString = ticketsJson.toString();
		
		List<String> tickets = gson.fromJson(ticketsString, List.class); 
		
		if(tickets.size()==0) {
			return Response.status(Response.Status.BAD_REQUEST).entity("No tickets provided.").build();
		}
		
		
		System.out.println("Donating Tickets POST");
		System.out.println(ticketsJson.toString());
		int i;
		for(i=0; i < tickets.size(); i++) {
			
			String tid = tickets.get(i);
			
			//mark order->ticket status as donated
			orderManager.setTicketStatusDonated(tid);
			
			//loop through sorted donation list to assign new donations
			donationManager.assignNewDonations(tid);
		}
    	return Response.status(Response.Status.CREATED).entity("").build();
    }
    
    
    //GET REPORTS and GET REPORTS BY 
    @Path("/reports/")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReports() {
    	Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
    	List<Report> reports = new ArrayList<Report>();
    	reports.add(new Report("801", "Theatre occupancy"));
    	reports.add(new Report("802", "Revenue from ticket sales"));
    	reports.add(new Report("803", "Donated tickets report"));
    	
        String s = gson.toJson(reports);
        return Response.status(Response.Status.OK).entity(s).build();
    	
    }
    
    
    
    
}
