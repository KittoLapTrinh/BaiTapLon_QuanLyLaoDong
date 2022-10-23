package entity;

import java.util.Objects;

public class ChuyenMon {
	private String maChuyenMon;
	private String tenChuyenMon;

	public ChuyenMon(String maChuyenMon, String tenChuyenMon) {
		super();
		this.maChuyenMon = maChuyenMon;
		this.tenChuyenMon = tenChuyenMon;
	}

	public ChuyenMon(String maChuyenMon) {
		super();
		this.maChuyenMon = maChuyenMon;
	}

	public ChuyenMon() {
		super();
	}

	public String getMaChuyenMon() {
		return maChuyenMon;
	}

	public void setMaChuyenMon(String maChuyenMon) {
		this.maChuyenMon = maChuyenMon;
	}

	public String getTenChuyenMon() {
		return tenChuyenMon;
	}

	public void setTenChuyenMon(String tenChuyenMon) {
		this.tenChuyenMon = tenChuyenMon;
	}

	@Override
	public String toString() {
		return "ChuyenMon [maChuyenMon=" + maChuyenMon + ", tenChuyenMon=" + tenChuyenMon + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(maChuyenMon);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChuyenMon other = (ChuyenMon) obj;
		return Objects.equals(maChuyenMon, other.maChuyenMon);
	}

}
