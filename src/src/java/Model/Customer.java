package Model;

/**
 *
 * @author Aykut
 * @author Sergen
 */
public class Customer {

	private int id;
	private String name;
	private String surname;
	private CustomerType customerType;
	private String phone;
	private String email;
	private String companyTitle;
	private String taxNumber;
	private String taxAdministration;
	private Address address;

	public Customer() {
		address = new Address();
	}

	public Customer(int id, String name, String surname, CustomerType customerTypeId, String phone, String email, String companyTitle, String taxNumber, String taxAdministration, Address address) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.customerType = customerType;
		this.phone = phone;
		this.email = email;
		this.companyTitle = companyTitle;
		this.taxNumber = taxNumber;
		this.taxAdministration = taxAdministration;
		this.address = address;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
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

	public CustomerType getCustomerType() {
		return customerType;
	}

	public void setCustomerType(CustomerType customerType) {
		this.customerType = customerType;
	}

	public String getCompanyTitle() {
		return companyTitle;
	}

	public void setCompanyTitle(String companyTitle) {
		this.companyTitle = companyTitle;
	}

	public String getTaxNumber() {
		return taxNumber;
	}

	public void setTaxNumber(String taxNumber) {
		this.taxNumber = taxNumber;
	}

	public String getTaxAdministration() {
		return taxAdministration;
	}

	public void setTaxAdministration(String taxAdministration) {
		this.taxAdministration = taxAdministration;
	}


	@Override
	public int hashCode() {
		int hash = 7;
		hash = 97 * hash + this.id;
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Customer other = (Customer) obj;
		return this.id == other.id;
	}

	@Override
	public String toString() {
		return String.valueOf(id); 
	}

	
}
