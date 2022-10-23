package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.CongNhan_DAO;
import dao.CongTrinh_DAO;
import dao.CongViec_DAO;
import dao.PhanCong_DAO;
import entity.CongNhan;
import entity.CongTrinh;
import entity.CongViec;
import entity.PhanCong;
import utils.HandleButton;
import utils.Utils;

public class PhanCongLaoDong_GUI extends JFrame implements WindowListener, MouseListener, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JPanel contentPane;
	private JComboBox<String> cboTimKiem;
	private JTextField txtMaPC;
	private JTextField txtGhiChu;
	private JTextField txtNgayBD;
	private JTextField txtNgayKT;
	private JComboBox<String> cboCongNhan;
	private JComboBox<String> cboCongViec;
	private JComboBox<String> cboCongTrinh;

	private PhanCong_DAO phanCong_DAO;
	private CongNhan_DAO congNhan_DAO;
	private CongViec_DAO congViec_DAO;
	private CongTrinh_DAO congTrinh_DAO;
	private DefaultTableModel modelTable;
	private JButton btnLamMoi;
	private JButton btnSua;
	private JButton btnXoa;
	private JButton btnThem;
	private JButton btnTim;

	public PhanCongLaoDong_GUI() {
		phanCong_DAO = new PhanCong_DAO();
		congNhan_DAO = new CongNhan_DAO();
		congViec_DAO = new CongViec_DAO();
		congTrinh_DAO = new CongTrinh_DAO();

		getContentPane().setBackground(SystemColor.control);
		this.setTitle("Quản lý phân công lao động");
		this.setIconImage(new ImageIcon("HinhAnh\\icon_PhanCong.png").getImage());
		this.setSize(1100, 650);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(244, 164, 96));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		new UI_JMenubar(this);
		JPanel container = new JPanel();
		getContentPane().add(container);

		JLabel lblTieuDe = new JLabel("PHÂN CÔNG LAO ĐỘNG");
		lblTieuDe.setVerticalAlignment(SwingConstants.TOP);
		lblTieuDe.setForeground(SystemColor.controlDkShadow);
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 18));

		JPanel pBox = new JPanel();
		pBox.setBorder(BorderFactory.createTitledBorder("Thông tin phân công lao động"));
		pBox.setBackground(new Color(245, 222, 179));

		JPanel pTable = new JPanel();
		pTable.setBorder(BorderFactory.createTitledBorder("Danh sách phân công lao động"));
		pTable.setBackground(new Color(245, 222, 179));

		JPanel pTimKiem = new JPanel();
		pTimKiem.setBorder(BorderFactory.createTitledBorder("Tìm kiếm phân công lao động"));
		pTimKiem.setBackground(new Color(245, 222, 179));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
				.addComponent(pTable, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 986, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(lblTieuDe, GroupLayout.DEFAULT_SIZE, 966, Short.MAX_VALUE).addContainerGap())
				.addComponent(pBox, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 986, Short.MAX_VALUE)
				.addComponent(pTimKiem, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 986, Short.MAX_VALUE));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(lblTieuDe, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(pBox, GroupLayout.PREFERRED_SIZE, 227, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(pTable, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(pTimKiem, GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)));
		pTimKiem.setLayout(null);

//		Tìm kiếm
		JLabel lblTimKiem = new JLabel("Chọn mã phân công cần tìm:");
		lblTimKiem.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblTimKiem.setBounds(50, 34, 193, 20);
		pTimKiem.add(lblTimKiem);

		cboTimKiem = new JComboBox<>();
		cboTimKiem.setBounds(264, 34, 101, 20);
		pTimKiem.add(cboTimKiem);

		btnTim = new JButton();
		HandleButton.setBtnTimKiem(btnTim);
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTim.setForeground(Color.BLACK);
		btnTim.setBackground(SystemColor.scrollbar);
		btnTim.setBounds(375, 29, 101, 30);
		pTimKiem.add(btnTim);
		pTable.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 20, 1055, 159);
		pTable.add(scrollPane);

//		Table
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
		table.setModel(modelTable = new DefaultTableModel(new Object[][] {}, new String[] { "Mã phân công", "Công nhân",
				"Công việc", "Công trình", "Ngày bắt đầu", "Ngày kết thúc", "Ghi chú" }));
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.RIGHT);
		table.getColumnModel().getColumn(4).setCellRenderer(dtcr);
		table.getColumnModel().getColumn(5).setCellRenderer(dtcr);
		scrollPane.setViewportView(table);
		pBox.setLayout(null);

