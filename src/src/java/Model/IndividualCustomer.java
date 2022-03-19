/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Aykut
 */
public class IndividualCustomer {

	private int id;
	private String phone;
	private String email;
	private int addressId;
	private int accounttypeId;
	private String companyTitle;
	private String name;
	private String surname;

	public IndividualCustomer() {
	}

	public IndividualCustomer(int id, String phone, String email, int addressId, int accounttypeId, String companyTitle, String name, String surname) {
		this.id = id;
		this.phone = phone;
		this.email = email;
		this.addressId = addressId;
		this.accounttypeId = accounttypeId;
		this.companyTitle = companyTitle;
		this.name = name;
		this.surname = surname;
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

	public int getAccounttypeId() {
		return accounttypeId;
	}

	public void setAccounttypeId(int accounttypeId) {
		this.accounttypeId = accounttypeId;
	}

	public String getCompanyTitle() {
		return companyTitle;
	}

	public void setCompanyTitle(String companyTitle) {
		this.companyTitle = companyTitle;
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
