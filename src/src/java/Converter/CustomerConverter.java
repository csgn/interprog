package Converter;

import DAO.CustomerDAO;
import Model.Customer;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

/**
 *
 * @author csgn
 */
@FacesConverter(value="customerConverter", forClass=Customer.class)
public class CustomerConverter implements Converter {

	private CustomerDAO dao = new CustomerDAO();

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
		return dao.find(Integer.valueOf(string));
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object t) {
		Customer o = (Customer) t;
		return String.valueOf(o.getId());
	}
	
}
