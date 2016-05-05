package controler;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.SublistItem;
import model.SublistItemDAO;

@WebServlet("/sublist")
public class SublistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		HttpSession session = request.getSession();
		String login = (String) session.getAttribute("login");
		if (login == null) {
			response.sendRedirect("./index.jsp");
			return;
		}

		int idparent = Integer.parseInt((String)session.getAttribute("idparent"));

		@SuppressWarnings("unchecked")
		List<SublistItem> sublistItems = (List<SublistItem>) session.getAttribute("sublistItems");
		if (sublistItems == null) {
			sublistItems = new LinkedList<>(SublistItemDAO.getAllLineItems(idparent, login));
		}

		String url = "./sublist.jsp";
		String action = request.getParameter("action");

		if (action == null) {
			// default action
			action = "list";
		}

		if (action.equals("list")) {
			session.setAttribute("action", "get");
		} else if (action.equals("add")) {
			String item = request.getParameter("item");
			if (item.equals("")) {
				response.sendRedirect(url);
				return;
			}
			int subitemCount = 0;
			subitemCount = SublistItemDAO.sublistItemCount(idparent, login);
			subitemCount++;
			SublistItemDAO.addLineItem(subitemCount, idparent, login, item);
			sublistItems.add(new SublistItem(subitemCount, idparent, login, item, false));
			session.setAttribute("action", "add");
			session.setAttribute("subitems", sublistItems);
		} else if (action.equals("update")) {
			String iditem = request.getParameter("update");
			if (iditem.startsWith("update")) {
				iditem = iditem.substring("update".length());
				String item = request.getParameter("item" + iditem);
				SublistItem sublistItem = new SublistItem(Integer.parseInt(iditem), idparent, login, item, false);
				update(sublistItem, sublistItems);

			} else if (iditem.startsWith("check")) {
				iditem = iditem.substring("check".length());
				check(Integer.parseInt(iditem), idparent, login, sublistItems);
			} else if (iditem.startsWith("delete")) {
				iditem = iditem.substring("update".length());
				delete(Integer.parseInt(iditem), idparent, login, sublistItems);
			} else if (iditem.startsWith("uncheck")) {
				iditem = iditem.substring("uncheck".length());
				uncheck(Integer.parseInt(iditem), idparent, login, sublistItems);
			}
		}
		session.setAttribute("subitems", sublistItems);
		response.sendRedirect(url);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	

	private void update(SublistItem sublistItem, List<SublistItem> items) {
		SublistItemDAO.updateItem(sublistItem);

		int count = 0;
		for (SublistItem item : items) {
			if (item.getIditem() == sublistItem.getIditem()) {
				items.set(count, sublistItem);
				break;
			}
			count++;
		}
	}

	private void check(int iditem, int idparent, String login, List<SublistItem> items) {
		SublistItemDAO.checkItem(iditem, idparent, login);
		for (SublistItem item : items) {
			if (item.getIditem() == iditem) {
				item.setChecked(true);
				break;
			}
		}
	}

	private void uncheck(int iditem, int idparent, String login, List<SublistItem> items) {
		SublistItemDAO.uncheckItem(iditem, idparent, login);
		for (SublistItem item : items) {
			if (item.getIditem() == iditem) {
				item.setChecked(false);
				break;
			}
		}
	}

	private void delete(int iditem, int idparent, String login, List<SublistItem> items) {
		SublistItemDAO.deleteItem(iditem, idparent, login);
		for (SublistItem item : items) {
			if (item.getIditem() == iditem) {
				items.remove(item);
				break;
			}
		}
	}

}
