package thaliatix;

import java.util.List;

public interface BoundaryInterfaceShow {
	List<ShowDetail> getAllShows();
    Show createShow(String name, String web, String date, String time/*, int sid, double price*/);
    SpecificShowDetail getShowDetail(String lid);
    void updateShow(String id, Show s);
    List<SectionInfo> getShowSections(String wid);
    public ShowSectionDetailedInfo getShowSectionDetail(String wid, String sid);
    public SeatRequest GetRequestedSeats(String wid, String sid, int count, String starting_seat_id);
    public boolean CheckSeatsAvailability(String showID, String sectionID, List<Seat> seatsList);
    public Show findById(String lid);
    public void SetTicketStatusToSold(String wid, String sid, List<Seat> seats);
    public int GetSectionPriceForShow(String wid, String sid);
    public Section getSectionInfoForSeat(String wid, String sid, String cid);
}
