package thaliatix;

public interface BoundaryInterfaceDonation {

	public Donation createSubscription(Donation donation);
	public Donation getSubscriptionById(String showID, String donationID);
	public void assignNewDonations(String tid);
}
