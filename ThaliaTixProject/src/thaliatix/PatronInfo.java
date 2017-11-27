package thaliatix;

public class PatronInfo {
	private String name;
	private String phone;
	private String email;
	private String billing_address;
	private String cc_number;
	private String cc_expiration_date;
	
	
	
	public PatronInfo(String name, String phone, String email, String billing_address, String cc_number, String cc_expiration_date) {
		
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.billing_address = billing_address;
		this.cc_number = cc_number;
		this.cc_expiration_date = cc_expiration_date;
	}

	public void setCc_number(String cc_number) {
		this.cc_number = cc_number;
	}

	public String getCc_number() {
		return cc_number;
	}
	
	
}
