package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DAOLogin;
import beans.UserBean;

@WebServlet("/pages/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DAOLogin daoLogin = new DAOLogin();

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String action = request.getParameter("action");
			if (action.equalsIgnoreCase("logout")) {
				request.getSession().invalidate();
			}
			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", request.getContextPath() + "/index.jsp");

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String login = request.getParameter("login");
		String password = request.getParameter("password");

		if (login != null && !login.isEmpty() && password != null && !password.isEmpty()) {
			try {
				Integer user_id = daoLogin.validateLogin(login, password);
				if (user_id != null) {// ok
					request.getSession().setAttribute("user", user_id);
					response.setStatus(response.SC_MOVED_TEMPORARILY);
					String url = "/pages/acessoLiberado.jsp";
					if(request.getSession().getAttribute("requestedUrl") !=null) {
						url = request.getSession().getAttribute("requestedUrl").toString();
					}
					response.setHeader("Location", request.getContextPath()
							+ url);
				} else {// acesso negado
					RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/acessoNegado.jsp");
					dispatcher.forward(request, response);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {

			response.setStatus(response.SC_MOVED_TEMPORARILY);
			response.setHeader("Location", request.getContextPath() + "/index.jsp");
		}
	}

}
