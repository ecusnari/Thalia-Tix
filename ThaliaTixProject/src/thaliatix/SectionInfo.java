package thaliatix;

public class SectionInfo {

	private String sid;
	private String section_name;
	//private int price;
	
	public SectionInfo(Section section) {
		System.out.println(section.toString());
		this.sid = section.getSid();
		this.section_name = section.getSection_name();
		//this.price = section.getPrice();
	}

	@Override
	public String toString() {
		return "SectionInfo [sid=" + sid + ", section_name=" + section_name + "]";
	}
	
	
}
