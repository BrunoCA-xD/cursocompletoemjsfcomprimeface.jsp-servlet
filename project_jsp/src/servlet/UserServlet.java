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
			String action = request.getParameter("acao") != null ? request.getParameter("acao") : "";
			String user = request.getParameter("usuario");

			if (action.equals("delete")) {
				daoUser.delete(user);

			} else if (action.equals("edit")) {
				UserBean userBean = daoUser.findById(user);
				request.setAttribute("user", userBean);
			}
			RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
			request.setAttribute("usuarios", daoUser.listAll());
			request.setAttribute("acao", action);
			view.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			String action = request.getParameter("acao") != null ? request.getParameter("acao") : "";
			if (!action.equalsIgnoreCase("reset")) {
				String id = request.getParameter("id");
				String login = request.getParameter("login");
				String senha = request.getParameter("senha");
				String nome = request.getParameter("nome");
				String fone = request.getParameter("fone");
				UserBean user = new UserBean(login, senha, nome, fone);
				if (daoUser.isLoginValid(login, id)) {
					if (daoUser.isPasswordValid(senha, id)) {
						if (id == null || id.isEmpty()) {
							daoUser.save(user);
							request.setAttribute("successMsg", "Usuário cadastrado com sucesso!");
						} else {
							user.setId(Long.valueOf(id));
							daoUser.update(user);
							request.setAttribute("successMsg", "Usuário atualizado com sucesso!");
						}
					} else {
						request.setAttribute("user", user);
						request.setAttribute("errorMsg", "Senha já usada em outro usuário");
					}
				} else {
					request.setAttribute("user", user);
					request.setAttribute("errorMsg", "Login já usado em outro usuário");
				}
			}
			RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
			request.setAttribute("usuarios", daoUser.listAll());
			view.forward(request, response);
		} catch (

		SQLException e) {

			e.printStackTrace();
		}
	}

}
