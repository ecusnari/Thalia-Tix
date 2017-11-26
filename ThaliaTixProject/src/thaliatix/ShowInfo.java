package thaliatix;

public class ShowInfo {

	private String name;
	private String web;
	private String date;
	private String time;
	
	public ShowInfo(Show s) {
		super();
		this.name = s.getName();
		this.web = s.getWeb();
		this.date = s.getDate();
		this.time = s.getTime();
	}

	@Override
	public String toString() {
		return "ShowInfo [name=" + name + ", web=" + web + ", date=" + date + ", time=" + time + "]";
	}

	
}
