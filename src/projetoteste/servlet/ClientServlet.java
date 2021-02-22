package projetoteste.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import projetoteste.dao.AccountDAO;
import projetoteste.dao.ClientDAO;
import projetoteste.entity.Account;
import projetoteste.entity.Client;

@WebServlet("/client/*")
public class ClientServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public ClientServlet() {
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if(action == null) {
			doGetIndex(request, response);
		} else if (action.equalsIgnoreCase("list")) {
			doGetList(request, response);
		} else if (action.equalsIgnoreCase("new")) {
			doGetNew(request, response);
		} else if (action.equalsIgnoreCase("edit")) {
			doGetEdit(request, response);
		} else if (action.equalsIgnoreCase("remove")) {
			doGetRemove(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");

		if (action == null) {
			doGetIndex(request, response);
		} else if (action.equalsIgnoreCase("insert")) {
			doGetInsert(request, response);
		} else if (action.equalsIgnoreCase("update")) {
			doGetUpdate(request, response);
		}
	}
	
	protected void doGetIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGetList(request, response);
	}
	
	protected void doGetList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Client> clientList = ClientDAO.getInstance().getAll();
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("client-list.jsp");
		request.setAttribute("client", clientList);
		requestDispatcher.forward(request, response);
	}
	
	protected void doGetNew(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("client-form.jsp");
		requestDispatcher.forward(request, response);
	}
	
	protected void doGetEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		Client client = ClientDAO.getInstance().getById(id);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("client-form.jsp");
		request.setAttribute("client", client);
		requestDispatcher.forward(request, response);
	}
	
	protected void doGetInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		Client client = new Client(name, email, password);
		
		ClientDAO.getInstance().insert(client);
		
		response.sendRedirect(request.getContextPath());
	}
	
	protected void doGetUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		
		List<Account> accountsList = AccountDAO.getInstance().getContas(id);
		
		Client client = new Client(id, name, email, password);
		
		response.sendRedirect(request.getContextPath());
	}
	
	protected void doGetRemove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		
		ClientDAO.getInstance().removeById(id);
		
		response.sendRedirect(request.getContextPath());
		
	}
}
