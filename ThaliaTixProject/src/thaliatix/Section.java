package thaliatix;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;



public class Section {

	
	@Expose  private String sid;
	@Expose private String section_name;
	private int price;
	private List<Seating> seating = new ArrayList<Seating>();
	
	public Section() {
		
	}
	public Section(String sct_name, String sID, int price) {
		this.sid = sID;
		this.section_name = sct_name;
		this.price = price;
	}
	
	public boolean matchesId(String lid) {
		return(lid.equals(this.sid));
	}
	
	public boolean isNil() {
        return false;
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
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public List<Seating> getSeating() {
		return this.seating;
	}
	public void setSeating(List<Seating> seating) {
		this.seating = seating;
	}
	
	public void addSeating(Seating seatingToAdd) {
		
		this.seating.add(seatingToAdd);
	}
	
	
	
	@Override
	public String toString() {
		return "Section [sid=" + sid + ", section_name=" + section_name + ", price=" + price + ", seating=" + seating
				+ "]";
	}
	public void toStringOut() {
		System.out.println("Section [sid=" + sid + ", section_name=" + section_name + ", price=" + price + ", seating=");
		for(Seating seat:seating) {
			System.out.println(seat.toString());
		}
	}
	
	
	
	
	
}
