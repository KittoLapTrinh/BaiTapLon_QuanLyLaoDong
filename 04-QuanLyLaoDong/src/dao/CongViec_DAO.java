package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.CongViec;

public class CongViec_DAO {

	private CongViec getCongViec(ResultSet resultSet) throws SQLException {
		String maCongViec = resultSet.getString("maCongViec");
		String tenCongViec = resultSet.getString("tenCongViec");
		return new CongViec(maCongViec, tenCongViec);
	}

	public List<CongViec> getAllCongViec() {
		List<CongViec> list = new ArrayList<>();

		Statement statement;
		try {
			statement = ConnectDB.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM CongViec");
			while (resultSet.next())
				list.add(getCongViec(resultSet));
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public CongViec getCongViec(String maCongViec) {
		CongViec congViec = null;
		PreparedStatement preparedStatement;
		try {
			preparedStatement = ConnectDB.getConnection()
					.prepareStatement("SELECT * FROM CongViec WHERE maCongViec = ?");
			preparedStatement.setString(1, maCongViec);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				congViec = getCongViec(resultSet);
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return congViec;
	}

	public boolean xoaCongViec(String maCongViec) {
		int res = 0;
		PreparedStatement preparedStatement;
		try {
			preparedStatement = ConnectDB.getConnection().prepareStatement("DELETE CongViec WHERE maCongViec = ?");
			preparedStatement.setString(1, maCongViec);
			res = preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res > 0;
	}

	public boolean themCongViec(CongViec CongViec) {
		int res = 0;

		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("INSERT CongViec VALUES (?, ?)");
			preparedStatement.setString(1, CongViec.getMaCongViec());
			preparedStatement.setString(2, CongViec.getTenCongViec());
			res = preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return res > 0;
	}

	public boolean suaCongViec(CongViec CongViec) {
		int res = 0;

		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("UPDATE CongViec SET tenCongViec = ? WHERE maCongViec = ?");
			preparedStatement.setString(1, CongViec.getTenCongViec());
			preparedStatement.setString(2, CongViec.getMaCongViec());
			res = preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return res > 0;
	}
}
