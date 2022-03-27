package Model;

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
	private Role role;

	public Employee() {
	}
	
	public Employee(int id, String name, String surname, String phone, String color, String password, Role role) {
		this.id = id;
		this.name = name;
		this.surname = surname;
		this.phone = phone;
		this.color = color;
		this.password = password;
		this.role = role;
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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 53 * hash + this.id;
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
		final Employee other = (Employee) obj;
		return this.id == other.id;
	}

	@Override
	public String toString() {
		return String.valueOf(id);
	}
}
