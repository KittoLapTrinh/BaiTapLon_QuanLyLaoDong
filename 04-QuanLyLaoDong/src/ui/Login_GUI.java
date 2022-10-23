package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.sql.SQLException;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import connectDB.ConnectDB;
import utils.ConvertImageToIcon;
import utils.Utils;

public class Login_GUI extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtTenDangNhap;
	private JTextField txtMatKhau;
	private JButton btnDangNhap;
	private JButton btnXoaTrang;
	private JButton btnThoat;

	private final String userADMIN = "admin";
	private final String passwordADMIN = "admin";

	public Login_GUI() {
		this.setTitle("Đăng nhập");
		this.setIconImage(new ImageIcon("HinhAnh\\login_icon.png").getImage());
		this.setSize(800, 400);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		JPanel container = new JPanel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics grphcs) {
				super.paintComponent(grphcs);
				Graphics2D g2d = (Graphics2D) grphcs;
				g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				GradientPaint gp = new GradientPaint(0, 0, new Color(67, 88, 207), getWidth(), getHeight(),
						Color.PINK);
				g2d.setPaint(gp);
				g2d.fillRect(0, 0, getWidth(), getHeight());
			}
		};
		container.setBackground(Color.WHITE);
		container.setLayout(null);
		this.add(container);

		JLabel imgLogin = new JLabel(ConvertImageToIcon.convertImageToIcon("HinhAnh\\iconUser.png", 200, 200));
		imgLogin.setBounds(100, 70, 200, 200);
		container.add(imgLogin);

		JLabel lblTitle = new JLabel("Đăng nhập");
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblTitle.setBounds(400, 0, 300, 100);
		lblTitle.setAlignmentX(SwingConstants.CENTER);
		container.add(lblTitle);

		JLabel lblTenDangNhap = new JLabel("Tên đăng nhập");
		JLabel lblMatKhau = new JLabel("Mật khẩu");
		txtTenDangNhap = new JTextField(20);
		txtMatKhau = new JPasswordField(20);
		btnDangNhap = new JButton("Đăng nhập",
				ConvertImageToIcon.convertImageToIcon("HinhAnh\\username_icon.jpg", 20, 20));
		btnXoaTrang = new JButton("Xóa trắng", ConvertImageToIcon.convertImageToIcon("HinhAnh\\lammoi1.png", 20, 20));
		btnThoat = new JButton("Thoát", ConvertImageToIcon.convertImageToIcon("HinhAnh\\iconThoat2.png", 20, 20));

		lblTenDangNhap.setBounds(400, 105, 90, 30);
		lblMatKhau.setBounds(400, 145, 90, 30);
		txtTenDangNhap.setBounds(500, 105, 200, 30);
		txtMatKhau.setBounds(500, 145, 200, 30);
		btnDangNhap.setBounds(400, 185, 300, 30);
		btnXoaTrang.setBounds(400, 225, 145, 30);
		btnThoat.setBounds(555, 225, 145, 30);

		btnDangNhap.setBackground(new Color(89, 183, 71));
		btnDangNhap.setForeground(Color.WHITE);
		btnXoaTrang.setBackground(new Color(0, 0, 0, 0));
		btnThoat.setBackground(new Color(0, 0, 0, 0));
		btnXoaTrang.setOpaque(false);
		btnThoat.setOpaque(false);

		container.add(lblTenDangNhap);
		container.add(lblMatKhau);
		container.add(txtTenDangNhap);
		container.add(txtMatKhau);
		container.add(btnDangNhap);
		container.add(btnXoaTrang);
		container.add(btnThoat);

		btnThoat.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnDangNhap.addActionListener(this);

//		
		txtTenDangNhap.setText("admin");
		txtMatKhau.setText("admin");

		this.addWindowListener(new utils.WindowListener());
		this.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub
				try {
					ConnectDB.getInstance().connect();
					System.out.println("Kết nối với cơ sở dữ liệu thành công!");
				} catch (SQLException e1) {
					System.out.println("Kết nối với cơ sở dữ liệu thất bại.");
					e1.printStackTrace();
				}
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}
		});
	}

	public static void main(String[] args) {
		new Login_GUI().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(btnThoat)) {
			Utils.thoatUngDung();
		} else if (o.equals(btnXoaTrang)) {
			txtTenDangNhap.setText("");
			txtMatKhau.setText("");
			txtTenDangNhap.requestFocus();
		} else if (o.equals(btnDangNhap)) {
			String tenDangNhap = txtTenDangNhap.getText().trim();
			String matKhau = txtMatKhau.getText().trim();

			Pattern regexTenDangNhap = Pattern.compile("[A-Za-z][A-Za-z0-9]*");

			if (tenDangNhap.equals("")) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập tên đăng nhập", "Dữ liệu trống",
						JOptionPane.WARNING_MESSAGE, null);
				txtTenDangNhap.setText("");
				txtTenDangNhap.requestFocus();
			} else if (!regexTenDangNhap.matcher(tenDangNhap).matches()) {
				JOptionPane.showMessageDialog(null, "Tên đăng nhập chỉ chứa ký tự chữ, số và bắt đầu bằng ký tự chữ",
						"Dữ liệu sai định dạng", JOptionPane.WARNING_MESSAGE, null);
				txtTenDangNhap.selectAll();
				txtTenDangNhap.requestFocus();
			} else if (matKhau.equals("")) {
				JOptionPane.showMessageDialog(null, "Vui lòng nhập mật khẩu", "Dữ liệu trống",
						JOptionPane.WARNING_MESSAGE, null);
				txtMatKhau.setText("");
				txtMatKhau.requestFocus();
			} else if (tenDangNhap.equals(userADMIN) && matKhau.equals(passwordADMIN)) {
				Utils.openJFrame(this, new TrangChu_GUI());
			} else {
				JOptionPane.showMessageDialog(null, "Tên đăng nhập hoặc mật khẩu không đúng.", "Lỗi đăng nhập",
						JOptionPane.ERROR_MESSAGE,
						ConvertImageToIcon.convertImageToIcon("HinhAnh\\login-error_icon.png", 20, 20));
				txtTenDangNhap.selectAll();
				txtTenDangNhap.requestFocus();
			}
		}
	}
}
