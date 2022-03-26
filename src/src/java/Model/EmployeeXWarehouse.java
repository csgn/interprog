package Model;

/**
 *
 * @author Aykut
 */
public class EmployeeXWarehouse {

	private int employeeId;
	private int warehouseId;

	public EmployeeXWarehouse() {
	}

	public EmployeeXWarehouse(int employeeId, int warehouseId) {
		this.employeeId = employeeId;
		this.warehouseId = warehouseId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(int warehouseId) {
		this.warehouseId = warehouseId;
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
		final EmployeeXWarehouse other = (EmployeeXWarehouse) obj;
		return this.employeeId == other.employeeId;
	}

	@Override
	public String toString() {
		return "EmployeeXWarehouse{" + "employeeId=" + employeeId + '}';
	}
	
}
