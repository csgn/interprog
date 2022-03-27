package Model;

import java.util.Objects;

/**
 *
 * @author Aykut
 */
public class JobXEmployee {

	private Job job;
	private Employee employee;

	public JobXEmployee() {
		job = new Job();
		employee = new Employee();
	}

	public JobXEmployee(Job job, Employee employee) {
		this.job = job;
		this.employee = employee;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 89 * hash + Objects.hashCode(this.job);
		hash = 89 * hash + Objects.hashCode(this.employee);
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
		if (!Objects.equals(this.job, other.job)) {
			return false;
		}
		return Objects.equals(this.employee, other.employee);
	}

	@Override
	public String toString() {
		return String.valueOf(job.getId() + " " + employee.getId());
	}
	
}
