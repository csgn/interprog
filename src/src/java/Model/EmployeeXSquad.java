/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
}
