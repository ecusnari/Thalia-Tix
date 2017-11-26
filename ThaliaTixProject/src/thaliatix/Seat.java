package thaliatix;

public class Seat {

	
	
	private String cid;
	private String seat;
	private String status;
	
	public Seat() {}
	
	public Seat(String cid, String seat) {
		this.cid = cid;
		this.seat = seat;
		this.status = "available";
	}
	
	public boolean isNill() {
		return false;
	}
	public void changeSeatStatus() {
		this.status = "occupied"; 
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String available) {
		this.status = available;
	}
	
	public boolean matchesSeatID(String cid) {
		if(this.cid.equals(cid)) {return true;}
		else {return false;}
	}
	
	
}
