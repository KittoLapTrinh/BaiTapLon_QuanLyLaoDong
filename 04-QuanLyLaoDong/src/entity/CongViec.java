package entity;

import java.util.Objects;

public class CongViec {
	private String maCongViec;
	private String tenCongViec;

	public String getMaCongViec() {
		return maCongViec;
	}

	public void setMaCongViec(String maCongViec) {
		this.maCongViec = maCongViec;
	}

	public String getTenCongViec() {
		return tenCongViec;
	}

	public void setTenCongViec(String tenCongViec) {
		this.tenCongViec = tenCongViec;
	}

	public CongViec(String maCongViec, String tenCongViec) {
		super();
		this.maCongViec = maCongViec;
		this.tenCongViec = tenCongViec;
	}

	public CongViec(String maCongViec) {
		super();
		this.maCongViec = maCongViec;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maCongViec);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CongViec other = (CongViec) obj;
		return Objects.equals(maCongViec, other.maCongViec);
	}

	@Override
	public String toString() {
		return "CongViec [maCongViec=" + maCongViec + ", tenCongViec=" + tenCongViec + "]";
	}

}
