package thaliatix;

import java.util.ArrayList;
import java.util.List;

public class SpecificShowDetail {

	private String wid;
	private ShowInfo show_info;
	private List<SeatingInfo> seating_info= new ArrayList<SeatingInfo>();
	
	public SpecificShowDetail() {
		
	}
	
	public SpecificShowDetail(Show show) {
		this.wid = show.getID();
		this.show_info = new ShowInfo(show);
		
		for(Section section : show.getSections()) {
			seating_info.add(new SeatingInfo(section));
		}
	}
	
	public boolean isNil() {
		return false;
	}
}
