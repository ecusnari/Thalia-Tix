package thaliatix;

import java.util.ArrayList;
import java.util.List;

public class ShowSectionDetailedInfo {

	private String wid;
	private ShowInfo show_info;
	private String sid;
	private String section_name;
	private int price;
	private List<Seating> seating = new ArrayList<Seating>();
	
	public ShowSectionDetailedInfo(Show show, Section section){
		this.wid = show.getID();
		this.show_info = new ShowInfo(show);
		this.sid = section.getSid();
		this.section_name = section.getSection_name();
		this.price = section.getPrice();
		this.seating.addAll(section.getSeating());
	}

	public String getWid() {
		return wid;
	}

	public ShowInfo getShow_info() {
		return show_info;
	}

	public String getSid() {
		return sid;
	}

	public String getSection_name() {
		return section_name;
	}

	public int getPrice() {
		return price;
	}

	public List<Seating> getSeating() {
		return seating;
	}
	
	
}
