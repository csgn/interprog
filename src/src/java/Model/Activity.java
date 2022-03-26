/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author Aykut
 */
public class Activity {
	
	private int id;
	private Date date;
	private String msg;
	private String info;
	Activity type;
	private int ownerId;
	private int jobId;

	public Activity() {
	}

	public Activity(int id, Date date, String msg, String info, Activity type, int ownerId, int jobId) {
		this.id = id;
		this.date = date;
		this.msg = msg;
		this.info = info;
		this.type = type;
		this.ownerId = ownerId;
		this.jobId = jobId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Activity getType() {
		return type;
	}

	public void setType(Activity type) {
		this.type = type;
	}

	public int getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(int ownerId) {
		this.ownerId = ownerId;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
}
