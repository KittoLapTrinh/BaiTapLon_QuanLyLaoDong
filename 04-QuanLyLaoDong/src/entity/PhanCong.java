package entity;

import java.time.LocalDate;
import java.util.Objects;

public class PhanCong {
	private String maPhanCong;
	private CongNhan congNhan;
	private CongViec congViec;
	private CongTrinh congTrinh;
	private LocalDate ngayBatDau;
	private LocalDate ngayKetThuc;
	private String ghiChu;

	public PhanCong(String maPhanCong, CongNhan congNhan, CongViec congViec, CongTrinh congTrinh, LocalDate ngayBatDau,
			LocalDate ngayKetThuc, String ghiChu) {
		super();
		this.maPhanCong = maPhanCong;
		this.congNhan = congNhan;
		this.congViec = congViec;
		this.congTrinh = congTrinh;
		this.ngayBatDau = ngayBatDau;
		this.ngayKetThuc = ngayKetThuc;
		this.ghiChu = ghiChu;
	}

	public PhanCong(String maPhanCong) {
		super();
		this.maPhanCong = maPhanCong;
	}

	public PhanCong() {
		super();
	}

	public String getMaPhanCong() {
		return maPhanCong;
	}

	public void setMaPhanCong(String maPhanCong) {
		this.maPhanCong = maPhanCong;
	}

	public CongNhan getCongNhan() {
		return congNhan;
	}

	public void setCongNhan(CongNhan congNhan) {
		this.congNhan = congNhan;
	}

	public CongViec getCongViec() {
		return congViec;
	}

	public void setCongViec(CongViec congViec) {
		this.congViec = congViec;
	}

	public CongTrinh getCongTrinh() {
		return congTrinh;
	}

	public void setCongTrinh(CongTrinh congTrinh) {
		this.congTrinh = congTrinh;
	}

	public LocalDate getNgayBatDau() {
		return ngayBatDau;
	}

	public void setNgayBatDau(LocalDate ngayBatDau) {
		this.ngayBatDau = ngayBatDau;
	}

	public LocalDate getNgayKetThuc() {
		return ngayKetThuc;
	}

	public void setNgayKetThuc(LocalDate ngayKetThuc) {
		this.ngayKetThuc = ngayKetThuc;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	@Override
	public String toString() {
		return "PhanCong [maPhanCong=" + maPhanCong + ", congNhan=" + congNhan + ", congViec=" + congViec
				+ ", congTrinh=" + congTrinh + ", ngayBatDau=" + ngayBatDau + ", ngayKetThuc=" + ngayKetThuc
				+ ", ghiChu=" + ghiChu + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(maPhanCong);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PhanCong other = (PhanCong) obj;
		return Objects.equals(maPhanCong, other.maPhanCong);
	}

}
