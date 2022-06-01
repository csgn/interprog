package Utils;

import Controller.ProductBean;
import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 *
 * @author csgn
 */
@WebServlet(name = "FileServlet", urlPatterns = {"/file/*"})
public class FileServlet extends HttpServlet {

	@Inject
	private ProductBean productController;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String file = request.getPathInfo();
		System.out.println("FILE: " + file);
		File f = new File(productController.getUploadTo() + file);

		Files.copy(f.toPath(), response.getOutputStream());
	}

}
