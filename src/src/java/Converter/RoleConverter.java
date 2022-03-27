package Converter;

import DAO.RoleDAO;
import Model.Role;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

/**
 *
 * @author csgn
 */
@FacesConverter(value="roleConverter", forClass=Role.class)
public class RoleConverter implements Converter {

	private RoleDAO dao = new RoleDAO();

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
		return dao.find(Integer.valueOf(string));
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object t) {
		Role o = (Role) t;
		return String.valueOf(o.getId());
	}
	
}
