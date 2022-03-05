package Utils;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import java.io.Serializable;

/**
 *
 * @author csgn
 */
@Named("navigation")
@ViewScoped
public class Navigation implements Serializable {
	private String path = "/modules/";
	private String extension = ".xhtml";
	private String content = "home";

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String fullPath() {
		return path + content + extension;
	}

}
