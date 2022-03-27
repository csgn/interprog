package Converter;

import DAO.CustomerTypeDAO;
import Model.CustomerType;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

/**
 *
 * @author csgn
 */
@FacesConverter(value="customerTypeConverter", forClass=CustomerType.class)
public class CustomerTypeConverter implements Converter {

	private CustomerTypeDAO dao = new CustomerTypeDAO();

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
		return dao.find(Integer.valueOf(string));
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object t) {
		CustomerType o = (CustomerType) t;
		return String.valueOf(o.getId());
	}
	
}
