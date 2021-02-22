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

@WebServlet("/account/*")
public class AccountServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	public AccountServlet() {
		
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
		}
	}
	
	protected void doGetIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGetList(request, response);
	}
	
	protected void doGetList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		List<Account> accountList = ClientDAO.getInstance().getContas(id);
		Client clients = ClientDAO.getInstance().getById(id);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("account-list.jsp");
		request.setAttribute("client", clients);
		request.setAttribute("account", accountList);
		requestDispatcher.forward(request, response);
	}
	
	protected void doGetNew(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int clientId = Integer.parseInt(request.getParameter("id"));
		Client clients = ClientDAO.getInstance().getById(clientId);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("account-form.jsp");
		request.setAttribute("client", clients);
		requestDispatcher.forward(request, response);
	}
	
	protected void doGetEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int clientId = Integer.parseInt(request.getParameter("id"));
		int id = Integer.parseInt(request.getParameter("id"));
		
		Account selectAccount = AccountDAO.getInstance().getById(id);
		Client selectClient = ClientDAO.getInstance().getById(clientId);
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("account-form.jsp");
		request.setAttribute("client", selectClient);
		request.setAttribute("account", selectAccount);
		requestDispatcher.forward(request, response);
	}
	
	protected void doGetInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String accountNumber = request.getParameter("account");
		Double accountBalance = Double.parseDouble(request.getParameter("balance"));
		int clientId = Integer.parseInt(request.getParameter("id"));
		
		Client client = ClientDAO.getInstance().getById(clientId);
		Account account = new Account(accountNumber, accountBalance, client);
		
		AccountDAO.getInstance().insert(account);
		response.sendRedirect(request.getContextPath());
		doGetList(request, response);
	}
	
	protected void doGetUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int clientId = Integer.parseInt(request.getParameter("id"));
		
		String accountNumber = request.getParameter("account");
		Double accountBalance = Double.parseDouble(request.getParameter("balance"));
		
//		List<Account> accountsList = AccountDAO.getInstance().getContas(id);
		
		Account account = AccountDAO.getInstance().getById(id);
		
		account.setAccountBalance(accountBalance);
		account.setAccountNumber(accountNumber);
		AccountDAO.getInstance().update(account);
		
		response.sendRedirect(request.getContextPath());
	}
	
	protected void doGetRemove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		int clientId = Integer.parseInt(request.getParameter("id"));
		
		AccountDAO.getInstance().removeById(id);
		
		response.sendRedirect(request.getContextPath()+"/account?action=list&client_id="+clientId);
		
	}
}
