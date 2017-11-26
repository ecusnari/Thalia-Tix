package thaliatix;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;

public class SeatingReducedInfo {

	private String row;
	private List<String> seats = new ArrayList<String>();
	 
	
	
	public SeatingReducedInfo(Seating seating) {
		this.row = seating.getRow();
		this.seats = seating.getSeatsStringList();
		
	}
	
}
