package controler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.User;
import model.UserDAO;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String path = "./login.jsp";
		String action = request.getParameter("action");
		if (action.equals("login")) {
			String login = request.getParameter("login");
			boolean remember = "true".equals(request.getParameter("remember"));
			if (UserDAO.checkLogin(new User(login, request.getParameter("password")))) {
				path = "./list";
				request.getSession().setAttribute("login", login);
				if (remember) {
					Cookie cookie = new Cookie("login", login);
					cookie.setMaxAge(2592000);
					cookie.setPath("/");
					response.addCookie(cookie);
				}
			} else {
				request.setAttribute("invalid", "true");
				request.getRequestDispatcher(path).forward(request, response);
				return;
			}
		}
		response.sendRedirect(path);
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie cookie : cookies) {
				if (UserDAO.checkIfUserExist(cookie.getValue())) {
					request.getSession().setAttribute("login", cookie.getValue());
					response.sendRedirect("./list");
					return;
				}
			}	
		}	
		response.sendRedirect("./login.jsp");
	}
}
