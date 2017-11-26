package thaliatix;

import java.util.ArrayList;
import java.util.List;


public class Seating {

	private String row;
	private List<Seat> seats = new ArrayList<Seat>();
	 
	
	public Seating(String r) {
		row = r;
	}
	
	public void addSeat(int seatNum, String cid) {
		this.seats.add(new Seat(cid, Integer.toString(seatNum)));
	}

	public void addSeatObject(Seat seat) {
		this.seats.add(seat);
	}
	public String getRow() {
		return row;
	}

	public void setRow(String row) {
		this.row = row;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	public void setSeats(List<Seat> seats) {
		this.seats = seats;
	}
	
	public List<String> getSeatsStringList(){
		List<String> seatsStringList = new ArrayList<String>();
		for(Seat seat:seats) {
			seatsStringList.add(seat.getSeat());
		}
		return seatsStringList;
	}
	
	public Seat getSeatByID(String cid) {
		for(Seat seat:seats) {
			if(seat.matchesSeatID(cid)) {
				return seat;
			}
		}
		return new SeatNull();
	}
	
	public boolean ContainsSeat(String cid) {
		for(Seat seat:this.seats) {
			if(seat.matchesSeatID(cid)) {
				return true;
			}	
		}
		return false;
	}

	private String SeatsToString() {
		String result = "";
		for(Seat seat:seats) {
			result = result.concat("cid: " + seat.getCid() + ", seat" + seat.getSeat());
		}
		return result;
	}
	
	@Override
	public String toString() {
		return "Seating [row=" + row + ", seats="+ SeatsToString();
	}
	
	
}
