package Validators;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

/**
 *
 * @author csgn
 */
@FacesValidator("nameValidator")
public class NameValidation implements Validator {

	@Override
	public void validate(FacesContext fc, UIComponent uic, Object t) throws ValidatorException {
	}
	
}
