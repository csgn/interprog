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
}
