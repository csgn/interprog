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
@FacesValidator("phoneValidator")
public class PhoneValidation implements Validator {
	
	String patterns 
      = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$" 
      + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$" 
      + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";
	
	private Pattern patternObject;
  private Matcher matcherObject;
	
	public PhoneValidation() {
		patternObject = Pattern.compile(patterns);
	}

	@Override
	public void validate(FacesContext fc, UIComponent uic, Object t) throws ValidatorException {
		matcherObject = patternObject.matcher(t.toString());
		if (!matcherObject.matches()) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Wrong Format", "Format of phone number is not valid");
			throw new ValidatorException(message);
		}
	}
}
