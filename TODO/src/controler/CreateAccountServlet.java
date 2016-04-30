package controler;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Database;
import model.User;
import model.UserDAO;

@WebServlet("/createaccount")
public class CreateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = null;
		String path = null;
		action = request.getParameter("action");
		if (action == null) {
			path = "./createaccount.jsp";
		} else if (action.equals("create")) {
			String login = request.getParameter("login");
			String password = request.getParameter("password");
			String confirm = request.getParameter("confirm");
			PreparedStatement statement = null;
			try {
				statement = Database.getConnection().prepareStatement("SELECT login FROM users WHERE login = ?");
				statement.setString(1, login);
				if (statement.executeQuery().next()) {
					request.setAttribute("exists", "true");
					path = "./createaccount.jsp";
					request.getRequestDispatcher(path).forward(request, response);
					return;
				} else if (!password.equals(confirm)) {
					request.setAttribute("passmismatch", "true");
					path = "./createaccount.jsp";
					request.getRequestDispatcher(path).forward(request, response);
					return;
				} else {
					path = "./index.jsp";
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			UserDAO.addUser(new User(login, password));
			HttpSession session = request.getSession();
			session.setAttribute("login", login);
			session.setAttribute("itemCount", 0);
			response.sendRedirect(path);
		}
	}

}
