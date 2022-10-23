package utils;

import javax.swing.JButton;

public class HandleButton {

	/**
	 * Đặt text và icon cho Button Hủy
	 * 
	 * @param jButton
	 */
	public static void setBtnHuy(JButton jButton) {
		jButton.setText("Hủy");
		jButton.setIcon(ConvertImageToIcon.convertImageToIcon("HinhAnh\\icon_Huy1.png", 20, 20));
	}

	public static boolean isBtnHuy(JButton jButton) {
		return jButton.getText().equals("Hủy");
	}

	public static boolean isBtnXoa(JButton jButton) {
		return jButton.getText().equals("Xóa");
	}

	/**
	 * Đặt text và icon cho Button Thêm
	 * 
	 * @param jButton
	 */
	public static void setBtnThem(JButton jButton) {
		jButton.setText("Thêm");
		jButton.setIcon(ConvertImageToIcon.convertImageToIcon("HinhAnh\\them.png", 20, 20));
	}

	/**
	 * Đặt text và icon cho Button Sửa
	 * 
	 * @param jButton
	 */
	public static void setBtnSua(JButton jButton) {
		jButton.setText("Sửa");
		jButton.setIcon(ConvertImageToIcon.convertImageToIcon("HinhAnh\\sua.png", 20, 20));
	}

	/**
	 * Đặt text và icon cho Button Lưu
	 * 
	 * @param jButton
	 */
	public static void setBtnLuu(JButton jButton) {
		jButton.setText("Lưu");
		jButton.setIcon(ConvertImageToIcon.convertImageToIcon("HinhAnh\\icon_Luu.png", 20, 20));
	}

	/**
	 * Đặt text và icon cho Button Xóa
	 * 
	 * @param jButton
	 */
	public static void setBtnXoa(JButton jButton) {
		jButton.setText("Xóa");
		jButton.setIcon(ConvertImageToIcon.convertImageToIcon("HinhAnh\\xoa.png", 20, 20));
	}

	public static void setBtnLamMoi(JButton jButton) {
		jButton.setText("Làm mới");
		jButton.setIcon(ConvertImageToIcon.convertImageToIcon("HinhAnh/lm.png", 20, 20));
	}

	public static void setBtnTimKiem(JButton jButton) {
		jButton.setText("Tìm");
		jButton.setIcon(ConvertImageToIcon.convertImageToIcon("HinhAnh/tim.png", 20, 20));
	}
}
