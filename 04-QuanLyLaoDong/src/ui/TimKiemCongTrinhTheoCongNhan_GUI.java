package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import dao.CongNhan_DAO;
import dao.PhanCong_DAO;
import entity.CongNhan;
import entity.PhanCong;
import utils.ConvertImageToIcon;
import utils.HandleButton;
import utils.TrangThai;
import utils.Utils;
import utils.XuLySuKienNutPhanTrang;

public class TimKiemCongTrinhTheoCongNhan_GUI extends JFrame implements ItemListener, WindowListener, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<String> cboCongNhan;
	private JRadioButton radTatCaCongTrinh;
	private JRadioButton radCongTrinhHT;
	private JRadioButton radCongTrinhChuaHT;
	private JRadioButton radTatCaCongViec;
	private JRadioButton radCongViecHT;
	private JRadioButton radCongViecChuaHT;
	private JButton btnTimKiem;
	private JButton btnXoaTrang;
	private JTable table;
	private DefaultTableModel modelTable;

	private CongNhan_DAO congNhan_DAO;
	private PhanCong_DAO phanCong_DAO;
	private ButtonGroup btnGroupCongTrinh;
	private ButtonGroup btnGroupCongViec;
	private JTextField txtLabel;
	private JButton btnNext;
	private JButton btnFirst;
	private JButton btnPrev;
	private JButton btnLast;
	private XuLySuKienNutPhanTrang btnPhanTrang;

	public TimKiemCongTrinhTheoCongNhan_GUI() {
		getContentPane().setBackground(SystemColor.control);
		this.setTitle("Tìm kiếm công trình theo công nhân");
		this.setSize(1000, 650);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(244, 164, 96));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		new UI_JMenubar(this);
		congNhan_DAO = new CongNhan_DAO();
		phanCong_DAO = new PhanCong_DAO();

		JLabel lblTieuDe = new JLabel(this.getTitle().toUpperCase());
		lblTieuDe.setVerticalAlignment(SwingConstants.TOP);
		lblTieuDe.setForeground(SystemColor.controlDkShadow);
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTieuDe);

		JPanel pBox = new JPanel();
		pBox.setBackground(new Color(245, 222, 179));
		pBox.setBorder(BorderFactory.createTitledBorder("Thông tin của công nhân"));

		JPanel pTable = new JPanel();
		pTable.setBackground(new Color(245, 222, 179));
		pTable.setBorder(BorderFactory.createTitledBorder("Danh sách các công trình"));

		JPanel pTimKiem = new JPanel();
		pTimKiem.setBackground(new Color(245, 222, 179));
		pTimKiem.setBorder(BorderFactory.createTitledBorder(""));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(lblTieuDe,
										GroupLayout.DEFAULT_SIZE, 972, Short.MAX_VALUE))
								.addComponent(pBox, GroupLayout.PREFERRED_SIZE, 978, GroupLayout.PREFERRED_SIZE)
								.addComponent(pTable, GroupLayout.PREFERRED_SIZE, 977, GroupLayout.PREFERRED_SIZE)
								.addComponent(pTimKiem, GroupLayout.PREFERRED_SIZE, 977, GroupLayout.PREFERRED_SIZE))
						.addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(lblTieuDe, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(pBox, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(pTable, GroupLayout.PREFERRED_SIZE, 250, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(pTimKiem, GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)));

//		Footer
		pTimKiem.setLayout(null);

		txtLabel = new JTextField();
		txtLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
		txtLabel.setEditable(false);
		txtLabel.setHorizontalAlignment(JTextField.CENTER);
		txtLabel.setBounds(450, 15, 100, 30);
		pTimKiem.add(txtLabel);

		btnFirst = new JButton(ConvertImageToIcon.convertImageToIcon("HinhAnh\\iconFirst.png", 25, 25));
		btnPrev = new JButton(ConvertImageToIcon.convertImageToIcon("HinhAnh\\iconPrev.png", 25, 25));
		btnNext = new JButton(ConvertImageToIcon.convertImageToIcon("HinhAnh\\iconNext.png", 25, 25));
		btnLast = new JButton(ConvertImageToIcon.convertImageToIcon("HinhAnh\\iconLast.png", 25, 25));

		btnFirst.setBounds(370, 15, 30, 30);
		btnPrev.setBounds(410, 15, 30, 30);
		btnNext.setBounds(560, 15, 30, 30);
		btnLast.setBounds(600, 15, 30, 30);

		btnFirst.setToolTipText("Công trình đầu tiên");
		btnPrev.setToolTipText("Công trình trước");
		btnNext.setToolTipText("Công trình sau");
		btnLast.setToolTipText("Công trình cuối cùng");

		pTimKiem.add(btnFirst);
		pTimKiem.add(btnPrev);
		pTimKiem.add(btnNext);
		pTimKiem.add(btnLast);

//		Content		
		pTable.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 20, 962, 220);
		pTable.add(scrollPane);

		table = new JTable() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		table.setModel(modelTable = new DefaultTableModel(new Object[][] {}, new String[] { "Mã công trình",
				"Tên công trình", "Tên công việc", "Ngày bắt đầu công việc", "Ngày kết thúc công việc", "Ghi chú" }));
		scrollPane.setViewportView(table);

