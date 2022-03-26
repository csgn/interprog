package Model;

/**
 *
 * @author Aykut
 */
public class JobXEmployee {

	private int jobId;
	private int employeeId;

	public JobXEmployee() {
	}

	public JobXEmployee(int jobId, int employeeId) {
		this.jobId = jobId;
		this.employeeId = employeeId;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 37 * hash + this.jobId;
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
		final JobXEmployee other = (JobXEmployee) obj;
		return this.jobId == other.jobId;
	}

	@Override
	public String toString() {
		return "JobXEmployee{" + "jobId=" + jobId + '}';
	}
	
}
