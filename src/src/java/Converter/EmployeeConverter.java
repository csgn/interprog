package Converter;

import DAO.EmployeeDAO;
import Model.Employee;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

/**
 *
 * @author csgn
 */
@FacesConverter(value="employeeConverter", forClass=Employee.class)
public class EmployeeConverter implements Converter {

	private EmployeeDAO dao = new EmployeeDAO();

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
		return dao.find(Integer.valueOf(string));
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object t) {
		Employee o = (Employee) t;
		return String.valueOf(o.getId());
	}
	
}
