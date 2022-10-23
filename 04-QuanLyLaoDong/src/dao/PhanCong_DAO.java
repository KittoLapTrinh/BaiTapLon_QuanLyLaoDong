package dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.CongNhan;
import entity.CongTrinh;
import entity.CongViec;
import entity.PhanCong;
import utils.TrangThai;
import utils.Utils;

public class PhanCong_DAO {
	private CongNhan_DAO congNhan_DAO;
	private CongViec_DAO congViec_DAO;
	private CongTrinh_DAO congTrinh_DAO;

	public PhanCong_DAO() {
		congNhan_DAO = new CongNhan_DAO();
		congViec_DAO = new CongViec_DAO();
		congTrinh_DAO = new CongTrinh_DAO();
	}

	private PhanCong getPhanCong(ResultSet resultSet) throws SQLException {
		String maPhanCong = resultSet.getString("maPhanCong");
		String maCongNhan = resultSet.getString("maCongNhan");
		String maCongViec = resultSet.getString("maCongViec");
		String maCongTrinh = resultSet.getString("maCongTrinh");
		LocalDate ngayBatDau = Utils.convertDateToLocalDate(resultSet.getDate("ngayBatDau"));
		LocalDate ngayKetThuc = Utils.convertDateToLocalDate(resultSet.getDate("ngayKetThuc"));
		String ghiChu = resultSet.getString("ghiChu");
		CongNhan congNhan = congNhan_DAO.getCongNhanTheoMa(maCongNhan);
		CongViec congViec = congViec_DAO.getCongViec(maCongViec);
		CongTrinh congTrinh = congTrinh_DAO.getCongTrinh(maCongTrinh);

		return new PhanCong(maPhanCong, congNhan, congViec, congTrinh, ngayBatDau, ngayKetThuc, ghiChu);
	}

