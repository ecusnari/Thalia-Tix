package thaliatix;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;

public class Donation {

	@Expose private String did;
	private String wid;
	private int count;
	private String status;
	private List<String> tickets = new ArrayList<String>();
	private PatronInfo patron_info;
	
	public Donation(String showID, int countInt, String status, List<String> ticketsList, PatronInfo newPatron) {
		this.wid = showID;
		this.count = countInt;
		this.status = status;
		this.tickets.addAll(ticketsList);
		this.patron_info = newPatron;
	}
	
	public void addTicket(String tid) {
		this.tickets.add(tid);
	}
	public String getDid() {
		return did;
	}
	public void setDid() {
		this.did = Integer.toString(UniqueDIDGenerator.getUniqueID());
	}
	public String getWid() {
		return wid;
	}
	public void setWid(String wid) {
		this.wid = wid;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public List<String> getTickets() {
		return tickets;
	}
	public void setTickets(List<String> tickets) {
		this.tickets = tickets;
	}
	public PatronInfo getPatron_info() {
		return patron_info;
	}
	public void setPatron_info(PatronInfo patron_info) {
		this.patron_info = patron_info;
	}

	@Override
	public String toString() {
		return "Donation [did=" + did + ", wid=" + wid + ", count=" + count + ", status=" + status + ", tickets="
				+ tickets + ", patron_info=" + patron_info + "]";
	}
	
	
	
}
