package Converter;

import DAO.ProductDAO;
import Model.Product;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.FacesConverter;

/**
 *
 * @author csgn
 */
@FacesConverter(value="productConverter", forClass=Product.class)
public class ProductConverter implements Converter {

	private ProductDAO dao = new ProductDAO();

	@Override
	public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
		return dao.find(Integer.valueOf(string));
	}

	@Override
	public String getAsString(FacesContext fc, UIComponent uic, Object t) {
		Product o = (Product) t;
		return String.valueOf(o.getId());
	}
	
}
