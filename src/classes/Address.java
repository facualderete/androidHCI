package classes;

public class Address {


	private String zip_code;
	private String phonenumber;
	private String address1;
	private String address2;
	private String name;
	private String city;
	
	public Address() {
	}

	public Address(String full_name, String address1, String address2,
			String city, String zip, String phone) {
		this.name = full_name;

		this.city = city;
		this.phonenumber = phone;
		this.zip_code = zip;
		this.address1 = address1;
		this.address2 = address2;
	}

	public String getFull_name() {
		return name;
	}

	public String getAddress_line_1() {
		return address1;
	}

	public String getAddress_line_2() {
		return address2;
	}
	
	public String getZip_code() {
		return zip_code;
	}

	public String getPhone_number() {
		return phonenumber;
	}

	public String getCity() {
		return city;
	}



}