package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.PhoneBean;
import connection.SingleConnection;

public class DAOPhone {

	private Connection conn;

	public DAOPhone() {
		conn = SingleConnection.getConnection();
	}

	public void save(PhoneBean phone) {
		try {
			String sql = "insert into telefone values(?,?,?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setLong(1, phone.getUserId());
			statement.setString(2, phone.getNumber());
			statement.setString(3, phone.getType());
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

	public void delete(String number) {
		try {
			String sql = "delete from telefone where numero ='" + number + "'";
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

	public List<PhoneBean> listPhoneByUser(Long user) throws SQLException {
		List<PhoneBean> lst = new ArrayList<PhoneBean>();
		String sql = "select * from telefone where usuario=" + user;
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet res = statement.executeQuery();
		while (res.next()) {
			lst.add(new PhoneBean(user, res.getString("numero"), res.getString("tipo")));
		}

		return lst;
	}
	
	public boolean validatePhoneNumber(String number) throws SQLException {
		String sql = "select count(1) as qtd from telefone where numero='" + number + "'";
		PreparedStatement statement = conn.prepareStatement(sql);
		ResultSet res = statement.executeQuery();

		if (res.next()) {
			if (res.getInt("qtd") == 0)
				return true;
		}

		return false;
	}
}
