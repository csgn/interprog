package Controller;

import DAO.DocumentDAO;
import DAO.ProductDAO;
import Model.Product;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 *
 * @author Aykut
 */
@Named(value = "productBean")
@SessionScoped
public class ProductBean implements Serializable {

	private Product model;
	private final ProductDAO dao;
	private final DocumentDAO docDao;

	private int page = 1;
	private int pageSize = 3;
	private int pageCount;

	private Part doc;
	// uploads klasorunun absolute pathini yapistirin
	// C://Users/user/Desktop/interprog/uploads/ gibi
	private final String uploadTo = "/home/csgn/Desktop/interprog/uploads/";

	public ProductBean() {
		Path root = Paths.get(".").normalize().toAbsolutePath();
		System.out.println("ROOT: " + root);

		model = new Product();
		dao = new ProductDAO();
		docDao = new DocumentDAO();

	}

	public Product find(int id) {
		return dao.find(id);
	}

	public List<Product> findAll() {
		return dao.findAll();
	}

	public List<Product> findAllLimit() {
		return dao.findAll(page, pageSize);
	}



	public void create() {
		try {
			InputStream is = doc.getInputStream();
			File f = new File(uploadTo + doc.getSubmittedFileName());
			Files.copy(is, f.toPath());
			model.getDocument().setFilePath(f.getPath());
			model.getDocument().setFileName(f.getName());
			model.getDocument().setFileType(doc.getContentType());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

		int docid = this.docDao.create(model.getDocument());
		if (docid != -1) {
			model.getDocument().setId(docid);
			int id = dao.create(model);
		}

		this.clearModel();
	}

	public void delete(int id) {
		dao.delete(id);
		this.clearModel();
	}

	public void update() {
		dao.update(model);
		this.clearModel();
	}

	public Product getModel() {
		return model;
	}

	public ProductDAO getDao() {
		return dao;
	}

	public void clearModel() {
		model = new Product();
	}

	public void editForm(Product p) {
		model = p;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageCount() {
		this.pageCount = (int) Math.ceil(this.dao.count() / (double) pageSize);
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public void next() {
		if (page == pageCount) {
			page = 1;
		} else {
			page++;
		}
	}

	public void previous() {
		if (page == 1) {
			page = pageCount;
		} else {
			page--;
		}
	}

	public Part getDoc() {
		return doc;
	}

	public void setDoc(Part doc) {
		this.doc = doc;
	}

	public String getUploadTo() {
		return uploadTo;
	}


}
