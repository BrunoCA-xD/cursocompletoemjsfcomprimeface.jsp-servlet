package servlet;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.codec.binary.Base64;

import DAO.DAOUser;
import beans.UserBean;

@WebServlet("/pages/saveUser")
@MultipartConfig
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
				request.getSession();

			} else if (action.equals("edit")) {
				UserBean userBean = daoUser.findById(user);
				request.setAttribute("user", userBean);
			} else if (action.equalsIgnoreCase("download")) {
				UserBean userBean = daoUser.findById(user);
				if (userBean != null) {
					response.setHeader("Content-Disposition",
							"attachment;filename=arquivo." + userBean.getContentType().split("/")[1]);

					byte[] photoBytes = Base64.decodeBase64(userBean.getPhotoBase64());

					InputStream is = new ByteArrayInputStream(photoBytes);
					int read = 0;
					byte[] bytes = new byte[1024];
					OutputStream os = response.getOutputStream();
					while ((read = is.read(bytes)) != -1) {
						os.write(bytes, 0, read);
					}
					os.flush();
					os.close();
				}
			}
			if (request.getAttribute("user") == null) {
				request.setAttribute("user", new UserBean());
			}
			RequestDispatcher view = request.getRequestDispatcher("/pages/cadastroUsuario.jsp");
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
			RequestDispatcher view = request.getRequestDispatcher("/pages/cadastroUsuario.jsp");

			String action = request.getParameter("action") != null ? request.getParameter("action") : "";
			if (!action.equalsIgnoreCase("reset")) {
				String id = request.getParameter("id");
				String login = request.getParameter("login");
				String password = request.getParameter("password");
				String name = request.getParameter("name");
				String zCode = request.getParameter("zCode");
				String street = request.getParameter("street");
				String number = request.getParameter("number");
				String district = request.getParameter("district");
				String city = request.getParameter("city");
				String state = request.getParameter("state");
				String ibge = request.getParameter("ibge");

				UserBean user = new UserBean(login, password, name, zCode, street, number, district, city, state, ibge);
				if (ServletFileUpload.isMultipartContent(request)) {

					Part photo = request.getPart("photo");
					if (photo != null && photo.getInputStream().available() > 0) {
						String photoBase64 = Base64
								.encodeBase64String(convertInputStreamToByte(photo.getInputStream()));
						user.setContentType(photo.getContentType());

						user.setPhotoBase64(photoBase64);
					} else {
						user.setPhotoBase64(request.getParameter("photoTemp"));
						user.setContentType(request.getParameter("contentTypeTemp"));
					}
				}

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

			request.setAttribute("user", new UserBean());
			request.setAttribute("users", daoUser.listAll());
			view.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private byte[] convertInputStreamToByte(InputStream inputStream) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int reads = inputStream.read();
		while (reads != -1) {
			baos.write(reads);
			reads = inputStream.read();
		}
		return baos.toByteArray();
	}

}
