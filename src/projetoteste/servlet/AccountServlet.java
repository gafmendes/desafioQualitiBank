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

@WebServlet("/conta")
public class AccountServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	public AccountServlet() {
		
	}
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		
		if(action == null) {
			doGetIndex(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		super.doPost(request, response);
	}
	
	protected void doGetIndex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGetList(request, response);
	}
	
	protected void doGetList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Account> accountList = AccountDAO.getInstance().getAll();
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("...");
		request.setAttribute("account", accountList);
		requestDispatcher.forward(request, response);
	}
	
	protected void doGetNew(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("...");
		requestDispatcher.forward(request, response);
	}
	
	protected void doGetEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.parseInt(request.getParameter("id"));
		Account account = AccountDAO.getInstance().getById(id);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("...");
		request.setAttribute("account", account);
		requestDispatcher.forward(request, response);
	}
	
	protected void doGetInsert(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Client client = request.getParameter("client");
		String accountNumber = request.getParameter("account");
		String accountBalance = request.getParameter("balance");
		
		Account account = new Account(accountNumber, accountBalance, client);
		
		AccountDAO.getInstance().insert(account);
		
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
		
		AccountDAO.getInstance().removeById(id);
		
		response.sendRedirect(request.getContextPath());
		
	}
}
