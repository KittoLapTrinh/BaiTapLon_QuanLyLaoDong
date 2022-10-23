package ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import utils.ConvertImageToIcon;
import utils.Utils;

public class UI_Navigation implements ActionListener {
	private int widthIconBtn = 20;
	private int heightIconBtn = 20;
	private JButton btnQuanLyCongNhan;
	private JButton btnQuanLyCongTrinh;
	private JButton btnPhanCongLaoDong;
	private JButton btnQuanLyCongViec;
	private JButton btnQuanLyChuyenMon;

	private JFrame frame;

	public UI_Navigation(JFrame frame) {
		this.frame = frame;
	}

	public int getHeightIconBtn() {
		return heightIconBtn;
	}

	public void setHeightIconBtn(int heightIconBtn) {
		this.heightIconBtn = heightIconBtn;
	}

	private int getMaxWidthBtn() {
		int width1 = Math.max((int) btnPhanCongLaoDong.getPreferredSize().getWidth(),
				(int) btnPhanCongLaoDong.getPreferredSize().getWidth());
		width1 = Math.max(width1, (int) btnPhanCongLaoDong.getPreferredSize().getWidth());
		width1 = Math.max(width1, (int) btnQuanLyCongNhan.getPreferredSize().getWidth());
		width1 = Math.max(width1, (int) btnQuanLyCongNhan.getPreferredSize().getWidth());
		return width1;
	}

	public Component getUINavigation() {
		JPanel box = new JPanel(null);
		box.setBackground(new Color(255, 227, 181));

		btnQuanLyCongNhan = new JButton("Quản lý công nhân",
				ConvertImageToIcon.convertImageToIcon("HinhAnh\\iconQuanLyCongNhan.png", widthIconBtn, heightIconBtn));
		btnQuanLyCongTrinh = new JButton("Quản lý công trình",
				ConvertImageToIcon.convertImageToIcon("HinhAnh\\iconQuanLyCongTrinh.png", widthIconBtn, heightIconBtn));
		btnPhanCongLaoDong = new JButton("Phân công lao động",
				ConvertImageToIcon.convertImageToIcon("HinhAnh\\icon_PhanCong.png", widthIconBtn, heightIconBtn));
		btnQuanLyCongViec = new JButton("Quản lý công việc",
				ConvertImageToIcon.convertImageToIcon("HinhAnh\\icon_CongViec.png", widthIconBtn, heightIconBtn));
		btnQuanLyChuyenMon = new JButton("Quản lý chuyên môn",
				ConvertImageToIcon.convertImageToIcon("HinhAnh\\iconPhanCongLaoDong.png", widthIconBtn, heightIconBtn));

		int x = 15, y = 50;
		int width = getMaxWidthBtn() + 20;

		btnQuanLyCongNhan.setBounds(x, y, width, 30);
		y += 45;
		btnQuanLyCongTrinh.setBounds(x, y, width, 30);
		y += 45;
		btnPhanCongLaoDong.setBounds(x, y, width, 30);
		y += 45;
		btnQuanLyCongViec.setBounds(x, y, width, 30);
		y += 45;
		btnQuanLyChuyenMon.setBounds(x, y, width, 30);

		Color color = new Color(179, 177, 249);
		btnQuanLyCongNhan.setBackground(color);
		btnQuanLyCongTrinh.setBackground(color);
		btnPhanCongLaoDong.setBackground(color);
		btnQuanLyCongViec.setBackground(color);
		btnQuanLyChuyenMon.setBackground(color);

		btnQuanLyCongNhan.setToolTipText("Quản lý công nhân");
		btnQuanLyCongTrinh.setToolTipText("Quản lý công trình");
		btnPhanCongLaoDong.setToolTipText("Phân công lao động");
		btnQuanLyCongViec.setToolTipText("Quản lý công việc");
		btnQuanLyChuyenMon.setToolTipText("Quản lý chuyên môn");

		box.add(btnQuanLyCongNhan);
		box.add(btnQuanLyCongTrinh);
		box.add(btnPhanCongLaoDong);
		box.add(btnQuanLyCongViec);
		box.add(btnQuanLyChuyenMon);

		btnQuanLyCongNhan.addActionListener(this);
		btnQuanLyCongTrinh.addActionListener(this);
		btnPhanCongLaoDong.addActionListener(this);
		btnQuanLyCongViec.addActionListener(this);
		btnQuanLyChuyenMon.addActionListener(this);

		return box;
	}

	public int getWidthNavigation() {
		return getMaxWidthBtn() + 50;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(btnQuanLyCongNhan)) {
			Utils.openJFrame(frame, new CongNhan_GUI());
		} else if (o.equals(btnQuanLyCongTrinh))
			Utils.openJFrame(frame, new CongTrinh_GUI());
		else if (o.equals(btnPhanCongLaoDong))
			Utils.openJFrame(frame, new PhanCongLaoDong_GUI());
		else if (o.equals(btnQuanLyChuyenMon))
			Utils.openJFrame(frame, new ChuyenMon_GUI());
		else if (o.equals(btnQuanLyCongViec))
			Utils.openJFrame(frame, new CongViec_GUI());
	}

}
