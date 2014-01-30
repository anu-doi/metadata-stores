package au.edu.anu.metadatastores.login;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name="LoginServlet", urlPatterns="/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static final Logger LOGGER = LoggerFactory.getLogger(LoginServlet.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String errorParam = request.getParameter("error");
		//String methodParam = request.getParameter("method");
		
		if ("true".equals(errorParam)) {
			request.setAttribute("error", "You have entered an invalid username or password");
		}
		else {
			request.removeAttribute("error");
		}
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("jsp/login.jsp");
		requestDispatcher.forward(request, response);
	}
}
