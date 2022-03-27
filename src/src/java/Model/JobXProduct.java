/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Objects;

/**
 *
 * @author Aykut
 */
public class JobXProduct {
	
	private Job job;
	private Product product;

	public JobXProduct() {
	}

	public JobXProduct(Job job, Product product) {
		this.job = job;
		this.product = product;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 71 * hash + Objects.hashCode(this.job);
		hash = 71 * hash + Objects.hashCode(this.product);
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
		if (!Objects.equals(this.job, other.job)) {
			return false;
		}
		return Objects.equals(this.product, other.product);
	}

	@Override
	public String toString() {
		return String.valueOf(job.getId() +" "+product.getId());
	}


	
	
}
