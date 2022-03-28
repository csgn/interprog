package Converter;

import DAO.SquadDAO;
import Model.Squad;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

/**
 *
 * @author csgn
 */
@FacesConverter(value="squadConverter", forClass=Squad.class)
public class SquadConverter implements Converter {

	private SquadDAO dao = new SquadDAO();

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
		return dao.find(Integer.valueOf(string));
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object t) {
		Squad o = (Squad) t;
		return String.valueOf(o.getId());
	}
	
}
