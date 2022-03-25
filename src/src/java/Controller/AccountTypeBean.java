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
	private AccountType entity;
	private List<AccountType> accountTypeList;

	public AccountTypeBean() {
	}

	public AccountTypeDAO getDao() {
		if (this.dao == null){
			this.dao = new AccountTypeDAO();
		}
		return dao;
	}

	public void setDao(AccountTypeDAO dao) {
		this.dao = dao;
	}

	public AccountType getEntity() {
		if (this.entity == null){
			this.entity = new AccountType();
		}
		return entity;
	}

	public void setEntity(AccountType entity) {
		this.entity = entity;
	}

	public List<AccountType> getAccountTypeList() {
		return accountTypeList;
	}

	public void setAccountTypeList(List<AccountType> accountTypeList) {
		this.accountTypeList = accountTypeList;
	}
	
}
