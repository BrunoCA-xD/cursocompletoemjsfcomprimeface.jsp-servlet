package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DAOUser;
import beans.UserBean;

@WebServlet("/saveUser")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DAOUser daoUser = new DAOUser();

	public UserServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String action = request.getParameter("acao");
			String user = request.getParameter("usuario");

			if (action.equals("delete")) {
				daoUser.delete(user);
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUser.listAll());
				view.forward(request, response);
			}else if(action.equals("edit")){
				UserBean userBean = daoUser.findByLogin(user);
				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("usuarios", daoUser.listAll());
				request.setAttribute("user", userBean);
				view.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String id = request.getParameter("id");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");

		UserBean user = new UserBean(login, senha);
		
		if(id == null || id.isEmpty()) {
			daoUser.save(user);			
		}else {
			user.setId(Long.valueOf(id));
			daoUser.update(user);
		}

		try {
			RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
			request.setAttribute("usuarios", daoUser.listAll());
			view.forward(request, response);
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

}
