package Converter;

import DAO.StatusDAO;
import Model.Status;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

/**
 *
 * @author csgn
 */
@FacesConverter(value="statusConverter", forClass=Status.class)
public class StatusConverter implements Converter {

	private StatusDAO dao = new StatusDAO();

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
		return dao.find(Integer.valueOf(string));
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object t) {
		Status o = (Status) t;
		return String.valueOf(o.getId());
	}
	
}
