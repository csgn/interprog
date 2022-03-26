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
}
