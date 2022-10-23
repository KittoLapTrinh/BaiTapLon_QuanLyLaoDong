package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import utils.ConvertImageToIcon;
import utils.Utils;

public class UI_JMenubar implements ActionListener {

	private JMenuItem jmnitThoat;
	private JMenuItem jmnitTroGiup;
	private JMenuItem jmnitTrangChu;
	private int widthIconMenubar = 16;
	private int heightIconMenubar = 16;
	private JMenu jmnitTimKiem;

	private JFrame jFrame;
	private JMenuItem jmnTimKiemCongNhan;
	private JMenuItem jmnTimKiemCongTrinh;

	public void setWidthIconMenubar(int width) {
		this.widthIconMenubar = width;
	}

	public void setHeightIconMenubar(int height) {
		this.heightIconMenubar = height;
	}

	public JMenuItem getJmnitThoat() {
		return jmnitThoat;
	}

	public void setJmnitThoat(JMenuItem jmnitThoat) {
		this.jmnitThoat = jmnitThoat;
	}

	public JMenuItem getJmnitTroGiup() {
		return jmnitTroGiup;
	}

	public void setJmnitTroGiup(JMenuItem jmnitTroGiup) {
		this.jmnitTroGiup = jmnitTroGiup;
	}

	public JMenuItem getJmnitTrangChu() {
		return jmnitTrangChu;
	}

	public void setJmnitTrangChu(JMenuItem jmnitTrangChu) {
		this.jmnitTrangChu = jmnitTrangChu;
	}

	public UI_JMenubar(JFrame frame) {
		this.jFrame = frame;

		JMenuBar jMenuBar = new JMenuBar();
		frame.setBackground(Color.LIGHT_GRAY);

		jmnitTrangChu = new JMenuItem("Trang chủ", ConvertImageToIcon.convertImageToIcon("HinhAnh\\iconTrangChu.png",
				widthIconMenubar, heightIconMenubar));
		jmnitTimKiem = new JMenu("Tìm kiếm");
		jmnitTimKiem.setIcon(
				ConvertImageToIcon.convertImageToIcon("HinhAnh\\iconTimKiem.png", widthIconMenubar, heightIconMenubar));
		jmnitTroGiup = new JMenuItem("Trợ giúp", ConvertImageToIcon.convertImageToIcon("HinhAnh\\iconTroGiup2.png",
				widthIconMenubar, heightIconMenubar));
		jmnitThoat = new JMenuItem("Thoát",
				ConvertImageToIcon.convertImageToIcon("HinhAnh\\iconThoat2.png", widthIconMenubar, heightIconMenubar));

		jmnitTrangChu.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		jmnitTimKiem.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		jmnitTroGiup.setBorder(BorderFactory.createLineBorder(Color.BLUE));
		jmnitThoat.setBorder(BorderFactory.createLineBorder(Color.BLUE));

		jMenuBar.setBackground(new Color(255, 202, 142));
		jmnitTrangChu.setBackground(new Color(255, 202, 142));
		jmnitTimKiem.setBackground(new Color(255, 202, 142));
		jmnitTroGiup.setBackground(new Color(255, 202, 142));
		jmnitThoat.setBackground(new Color(255, 202, 142));

		jmnitTrangChu.setPreferredSize(new Dimension(200, 30));
		jmnitTimKiem.setPreferredSize(new Dimension(200, 30));
		jmnitTroGiup.setPreferredSize(new Dimension(200, 30));
		jmnitThoat.setPreferredSize(new Dimension(200, 30));

		jmnitTrangChu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, ActionEvent.ALT_MASK));
		jmnitTimKiem.setMnemonic(KeyEvent.VK_F2);
		jmnitTroGiup.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, ActionEvent.ALT_MASK));
		jmnitThoat.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));

		jmnitTrangChu.setToolTipText("Trang chủ");
		jmnitTimKiem.setToolTipText("Tìm kiếm");
		jmnitTroGiup.setToolTipText("Trợ giúp");
		jmnitThoat.setToolTipText("Thoát");

//		Menu Tìm kiếm
		jmnTimKiemCongNhan = new JMenuItem("Tìm kiếm công nhân theo công trình");
		jmnTimKiemCongTrinh = new JMenuItem("Tìm kiếm công trình theo công nhân");

		jmnitTimKiem.add(jmnTimKiemCongNhan);
		jmnitTimKiem.add(jmnTimKiemCongTrinh);

		jmnTimKiemCongNhan.setBackground(new Color(255, 227, 181));
		jmnTimKiemCongTrinh.setBackground(new Color(255, 227, 181));

		jmnTimKiemCongNhan.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
		jmnTimKiemCongTrinh.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));

		jmnTimKiemCongNhan.setToolTipText("Tìm kiếm công nhân theo công trình");
		jmnTimKiemCongTrinh.setToolTipText("Tìm kiếm công trình theo công nhân");

		jMenuBar.add(jmnitTrangChu);
		jMenuBar.add(jmnitTimKiem);
		jMenuBar.add(jmnitTroGiup);
		jMenuBar.add(jmnitThoat);

		frame.setJMenuBar(jMenuBar);

		jmnitThoat.addActionListener(this);
		jmnitTrangChu.addActionListener(this);
		jmnTimKiemCongNhan.addActionListener(this);
		jmnTimKiemCongTrinh.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(jmnitThoat)) {
			Utils.thoatUngDung();
		} else if (o.equals(jmnitTrangChu)) {
			Utils.openTrangChu(jFrame);
		} else if (o.equals(jmnTimKiemCongNhan))
			Utils.openJFrame(jFrame, new TimKiemCongNhanTheoCongTrinh_GUI());
		else if (o.equals(jmnTimKiemCongTrinh))
			Utils.openJFrame(jFrame, new TimKiemCongTrinhTheoCongNhan_GUI());

	}
}
