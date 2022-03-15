/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Aykut
 */
public class CorporateCustomer {
    private int id;
		private String phone;
		private String email;
		private int addressId;
		private int  accountTypeId;
		private String companyTitle;
		private String authorizedPerson;
		private String taxNumber;
		private String taxAdministration;
		
		public CorporateCustomer(){
			
		}

	public CorporateCustomer(int id, String phone, String email, int addressId, int accountTypeId, String companyTitle, String authorizedPerson, String taxNumber, String taxAdministration) {
		this.id = id;
		this.phone = phone;
		this.email = email;
		this.addressId = addressId;
		this.accountTypeId = accountTypeId;
		this.companyTitle = companyTitle;
		this.authorizedPerson = authorizedPerson;
		this.taxNumber = taxNumber;
		this.taxAdministration = taxAdministration;
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
		
	
}
