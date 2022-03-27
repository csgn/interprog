package Model;

import java.util.Objects;

/**
 *
 * @author Aykut
 */
public class CorporateCustomer extends Customer {

	private String companyTitle;
	private String authorizedPerson;
	private String taxNumber;
	private String taxAdministration;

	public CorporateCustomer() {
		super();
	}

	public CorporateCustomer(int id, String phone, String email, int addressId, int accountTypeId, String companyTitle, String authorizedPerson, String taxNumber, String taxAdministration) {
		super();
		this.companyTitle = companyTitle;
		this.authorizedPerson = authorizedPerson;
		this.taxNumber = taxNumber;
		this.taxAdministration = taxAdministration;
	}

	public String getCompanyTitle() {
		return companyTitle;
	}

	public void setCompanyTitle(String companyTitle) {
		this.companyTitle = companyTitle;
	}

	public String getAuthorizedPerson() {
		return authorizedPerson;
	}

	public void setAuthorizedPerson(String authorizedPerson) {
		this.authorizedPerson = authorizedPerson;
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
		hash = 53 * hash + Objects.hashCode(this.companyTitle);
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
		final CorporateCustomer other = (CorporateCustomer) obj;
		return Objects.equals(this.companyTitle, other.companyTitle);
	}

	@Override
	public String toString() {
		return "CorporateCustomer{" + "companyTitle=" + companyTitle + '}';
	}
	
}
