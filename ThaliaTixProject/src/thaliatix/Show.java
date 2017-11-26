package thaliatix;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;


public class Show {
	
	@Expose private String wid;
	
	private String name;
	private String web;
	private String date;
	private String time;
	
	private List<Section> sections = new ArrayList<Section>();
	
	public Show() {
	}
	
	public Show(String name, String web, String date, String time) {
		this.name = name;
		this.web = web;
		this.date = date;
		this.time = time;		
		this.wid = Integer.toString(UniqueIdGenerator.getUniqueID());
	}
	
	public String getID() {
		return this.wid;
	}
	
	public boolean matchesId(String lid) {
		return(lid.equals(this.wid));
	}
	
	public boolean isNil() {
        return false;
    }

	public String getName() {
		return name;
	}

	public String getWeb() {
		return web;
	}

	public String getDate() {
		return date;
	}

	public String getTime() {
		return time;
	}

	

	public void setWid(String wid) {
		this.wid = wid;
	}
	
	public void add(Section sec) {
		sections.add(sec);
	}
	
	public List<Section> getSections(){
		return this.sections;
	}

	public Section getSectionById(String sectionId) {
		for(Section section:this.sections) {
			if(section.matchesId(sectionId)) {
				return section;
			}
		}
		return new NullSection();
	}
	
	@Override
	public String toString() {
		return "Show [wid=" + wid + ", name=" + name + ", web=" + web + ", date=" + date + ", time=" + time
				+ ", sections=" + sections + "]";
	}
	
	

}