//		Button
		btnThem = new JButton();
		HandleButton.setBtnThem(btnThem);
		btnThem.setForeground(Color.BLACK);
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnThem.setBackground(SystemColor.scrollbar);
		btnThem.setBounds(131, 171, 100, 30);
		pBox.add(btnThem);

		btnXoa = new JButton();
		HandleButton.setBtnXoa(btnXoa);
		btnXoa.setForeground(Color.BLACK);
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnXoa.setBackground(SystemColor.scrollbar);
		btnXoa.setBounds(362, 171, 100, 30);
		pBox.add(btnXoa);

		btnSua = new JButton();
		HandleButton.setBtnSua(btnSua);
		btnSua.setForeground(Color.BLACK);
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSua.setBackground(SystemColor.scrollbar);
		btnSua.setBounds(593, 171, 100, 30);
		pBox.add(btnSua);

		btnLamMoi = new JButton();
		HandleButton.setBtnLamMoi(btnLamMoi);
		btnLamMoi.setForeground(Color.BLACK);
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLamMoi.setBackground(SystemColor.scrollbar);
		btnLamMoi.setBounds(824, 171, 124, 30);
		pBox.add(btnLamMoi);

		JLabel lblMaPC = new JLabel("Mã phân công:");
		lblMaPC.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblMaPC.setBounds(100, 28, 110, 19);
		pBox.add(lblMaPC);

		txtMaPC = new JTextField();
		txtMaPC.setBounds(220, 28, 280, 19);
		pBox.add(txtMaPC);
		txtMaPC.setColumns(10);

		JLabel lblTenCN = new JLabel("Công nhân:");
		lblTenCN.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblTenCN.setBounds(600, 28, 110, 19);
		pBox.add(lblTenCN);

		cboCongNhan = new JComboBox<>();
		cboCongNhan.setBounds(720, 28, 280, 19);
		pBox.add(cboCongNhan);

		JLabel lblCongViec = new JLabel("Công việc:");
		lblCongViec.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblCongViec.setBounds(100, 55, 110, 19);
		pBox.add(lblCongViec);

		cboCongViec = new JComboBox<>();
		cboCongViec.setBounds(220, 55, 280, 19);
		pBox.add(cboCongViec);

		JLabel lblCongTrinh = new JLabel("Công trình:");
		lblCongTrinh.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblCongTrinh.setBounds(600, 55, 110, 19);
		pBox.add(lblCongTrinh);

		cboCongTrinh = new JComboBox<>();
		cboCongTrinh.setBounds(720, 55, 280, 19);
		pBox.add(cboCongTrinh);

		JLabel lblNgayBD = new JLabel("Ngày bắt đầu:");
		lblNgayBD.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNgayBD.setBounds(100, 82, 110, 19);
		pBox.add(lblNgayBD);

		txtNgayBD = new JTextField();
		txtNgayBD.setColumns(10);
		txtNgayBD.setBounds(220, 82, 280, 19);
		pBox.add(txtNgayBD);

		JLabel lblNgayKT = new JLabel("Ngày kết thúc:");
		lblNgayKT.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNgayKT.setBounds(600, 82, 110, 19);
		pBox.add(lblNgayKT);

		txtNgayKT = new JTextField();
		txtNgayKT.setColumns(10);
		txtNgayKT.setBounds(720, 82, 280, 19);
		pBox.add(txtNgayKT);

		JLabel lblGhiChu = new JLabel("Ghi chú:");
		lblGhiChu.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblGhiChu.setBounds(100, 109, 110, 19);
		pBox.add(lblGhiChu);

		txtGhiChu = new JTextField();
		txtGhiChu.setColumns(10);
		txtGhiChu.setBounds(220, 109, 280, 19);
		pBox.add(txtGhiChu);
		getContentPane().setLayout(groupLayout);

		cboCongNhan.setEnabled(false);
		cboCongTrinh.setEnabled(false);
		cboCongViec.setEnabled(false);

		this.addWindowListener(this);
		this.addWindowListener(new utils.WindowListener());
		table.addMouseListener(this);
		btnTim.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnSua.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
	}

	private void handleBtnTim() {
		String maChuyenMon = (String) cboTimKiem.getSelectedItem();
		for (int i = 0; i < modelTable.getRowCount(); i++)
			if (modelTable.getValueAt(i, 0).equals(maChuyenMon)) {
				duaPhanCongLenForm(getPhanCongTuTable(i));
				Utils.selectAndScrollToRow(i, table);
				return;
			}
	}

	private void handleBtnThem() {
		if (HandleButton.isBtnHuy(btnThem)) {
			table.setEnabled(true);
			btnSua.setEnabled(true);
			btnTim.setEnabled(true);
			cboCongNhan.setEnabled(false);
			cboCongTrinh.setEnabled(false);
			cboCongViec.setEnabled(false);
			HandleButton.setBtnThem(btnThem);
			HandleButton.setBtnXoa(btnXoa);

			int row = table.getSelectedRow();

			if (row != -1)
				duaPhanCongLenForm(getPhanCongTuTable(row));
			else
				xoaTrang();
		} else {
			table.setEnabled(false);
			xoaTrang();
			cboCongNhan.setEnabled(true);
			cboCongTrinh.setEnabled(true);
			cboCongViec.setEnabled(true);
			txtMaPC.requestFocus();
			btnSua.setEnabled(false);
			btnTim.setEnabled(false);
			HandleButton.setBtnHuy(btnThem);
			HandleButton.setBtnLuu(btnXoa);
		}
	}

	private void handleBtnSua() {
		if (HandleButton.isBtnHuy(btnSua)) {
			huyKhiSuaPhanCong();
		} else {
			int row = table.getSelectedRow();

			if (row == -1)
				JOptionPane.showMessageDialog(null, "Vui lòng chọn phân công lao động cần sửa",
						"Sửa thông tin phân công lao động", JOptionPane.WARNING_MESSAGE);
			else {
				PhanCong phanCong = getPhanCongTuTable(row);
				duaPhanCongLenForm(phanCong);
				txtMaPC.setEditable(false);
				table.setEnabled(false);
				btnTim.setEnabled(false);
				btnThem.setEnabled(false);
				cboCongNhan.setEnabled(true);
				cboCongTrinh.setEnabled(true);
				cboCongViec.setEnabled(true);

				HandleButton.setBtnHuy(btnSua);
				HandleButton.setBtnLuu(btnXoa);
			}
		}
	}

	private boolean validator() {
		String maPhanCong = txtMaPC.getText().trim();
		String ngayBatDau = txtNgayBD.getText().trim();
		String ngayKetThuc = txtNgayKT.getText().trim();

		if (maPhanCong.equals(""))
			return Utils.showMessAndFocus("Vui lòng nhập mã phân công", "Lỗi", JOptionPane.ERROR_MESSAGE, txtMaPC);
		if (!Pattern.matches("[A-Za-z0-9]+", maPhanCong))
			return Utils.showMessAndFocus("Mã phân chỉ chứa các ký tự chữ cái và số", "Lỗi", JOptionPane.ERROR_MESSAGE,
					txtMaPC);
		if (cboCongNhan.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn nhân viên", "Chọn nhân viên", JOptionPane.ERROR_MESSAGE);
			cboCongNhan.requestFocus();
			return false;
		}
		if (cboCongViec.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn công việc", "Chọn công việc", JOptionPane.ERROR_MESSAGE);
			cboCongViec.requestFocus();
			return false;
		}
		if (cboCongTrinh.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn công trình", "Chọn công trình",
					JOptionPane.ERROR_MESSAGE);
			cboCongTrinh.requestFocus();
			return false;
		}
		if (ngayBatDau.equals(""))
			return Utils.showMessAndFocus("Vui lòng nhập ngày bắt đầu làm việc", "Lỗi", JOptionPane.ERROR_MESSAGE,
					txtNgayBD);
		if (!Utils.isDate(ngayBatDau))
			return Utils.showMessAndFocus("Ngày bắt đầu phải có dạng dd/MM/yyyy", "Lỗi", JOptionPane.ERROR_MESSAGE,
					txtNgayBD);
		String maCongTrinh = cboCongTrinh.getSelectedItem().toString().split(" - ")[0];
		LocalDate ngayBD = Utils.getLocalDate(ngayBatDau);
		LocalDate ngayKhoiCongCT = congTrinh_DAO.getNgayKhoiCong(maCongTrinh);
		if (ngayBD.isBefore(ngayKhoiCongCT))
			return Utils.showMessAndFocus(
					String.format("Ngày bắt đầu làm việc >= %s", Utils.formatDate(ngayKhoiCongCT)), "Lỗi",
					JOptionPane.ERROR_MESSAGE, txtNgayBD);
		LocalDate ngayBatDauHopLe = phanCong_DAO.ngayBatDauHopLe(
				cboCongNhan.getSelectedItem().toString().split(" - ")[0], Utils.getLocalDate(ngayBatDau));
		if (ngayBatDauHopLe != null && !ngayBatDauHopLe.isBefore(ngayBD))
			return Utils.showMessAndFocus(
					String.format("Công nhân này đang làm việc cho công trình khác đến hết ngày %s mới hoàn thành",
							Utils.formatDate(ngayBatDauHopLe)),
					"Lỗi", JOptionPane.ERROR_MESSAGE, txtNgayBD);
		if (ngayKetThuc.equals(""))
			return Utils.showMessAndFocus("Vui lòng nhập ngày kết thúc công việc", "Lỗi", JOptionPane.ERROR_MESSAGE,
					txtNgayKT);
		if (!Utils.isDate(ngayKetThuc))
			return Utils.showMessAndFocus("Ngày kết thúc phải có dạng dd/MM/yyyy", "Lỗi", JOptionPane.ERROR_MESSAGE,
					txtNgayKT);
		LocalDate ngayKT = Utils.getLocalDate(ngayKetThuc);
		if (ngayKT.isBefore(ngayBD))
			return Utils.showMessAndFocus(String.format("Ngày kết thúc công việc phải >= %s", Utils.formatDate(ngayBD)),
					"Lỗi", JOptionPane.ERROR_MESSAGE, txtNgayKT);
		String maCongNhan = cboCongNhan.getSelectedItem().toString().split(" - ")[0];
		LocalDate ngayKetThucHopLe = phanCong_DAO.ngayKetThucHopLe(maCongNhan, ngayBD, ngayKT);
		if (ngayKetThucHopLe != null && !ngayKetThucHopLe.isBefore(ngayBD))
			return Utils.showMessAndFocus(String.format("Ngày %s công nhân sẽ bắt đầu làm việc cho công trình khác",
					Utils.formatDate(ngayKetThucHopLe)), "Lỗi", JOptionPane.ERROR_MESSAGE, txtNgayKT);
		return true;
	}

	private int getRow(String maChuyenMon) {
		for (int i = 0; i < table.getRowCount(); i++)
			if (modelTable.getValueAt(i, 0).toString().equalsIgnoreCase(maChuyenMon))
				return i;
		return -1;
	}

	private void huyKhiSuaPhanCong() {
		txtMaPC.setEditable(true);
		table.setEnabled(true);
		btnTim.setEnabled(true);
		btnThem.setEnabled(true);
		cboCongNhan.setEnabled(false);
		cboCongTrinh.setEnabled(false);
		cboCongViec.setEnabled(false);

		HandleButton.setBtnSua(btnSua);
		HandleButton.setBtnXoa(btnXoa);

		int row = table.getSelectedRow();

		if (row != -1)
			duaPhanCongLenForm(getPhanCongTuTable(row));
	}

	private void handleBtnXoa() {
		if (HandleButton.isBtnXoa(btnXoa)) {
			int row = table.getSelectedRow();

			if (row == -1)
				JOptionPane.showMessageDialog(null, "Vui lòng chọn dòng cần xóa", "Xóa phân công",
						JOptionPane.WARNING_MESSAGE);
			else {
				int res = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa phân công này?",
						"Xóa phân công", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (res == JOptionPane.OK_OPTION) {
					String maPhanCong = modelTable.getValueAt(row, 0).toString();
					if (phanCong_DAO.xoa(maPhanCong)) {
						cboTimKiem.removeItem(maPhanCong);
						modelTable.removeRow(row);
						if (table.getRowCount() > 0) {
							duaPhanCongLenForm(getPhanCongTuTable(0));
							Utils.selectAndScrollToRow(0, table);
						} else {
							xoaTrang();
							btnTim.setEnabled(false);
						}
						JOptionPane.showMessageDialog(null, String.format("Xóa phân công %s thành công!", maPhanCong),
								"Xóa phân công", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, String.format("Xóa phân công %s thất bại", maPhanCong),
								"Xóa phân công", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		} else {
			if (validator()) {
				String maPhanCong = txtMaPC.getText().trim();
				String ngayBatDau = txtNgayBD.getText().trim();
				String ngayKetThuc = txtNgayKT.getText().trim();
				String ghiChu = txtGhiChu.getText().trim();
				String maCongNhan = cboCongNhan.getSelectedItem().toString().split(" - ")[0];
				String maCongViec = cboCongViec.getSelectedItem().toString().split(" - ")[0];
				String maCongTrinh = cboCongTrinh.getSelectedItem().toString().split(" - ")[0];

				PhanCong phanCong = new PhanCong(maPhanCong, congNhan_DAO.getCongNhanTheoMa(maCongNhan),
						congViec_DAO.getCongViec(maCongViec), congTrinh_DAO.getCongTrinh(maCongTrinh),
						Utils.getLocalDate(ngayBatDau), Utils.getLocalDate(ngayKetThuc), ghiChu);

				if (HandleButton.isBtnHuy(btnThem)) {
					if (phanCong_DAO.phanCongDaTonTai(phanCong)) {
						JOptionPane.showMessageDialog(null,
								String.format("Công việc %s đã được phân công cho công nhân %s %s ở công trình %s",
										phanCong.getCongViec().getTenCongViec(), phanCong.getCongNhan().getHo(),
										phanCong.getCongNhan().getTen(), phanCong.getCongTrinh().getTenCongTrinh()),
								"Lỗi", JOptionPane.ERROR_MESSAGE);
					} else if (phanCong_DAO.them(phanCong)) {
						themPhanCongVaoTable(phanCong);
						cboTimKiem.addItem(maPhanCong);
						xoaTrang();
						txtMaPC.requestFocus();
						JOptionPane.showMessageDialog(null, "Phân công công việc thành công", "Phân công công việc",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Trùng mã phân công", "Lỗi", JOptionPane.ERROR_MESSAGE);
						txtMaPC.selectAll();
						txtMaPC.requestFocus();
					}
				} else if (HandleButton.isBtnHuy(btnSua)) {
					if (!phanCong_DAO.isEditable(phanCong))
						JOptionPane.showMessageDialog(null,
								String.format("Công việc %s đã được phân công cho công nhân %s %s ở công trình %s",
										phanCong.getCongViec().getTenCongViec(), phanCong.getCongNhan().getHo(),
										phanCong.getCongNhan().getTen(), phanCong.getCongTrinh().getTenCongTrinh()),
								"Lỗi", JOptionPane.ERROR_MESSAGE);
					else if (phanCong_DAO.sua(phanCong)) {
						int row = getRow(maPhanCong);
						modelTable.setValueAt(String.format("%s - %s %s", phanCong.getCongNhan().getMaCongNhan(),
								phanCong.getCongNhan().getHo(), phanCong.getCongNhan().getTen()), row, 1);
						modelTable.setValueAt(String.format("%s - %s", phanCong.getCongViec().getMaCongViec(),
								phanCong.getCongViec().getTenCongViec()), row, 2);
						modelTable.setValueAt(String.format("%s - %s", phanCong.getCongTrinh().getMaCongTrinh(),
								phanCong.getCongTrinh().getTenCongTrinh()), row, 3);
						modelTable.setValueAt(Utils.formatDate(phanCong.getNgayBatDau()), row, 4);
						modelTable.setValueAt(Utils.formatDate(phanCong.getNgayKetThuc()), row, 5);
						modelTable.setValueAt(phanCong.getGhiChu(), row, 6);
						JOptionPane.showMessageDialog(null, "Sửa thông tin thành công", "Sửa chuyên môn",
								JOptionPane.INFORMATION_MESSAGE);
						huyKhiSuaPhanCong();
					} else {
						JOptionPane.showMessageDialog(null, "Sửa thông tin thất bại", "Lỗi", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(btnTim)) {
			handleBtnTim();
		} else if (o.equals(btnThem)) {
			handleBtnThem();
		} else if (o.equals(btnLamMoi)) {
			if (HandleButton.isBtnHuy(btnSua)) {
				xoaTrangSua();
			} else {
				xoaTrang();
				txtMaPC.requestFocus();
			}
		} else if (o.equals(btnXoa)) {
			handleBtnXoa();
		} else if (o.equals(btnSua)) {
			handleBtnSua();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (table.isEnabled()) {
			int row = table.getSelectedRow();
			duaPhanCongLenForm(getPhanCongTuTable(row));
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	private void themPhanCongVaoTable(PhanCong phanCong) {
		modelTable.addRow(new Object[] { phanCong.getMaPhanCong(),
				String.format("%s - %s %s", phanCong.getCongNhan().getMaCongNhan(), phanCong.getCongNhan().getHo(),
						phanCong.getCongNhan().getTen()),
				String.format("%s - %s", phanCong.getCongViec().getMaCongViec(),
						phanCong.getCongViec().getTenCongViec()),
				String.format("%s - %s", phanCong.getCongTrinh().getMaCongTrinh(),
						phanCong.getCongTrinh().getTenCongTrinh()),
				Utils.formatDate(phanCong.getNgayBatDau()), Utils.formatDate(phanCong.getNgayKetThuc()),
				phanCong.getGhiChu() });
	}

	private void themPhanCongVaoTable(List<PhanCong> list) {
		list.forEach(phanCong -> themPhanCongVaoTable(phanCong));
	}

	private PhanCong getPhanCongTuTable(int row) {
		String maPhanCong = modelTable.getValueAt(row, 0).toString();
		String maCongNhan = modelTable.getValueAt(row, 1).toString().split(" - ")[0];
		String maCongViec = modelTable.getValueAt(row, 2).toString().split(" - ")[0];
		String maCongTrinh = modelTable.getValueAt(row, 3).toString().split(" - ")[0];
		LocalDate ngayBatDau = Utils.getLocalDate(modelTable.getValueAt(row, 4).toString());
		LocalDate ngayKetThuc = Utils.getLocalDate(modelTable.getValueAt(row, 5).toString());
		String ghiChu = modelTable.getValueAt(row, 6).toString();
		return new PhanCong(maPhanCong, congNhan_DAO.getCongNhanTheoMa(maCongNhan),
				congViec_DAO.getCongViec(maCongViec), congTrinh_DAO.getCongTrinh(maCongTrinh), ngayBatDau, ngayKetThuc,
				ghiChu);
	}

	private void duaPhanCongLenForm(PhanCong phanCong) {
		txtMaPC.setText(phanCong.getMaPhanCong());
		cboCongNhan.setSelectedItem(String.format("%s - %s %s", phanCong.getCongNhan().getMaCongNhan(),
				phanCong.getCongNhan().getHo(), phanCong.getCongNhan().getTen()));
		cboCongTrinh.setSelectedItem(String.format("%s - %s", phanCong.getCongTrinh().getMaCongTrinh(),
				phanCong.getCongTrinh().getTenCongTrinh()));
		cboCongViec.setSelectedItem(String.format("%s - %s", phanCong.getCongViec().getMaCongViec(),
				phanCong.getCongViec().getTenCongViec()));
		txtNgayBD.setText(Utils.formatDate(phanCong.getNgayBatDau()));
		txtNgayKT.setText(Utils.formatDate(phanCong.getNgayKetThuc()));
		txtGhiChu.setText(phanCong.getGhiChu());
	}

	@Override
	public void windowOpened(WindowEvent e) {
//		Phân công
		List<PhanCong> listPhanCong = phanCong_DAO.getAllPhanCong();

		themPhanCongVaoTable(listPhanCong);

		listPhanCong.forEach(phanCong -> cboTimKiem.addItem(phanCong.getMaPhanCong()));
		if (listPhanCong.size() > 0) {
			Utils.selectAndScrollToRow(0, table);
			duaPhanCongLenForm(getPhanCongTuTable(0));
		} else {
			btnTim.setEnabled(false);
		}

//		Công nhân
		List<CongNhan> listCongNhan = congNhan_DAO.getAllCongNhan();
		listCongNhan.forEach(congNhan -> cboCongNhan
				.addItem(String.format("%s - %s %s", congNhan.getMaCongNhan(), congNhan.getHo(), congNhan.getTen())));

//		Công trình
		List<CongTrinh> listCongTrinh = congTrinh_DAO.getAllCongTrinh();
		listCongTrinh.forEach(congTrinh -> cboCongTrinh
				.addItem(String.format("%s - %s", congTrinh.getMaCongTrinh(), congTrinh.getTenCongTrinh())));
//		Công việc
		List<CongViec> listCongViec = congViec_DAO.getAllCongViec();
		listCongViec.forEach(congViec -> cboCongViec
				.addItem(String.format("%s - %s", congViec.getMaCongViec(), congViec.getTenCongViec())));
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

	private void xoaTrangSua() {
		cboCongNhan.setSelectedIndex(-1);
		cboCongTrinh.setSelectedIndex(-1);
		cboCongViec.setSelectedIndex(-1);
		txtNgayBD.setText("");
		txtNgayKT.setText("");
		txtGhiChu.setText("");
	}

	private void xoaTrang() {
		xoaTrangSua();
		txtMaPC.setText("");
	}

}
