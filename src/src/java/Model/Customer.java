package Model;

/**
 *
 * @author Aykut
 */
public class Customer {

	private int id;
	private String phone;
	private String email;
	private int addressId;
	private int accountTypeId;

	public Customer() {
	}

	public Customer(int id, String phone, String email, int addressId, int accountTypeId) {
		this.id = id;
		this.phone = phone;
		this.email = email;
		this.addressId = addressId;
		this.accountTypeId = accountTypeId;
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

	public int getAccountTypeId() {
		return accountTypeId;
	}

	public void setAccountTypeId(int accountTypeId) {
		this.accountTypeId = accountTypeId;
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
		return "Customer{" + "id=" + id + '}';
	}
	
	
}
