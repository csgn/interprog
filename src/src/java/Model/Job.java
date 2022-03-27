package Model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author Aykut
 */
public class Job {

	private int id;
	private Date creationDate;
	private String description;
	private Date date;
	private Status status;
	private Employee owner;
	private Customer customer;

	public Job() {
		status = new Status();
		owner = new Employee();
		customer = new Customer();
	}

	public Job(int id, Date creationDate, String description, Date date, Status status, Employee owner, Customer customer) {
		this.id = id;
		this.creationDate = creationDate;
		this.description = description;
		this.date = date;
		this.status = status;
		this.owner = owner;
		this.customer = customer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Employee getOwner() {
		return owner;
	}

	public void setOwner(Employee ownerId) {
		this.owner = owner;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 59 * hash + this.id;
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
		final Job other = (Job) obj;
		return this.id == other.id;
	}

	@Override
	public String toString() {
		return String.valueOf(id);
	}

}
