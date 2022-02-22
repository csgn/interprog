package Beans;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import java.io.Serializable;


@Named("AppBean")
@SessionScoped
public class AppBean implements Serializable {
	public AppBean() {
	}
	
}
