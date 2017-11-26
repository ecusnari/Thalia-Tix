package thaliatix;

import java.util.ArrayList;
import java.util.List;

public class SectionDetail {
	private String sid;
	private String section_name;
	private List<SeatingReducedInfo> seating = new ArrayList<SeatingReducedInfo>();
	
	public SectionDetail(Section section){
		this.sid = section.getSid();
		this.section_name = section.getSection_name();
		
		for(Seating seating:section.getSeating()) {
			this.seating.add(new SeatingReducedInfo(seating));
		}
	}
	
	public boolean isNil() {
        return false;
    }
	
	
}
