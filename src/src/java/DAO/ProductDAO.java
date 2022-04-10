package DAO;

import Model.Product;
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
 * @author Sergen
 */
public class ProductDAO implements IDAO<Product> {
	
	private Product tmp;
	private List<Product> products;
	private WarehouseDAO warehouseDAO;
	
	@Override
	public List<Product> findAll() {
		products = new ArrayList<>();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement("Select * from product");
			rs = ps.executeQuery();
			while (rs.next()) {
				tmp = new Product(
								rs.getInt("id"),
								rs.getString("name"),
								rs.getString("serialNumber"),
								rs.getString("unit"),
								rs.getInt("purchasePrice"),
								rs.getInt("salePrice"),
								rs.getInt("vat"),
								rs.getInt("quantity"),
								this.getWarehouseDAO().find(rs.getInt("warehouseId")));
				products.add(tmp);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		return products;
	}

	@Override
	public Product find(int id) {
		Product product = new Product();
		PreparedStatement ps;
		ResultSet rs;
		
		try {
			ps = conn.prepareStatement("SELECT * FROM product WHERE id=?");
			ps.setInt(1, id);

			rs = ps.executeQuery();

			while (rs.next()) {
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setSerialNumber(rs.getString("serialNumber"));
				product.setUnit(rs.getString("unit"));
				product.setPurchasePrice(rs.getInt("purchasePrice"));
				product.setSalePrice(rs.getInt("salePrice"));
				product.setVat(rs.getInt("vat"));
				product.setQuantity(rs.getInt("quantity"));
				product.setWarehouse(this.getWarehouseDAO().find(rs.getInt("warehouseId")));
				
			}
		} catch (SQLException ex) {
			Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
		}

		return product;
	}

	@Override
	public int create(Product p) {
		PreparedStatement ps;
		ResultSet rs;
		
		int id = -1;
		try {
			ps = conn.prepareStatement("INSERT INTO product (name, serialnumber, unit, purchaseprice, saleprice, vat, quantity, warehouseid) VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING id");
			ps.setString(1, p.getName());
			ps.setString(2, p.getSerialNumber());
			ps.setString(3, p.getUnit());
			ps.setInt(4, p.getPurchasePrice());
			ps.setInt(5, p.getSalePrice());
			ps.setInt(6, p.getVat());
			ps.setInt(7, p.getQuantity());
			ps.setInt(8, p.getWarehouse().getId());
			rs = ps.executeQuery();

			while (rs.next()) {
				id = rs.getInt("id");
			}
		} catch (SQLException ex) {
			Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
		return id;
	}

	@Override
	public void update(Product p) {
		PreparedStatement ps;

		try {
			ps = conn.prepareStatement("UPDATE product SET name=?,serialnumber=?, unit=?, purchaseprice=?, saleprice=?,vat=?,quantity=?,warehouseid=? where id=?");
			ps.setString(1, p.getName());
			ps.setString(2, p.getSerialNumber());
			ps.setString(3, p.getUnit());
			ps.setInt(4, p.getPurchasePrice());
			ps.setInt(5, p.getSalePrice());
			ps.setInt(6, p.getVat());
			ps.setInt(7, p.getQuantity());
			ps.setInt(8, p.getWarehouse().getId());
			ps.setInt(9, p.getId());
			ps.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	@Override
	public void delete(int id) {
		PreparedStatement ps;
		
		try {
			ps = conn.prepareStatement("DELETE FROM product where id=?");
			ps.setInt(1, id);

			ps.executeUpdate();
		} catch (SQLException ex) {
			Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public WarehouseDAO getWarehouseDAO() {
		if (warehouseDAO == null) {
			this.warehouseDAO = new WarehouseDAO();
		}

		return warehouseDAO;
	}
}
