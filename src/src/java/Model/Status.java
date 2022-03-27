package Model;

/**
 *
 * @author Metin
 */
public class Status {

	private int id;
	private String name;
	private String color;

	public Status() {
	}

	public Status(int id, String name, String color) {
		this.id = id;
		this.name = name;
		this.color = color;
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

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 23 * hash + this.id;
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
		final Status other = (Status) obj;
		return this.id == other.id;
	}

	@Override
	public String toString() {
		return String.valueOf(id); 
	}
}
