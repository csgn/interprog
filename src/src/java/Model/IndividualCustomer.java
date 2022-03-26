package Model;

import java.util.Objects;

/**
 *
 * @author Aykut
 */
public class IndividualCustomer extends Customer {

	private String name;
	private String surname;
	private String companyTitle;
	

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

	public String getCompanyTitle() {
		return companyTitle;
	}

	public void setCompanyTitle(String companyTitle) {
		this.companyTitle = companyTitle;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 37 * hash + Objects.hashCode(this.name);
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
		final IndividualCustomer other = (IndividualCustomer) obj;
		return Objects.equals(this.name, other.name);
	}

	@Override
	public String toString() {
		return "IndividualCustomer{" + "name=" + name + '}';
	}
	

}
