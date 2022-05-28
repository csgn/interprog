/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
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
 * @author Aykut
 */
@FacesValidator("emailValidator")
public class EmailValidation implements Validator {

	String emailPattern="^[A-Za-z0-9+-]+(\\.[A-Za-z0-9+-]+)*@[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";
	private Pattern patternObject;
  private Matcher matcherObject;
	
	public EmailValidation() {
		patternObject = Pattern.compile(emailPattern);
	}
	

	@Override
	public void validate(FacesContext facesContext, UIComponent uiComponent, Object o) throws ValidatorException {
		matcherObject = patternObject.matcher(o.toString());
		if (!matcherObject.matches()) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Wrong Format", "Format of E-mail address is not valid");
      throw new ValidatorException(message);
    }
	}
}
	
