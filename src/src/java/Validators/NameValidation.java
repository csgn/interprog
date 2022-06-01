package Validators;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.validator.FacesValidator;
import jakarta.faces.validator.Validator;
import jakarta.faces.validator.ValidatorException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author csgn
 */
@FacesValidator("nameValidator")
public class NameValidation implements Validator {
	String namePattern = "[A-Z][a-z]*";
	private Pattern patternObject;
	private Matcher matcherObject;

	public NameValidation() {
		patternObject = Pattern.compile(namePattern);
	}

	@Override
	public void validate(FacesContext fc, UIComponent uic, Object t) throws ValidatorException {
		
	String v = (String) t;

	if (v.isEmpty()) {
		throw new ValidatorException(new FacesMessage("Name is required!"));
	} 

	matcherObject = patternObject.matcher(v);

	if (!matcherObject.matches()) {
		throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Name is not valid!", null));
	}



	}
	
}
