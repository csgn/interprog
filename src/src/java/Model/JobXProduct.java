/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Aykut
 */
public class JobXProduct {
	
	private int jobId;
	private int productId;

	public JobXProduct() {
	}

	public JobXProduct(int jobId, int productId) {
		this.jobId = jobId;
		this.productId = productId;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 41 * hash + this.jobId;
		hash = 41 * hash + this.productId;
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
		final JobXProduct other = (JobXProduct) obj;
		if (this.jobId != other.jobId) {
			return false;
		}
		return this.productId == other.productId;
	}
	
	
}
