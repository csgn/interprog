package Controller;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;


@Named("appBean")
@SessionScoped
public class AppBean implements Serializable {

	public AppBean() {
	}


}
