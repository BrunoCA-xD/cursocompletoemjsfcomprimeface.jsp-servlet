package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.DAOProduct;
import DAO.DAOUser;
import beans.ProductBean;
import beans.UserBean;

@WebServlet("/saveProduct")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DAOProduct daoProduct = new DAOProduct();

	public ProductServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String action = request.getParameter("acao") != null ? request.getParameter("acao") : "";
			String product = request.getParameter("produto");

			if (action.equals("delete")) {
				daoProduct.delete(product);

			} else if (action.equals("edit")) {
				ProductBean productBean = daoProduct.findById(product);
				request.setAttribute("product", productBean);
			}
			RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
			request.setAttribute("produtos", daoProduct.listAll());
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
				String nome = request.getParameter("nome");
				Double quantidade = Double.valueOf(request.getParameter("quantidade"));
				Double valor = Double.valueOf(request.getParameter("valor"));
				ProductBean product = new ProductBean(nome, quantidade, valor);
				if (daoProduct.isNameValid(nome, id)) {
					if (id == null || id.isEmpty()) {
						daoProduct.save(product);
						request.setAttribute("successMsg", "Produto cadastrado com sucesso!");
					} else {
						product.setId(Long.valueOf(id));
						daoProduct.update(product);
						request.setAttribute("successMsg", "Produto atualizado com sucesso!");
					}
				} else {
					request.setAttribute("product", product);
					request.setAttribute("errorMsg", "Nome já usado em outro produto");
				}
			}
			RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
			request.setAttribute("produtos", daoProduct.listAll());
			view.forward(request, response);
		} catch (

		SQLException e) {

			e.printStackTrace();
		}
	}

}
