package thaliatix;

public class SeatingInfo {

	String sid;
	int price;
	
	public SeatingInfo(Section section) {
		this.sid = section.getSid();
		this.price = section.getPrice();
	}
}
