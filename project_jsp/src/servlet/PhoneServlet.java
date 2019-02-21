package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DAOPhone;
import DAO.DAOUser;
import beans.PhoneBean;
import beans.UserBean;

@WebServlet("/pages/savePhone")
public class PhoneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DAOUser daoUser = new DAOUser();
	private DAOPhone daoPhone = new DAOPhone();

	public PhoneServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String action = request.getParameter("phoneAction") != null ? request.getParameter("phoneAction") : "";
			String user = request.getParameter("user");
			if (action.equalsIgnoreCase("deleteNumber")) {
				String phoneNumber = request.getParameter("number");

					daoPhone.delete(phoneNumber);
				 
			}
			if (user != null && !user.isEmpty()) {
				request.setAttribute("phones", daoPhone.listPhoneByUser(Long.parseLong(user)));
				request.setAttribute("user", daoUser.findById(user));
			}
			request.setAttribute("showPhoneModal", true);

			new UserServlet().doGet(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String user = request.getParameter("user");
			Long userID = (user == null || user.isEmpty()) ? null : Long.parseLong(user);
			PhoneBean phone = new PhoneBean(userID, request.getParameter("phoneNumber"), request.getParameter("type"));
			if (phone!= null && !phone.getNumber().isEmpty()) {
				if (!daoPhone.validatePhoneNumber(phone.getNumber())) {

					request.setAttribute("errorMsg", "Telefone já cadastrado! para este usuario ou outro");
					doGet(request, response);
					return;
				}
				daoPhone.save(phone);
				request.setAttribute("successMsg", "Telefone cadastrado com sucesso!");
			}else {
				request.setAttribute("errorMsg", "Informar o numero é obrigatorio");
			}
			doGet(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
