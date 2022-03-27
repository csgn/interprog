package Model;

/**
 *
 * @author Aykut
 */
public class Customer {

	private int id;
	private String name;
	private String surname;
	private int customerTypeId;
	private String phone;
	private String email;
	private String companyTitle;
	private String taxNumber;
	private String taxAdministration;
	private int addressId;

	public Customer() {
	}

	public Customer(int id, String name, String surname, int customerTypeId, String phone, String email, String companyTitle, String taxNumber, String taxAdministration, int addressId) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.customerTypeId = customerTypeId;
		this.phone = phone;
		this.email = email;
		this.companyTitle = companyTitle;
		this.taxNumber = taxNumber;
		this.taxAdministration = taxAdministration;
		this.addressId = addressId;
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

	public int getAddressId() {
		return addressId;
	}

	public void setAddressId(int addressId) {
		this.addressId = addressId;
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

	public int getCustomerTypeId() {
		return customerTypeId;
	}

	public void setCustomerTypeId(int customerTypeId) {
		this.customerTypeId = customerTypeId;
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
		return name + " " + surname;
	}
	
	
}
