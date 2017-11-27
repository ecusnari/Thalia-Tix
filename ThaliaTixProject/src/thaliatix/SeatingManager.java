package thaliatix;

import java.util.ArrayList;
import java.util.List;


//This Class will contain the general Layout of the Theater Seating in form of the list "Sections"
//This Class contains seating management related functions
public class SeatingManager implements BoundaryInterfaceSeating{

	private List<Section> Sections = new ArrayList<Section>();
	
		
		
		public Section createSections(String sct_name, String sID, int price) {
		   Section s= new Section(sct_name, sID, price);
		   Sections.add(s);
	       return(s);
	    }
	   
		public void updateSectionPrice(String sID, int price) {
			for(Section section: Sections) {
				if(section.matchesId(sID)) {
					section.setPrice(price);
					System.out.println("Price updated for section " + section.getSid()+"to price "+ section.getPrice() );
					return;
				}
			}
		}
		
		public void addSeatingToSection(String sectionID, Seating seatingToAdd) {
			for(Section section: Sections) {
				if(section.matchesId(sectionID)) {
					section.addSeating(seatingToAdd);
				}
			}
		}
		
	   	public List<Section> getAllSeats() {
	   		return(Sections);
	    }
	   	
	   	public SectionDetail getSectionDetail(String lid) {
			Section returnedSection = findById(lid);
			if(returnedSection.isNil()) {
				return new NullSectionDetail();
			}else {
				return(new SectionDetail(returnedSection));
			}
		}
	   	
