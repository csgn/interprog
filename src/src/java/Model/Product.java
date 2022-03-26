/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Aykut
 */
public class Product {
	
	private int id;
	private String name;
	private String serialNumber;
	private String unit;
	private int purchasePrice;
	private int salePrice;
	private int vat;
	private String tags;
	private int quantitiy;
	private String files;
	private int warehouseId;

	public Product() {
	}

	public Product(int id, String name, String serialNumber, String unit, int purchasePrice, int salePrice, int vat, String tags, int quantitiy, String files, int warehouseId) {
		this.id = id;
		this.name = name;
		this.serialNumber = serialNumber;
		this.unit = unit;
		this.purchasePrice = purchasePrice;
		this.salePrice = salePrice;
		this.vat = vat;
		this.tags = tags;
		this.quantitiy = quantitiy;
		this.files = files;
		this.warehouseId = warehouseId;
	}
	
	
	
	public int getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(int purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public int getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}

	

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public int getVat() {
		return vat;
	}

	public void setVat(int vat) {
		this.vat = vat;
	}

	public String getTags() {
		return tags;
	}

	public void setTags(String tags) {
		this.tags = tags;
	}

	public int getQuantitiy() {
		return quantitiy;
	}

	public void setQuantitiy(int quantitiy) {
		this.quantitiy = quantitiy;
	}

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	public int getWarehouseId() {
		return warehouseId;
	}

	public void setWarehouseId(int warehouseId) {
		this.warehouseId = warehouseId;
	}
	
	
	
}
