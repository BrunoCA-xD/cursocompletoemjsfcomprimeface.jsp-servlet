package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.UserBean;
import connection.SingleConnection;

public class DAOUser {

	private Connection conn;

	public DAOUser() {
		conn = SingleConnection.getConnection();
	}

	public void save(UserBean user) {
		try {
			String sql = "insert into usuario values(null,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, user.getLogin());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getName());
			statement.setString(4, user.getPhone());
			statement.setString(5, user.getzCode());
			statement.setString(6, user.getStreet());
			statement.setString(7, user.getNumber());
			statement.setString(8, user.getDistrict());
			statement.setString(9, user.getCity());
			statement.setString(10, user.getState());
			statement.setString(11, user.getIbge());
			statement.execute();
			conn.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public void delete(String id) {
		try {
			String sql = "delete from usuario where id ='" + id + "'";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.execute();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public void update(UserBean user) {
		try {
			String sql = "update usuario set login=?,"
					+ " senha=?, nome=?, telefone=?, cep=?,"
					+ " rua=?, num=?, bairro=?, cidade=?, uf=?, ibge=?"
					+ " where id =" + user.getId();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, user.getLogin());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getName());
			statement.setString(4, user.getPhone());
			statement.setString(5, user.getzCode());
			statement.setString(6, user.getStreet());
			statement.setString(7, user.getNumber());			
			statement.setString(8, user.getDistrict());
			statement.setString(9, user.getCity());
			statement.setString(10, user.getState());
			statement.setString(11, user.getIbge());
			statement.executeUpdate();
			conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
	}

	public List<UserBean> listAll() throws SQLException {
		List<UserBean> lst = new ArrayList<UserBean>();
		String sql = "select * from usuario";

		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet res = statement.executeQuery();

		while (res.next()) {
			UserBean user = new UserBean(
					res.getLong("id"), res.getString("login"), res.getString("senha"),
					res.getString("nome"), res.getString("telefone"), res.getString("cep"),
					res.getString("rua"), res.getString("num"),res.getString("bairro"),
					res.getString("cidade"), res.getString("uf"),res.getString("ibge"));
			lst.add(user);
		}
		return lst;
	}

	public UserBean findById(String id) throws SQLException {
		String sql = "select * from usuario where id='" + id + "'";
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet res = statement.executeQuery();

		if (res.next()) {
			UserBean user = new UserBean(
					res.getLong("id"), res.getString("login"), res.getString("senha"),
					res.getString("nome"), res.getString("telefone"), res.getString("cep"),
					res.getString("rua"), res.getString("num"),res.getString("bairro"),
					res.getString("cidade"), res.getString("uf"),res.getString("ibge"));
			return user;
		}
		return null;
	}

	/* 
	 * @return true se a contagem de login for ==0 ou se for <=1 e id do usuario igual ao recuperado
	 *  na contagem
	 * */
	public boolean isLoginValid(String login, String id) throws SQLException {
		String sql = "select count(1) as qtd, id from usuario where login='" + login + "'";
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet res = statement.executeQuery();

		if (res.next()) {
			if (res.getInt("qtd") == 0)
				return true;
			if (res.getString("id").equals(id))
				return true;
		}

		return false;
	}
	/* 
	 * @return true se a contagem de senha(password) for ==0 ou se for <=1 e id do usuario igual ao recuperado
	 *  na contagem
	 *  
	 *  Esse metodo de validação foi incluido só por pedido do professor
	 * */
	public boolean isPasswordValid(String password, String id) throws SQLException {
		String sql = "select count(1) as qtd, id from usuario where senha='" + password + "'";
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet res = statement.executeQuery();

		if (res.next()) {
			if (res.getInt("qtd") == 0)
				return true;
			if (res.getString("id").equals(id))
				return true;
		}

		return false;
	}
}
