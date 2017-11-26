package thaliatix;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;

public class Report {

	@Expose private String mrid;
	@Expose private String name;
	private String start_date;
	private String end_date;
	private int total_shows;
	private int total_seats;
	private String overall_occupancy;
	private List<Show> shows = new ArrayList<Show>();
	
	public Report(String mrid, String name) {
		this.mrid = mrid;
		this.name = name;
	}
}