		public Seating createSeating(String r, int seatNum, int cid) {
			Seating s = new Seating(r);
			s.addSeat(seatNum, Integer.toString(cid));
			return s;
		}
			
		
		private Section findById(String lid) {
			for(Section section: Sections) {
				if(section.matchesId(lid)) {
					return section;
				}
			}
			return(new NullSection());
		}
		public void ConfigureGeneralSeatsLayout() {
			
			System.out.println("ConfigureGeneralSeatsLayout called. At moment size of List<Seactions> is: " + this.Sections.size());			
			//########################ROW 1########################################
			//Front Right
			//ROW 1 Seats 1-4
			createSections("Front right", "123", 0);
			
			int cid = 201;
			String row = "1";
			
			Seating newSeating = new Seating(row);
			newSeating.addSeat(1, Integer.toString(cid));
			
			cid++; 
			newSeating.addSeat(2, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(3, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(4, Integer.toString(cid));
			addSeatingToSection("123", newSeating);
			
			
		
			//Front Center
			//ROW 1 Seats 5-8
			createSections("Front center", "124", 0);
			
			cid++;
			newSeating = new Seating(row);
			newSeating.addSeat(5, Integer.toString(cid));
			
			cid++; 
			newSeating.addSeat(6, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(7, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(8, Integer.toString(cid));
			addSeatingToSection("124", newSeating);
			
			
			//Front Left
			//ROW 1 Seats 9-12
			createSections("Front left", "125", 0);
			
			cid++;
			newSeating = new Seating(row);
			newSeating.addSeat(9, Integer.toString(cid));
			
			cid++; 
			newSeating.addSeat(10, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(11, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(12, Integer.toString(cid));
			addSeatingToSection("125", newSeating);
			
			//########################ROW 2########################################
			//Front Right
			//ROW 2 Seats 1-4		
			row = "2";//row 2
			cid++;			
			newSeating = new Seating(row);
			newSeating.addSeat(1, Integer.toString(cid));
			
			cid++; 
			newSeating.addSeat(2, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(3, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(4, Integer.toString(cid));
			addSeatingToSection("123", newSeating);
			
			
		
			//Front Center
			//ROW 2 Seats 5-8	
			cid++;
			newSeating = new Seating(row);
			newSeating.addSeat(5, Integer.toString(cid));
			
			cid++; 
			newSeating.addSeat(6, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(7, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(8, Integer.toString(cid));
			addSeatingToSection("124", newSeating);
			
			
			//Front Left
			//ROW 2 Seats 9-12
			
			cid++;
			newSeating = new Seating(row);
			newSeating.addSeat(9, Integer.toString(cid));
			
			cid++; 
			newSeating.addSeat(10, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(11, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(12, Integer.toString(cid));
			addSeatingToSection("125", newSeating);
			
			//########################ROW 3########################################
			//Front Right
			//ROW 3 Seats 1-4			
			row="3";//row 3
			cid++;			
			newSeating = new Seating(row);
			newSeating.addSeat(1, Integer.toString(cid));
			
			cid++; 
			newSeating.addSeat(2, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(3, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(4, Integer.toString(cid));
			addSeatingToSection("123", newSeating);
			
			
		
			//Front Center
			//ROW 3 Seats 5-9
			cid++;
			newSeating = new Seating(row);
			newSeating.addSeat(5, Integer.toString(cid));
			
			cid++; 
			newSeating.addSeat(6, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(7, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(8, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(9, Integer.toString(cid));
			addSeatingToSection("124", newSeating);
			
			//Front Left
			//ROW 3 Seats 10-13
			cid++; 
			newSeating = new Seating(row);
			newSeating.addSeat(10, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(11, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(12, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(13, Integer.toString(cid));
			addSeatingToSection("125", newSeating);
			
			
			//########################ROW 4########################################
			//Front Right
			//ROW 4 Seats 1-4			
			row="4";//row 4
			cid++;						
			newSeating = new Seating(row);
			newSeating.addSeat(1, Integer.toString(cid));
			
			cid++; 
			newSeating.addSeat(2, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(3, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(4, Integer.toString(cid));
			addSeatingToSection("123", newSeating);
			
			
		
			//Front Center
			//ROW 4 Seats 5-10
			cid++;			
			newSeating = new Seating(row);
			newSeating.addSeat(5, Integer.toString(cid));
			
			cid++; 
			newSeating.addSeat(6, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(7, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(8, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(9, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(10, Integer.toString(cid));
			addSeatingToSection("124", newSeating);
			
			//Front Left
			//ROW 4 Seats 11-14
			cid++;
			newSeating = new Seating(row);
			newSeating.addSeat(11, Integer.toString(cid));
			
			cid++; 
			newSeating.addSeat(12, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(13, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(14, Integer.toString(cid));
			addSeatingToSection("125", newSeating);
			
			//########################ROW 5########################################
			//Main Right
			//ROW 5 Seats 1-5
			createSections("Main right", "126", 0);
			
			row="5";//row 5
			cid++;
			newSeating = new Seating(row);
			newSeating.addSeat(1, Integer.toString(cid));
			
			cid++; 
			newSeating.addSeat(2, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(3, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(4, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(5, Integer.toString(cid));
			addSeatingToSection("126", newSeating);
			
			
			//Main Center
			//ROW 5 Seats 6-11
			createSections("Main center", "127", 0);
			
			cid++;
			newSeating = new Seating(row);
			newSeating.addSeat(6, Integer.toString(cid));
			
			cid++; 
			newSeating.addSeat(7, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(8, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(9, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(10, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(11, Integer.toString(cid));
			addSeatingToSection("127", newSeating);
	
			
			
			//Main Left
			createSections("Main left", "128", 0);
			//ROW 5 Seats 12-16
			
			cid++;
			newSeating = new Seating(row);
			newSeating.addSeat(12, Integer.toString(cid));
			
			cid++; 
			newSeating.addSeat(13, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(14, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(15, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(16, Integer.toString(cid));
			
			addSeatingToSection("128", newSeating);
			
			//########################ROW 6########################################
			//Main Right
			//ROW 6 Seats 1-5
			row="6";//row 6
			cid++;
			newSeating = new Seating(row);
			newSeating.addSeat(1, Integer.toString(cid));
			
			cid++; 
			newSeating.addSeat(2, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(3, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(4, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(5, Integer.toString(cid));
			
			addSeatingToSection("126", newSeating);
			
			
			//Main Center
			//ROW 6 Seats 6-12			
			cid++;
			newSeating = new Seating(row);
			newSeating.addSeat(6, Integer.toString(cid));
			
			cid++; 
			newSeating.addSeat(7, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(8, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(9, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(10, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(11, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(12, Integer.toString(cid));
			addSeatingToSection("127", newSeating);
	
			
			
			//Main Left
			//ROW 6 Seats 13-17
			
			cid++;
			newSeating = new Seating(row);
			newSeating.addSeat(13, Integer.toString(cid));
			
			cid++; 
			newSeating.addSeat(14, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(15, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(16, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(17, Integer.toString(cid));
			addSeatingToSection("128", newSeating);
			
			
			//########################ROW 7########################################
			//Main Right
			//ROW 7 Seats 1-5
			row="7";//row 7
			cid++;
			newSeating = new Seating(row);
			newSeating.addSeat(1, Integer.toString(cid));
			
			cid++; 
			newSeating.addSeat(2, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(3, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(4, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(5, Integer.toString(cid));
			addSeatingToSection("126", newSeating);
			
			
			//Main Center
			//ROW 7 Seats 6-13			
			cid++;
			newSeating = new Seating(row);
			newSeating.addSeat(6, Integer.toString(cid));
			
			cid++; 
			newSeating.addSeat(7, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(8, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(9, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(10, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(11, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(12, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(13, Integer.toString(cid));
			addSeatingToSection("127", newSeating);
	
			
			
			//Main Left
			//ROW 7 Seats 14-18
			
			cid++;
			newSeating = new Seating(row);
			newSeating.addSeat(14, Integer.toString(cid));
			
			cid++; 
			newSeating.addSeat(15, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(16, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(17, Integer.toString(cid));
			
			cid++;
			newSeating.addSeat(18, Integer.toString(cid));
			addSeatingToSection("127", newSeating);
			}

		

		
		}
		
		



