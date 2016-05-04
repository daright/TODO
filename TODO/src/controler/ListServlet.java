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

import model.LineItem;
import model.LineItemDAO;
import model.UserDAO;

@WebServlet("/list")
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String login = (String) session.getAttribute("login");
		if (login == null) {
			response.sendRedirect("./login.jsp");
			return;
		}
		int itemCount = 0;
		if (session.getAttribute("itemCount") != null) {
			itemCount = (int) session.getAttribute("itemCount");
		}

		if (itemCount == 0) {
			itemCount = UserDAO.getItemCount(login);
		}
		@SuppressWarnings("unchecked")
		List<LineItem> items = (List<LineItem>) session.getAttribute("items");
		if (items == null) {
			items = new LinkedList<>(LineItemDAO.getAllLineItems(login));
		}
		for (LineItem lineItem : items) {
			lineItem.setNumOfSubitems(LineItemDAO.getNumOfSubitems(lineItem.getIditem(), login));
			lineItem.setNumOfCheckedSubitems(LineItemDAO.getNumOfCheckedSubitems(lineItem.getIditem(), login));
		}
		if (itemCount > 0 && items.size() == 0) {
			itemCount = 0;
			UserDAO.updateItemCount(0, login);
		}

		String url = "./index.jsp";
		String action = request.getParameter("action");

		if (action == null) {
			// default action
			action = "list";
		}

		if (action.equals("list")) {
			session.setAttribute("items", items);
		} if (action.equals("add")) {
			String item = request.getParameter("item");
			if (item.equals("")) {
				response.sendRedirect(url);
				return;
			}
			itemCount++;
			LineItemDAO.addLineItem(itemCount, item, login);
			UserDAO.updateItemCount(itemCount, login);
			items.add(new LineItem(itemCount, item, false, login));
			session.setAttribute("itemCount", itemCount);
		} else if (action.equals("update")) {
			String iditem = request.getParameter("update");
			if (iditem.startsWith("update")) {
				iditem = iditem.substring("update".length());
				String item = request.getParameter("item" + iditem);
				LineItem lineItem = new LineItem(Integer.parseInt(iditem), item, false, login);
				update(lineItem, items);

			} else if (iditem.startsWith("check")) {
				iditem = iditem.substring("check".length());
				check(Integer.parseInt(iditem), login, items);
			} else if (iditem.startsWith("delete")) {
				iditem = iditem.substring("update".length());
				delete(Integer.parseInt(iditem), login, items);
			} else if (iditem.startsWith("uncheck")) {
				iditem = iditem.substring("uncheck".length());
				uncheck(Integer.parseInt(iditem), login, items);
			} else if (iditem.startsWith("sublist")) {
				iditem = iditem.substring("sublist".length());
				session.setAttribute("idparent", iditem);
				String item = request.getParameter("item" + iditem);
				session.setAttribute("parent", item);
				url = "./sublist";
			}
			session.setAttribute("items", items);
		}
		response.sendRedirect(url);
	}

	private void update(LineItem lineItem, List<LineItem> items) {
		LineItemDAO.updateItem(lineItem);
		int count = 0;
		for (LineItem item : items) {
			if (item.getIditem() == lineItem.getIditem()) {
				items.set(count, lineItem);
				break;
			}
			count++;
		}
	}

	private void check(int id, String login, List<LineItem> items) {
		LineItemDAO.checkItem(id, login);
		for (LineItem lineItem : items) {
			if (lineItem.getIditem() == id) {
				lineItem.setChecked(true);
				break;
			}
		}
	}

	private void uncheck(int id, String login, List<LineItem> items) {
		LineItemDAO.uncheckItem(id, login);
		for (LineItem lineItem : items) {
			if (lineItem.getIditem() == id) {
				lineItem.setChecked(false);
				break;
			}
		}
	}

	private void delete(int id, String login, List<LineItem> items) {
		LineItemDAO.deleteItem(id, login);
		for (LineItem lineItem : items) {
			if (lineItem.getIditem() == id) {
				items.remove(lineItem);
				break;
			}
		}
	}
}
