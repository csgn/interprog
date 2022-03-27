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
	private List<String> files;
	private int statusId;
	private int ownerId;
	private int customerId;

	public Job() {
	}

	public Job(int id, Date creationDate, String description, Date date, List<String> files, int statusId, int ownerId, int customerId) {
		this.id = id;
		this.creationDate = creationDate;
		this.description = description;
		this.date = date;
		this.files = files;
		this.statusId = statusId;
		this.ownerId = ownerId;
		this.customerId = customerId;
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

	public List<String> getFiles() {
		return files;
	}

	public void setFiles(List<String> files) {
		this.files = files;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
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
		return "Job{" + "id=" + id + '}';
	}
	
}
