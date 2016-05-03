package controler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.User;
import model.UserDAO;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String path = "./login.jsp";
		String action = request.getParameter("action");
		if (action.equals("login")) {
			String login = request.getParameter("login");
			String password = request.getParameter("password");

			if (UserDAO.checkLogin(new User(login, password))) {
				path = "./list";
				session.setAttribute("login", login);
			} else {
				request.setAttribute("invalid", "true");
				request.getRequestDispatcher(path).forward(request, response);
				return;
			}
		}
		response.sendRedirect(path);
	}
}
