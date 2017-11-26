package thaliatix;

public class PatronInfo {
	private String name;
	private String phone;
	private String email;
	private String billing_address;
	private String cc_number;
	private String cc_expiration_date;
	
	public void setCc_number(String cc_number) {
		this.cc_number = cc_number;
	}

	public String getCc_number() {
		return cc_number;
	}
	
	
}
