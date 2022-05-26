package Validators;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;

/**
 *
 * @author csgn
 */

@FacesValidator("passwordValidator")
public class PasswordValidation implements Validator {

	@Override
	public void validate(FacesContext fc, UIComponent uic, Object t) throws ValidatorException {
		System.out.println("######################################################PASSWORDVALIDATE");

		String v = (String) t;


		if (v.isEmpty()) {
			throw new ValidatorException(new FacesMessage("Password is required!"));
		} else if (v.length() < 5) {
			throw new ValidatorException(new FacesMessage("Password cannot be less than 5 characters!"));
		}

		System.out.println("VALUE IS: " + v);
	}
}
