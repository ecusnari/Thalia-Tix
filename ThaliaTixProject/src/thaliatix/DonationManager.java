package thaliatix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DonationManager implements BoundaryInterfaceDonation{
	
	private static List<Donation> subscriptions = new ArrayList<Donation>();
	
	public Donation createSubscription(Donation donation) {
		donation.setDid();
		this.subscriptions.add(donation);
		return donation;
	}

	
	public Donation getSubscriptionById(String showID, String donationID) {
		for(Donation subscription:subscriptions) {
			if(subscription.getWid().equals(showID) && subscription.getDid().equals(donationID)) {
				return subscription;
			}
		}
		
		return null;
	}
	
	public void assignNewDonations(String tid) {
		Collections.sort(subscriptions, Comparator.comparing(Donation::getDid));
		for(Donation subscription:subscriptions) {
			if(subscription.getCount() > subscription.getTickets().size()) {
				subscription.addTicket(tid);
				subscription.setStatus("assigned");
				break;
			}
		}
	}
}
