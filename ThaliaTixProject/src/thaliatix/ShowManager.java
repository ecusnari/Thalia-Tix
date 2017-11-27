package thaliatix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;


public class ShowManager implements BoundaryInterfaceShow{

	private static List<Show> Shows = new ArrayList<Show>();


    public Show createShow(String name, String web, String date, String time) {
        Show s= new Show(name, web, date, time);
        Shows.add(s);
        return(s);
    }

    public Show findById(String lid) {
        Iterator<Show> li = Shows.listIterator();
        
        while(li.hasNext()) {
        		
        		Show s = li.next();
        
        		if(s.matchesId(lid))
            		return(s);
        		
        		
        }
        return(new NullShow());
    }
    
    public List<ShowDetail> getAllShows() {
        List<ShowDetail> showDetailList = new ArrayList<ShowDetail>();
    	for(Show s:Shows) {
        	showDetailList.add(new ShowDetail(s));
        }
    	return(showDetailList);
    }


	public SpecificShowDetail getShowDetail(String lid) {
		
		Show showFound = findById(lid);
		if(showFound.isNil()) {return new SpecificShowDetailNull();}
		
		SpecificShowDetail showDetail = new SpecificShowDetail(showFound);
		
		return(showDetail);
	}

	public void updateShow(String id, Show s) {
		Show rep = findById(id);
		Shows.remove(rep);
		Shows.add(s);
	}
	
	public List<SectionInfo> getShowSections(String wid){
		Show show = findById(wid);
		List<SectionInfo> sectionInfo = new ArrayList<SectionInfo>();
		for(Section section: show.getSections()) {
			sectionInfo.add(new SectionInfo(section));
		}
		return sectionInfo;
	}
	
	public ShowSectionDetailedInfo getShowSectionDetail(String wid, String sid){
		
		
		Show show = findById(wid);
		for(Section section:show.getSections()) {
			if(section.matchesId(sid)) {
				return(new ShowSectionDetailedInfo(show, section));
			}
		}		
		
		return null;		
	}
	
	public SeatRequest GetRequestedSeats(String wid, String sid, int count, String starting_seat_id) {
		
		
		ShowSectionDetailedInfo seatsQuery =  getShowSectionDetail(wid, sid);
		if(seatsQuery == null) {
			return null;//404 not found
		}
		
		SeatRequest requestResult = new SeatRequest(seatsQuery);
		List<Seating> listSeatings = seatsQuery.getSeating();
		Collections.sort(listSeatings, Comparator.comparing(Seating::getRow));
		
		String firstSeatIdOfSeaction;
		if(starting_seat_id != null) {
			if(Integer.parseInt(starting_seat_id) > Integer.parseInt(listSeatings.get(listSeatings.size()-1).getSeats().get(listSeatings.get(listSeatings.size()-1).getSeats().size()-1).getCid())) {
				return null;//invalid start cid for the given section-->404 error
			}else {
				firstSeatIdOfSeaction = starting_seat_id;
			}
		}else {
			firstSeatIdOfSeaction = listSeatings.get(0).getSeats().get(0).getCid();
			System.out.print("First seat cid: " + firstSeatIdOfSeaction);
		}
		
		
		Seating availableContiguousSeats = FindNContiguousSeats(count, listSeatings, firstSeatIdOfSeaction);
		if(availableContiguousSeats.getSeats().size()==0) {
			//Populate object accordingly to empty query result
			requestResult.setStarting_seat_id(firstSeatIdOfSeaction);
			requestResult.setStatus("Error: " + count + " contiguous seats not available");
			return requestResult;
		}else {
			//Populate object according to all available fields
			requestResult.setStarting_seat_id(firstSeatIdOfSeaction);
			requestResult.setStatus("ok");
			requestResult.setTotal_amount(availableContiguousSeats.getSeats().size()*seatsQuery.getPrice());
			requestResult.addSeating(availableContiguousSeats);
			return requestResult;
		}
		
		
	}
	
