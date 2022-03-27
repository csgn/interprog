package Converter;

import DAO.WarehouseDAO;
import Model.Warehouse;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

/**
 *
 * @author csgn
 */
@FacesConverter(value="warehouseConverter", forClass=Warehouse.class)
public class WarehouseConverter implements Converter {

	private WarehouseDAO dao = new WarehouseDAO();

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
		return dao.find(Integer.valueOf(string));
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object t) {
		Warehouse o = (Warehouse) t;
		return String.valueOf(o.getId());
	}
	
}
