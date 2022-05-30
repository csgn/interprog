/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author Aykut
 * @author Sergen
 */
public class Product {
	
	private int id;
	private String name;
	private String serialNumber;
	private String unit;
	private int purchasePrice;
	private int salePrice;
	private int vat;
	private int quantity;
	private Warehouse warehouse;
	private Document document;

	public Product() {
		warehouse = new Warehouse();
		document = new Document();
	}

	public Product(int id, String name, String serialNumber, String unit, int purchasePrice, int salePrice, int vat, int quantity, Warehouse warehouse, Document document) {
		this.id = id;
		this.name = name;
		this.serialNumber = serialNumber;
		this.unit = unit;
		this.purchasePrice = purchasePrice;
		this.salePrice = salePrice;
		this.vat = vat;
		this.quantity = quantity;
		this.warehouse = warehouse;
		this.document = document;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Warehouse getWarehouse() {
		return warehouse;
	}

	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	public Document getDocument() {
		return document;
	}

	public void setDocument(Document document) {
		this.document = document;
	}


	@Override
	public int hashCode() {
		int hash = 3;
		hash = 37 * hash + this.id;
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
		final Product other = (Product) obj;
		return this.id == other.id;
	}

	@Override
	public String toString() {
		return String.valueOf(id);
	}
}
