package entity;

import java.time.LocalDate;
import java.util.Objects;

public class CongTrinh {
	private String maCongTrinh;
	private String tenCongTrinh;
	private String diaDiem;
	private String chuDauTu;
	private String giayPhepXD;
	private LocalDate ngayCap;
	private LocalDate ngayKhoiCong;
	private LocalDate ngayHT;

	public CongTrinh(String maCongTrinh, String tenCongTrinh, String diaDiem, String chuDauTu, String giayPhepXD,
			LocalDate ngayCap, LocalDate ngayKhoiCong, LocalDate ngayHT) {
		super();
		this.maCongTrinh = maCongTrinh;
		this.tenCongTrinh = tenCongTrinh;
		this.diaDiem = diaDiem;
		this.chuDauTu = chuDauTu;
		this.giayPhepXD = giayPhepXD;
		this.ngayCap = ngayCap;
		this.ngayKhoiCong = ngayKhoiCong;
		this.ngayHT = ngayHT;
	}

	public CongTrinh(String maCongTrinh) {
		super();
		this.maCongTrinh = maCongTrinh;
	}

	public CongTrinh() {
		super();
	}

	public String getMaCongTrinh() {
		return maCongTrinh;
	}

	public void setMaCongTrinh(String maCongTrinh) {
		this.maCongTrinh = maCongTrinh;
	}

	public String getTenCongTrinh() {
		return tenCongTrinh;
	}

	public void setTenCongTrinh(String tenCongTrinh) {
		this.tenCongTrinh = tenCongTrinh;
	}

	public String getDiaDiem() {
		return diaDiem;
	}

	public void setDiaDiem(String diaDiem) {
		this.diaDiem = diaDiem;
	}

	public String getGiayPhepXD() {
		return giayPhepXD;
	}

	public void setGiayPhepXD(String giayPhepXD) {
		this.giayPhepXD = giayPhepXD;
	}

	public LocalDate getNgayCap() {
		return ngayCap;
	}

	public void setNgayCap(LocalDate ngayCap) {
		this.ngayCap = ngayCap;
	}

	public LocalDate getNgayKhoiCong() {
		return ngayKhoiCong;
	}

	public void setNgayKhoiCong(LocalDate ngayKhoiCong) {
		this.ngayKhoiCong = ngayKhoiCong;
	}

	public LocalDate getNgayHT() {
		return ngayHT;
	}

	public void setNgayHT(LocalDate ngayHT) {
		this.ngayHT = ngayHT;
	}

	public String getChuDauTu() {
		return chuDauTu;
	}

	public void setChuDauTu(String chuDauTu) {
		this.chuDauTu = chuDauTu;
	}

	@Override
	public String toString() {
		return "CongTrinh [maCongTrinh=" + maCongTrinh + ", tenCongTrinh=" + tenCongTrinh + ", diaDiem=" + diaDiem
				+ ", chuDauTu=" + chuDauTu + ", giayPhepXD=" + giayPhepXD + ", ngayCap=" + ngayCap + ", ngayKhoiCong="
				+ ngayKhoiCong + ", ngayHT=" + ngayHT + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(maCongTrinh);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CongTrinh other = (CongTrinh) obj;
		return Objects.equals(maCongTrinh, other.maCongTrinh);
	}

}
