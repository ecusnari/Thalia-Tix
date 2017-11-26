package thaliatix;

public class ReducedTicketInfo {

	private String tid;
	private String status;
	
	public ReducedTicketInfo(Ticket ticket) {
		this.tid = ticket.getTid();
		this.status = ticket.getStatus();
	}
	
	public String getTid() {
		return this.tid;
	}
	
	public String getStatus() {
		return this.status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
}
