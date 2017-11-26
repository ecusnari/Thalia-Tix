package thaliatix;

import java.util.ArrayList;
import java.util.List;

public class Ticket {
	
	private String tid;
	private int price;
	private String status;
	private String wid;
	private ShowInfo show_info;
	private PatronInfo patron_info;
	private String sid;
	private String section_name;
	private List<Seating> seating = new ArrayList<Seating>();
	
	public Ticket(String showID, ShowInfo showInfo, PatronInfo patronInfo, Section sectionInfo) {
		this.price = sectionInfo.getPrice();
		this.status = "open";
		this.wid = showID;
		this.show_info = showInfo;
		this.patron_info = patronInfo;
		this.sid = sectionInfo.getSid();
		this.section_name = sectionInfo.getSection_name();
		this.seating.add(sectionInfo.getSeating().get(0));
	}
	
	public String getTid() {
		return tid;
	}
	public void setTid() {
		this.tid = Integer.toString(UniqueOIDGenerator.getUniqueID());
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getWid() {
		return wid;
	}
	public void setWid(String wid) {
		this.wid = wid;
	}
	public ShowInfo getShow_info() {
		return show_info;
	}
	public void setShow_info(ShowInfo show_info) {
		this.show_info = show_info;
	}
	public PatronInfo getPatron_info() {
		return patron_info;
	}
	public void setPatron_info(PatronInfo patron_info) {
		this.patron_info = patron_info;
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getSection_name() {
		return section_name;
	}
	public void setSection_name(String section_name) {
		this.section_name = section_name;
	}
	public List<Seating> getSeating() {
		return seating;
	}
	public void setSeating(List<Seating> seating) {
		this.seating = seating;
	}
	@Override
	public String toString() {
		return "Ticket [tid=" + tid + ", price=" + price + ", status=" + status + ", wid=" + wid + ", show_info="
				+ show_info + ", patron_info=" + patron_info + ", sid=" + sid + ", section_name=" + section_name
				+ ", seating=" + seating + "]";
	}
	
	
}
