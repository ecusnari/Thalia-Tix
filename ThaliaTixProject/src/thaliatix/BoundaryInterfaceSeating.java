package thaliatix;

import java.util.List;

public interface BoundaryInterfaceSeating {
	List<Section> getAllSeats();
	Section createSections(String sct_name, String sID, int price);
	Seating createSeating(String r, int seatNum, int cid);
	void addSeatingToSection(String sectionID, Seating seatingToAdd);
	SectionDetail getSectionDetail(String lid);
	void ConfigureGeneralSeatsLayout();
	public void updateSectionPrice(String sID, int price);
}
