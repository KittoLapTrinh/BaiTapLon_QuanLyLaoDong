package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.ChuyenMon;

public class ChuyenMon_DAO {

	private ChuyenMon getChuyenMon(ResultSet resultSet) throws SQLException {
		String ma = resultSet.getString("maChuyenMon");
		String ten = resultSet.getString("tenChuyenMon");
		return new ChuyenMon(ma, ten);
	}

	public List<ChuyenMon> getAllChuyenMon() {
		List<ChuyenMon> list = new ArrayList<>();

		Statement statement;
		try {
			statement = ConnectDB.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM ChuyenMon");
			while (resultSet.next())
				list.add(getChuyenMon(resultSet));
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public boolean xoaChuyenMon(String maChuyenMon) {
		int res = 0;
		PreparedStatement preparedStatement;
		try {
			preparedStatement = ConnectDB.getConnection().prepareStatement("DELETE ChuyenMon WHERE maChuyenMon = ?");
			preparedStatement.setString(1, maChuyenMon);
			res = preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res > 0;
	}

	public boolean themChuyenMon(ChuyenMon chuyenMon) {
		int res = 0;

		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("INSERT ChuyenMon VALUES (?, ?)");
			preparedStatement.setString(1, chuyenMon.getMaChuyenMon());
			preparedStatement.setString(2, chuyenMon.getTenChuyenMon());
			res = preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return res > 0;
	}

	public boolean suaChuyenMon(ChuyenMon chuyenMon) {
		int res = 0;

		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("UPDATE ChuyenMon SET tenChuyenMon = ? WHERE maChuyenMon = ?");
			preparedStatement.setString(1, chuyenMon.getTenChuyenMon());
			preparedStatement.setString(2, chuyenMon.getMaChuyenMon());
			res = preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return res > 0;
	}

	public String getTenChuyenMon(String maChuyenMon) {
		String res = null;

		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("SELECT tenChuyenMon FROM ChuyenMon WHERE maChuyenMon = ?");
			preparedStatement.setString(1, maChuyenMon);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next())
				res = resultSet.getString(1);

			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return res;
	}
}
