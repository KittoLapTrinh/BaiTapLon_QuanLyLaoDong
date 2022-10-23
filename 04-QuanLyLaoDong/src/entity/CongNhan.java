package entity;

import java.time.LocalDate;
import java.util.Objects;

public class CongNhan {
	private String maCongNhan;
	private String ho;
	private String ten;
	private boolean gioiTinh;
	private LocalDate ngaySinh;
	private String diaChi;
	private String soDienThoai;
	private String cccd;
	private String bacTho;
	private String trinhDo;
	private ChuyenMon chuyenMon;
	private int namKinhNghiem;

	public CongNhan(String maCongNhan, String ho, String ten, boolean gioiTinh, LocalDate ngaySinh, String diaChi,
			String soDienThoai, String cccd, String bacTho, String trinhDo, ChuyenMon chuyenMon, int namKinhNghiem) {
		super();
		this.maCongNhan = maCongNhan;
		this.ho = ho;
		this.ten = ten;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.diaChi = diaChi;
		this.soDienThoai = soDienThoai;
		this.cccd = cccd;
		this.bacTho = bacTho;
		this.trinhDo = trinhDo;
		this.chuyenMon = chuyenMon;
		this.namKinhNghiem = namKinhNghiem;
	}

	public CongNhan(String maCongNhan) {
		super();
		this.maCongNhan = maCongNhan;
	}

	public CongNhan() {
		super();
	}

	public String getMaCongNhan() {
		return maCongNhan;
	}

	public void setMaCongNhan(String maCongNhan) {
		this.maCongNhan = maCongNhan;
	}

	public String getHo() {
		return ho;
	}

	public void setHo(String ho) {
		this.ho = ho;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public boolean isGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public LocalDate getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public String getCccd() {
		return cccd;
	}

	public void setCccd(String cccd) {
		this.cccd = cccd;
	}

	public String getBacTho() {
		return bacTho;
	}

	public void setBacTho(String bacTho) {
		this.bacTho = bacTho;
	}

	public String getTrinhDo() {
		return trinhDo;
	}

	public void setTrinhDo(String trinhDo) {
		this.trinhDo = trinhDo;
	}

	public ChuyenMon getChuyenMon() {
		return chuyenMon;
	}

	public void setChuyenMon(ChuyenMon chuyenMon) {
		this.chuyenMon = chuyenMon;
	}

	public int getNamKinhNghiem() {
		return namKinhNghiem;
	}

	public void setNamKinhNghiem(int namKinhNghiem) {
		this.namKinhNghiem = namKinhNghiem;
	}

	@Override
	public String toString() {
		return "CongNhan [maCongNhan=" + maCongNhan + ", ho=" + ho + ", ten=" + ten + ", gioiTinh=" + gioiTinh
				+ ", ngaySinh=" + ngaySinh + ", diaChi=" + diaChi + ", soDienThoai=" + soDienThoai + ", cccd=" + cccd
				+ ", bacTho=" + bacTho + ", trinhDo=" + trinhDo + ", chuyenMon=" + chuyenMon + ", namKinhNghiem="
				+ namKinhNghiem + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(maCongNhan);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CongNhan other = (CongNhan) obj;
		return Objects.equals(maCongNhan, other.maCongNhan);
	}

}
