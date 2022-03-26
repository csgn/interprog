package Model;

/**
 *
 * @author Aykut
 */
public class Address {
	
	private int id;
	private String title;
	private String context;
	private String region;
	private String district;
	private String directions;
	
	public Address(){
	}
	
	public Address(int id, String title, String context, String region, String district, String directions) {
		this.id = id;
		this.title = title;
		this.context = context;
		this.region = region;
		this.district = district;
		this.directions = directions;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getDirections() {
		return directions;
	}

	public void setDirections(String directions) {
		this.directions = directions;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 41 * hash + this.id;
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
		final Address other = (Address) obj;
		return this.id == other.id;
	}

	@Override
	public String toString() {
		return id + title + context + region + district + directions;
	}
}