	public List<PhanCong> getAllPhanCong() {
		List<PhanCong> list = new ArrayList<>();

		Statement statement;
		try {
			statement = ConnectDB.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM PhanCong");
			while (resultSet.next())
				list.add(getPhanCong(resultSet));
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}

	public boolean them(PhanCong phanCong) {
		int res = 0;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("INSERT PhanCong VALUES (?, ?, ?, ?, ?, ?, ?)");
			preparedStatement.setString(1, phanCong.getMaPhanCong());
			preparedStatement.setString(2, phanCong.getCongNhan().getMaCongNhan());
			preparedStatement.setString(3, phanCong.getCongViec().getMaCongViec());
			preparedStatement.setString(4, phanCong.getCongTrinh().getMaCongTrinh());
			preparedStatement.setDate(5, Utils.convertLocalDateToDate(phanCong.getNgayBatDau()));
			preparedStatement.setDate(6, Utils.convertLocalDateToDate(phanCong.getNgayKetThuc()));
			preparedStatement.setString(7, phanCong.getGhiChu());
			res = preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res > 0;
	}

	public boolean sua(PhanCong phanCong) {
		int res = 0;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement(
					"UPDATE PhanCong SET maCongNhan = ?, maCongViec = ?, maCongTrinh = ?, ngayBatDau = ?, ngayKetThuc = ?, ghiChu = ? WHERE maPhanCong = ?");
			preparedStatement.setString(1, phanCong.getCongNhan().getMaCongNhan());
			preparedStatement.setString(2, phanCong.getCongViec().getMaCongViec());
			preparedStatement.setString(3, phanCong.getCongTrinh().getMaCongTrinh());
			preparedStatement.setDate(4, Utils.convertLocalDateToDate(phanCong.getNgayBatDau()));
			preparedStatement.setDate(5, Utils.convertLocalDateToDate(phanCong.getNgayKetThuc()));
			preparedStatement.setString(6, phanCong.getGhiChu());
			preparedStatement.setString(7, phanCong.getMaPhanCong());
			res = preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res > 0;
	}

	public boolean xoa(String maPhanCong) {
		int res = 0;
		PreparedStatement preparedStatement;
		try {
			preparedStatement = ConnectDB.getConnection().prepareStatement("DELETE PhanCong WHERE maPhanCong = ?");
			preparedStatement.setString(1, maPhanCong);
			res = preparedStatement.executeUpdate();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res > 0;
	}

	public boolean phanCongDaTonTai(PhanCong phanCong) {
		boolean res = false;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement(
					"SELECT * FROM PhanCong WHERE maCongNhan = ? AND maCongViec = ? AND maCongTrinh = ? AND ngayBatDau = ?");
			preparedStatement.setString(1, phanCong.getCongNhan().getMaCongNhan());
			preparedStatement.setString(2, phanCong.getCongViec().getMaCongViec());
			preparedStatement.setString(3, phanCong.getCongTrinh().getMaCongTrinh());
			preparedStatement.setDate(4, Utils.convertLocalDateToDate(phanCong.getNgayBatDau()));
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				res = true;
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	public boolean isEditable(PhanCong phanCong) {
		boolean res = true;
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement(
					"SELECT * FROM PhanCong WHERE maCongNhan = ? AND maCongViec = ? AND maCongTrinh = ? AND ngayBatDau = ? AND maPhanCong <> ?");
			preparedStatement.setString(1, phanCong.getCongNhan().getMaCongNhan());
			preparedStatement.setString(2, phanCong.getCongViec().getMaCongViec());
			preparedStatement.setString(3, phanCong.getCongTrinh().getMaCongTrinh());
			preparedStatement.setDate(4, Utils.convertLocalDateToDate(phanCong.getNgayBatDau()));
			preparedStatement.setString(5, phanCong.getMaPhanCong());
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				res = false;
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return res;
	}

	public LocalDate ngayBatDauHopLe(String maCongNhan, LocalDate ngayBatDau) {
		LocalDate date = null;

		String sql = "SELECT MAX(ngayKetThuc) FROM PhanCong WHERE ngayBatDau <= ? AND maCongNhan = ?";
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement(sql);
			preparedStatement.setDate(1, Utils.convertLocalDateToDate(ngayBatDau));
			preparedStatement.setString(2, maCongNhan);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				Date d = resultSet.getDate(1);
				if (d != null)
					date = Utils.convertDateToLocalDate(d);
			}

			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return date;
	}

	public LocalDate ngayKetThucHopLe(String maCongNhan, LocalDate ngayBatDau, LocalDate ngayKetThuc) {
		LocalDate date = null;

		String sql = "SELECT MIN(ngayBatDau) FROM PhanCong WHERE ngayBatDau <= ? AND ngayBatDau > ? AND maCongNhan = ?";
		try {
			PreparedStatement preparedStatement = ConnectDB.getConnection().prepareStatement(sql);
			preparedStatement.setDate(1, Utils.convertLocalDateToDate(ngayKetThuc));
			preparedStatement.setDate(2, Utils.convertLocalDateToDate(ngayBatDau));
			preparedStatement.setString(3, maCongNhan);
			ResultSet resultSet = preparedStatement.executeQuery();
			System.out.println(resultSet.toString());
			System.out.println(resultSet.next());
			if (resultSet.next()) {
				Date d = resultSet.getDate(1);
				if (d != null)
					date = Utils.convertDateToLocalDate(d);
			}

			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return date;
	}

	public List<PhanCong> getAllCongTrinhTheoCongNhan(String maCongNhan, TrangThai trangThaiCT, TrangThai trangThaiCV) {
		List<PhanCong> list = new ArrayList<>();

		try {
			String whereCV = "";
			if (trangThaiCV == TrangThai.DaHoanThanh)
				whereCV = " AND ngayKetThuc < ?";
			else if (trangThaiCV == TrangThai.ChuaHoanThanh)
				whereCV = " AND ngayKetThuc >= ?";
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("SELECT * FROM PhanCong WHERE maCongNhan = ?" + whereCV);
			preparedStatement.setString(1, maCongNhan);
			if (trangThaiCV != TrangThai.TatCa)
				preparedStatement.setDate(2, Utils.convertLocalDateToDate(LocalDate.now()));
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				PhanCong phanCong = getPhanCong(resultSet);
				LocalDate dateNow = LocalDate.now();
				if (trangThaiCT == TrangThai.DaHoanThanh) {
					if (phanCong.getCongTrinh().getNgayHT().isBefore(dateNow))
						list.add(getPhanCong(resultSet));
				} else if (trangThaiCT == TrangThai.ChuaHoanThanh) {
					if (!phanCong.getCongTrinh().getNgayHT().isBefore(dateNow))
						list.add(getPhanCong(resultSet));
				} else
					list.add(getPhanCong(resultSet));
			}

			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public List<PhanCong> getAllCongNhanTheoCongTrinh(String maCongTrinh, TrangThai trangThaiCT,
			TrangThai trangThaiCV) {
		List<PhanCong> list = new ArrayList<>();

		try {
			String whereCV = "";
			if (trangThaiCV == TrangThai.DaHoanThanh)
				whereCV = " AND ngayKetThuc < ?";
			else if (trangThaiCV == TrangThai.ChuaHoanThanh)
				whereCV = " AND ngayKetThuc >= ?";
			PreparedStatement preparedStatement = ConnectDB.getConnection()
					.prepareStatement("SELECT * FROM PhanCong WHERE maCongTrinh = ?" + whereCV);
			preparedStatement.setString(1, maCongTrinh);
			if (trangThaiCV != TrangThai.TatCa)
				preparedStatement.setDate(2, Utils.convertLocalDateToDate(LocalDate.now()));
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				PhanCong phanCong = getPhanCong(resultSet);
				LocalDate dateNow = LocalDate.now();
				if (trangThaiCT == TrangThai.DaHoanThanh) {
					if (phanCong.getCongTrinh().getNgayHT().isBefore(dateNow))
						list.add(getPhanCong(resultSet));
				} else if (trangThaiCT == TrangThai.ChuaHoanThanh) {
					if (!phanCong.getCongTrinh().getNgayHT().isBefore(dateNow))
						list.add(getPhanCong(resultSet));
				} else
					list.add(getPhanCong(resultSet));
			}

			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
