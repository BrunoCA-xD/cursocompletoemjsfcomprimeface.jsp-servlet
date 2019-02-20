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
			String action = request.getParameter("action") != null ? request.getParameter("action") : "";
			String user = request.getParameter("user");

			if (action.equals("delete")) {
				daoUser.delete(user);

			} else if (action.equals("edit")) {
				UserBean userBean = daoUser.findById(user);
				request.setAttribute("user", userBean);
			}
			RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
			request.setAttribute("users", daoUser.listAll());
			request.setAttribute("action", action);
			view.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");

			String action = request.getParameter("action") != null ? request.getParameter("action") : "";
			if (!action.equalsIgnoreCase("reset")) {
				String id = request.getParameter("id");
				String login = request.getParameter("login");
				String password = request.getParameter("password");
				String name = request.getParameter("name");
				String phone = request.getParameter("phone");
				String zCode = request.getParameter("zCode");
				String street = request.getParameter("street");
				String number = request.getParameter("number");
				String district = request.getParameter("district");
				String city = request.getParameter("city");
				String state = request.getParameter("state");
				String ibge = request.getParameter("ibge");
				UserBean user = new UserBean(login, password, name, phone, zCode, street, number, district, city, state,
						ibge);

				user.setId((id == null || id.isEmpty() ? null : Long.parseLong(id)));

				// trabalho de classe service, ou bussiness object (BO)
				String errorMsg = null;
				if (login == null || login.trim().isEmpty())
					errorMsg = "Informar um login é obrigatório";
				if (password == null || password.trim().isEmpty()) {
					errorMsg = errorMsg == null ? "" : errorMsg + " <br/> ";
					errorMsg += "Informar uma senha é obrigatório";
				}
				boolean loginValid = false, passwordValid = false;
				if (errorMsg == null) {
					loginValid = daoUser.isLoginValid(login, id);
					passwordValid = daoUser.isPasswordValid(password, id);
					if (!loginValid)
						errorMsg = "Login já usado em outro usuário";
					else if (!passwordValid)
						errorMsg = "Senha já usada em outro usuário";
				}
				if (!loginValid || !passwordValid) {
					request.setAttribute("users", daoUser.listAll());
					request.setAttribute("errorMsg", errorMsg);
					request.setAttribute("user", user);
					view.forward(request, response);
					return;
				}

				if (id == null || id.isEmpty()) {
					daoUser.save(user);
					request.setAttribute("successMsg", "Usuário cadastrado com sucesso!");
				} else {
					daoUser.update(user);
					request.setAttribute("successMsg", "Usuário atualizado com sucesso!");
				}
			}

			request.setAttribute("users", daoUser.listAll());
			view.forward(request, response);
		} catch (

		SQLException e) {
			e.printStackTrace();
		}
	}

}
