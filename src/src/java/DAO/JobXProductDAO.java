package DAO;

import Model.JobXProduct;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Aykut
 */
public class JobXProductDAO implements IDAO<JobXProduct> {

	private PreparedStatement ps;
	private ResultSet rs;
	private JobXProduct tmp;
	private List<JobXProduct> jobXProducts;

	private JobDAO jobDAO;
	private ProductDAO productDAO;

	@Override
	public JobXProduct find(int jobId) {
		JobXProduct jobXProduct = new JobXProduct();
		try {
			this.ps = conn.prepareStatement("SELECT * FROM jobXProduct WHERE jobid=?");
			this.ps.setInt(1, jobId);
			rs = ps.executeQuery();

			while (rs.next()) {
				jobXProduct.setJob(getJobDAO().find(rs.getInt("id")));

			}
		} catch (SQLException e) {
			Logger.getLogger(JobXProduct.class.getName()).log(Level.SEVERE, null, e);
		}
		return jobXProduct;
	}

	@Override
	public List<JobXProduct> findAll() {
		try {
			jobXProducts = new ArrayList<>();
			this.ps = this.conn.prepareStatement("Select * from jobxproduct");
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new JobXProduct(
								getJobDAO().find(rs.getInt("jobId")),
								getProductDAO().find(rs.getInt("productId")));
				jobXProducts.add(tmp);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return jobXProducts;
	}

	@Override
	public int create(JobXProduct j) {
		int jobId = -1;
		try {
			ps = conn.prepareStatement("INSERT INTO jobxproduct (jobid, productid) VALUES (?, ?) RETURNING jobid");
			ps.setInt(1, j.getJob().getId());
			ps.setInt(2, j.getProduct().getId());
			rs = ps.executeQuery();
			while (rs.next()) {
				jobId = rs.getInt("jobId");
			}
		} catch (SQLException e) {
			Logger.getLogger(JobXProduct.class.getName()).log(Level.SEVERE, null, e);
		}
		return jobId;
	}

	@Override
	public void update(JobXProduct j) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}

	@Override
	public void delete(int jobId) {
		try {
			ps = conn.prepareStatement("DELETE FROM jobxproduct where jobId=?");
			ps.setInt(1, jobId);

			ps.executeUpdate();
		} catch (SQLException e) {
			Logger.getLogger(JobXProduct.class.getName()).log(Level.SEVERE, null, e);
		}
	}

	public JobDAO getJobDAO() {
		if (jobDAO == null)
			jobDAO = new JobDAO();

		return jobDAO;
	}

	public ProductDAO getProductDAO() {
		if (productDAO == null)
			productDAO = new ProductDAO();

		return productDAO;
	}
}
