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
import beans.ProductBean;

@WebServlet("/pages/saveProduct")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DAOProduct daoProduct = new DAOProduct();

	public ProductServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String action = request.getParameter("action") != null ? request.getParameter("action") : "";
			String product = request.getParameter("product");

			if (action.equals("delete")) {
				daoProduct.delete(product);

			} else if (action.equals("edit")) {
				ProductBean productBean = daoProduct.findById(product);
				request.setAttribute("product", productBean);
			}
			RequestDispatcher view = request.getRequestDispatcher("/pages/cadastroProduto.jsp");
			request.setAttribute("products", daoProduct.listAll());
			request.setAttribute("action", action);
			view.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			RequestDispatcher view = request.getRequestDispatcher("/pages/cadastroProduto.jsp");
			request.setAttribute("products", daoProduct.listAll());

			String action = request.getParameter("action") != null ? request.getParameter("action") : "";
			if (!action.equalsIgnoreCase("reset")) {
				String errorMsg = null;
				String id = request.getParameter("id");
				String name = request.getParameter("name");
				String amount = request.getParameter("amount");
				String value = request.getParameter("value");
				ProductBean product = new ProductBean(name);
				if (name == null || name.trim().isEmpty()) {
					errorMsg = "Informar o nome do produto é obrigatório";
				}
				if (amount == null || amount.trim().isEmpty()) {
					errorMsg = errorMsg == null ? "" : errorMsg + " <br/> ";
					errorMsg += "Informar a quantidade do produto é obrigatório";

				} else {
					product.setAmount(Double.valueOf(amount.replace(".", "").replace(",", ".")));
				}
				if (value == null || value.trim().isEmpty()) {
					errorMsg = errorMsg == null ? "" : errorMsg + " <br/> ";
					errorMsg += "Informar o valor do produto é obrigatório";

				} else {
					product.setValue(Double.valueOf(value.replace(".", "").replace(",", ".")));
				}
				boolean isNameValid = daoProduct.isNameValid(name, id);
				if (!isNameValid) {
					errorMsg = "Já existe um produto com esse nome";
				}
				if (errorMsg != null || !isNameValid) {

					request.setAttribute("errorMsg", errorMsg);
					product.setId((id == null || id.isEmpty()) ? null : Long.parseLong(id));
					request.setAttribute("product", product);
					view.forward(request, response);
					return;
				}
				if (id == null || id.isEmpty()) {
					daoProduct.save(product);
					request.setAttribute("successMsg", "Produto cadastrado com sucesso!");
				} else {
					product.setId(Long.valueOf(id));
					daoProduct.update(product);
					request.setAttribute("successMsg", "Produto atualizado com sucesso!");
				}
			}
			request.setAttribute("products", daoProduct.listAll());
			view.forward(request, response);
		} catch (

		SQLException e) {

			e.printStackTrace();
		}
	}

}
