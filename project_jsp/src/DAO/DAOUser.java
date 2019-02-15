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
			String sql = "insert into usuario values(null,?,?,?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, user.getLogin());
			statement.setString(2, user.getSenha());
			statement.setString(3, user.getNome());
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

	public List<UserBean> listAll() throws SQLException {
		List<UserBean> lst = new ArrayList<UserBean>();
		String sql = "select * from usuario";

		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet res = statement.executeQuery();

		while (res.next()) {
			UserBean user = new UserBean(res.getLong("id"),
					res.getString("login"),
					res.getString("senha"),
					res.getString("nome"));
			lst.add(user);
		}
		return lst;
	}

	public UserBean findById(String id) throws SQLException {
		String sql = "select * from usuario where id='" + id + "'";
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet res = statement.executeQuery();

		if (res.next()) {
			UserBean user = new UserBean(res.getLong("id"),
					res.getString("login"),
					res.getString("senha"),
					res.getString("nome"));
			return user;
		}
		return null;
	}

	public void update(UserBean user) {
		try {
			String sql = "update usuario set login=?, senha=?, nome=? where id =" + user.getId();

			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setString(1, user.getLogin());
			statement.setString(2, user.getSenha());
			statement.setString(3, user.getNome());
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
}
