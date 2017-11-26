package thaliatix;

import java.util.ArrayList;
import java.util.List;

public class ShowDetail {

	private String wid;
	private ShowInfo show_info;
	
	public ShowDetail(Show s) {
		this.show_info = new ShowInfo(s);		
		this.wid = s.getID();
	}
}
