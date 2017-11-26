package thaliatix;

import java.util.ArrayList;
import java.util.List;

public class SeatRequest {

	private String wid;
	private ShowInfo show_info;
	private String sid;
	private String section_name;
	private String starting_seat_id;
	private String status;
	private int total_amount;
	private List<Seating> seating = new ArrayList<Seating>();
	
	public SeatRequest(ShowSectionDetailedInfo sectionDetails) {
		this.wid = sectionDetails.getWid();
		this.show_info = sectionDetails.getShow_info();
		this.sid = sectionDetails.getSid();
		this.section_name = sectionDetails.getSection_name();
	}

	

	public void setStarting_seat_id(String starting_seat_id) {
		this.starting_seat_id = starting_seat_id;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setTotal_amount(int total_amount) {
		this.total_amount = total_amount;
	}
	
	public void addSeating(Seating availableSeating) {
		this.seating.add(availableSeating);
	}
	
}