//		Header
		pBox.setLayout(null);

		JLabel lblCongNhan = new JLabel("Công nhân:");
		lblCongNhan.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblCongNhan.setBounds(205, 30, 200, 20);
		pBox.add(lblCongNhan);

		cboCongNhan = new JComboBox<>();
		cboCongNhan.setBounds(405, 30, 390, 20);
		pBox.add(cboCongNhan);

		JLabel lblCongTrinh = new JLabel("Trạng thái công trình:");
		lblCongTrinh.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblCongTrinh.setBounds(205, 65, 200, 20);
		pBox.add(lblCongTrinh);

		radTatCaCongTrinh = new JRadioButton("Tất cả");
		radCongTrinhHT = new JRadioButton("Đã hoàn thành");
		radCongTrinhChuaHT = new JRadioButton("Chưa hoàn thành");

		btnGroupCongTrinh = new ButtonGroup();
		btnGroupCongTrinh.add(radTatCaCongTrinh);
		btnGroupCongTrinh.add(radCongTrinhHT);
		btnGroupCongTrinh.add(radCongTrinhChuaHT);

		radTatCaCongTrinh.setBounds(405, 65, 130, 20);
		radCongTrinhHT.setBounds(535, 65, 130, 20);
		radCongTrinhChuaHT.setBounds(665, 65, 130, 20);

		radTatCaCongTrinh.setBackground(new Color(245, 222, 179));
		radCongTrinhHT.setBackground(new Color(245, 222, 179));
		radCongTrinhChuaHT.setBackground(new Color(245, 222, 179));

		pBox.add(radTatCaCongTrinh);
		pBox.add(radCongTrinhHT);
		pBox.add(radCongTrinhChuaHT);

		JLabel lblCongViec = new JLabel("Trạng thái công việc:");
		lblCongViec.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblCongViec.setBounds(205, 100, 200, 20);
		pBox.add(lblCongViec);

		radTatCaCongViec = new JRadioButton("Tất cả");
		radCongViecHT = new JRadioButton("Đã hoàn thành");
		radCongViecChuaHT = new JRadioButton("Chưa hoàn thành");

		btnGroupCongViec = new ButtonGroup();
		btnGroupCongViec.add(radTatCaCongViec);
		btnGroupCongViec.add(radCongViecHT);
		btnGroupCongViec.add(radCongViecChuaHT);

		radTatCaCongViec.setBounds(405, 100, 130, 20);
		radCongViecHT.setBounds(535, 100, 130, 20);
		radCongViecChuaHT.setBounds(665, 100, 130, 20);

		radTatCaCongViec.setBackground(new Color(245, 222, 179));
		radCongViecHT.setBackground(new Color(245, 222, 179));
		radCongViecChuaHT.setBackground(new Color(245, 222, 179));

		pBox.add(radTatCaCongViec);
		pBox.add(radCongViecHT);
		pBox.add(radCongViecChuaHT);

		btnTimKiem = new JButton();
		btnXoaTrang = new JButton();

		HandleButton.setBtnTimKiem(btnTimKiem);
		HandleButton.setBtnLamMoi(btnXoaTrang);

		btnTimKiem.setForeground(Color.BLACK);
		btnXoaTrang.setForeground(Color.BLACK);

		btnTimKiem.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnXoaTrang.setFont(new Font("Tahoma", Font.BOLD, 14));

		btnTimKiem.setBackground(SystemColor.scrollbar);
		btnXoaTrang.setBackground(SystemColor.scrollbar);

		btnTimKiem.setBounds(300, 140, 150, 30);
		btnXoaTrang.setBounds(550, 140, 150, 30);

		pBox.add(btnTimKiem);
		pBox.add(btnXoaTrang);

		getContentPane().setLayout(groupLayout);

		btnPhanTrang = new XuLySuKienNutPhanTrang(txtLabel, btnFirst, btnPrev, btnNext, btnLast, table);
		this.addWindowListener(this);
		this.addWindowListener(new utils.WindowListener());
		radCongTrinhHT.addItemListener(this);
		btnTimKiem.addActionListener(this);
		btnXoaTrang.addActionListener(this);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		// TODO Auto-generated method stub
		if (radCongTrinhHT.isSelected()) {
			radCongViecHT.setSelected(true);
			radTatCaCongViec.setEnabled(false);
			radCongViecChuaHT.setEnabled(false);
		} else {
			radCongViecHT.setSelected(false);
			radTatCaCongViec.setEnabled(true);
			radCongViecChuaHT.setEnabled(true);
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		List<CongNhan> listCongNhan = congNhan_DAO.getAllCongNhan();

		listCongNhan.forEach(congNhan -> cboCongNhan
				.addItem(String.format("%s - %s %s", congNhan.getMaCongNhan(), congNhan.getHo(), congNhan.getTen())));
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
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();

		emptyTable();
		btnPhanTrang.setSoTrang(0);

		if (o.equals(btnXoaTrang)) {
			cboCongNhan.setSelectedIndex(-1);
			btnGroupCongTrinh.clearSelection();
			btnGroupCongViec.clearSelection();
		} else if (o.equals(btnTimKiem)) {
			if (cboCongNhan.getSelectedIndex() == -1) {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn công nhân cần tìm", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
				cboCongNhan.requestFocus();
			} else if (!radCongTrinhChuaHT.isSelected() && !radCongTrinhHT.isSelected()
					&& !radTatCaCongTrinh.isSelected()) {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn trạng thái công trình cần tìm", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
			} else if (!radCongViecChuaHT.isSelected() && !radCongViecHT.isSelected()
					&& !radTatCaCongViec.isSelected()) {
				JOptionPane.showMessageDialog(null, "Vui lòng chọn trạng thái công việc cần tìm", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
			} else {
				String congNhan[] = cboCongNhan.getSelectedItem().toString().split(" - ");
				String maCongNhan = congNhan[0];
				String tenCongNhan = congNhan[1];
				List<PhanCong> list = phanCong_DAO.getAllCongTrinhTheoCongNhan(maCongNhan,
						getTrangThai(radTatCaCongTrinh, radCongTrinhHT), getTrangThai(radTatCaCongViec, radCongViecHT));

				if (list.size() == 0) {
					JOptionPane.showMessageDialog(null,
							String.format("Công nhân %s chưa được phân công vào công trình nào", tenCongNhan),
							"Tìm kiếm", JOptionPane.WARNING_MESSAGE);
				} else {
					list.forEach(phanCong -> themCongTrinhVaoTable(phanCong));
					btnPhanTrang.setSoTrang(list.size());
				}
			}
		}
	}

	private void themCongTrinhVaoTable(PhanCong phanCong) {
		modelTable.addRow(
				new Object[] { phanCong.getCongTrinh().getMaCongTrinh(), phanCong.getCongTrinh().getTenCongTrinh(),
						phanCong.getCongViec().getTenCongViec(), Utils.formatDate(phanCong.getNgayBatDau()),
						Utils.formatDate(phanCong.getNgayKetThuc()), phanCong.getGhiChu() });
	}

	private void emptyTable() {
		while (table.getRowCount() > 0)
			modelTable.removeRow(0);
	}

	private TrangThai getTrangThai(JRadioButton tatCa, JRadioButton daHoanThanh) {
		if (tatCa.isSelected())
			return TrangThai.TatCa;
		if (daHoanThanh.isSelected())
			return TrangThai.DaHoanThanh;
		return TrangThai.ChuaHoanThanh;
	}
}
