package Converter;

import DAO.AddressDAO;
import Model.Address;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

/**
 *
 * @author csgn
 */
@FacesConverter(value="addressConverter", forClass=Address.class)
public class AddressConverter implements Converter {

	private AddressDAO dao = new AddressDAO();

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
		return dao.find(Integer.valueOf(string));
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object t) {
		Address o = (Address) t;
		return String.valueOf(o.getId());
	}
	
}
