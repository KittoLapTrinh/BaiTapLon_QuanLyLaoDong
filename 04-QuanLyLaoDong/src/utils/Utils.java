package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Pattern;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import connectDB.ConnectDB;
import ui.TrangChu_GUI;

public class Utils {

	public static String getVietnameseDiacriticCharacters() {
		return "ẮẰẲẴẶĂẤẦẨẪẬÂÁÀÃẢẠĐẾỀỂỄỆÊÉÈẺẼẸÍÌỈĨỊỐỒỔỖỘÔỚỜỞỠỢƠÓÒÕỎỌỨỪỬỮỰƯÚÙỦŨỤÝỲỶỸỴ";
	}
	
	/**
	 * Đóng JFrame jFrame và mở JFrame newJFrame
	 * 
	 * @param jFrame    jFrame cần đóng
	 * @param newJFrame newJFrame cần mở
	 */
	public static void openJFrame(JFrame jFrame, JFrame newJFrame) {
		newJFrame.setVisible(true);
		jFrame.setVisible(false);
		jFrame.dispose();
	}

	/**
	 * Mở JFrame trang chủ nếu là admin
	 * 
	 * @param jFrame
	 */
	public static void openTrangChu(JFrame jFrame) {
		openJFrame(jFrame, new TrangChu_GUI());
	}

	/**
	 * Mở trang chủ khi không là admin
	 * 
	 * @param jFrame
	 * @param quyen
	 * @param maNV
	 */
	public static void openTrangChu(JFrame jFrame, Quyen quyen, String maNV) {
		openJFrame(jFrame, new TrangChu_GUI());
	}

	/**
	 * Đóng JFrame
	 */
	public static void thoatUngDung() {
		int res = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn đóng ứng dụng?", "Đóng ứng dụng",
				JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
				ConvertImageToIcon.convertImageToIcon("HinhAnh\\iconThoatUngDung.png", 50, 50));
		if (res == JOptionPane.OK_OPTION) {
			ConnectDB.getInstance().disconnect();
			System.out.println("Đóng kết nối với cơ sở dữ liệu thành công!");
			System.exit(0);
		}
	}

	/**
	 * Chuyển Date sang LocalDate
	 * 
	 * @param dateToConvert
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static LocalDate convertDateToLocalDate(Date dateToConvert) {
		LocalDate localDate = LocalDate.of(dateToConvert.getYear() + 1900, dateToConvert.getMonth() + 1,
				dateToConvert.getDate());
		return localDate;
	}

	/**
	 * Chuyển LocalDate sang Date của SQL
	 * 
	 * @param localDate
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static java.sql.Date convertLocalDateToDate(LocalDate localDate) {
		return new java.sql.Date(
				new Date(localDate.getYear() - 1900, localDate.getMonthValue() - 1, localDate.getDayOfMonth())
						.getTime());
	}

	/**
	 * Định dạng Date
	 * 
	 * @param date
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static String formatDate(Date date) {
		return String.format("%d/%d/%d", date.getDate(), date.getMonth() + 1, date.getYear() + 1900);
	}

	/**
	 * Định dạng Date
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(LocalDate date) {
		return String.format("%s/%s/%d", date.getDayOfMonth() < 9 ? "0" + date.getDayOfMonth() : date.getDayOfMonth(),
				date.getMonthValue() < 9 ? "0" + date.getMonthValue() : date.getMonthValue(), date.getYear());
	}

	/**
	 * Chuyển String sang LocalDate
	 * 
	 * @param date
	 * @return
	 */
	public static LocalDate getLocalDate(String date) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String dates[] = date.split("/");
		for (int i = 0; i < 2; i++)
			if (dates[i].length() == 1)
				dates[i] = '0' + dates[i];
		if (dates[2].length() == 2)
			dates[2] = "19" + dates[2];
		return LocalDate.parse(String.format("%s/%s/%s", dates[0], dates[1], dates[2]), dtf);
	}

	public static boolean isInteger(String s) {
		return Pattern.matches("\\d{1,}", s);
	}

	public static boolean isDate(String date) {
		return Pattern.matches("((0?[0-9])|([1-2][0-9])|(3[0-1]))\\/((0?[1-9])|(1[0-2]))\\/(\\d{4})", date);
	}

	public static void selectAndScrollToRow(int row, JTable jTable) {
		jTable.setRowSelectionInterval(row, row);
		jTable.scrollRectToVisible(jTable.getCellRect(row, row, true));
	}

	public static boolean showMessAndFocus(String mess, String title, int messType, JTextField jTextField) {
		JOptionPane.showMessageDialog(null, mess, title, messType);
		jTextField.selectAll();
		jTextField.requestFocus();
		return false;
	}
}
