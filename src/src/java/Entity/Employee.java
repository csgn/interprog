package Entity;

/**
 *
 * @author Metin
 */

public class Employee {
		private int id;
		private String name;
		private String surname;
		private String phone;
		private String color;
		private String password;
		private int roleId;

	public Employee(int id, String name, String surname, String phone, String color, String password, int roleId) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.phone = phone;
		this.color = color;
		this.password = password;
		this.roleId = roleId;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}
		
		
}