	private Seating FindNContiguousSeats(int count, List<Seating> seactionSeatings, String start_cid){
		List<Seat> resultListOfSeats = new ArrayList<Seat>();
		
		for(Seating seating:seactionSeatings) {
			int rowLength = seating.getSeats().size();
			String firstSeatCID = seating.getSeats().get(0).getCid();
			String lastSeatCID = seating.getSeats().get(rowLength-1).getCid();
			if(count <= rowLength) {
				for(Seat seat:seating.getSeats()) {
					String firstSeatCheckedCID = seat.getCid();
					
					int lastSeat = Integer.parseInt(firstSeatCheckedCID)+count - 1;
					System.out.println("Last seat int: " + lastSeat);
					
					String lastContiguousSeatCID = Integer.toString(lastSeat);
					System.out.println("lastContiguousSeatCID: " + lastContiguousSeatCID);
					
					System.out.println("seat status: " + seat.getStatus());
					System.out.println("if last seat is !null " + !seating.getSeatByID(lastContiguousSeatCID).isNill());
					System.out.println("last seat available " + seating.getSeatByID(lastContiguousSeatCID).getStatus());
					System.out.println("first seat of section" + Integer.parseInt(firstSeatCheckedCID));
					
					if(seat.getStatus().equals("available") && !seating.getSeatByID(lastContiguousSeatCID).isNill() && seating.getSeatByID(lastContiguousSeatCID).getStatus().equals("available") && 
							Integer.parseInt(firstSeatCheckedCID)>=Integer.parseInt(start_cid)) {
						resultListOfSeats = checkSeatsAvailabilityInBetween(seating, firstSeatCheckedCID, lastContiguousSeatCID);
						if(resultListOfSeats.size()!=0) {
							Seating resultSeating = new Seating(seating.getRow());
							resultSeating.setSeats(resultListOfSeats);
							return resultSeating;
						}
					}
				}
			}
			
		}
		Seating resultEmptySeating = new Seating("0");
		return resultEmptySeating;
	}

	private List<Seat> checkSeatsAvailabilityInBetween(Seating seating, String firstSeatCheckedCID,
			String lastContiguousSeatCID) {
		List<Seat> listAvailableSeats = new ArrayList<Seat>();
		for(int i = Integer.parseInt(firstSeatCheckedCID); i<= Integer.parseInt(lastContiguousSeatCID);i++) {
			if(seating.getSeatByID(Integer.toString(i)).getStatus().equals("available")) {
				listAvailableSeats.add(seating.getSeatByID(Integer.toString(i)));
			}else {
				listAvailableSeats.clear();
				return listAvailableSeats;
			}
		}
	
		return listAvailableSeats;
	}
	
	
	public boolean CheckSeatsAvailability(String showID, String sectionID, List<Seat> seatsList) {
		Show showToInspect = findById(showID);
		Section sectionToInspect = showToInspect.getSectionById(sectionID);
		for(Seating seating:sectionToInspect.getSeating()) {
			if(seating.ContainsSeat(seatsList.get(0).getCid())) {
				List<Seat> seatsAvailable = checkSeatsAvailabilityInBetween(seating, seatsList.get(0).getCid(), seatsList.get(seatsList.size()-1).getCid());
				if(seatsAvailable.size() > 0) {return true;}
				else {return false;}
			}
		}
		
		return false;
	}
	
	public void SetTicketStatusToSold(String wid, String sid, List<Seat> seats) {
		Show showToInspect = findById(wid);
		Section sectionToInspect = showToInspect.getSectionById(sid);
		for(Seating seating:sectionToInspect.getSeating()) {
			if(seating.ContainsSeat(seats.get(0).getCid())) {
				for(Seat seatSold:seating.getSeats()) {
					for(Seat seatToFind:seats) {
						if(seatSold.matchesSeatID(seatToFind.getCid())) {seatSold.setStatus("sold");}
					}
				}
			}
		}
	}
	
	public int GetSectionPriceForShow(String wid, String sid) {
		Show showToInspect = findById(wid);
		Section sectionToInspect = showToInspect.getSectionById(sid);
		return sectionToInspect.getPrice();
	}
	
	public Section getSectionInfoForSeat(String wid, String sid, String cid) {
		System.out.println("Trying to add seat : " + cid + "to ticket 'Seating'");
		Show showToInspect = findById(wid);
		Section sectionToInspect = showToInspect.getSectionById(sid);
		Section sectionInfo = new Section(sectionToInspect.getSection_name(), sectionToInspect.getSid(), sectionToInspect.getPrice());
		
		Seating seatingReturn;
		for(Seating seating:sectionToInspect.getSeating()) {
			if(seating.ContainsSeat(cid)) {
				System.out.println("getSectionInfoForSeat: trying to add the seat");
				seatingReturn = new Seating(seating.getRow());
				System.out.println("getSectionInfoForSeat: Create SeatingRetunr "+seatingReturn.toString());
				seatingReturn.addSeatObject(seating.getSeatByID(cid));
				System.out.println("getSectionInfoForSeat: Seating added "+seatingReturn.toString());
				sectionInfo.addSeating(seatingReturn);
				System.out.println("getSectionInfoForSeat: Complete sectionInfo ");
				sectionInfo.toStringOut();
				return sectionInfo;
			}
		}
		
		return new NullSection();
	}
}
