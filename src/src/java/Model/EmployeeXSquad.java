package Model;

/**
 *
 * @author Aykut
 */
public class EmployeeXSquad {

	private int employeeId;
	private int squadId;

	public EmployeeXSquad() {
	}

	public EmployeeXSquad(int employeeId, int squadId) {
		this.employeeId = employeeId;
		this.squadId = squadId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getSquadId() {
		return squadId;
	}

	public void setSquadId(int squadId) {
		this.squadId = squadId;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 59 * hash + this.employeeId;
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
		final EmployeeXSquad other = (EmployeeXSquad) obj;
		return this.employeeId == other.employeeId;
	}

	@Override
	public String toString() {
		return "EmployeeXSquad{" + "employeeId=" + employeeId + '}';
	}
	
	
}
