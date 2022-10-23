package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.CongTrinh;
import utils.Utils;

public class CongTrinh_DAO {
	public List<String> getMaVaTenCongTrinh() {
		List<String> list = new ArrayList<>();

		try {
			Statement statement = ConnectDB.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM CongTrinh");
			while (resultSet.next()) {
				String maCT = resultSet.getString("maCongTrinh");
				String tenCT = resultSet.getString("tenCongTrinh");
				list.add(String.format("%s - %s", maCT, tenCT));
			}
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	private CongTrinh getCongTrinh(ResultSet resultSet) throws SQLException {
		String maCongTrinh = resultSet.getString("maCongTrinh");
		String tenCongTrinh = resultSet.getString("tenCongTrinh");
		String diaDiem = resultSet.getString("diaDiem");
		String chuDauTu = resultSet.getString("chuDauTu");
		String giayPhepXD = resultSet.getString("giayPhepXD");
		LocalDate ngayCap = Utils.convertDateToLocalDate(resultSet.getDate("ngayCap"));
		LocalDate ngayKhoiCong = Utils.convertDateToLocalDate(resultSet.getDate("ngayKhoiCong"));
		LocalDate ngayHT = Utils.convertDateToLocalDate(resultSet.getDate("ngayHT"));
		return new CongTrinh(maCongTrinh, tenCongTrinh, diaDiem, chuDauTu, giayPhepXD, ngayCap, ngayKhoiCong, ngayHT);
	}

	public List<CongTrinh> getAllCongTrinh() {
		List<CongTrinh> list = new ArrayList<>();

		try {
			Statement statement = ConnectDB.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM CongTrinh");
			while (resultSet.next())
				list.add(getCongTrinh(resultSet));

			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public boolean themCongTrinh(CongTrinh congTrinh) {
		int res = 0;
		PreparedStatement preparedStatement;
		try {
			preparedStatement = ConnectDB.getConnection()
					.prepareStatement("INSERT CongTrinh VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
			preparedStatement.setString(1, congTrinh.getMaCongTrinh());
			preparedStatement.setString(2, congTrinh.getTenCongTrinh());
			preparedStatement.setString(3, congTrinh.getDiaDiem());
			preparedStatement.setString(4, congTrinh.getChuDauTu());
			preparedStatement.setString(5, congTrinh.getGiayPhepXD());
			preparedStatement.setDate(6, Utils.convertLocalDateToDate(congTrinh.getNgayCap()));
			preparedStatement.setDate(7, Utils.convertLocalDateToDate(congTrinh.getNgayKhoiCong()));
			preparedStatement.setDate(8, Utils.convertLocalDateToDate(congTrinh.getNgayHT()));
			res = preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res > 0;
	}

	public boolean suaCongTrinh(CongTrinh congTrinh) {
		int res = 0;
		PreparedStatement preparedStatement;
		try {
			preparedStatement = ConnectDB.getConnection().prepareStatement(
					"UPDATE CongTrinh SET tenCongTrinh = ?, diaDiem = ?, chuDauTu = ?, giayPhepXD = ?, ngayCap = ?, ngayKhoiCong = ?, ngayHT = ? WHERE maCongTrinh = ?");
			preparedStatement.setString(1, congTrinh.getTenCongTrinh());
			preparedStatement.setString(2, congTrinh.getDiaDiem());
			preparedStatement.setString(3, congTrinh.getChuDauTu());
			preparedStatement.setString(4, congTrinh.getGiayPhepXD());
			preparedStatement.setDate(5, Utils.convertLocalDateToDate(congTrinh.getNgayCap()));
			preparedStatement.setDate(6, Utils.convertLocalDateToDate(congTrinh.getNgayKhoiCong()));
			preparedStatement.setDate(7, Utils.convertLocalDateToDate(congTrinh.getNgayHT()));
			preparedStatement.setString(8, congTrinh.getMaCongTrinh());
			res = preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res > 0;
	}

	public boolean xoaCongTrinh(String maCongTrinh) {
		int res = 0;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("DELETE CongTrinh WHERE maCongTrinh = ?");
			preparedStatement.setString(1, maCongTrinh);
			res = preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res > 0;
	}

	public CongTrinh getCongTrinh(String maCongTrinh) {
		CongTrinh congTrinh = null;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("SELECT * FROM CongTrinh WHERE maCongTrinh = ?");
			preparedStatement.setString(1, maCongTrinh);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				congTrinh = getCongTrinh(resultSet);
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return congTrinh;
	}

	public LocalDate getNgayKhoiCong(String maCongTrinh) {
		LocalDate ngayBatDau = null;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("SELECT ngayKhoiCong FROM CongTrinh WHERE maCongTrinh = ?");
			preparedStatement.setString(1, maCongTrinh);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				ngayBatDau = Utils.convertDateToLocalDate(resultSet.getDate(1));
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ngayBatDau;
	}
}
