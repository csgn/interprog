package Model;

/**
 *
 * @author Aykut
 */
public class IndividualCustomer extends Customer {

	private String name;
	private String surname;

	public IndividualCustomer() {
		super();
	}

	public IndividualCustomer(int id, String phone, String email, int addressId, int accounttypeId, String companyTitle, String name, String surname) {
		super();
		this.name = name;
		this.surname = surname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}
}
