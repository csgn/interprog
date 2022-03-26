package Controller;

import DAO.AccountTypeDAO;
import Model.AccountType;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Aykut
 */
@Named(value= "accountTypeBean")
@SessionScoped
public class AccountTypeBean implements Serializable {
	
	private AccountTypeDAO dao;
	private AccountType model;

	public AccountTypeBean() {
		model = new AccountType();
		dao = new AccountTypeDAO();
	}
	
	public AccountType find(int id){
		return dao.find(id);
	}
	
	public List<AccountType> findAll(){
		return dao.findAll();
	}
	
	public void create() {
		int id = dao.create(model);
		this.clearModel();

		System.out.println("CREATED ID: " + id);
	}
	
	public void delete(int id) {
		dao.delete(id);
		this.clearModel();
	}

	public void update() {
		dao.update(model);
		this.clearModel();
	}
	
	public AccountTypeDAO getDao() {
		if (this.dao == null){
			this.dao = new AccountTypeDAO();
		}
		return dao;
	}

	public AccountType getModel() {
		if (this.model == null){
			this.model = new AccountType();
		}
		return model;
	}

	public void clearModel() {
		model = new AccountType();
	}

	public void editForm(AccountType a) {
		model = a;
	}
}
