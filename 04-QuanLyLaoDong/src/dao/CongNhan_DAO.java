package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;
import entity.ChuyenMon;
import entity.CongNhan;
import utils.Utils;

public class CongNhan_DAO {

	private ChuyenMon_DAO chuyenMon_DAO;

	public CongNhan_DAO() {
		chuyenMon_DAO = new ChuyenMon_DAO();
	}

	private CongNhan getCongNhan(ResultSet rs) throws SQLException {
		CongNhan nv = new CongNhan();
		nv.setMaCongNhan(rs.getString("maCongNhan"));
		nv.setHo(rs.getString("ho"));
		nv.setTen(rs.getString("ten"));
		nv.setGioiTinh(rs.getBoolean("gioiTinh"));
		nv.setNgaySinh(Utils.convertDateToLocalDate(rs.getDate("ngaySinh")));
		nv.setDiaChi(rs.getString("diaChi"));
		nv.setSoDienThoai(rs.getString("soDienThoai"));
		nv.setCccd(rs.getString("cccd"));
		nv.setBacTho(rs.getString("bacTho"));
		nv.setTrinhDo(rs.getString("trinhDo"));
		String maChuyenMon = rs.getString("maChuyenMon");
		nv.setChuyenMon(new ChuyenMon(maChuyenMon, chuyenMon_DAO.getTenChuyenMon(maChuyenMon)));
		nv.setNamKinhNghiem(rs.getInt("namKinhNghiem"));
		return nv;
	}

	/**
	 * Lấy tất cả công nhan
	 * 
	 * @return
	 */
	public List<CongNhan> getAllCongNhan() {
		List<CongNhan> dsNV = new ArrayList<CongNhan>();
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			String sql = "Select * from CongNhan";
			Statement sta = con.createStatement();
			ResultSet rs = sta.executeQuery(sql);
			while (rs.next())
				dsNV.add(getCongNhan(rs));
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return dsNV;
	}

	/**
	 * Lấy công nhan theo mã công nhan
	 * 
	 * @param ma
	 * @return
	 */
	public CongNhan getCongNhanTheoMa(String ma) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		try {
			PreparedStatement preparedStatement = con.prepareStatement("Select * from CongNhan where maCongNhan = ?");
			preparedStatement.setString(1, ma);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next())
				return getCongNhan(rs);
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Thêm công nhan
	 * 
	 * @param nv
	 * @return
	 */
	public boolean them(CongNhan nv) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement psta = null;
		int n = 0;
		try {
			psta = con.prepareStatement("insert into CongNhan values(?,?,?,?,?,?,?,?,?,?,?,?)");
			psta.setString(1, nv.getMaCongNhan());
			psta.setString(2, nv.getHo());
			psta.setString(3, nv.getTen());
			psta.setBoolean(4, nv.isGioiTinh());
			psta.setDate(5, Utils.convertLocalDateToDate(nv.getNgaySinh()));
			psta.setString(6, nv.getDiaChi());
			psta.setString(7, nv.getSoDienThoai());
			psta.setString(8, nv.getCccd());
			psta.setString(9, nv.getBacTho());
			psta.setString(10, nv.getTrinhDo());
			psta.setString(11, nv.getChuyenMon().getMaChuyenMon());
			psta.setInt(12, nv.getNamKinhNghiem());
			n = psta.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				psta.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return n > 0;
	}

	/**
	 * Xóa công nhan theo mã
	 * 
	 * @param ma
	 * @return
	 */
	public boolean xoa(String ma) {
		ConnectDB.getInstance();
		Connection con = ConnectDB.getConnection();
		PreparedStatement stmt = null;
		int n = 0;
		try {
			stmt = con.prepareStatement("delete CongNhan where maCongNhan = ?");
			stmt.setString(1, ma);
			n = stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return n > 0;
	}

	/**
	 * Sửa thông tin công nhan
	 * 
	 * @param nv
	 * @return
	 */
	public boolean sua(CongNhan nv) {
		int res = 0;
		Connection con = ConnectDB.getConnection();
		PreparedStatement pre = null;
		try {
			pre = con.prepareStatement(
					"UPDATE CongNhan SET ho = ?,ten = ?, gioiTinh = ?, ngaySinh =?, diaChi = ?, soDienThoai = ?, cccd = ?, bacTho = ?, trinhDo = ?, maChuyenMon = ?, namKinhNghiem = ? WHERE maCongNhan = ?");
			pre.setString(1, nv.getHo());
			pre.setString(2, nv.getTen());
			pre.setBoolean(3, nv.isGioiTinh());
			pre.setDate(4, (Date) Utils.convertLocalDateToDate(nv.getNgaySinh()));
			pre.setString(5, nv.getDiaChi());
			pre.setString(6, nv.getSoDienThoai());
			pre.setString(7, nv.getCccd());
			pre.setString(8, nv.getBacTho());
			pre.setString(9, nv.getTrinhDo());
			pre.setString(10, nv.getChuyenMon().getMaChuyenMon());
			pre.setInt(11, nv.getNamKinhNghiem());
			res = pre.executeUpdate();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pre.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return res > 0;
	}

	public boolean isCCCDTonTai(String cccd) {
		List<String> list = new ArrayList<>();
		try {
			Statement statement = ConnectDB.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT cccd FROM CongNhan");
			while (resultSet.next())
				list.add(resultSet.getString(1));
			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list.contains(cccd);
	}
}
