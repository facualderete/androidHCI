package classes;

public class Order {
	int id;
	String creationDate;
	String confirmationDate;
	Address address;

	public Order(int id, String creation, String confirmation) {
		this.id = id;
		this.creationDate = creation;
		this.confirmationDate = confirmation;

	}
	public int getId() {
		return id;
	}
	public void addAddress(Address address) {
		this.address = address;
	}

	
	public Address getAddress() {
		return address;
	}

	public String getCreated_date() {
		return creationDate;
	}

	public String getConfirmed_date() {
		return confirmationDate;
	}
}
