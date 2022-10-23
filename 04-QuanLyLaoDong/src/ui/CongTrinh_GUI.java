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
import javax.swing.JComponent;
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

import dao.CongTrinh_DAO;
import entity.CongTrinh;
import utils.HandleButton;
import utils.Utils;

public class CongTrinh_GUI extends JFrame implements WindowListener, MouseListener, ActionListener {
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JComponent contentPane;
	private JComboBox<String> cboTimKiem;
	private DefaultTableModel modelTable;

	private CongTrinh_DAO congTrinh_DAO;
	private JButton btnLamMoi;
	private JButton btnSua;
	private JButton btnXoa;
	private JButton btnThem;
	private JButton btnTim;

	private JTextField txtMaCT;
	private JLabel lblTenCT;
	private JTextField txtTenCT;
	private JLabel lblDiaDiem;
	private JTextField txtDiaDiem;
	private JLabel lblChuDT;
	private JTextField txtChuDT;
	private JLabel lblGiayPhep;
	private JTextField txtGiayPhep;
	private JLabel lblNgayCap;
	private JTextField txtNgayCap;
	private JLabel lblNgayKC;
	private JTextField txtNgayKC;
	private JLabel lblNgayHT;
	private JTextField txtNgayHT;

	public CongTrinh_GUI() {
		congTrinh_DAO = new CongTrinh_DAO();

		this.setTitle("Quản lý Công trình");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setSize(1000, 600);
		this.setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(244, 164, 96));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		this.setIconImage(new ImageIcon("HinhAnh\\iconQuanLyCongTrinh.png").getImage());
		setResizable(false);

		new UI_JMenubar(this);
		JPanel container = new JPanel();
		getContentPane().add(container);

		JLabel lblTieuDe = new JLabel("THÔNG TIN CÔNG TRÌNH");
		lblTieuDe.setBackground(new Color(244, 164, 96));
		lblTieuDe.setBounds(5, 5, 976, 22);
		lblTieuDe.setForeground(SystemColor.controlDkShadow);
		lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTieuDe);

		JPanel pBox = new JPanel();
		pBox.setBackground(new Color(245, 222, 179));
		pBox.setBorder(BorderFactory.createTitledBorder("Thông tin Công trình"));

		JPanel pTable = new JPanel();
		pTable.setBorder(BorderFactory.createTitledBorder("Danh sách Công trình"));

		JPanel pTimKiem = new JPanel();
		pTimKiem.setBackground(new Color(245, 222, 179));
		pTimKiem.setBorder(BorderFactory.createTitledBorder("Tìm kiếm Công trình"));
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

		JLabel lblTimKiem = new JLabel("Nhập mã Công trình cần tìm:");
		lblTimKiem.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblTimKiem.setBounds(60, 26, 193, 13);
		pTimKiem.add(lblTimKiem);

		cboTimKiem = new JComboBox<>();
		cboTimKiem.setBounds(274, 24, 105, 19);
		pTimKiem.add(cboTimKiem);

		btnTim = new JButton();
		HandleButton.setBtnTimKiem(btnTim);
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTim.setForeground(Color.BLACK);
		btnTim.setBackground(SystemColor.scrollbar);
		btnTim.setBounds(400, 17, 100, 30);
		pTimKiem.add(btnTim);
		pTable.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 20, 966, 159);
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
		table.setModel(modelTable = new DefaultTableModel(new Object[][] {},
				new String[] { "Mã công trình", "Tên công trình", "Địa điểm", "Chủ đầu tư", "Giấy phép xây dựng",
						"Ngày cấp", "Ngày khởi công", "Ngày hoàn thành" }));
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.RIGHT);
		table.getColumnModel().getColumn(5).setCellRenderer(dtcr);
		table.getColumnModel().getColumn(6).setCellRenderer(dtcr);
		table.getColumnModel().getColumn(7).setCellRenderer(dtcr);
		scrollPane.setViewportView(table);
		pBox.setLayout(null);
		// 1
		JLabel lblMa = new JLabel("Mã công trình:");
		lblMa.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblMa.setBounds(60, 24, 120, 23);
		pBox.add(lblMa);

		btnThem = new JButton();
		HandleButton.setBtnThem(btnThem);
		btnThem.setIconTextGap(1);
		btnThem.setBackground(SystemColor.scrollbar);
		btnThem.setForeground(new Color(0, 0, 0));
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnThem.setBounds(188, 171, 100, 30);
		pBox.add(btnThem);

		btnXoa = new JButton();
		HandleButton.setBtnXoa(btnXoa);
		btnXoa.setForeground(Color.BLACK);
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnXoa.setBackground(SystemColor.scrollbar);
		btnXoa.setBounds(356, 171, 100, 30);
		pBox.add(btnXoa);

		btnSua = new JButton();
		HandleButton.setBtnSua(btnSua);
		btnSua.setForeground(Color.BLACK);
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSua.setBackground(SystemColor.scrollbar);
		btnSua.setBounds(526, 171, 100, 30);
		pBox.add(btnSua);

		btnLamMoi = new JButton();
		HandleButton.setBtnLamMoi(btnLamMoi);
		btnLamMoi.setForeground(Color.BLACK);
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLamMoi.setBackground(SystemColor.scrollbar);
		btnLamMoi.setBounds(693, 171, 122, 30);
		pBox.add(btnLamMoi);

		txtMaCT = new JTextField();
		txtMaCT.setBounds(238, 28, 150, 19);
		pBox.add(txtMaCT);
		txtMaCT.setColumns(10);

		lblTenCT = new JLabel("Tên công trình:");
		lblTenCT.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblTenCT.setBounds(613, 24, 120, 23);
		pBox.add(lblTenCT);

		txtTenCT = new JTextField();
		txtTenCT.setColumns(10);
		txtTenCT.setBounds(753, 28, 150, 19);
		pBox.add(txtTenCT);

		lblDiaDiem = new JLabel("Địa điểm:");
		lblDiaDiem.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblDiaDiem.setBounds(60, 60, 120, 23);
		pBox.add(lblDiaDiem);

		txtDiaDiem = new JTextField();
		txtDiaDiem.setColumns(10);
		txtDiaDiem.setBounds(238, 64, 150, 19);
		pBox.add(txtDiaDiem);

		lblChuDT = new JLabel("Chủ đầu tư:");
		lblChuDT.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblChuDT.setBounds(613, 60, 120, 23);
		pBox.add(lblChuDT);

		txtChuDT = new JTextField();
		txtChuDT.setColumns(10);
		txtChuDT.setBounds(753, 64, 150, 19);
		pBox.add(txtChuDT);

		lblGiayPhep = new JLabel("Giấy phép xây dựng:");
		lblGiayPhep.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblGiayPhep.setBounds(60, 96, 163, 23);
		pBox.add(lblGiayPhep);

		txtGiayPhep = new JTextField();
		txtGiayPhep.setColumns(10);
		txtGiayPhep.setBounds(238, 100, 150, 19);
		pBox.add(txtGiayPhep);

		lblNgayCap = new JLabel("Ngày cấp:");
		lblNgayCap.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNgayCap.setBounds(613, 96, 120, 23);
		pBox.add(lblNgayCap);

		txtNgayCap = new JTextField();
		txtNgayCap.setColumns(10);
		txtNgayCap.setBounds(753, 100, 150, 19);
		pBox.add(txtNgayCap);

		lblNgayKC = new JLabel("Ngày khởi công:");
		lblNgayKC.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNgayKC.setBounds(60, 132, 163, 23);
		pBox.add(lblNgayKC);

		txtNgayKC = new JTextField();
		txtNgayKC.setColumns(10);
		txtNgayKC.setBounds(238, 136, 150, 19);
		pBox.add(txtNgayKC);

		lblNgayHT = new JLabel("Ngày hoàn thành:");
		lblNgayHT.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNgayHT.setBounds(613, 132, 130, 23);
		pBox.add(lblNgayHT);

		txtNgayHT = new JTextField();
		txtNgayHT.setColumns(10);
		txtNgayHT.setBounds(753, 136, 150, 19);
		pBox.add(txtNgayHT);
		getContentPane().setLayout(groupLayout);

		this.addWindowListener(this);
		this.addWindowListener(new utils.WindowListener());
		table.addMouseListener(this);
		btnTim.addActionListener(this);
		btnThem.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnSua.addActionListener(this);
		btnXoa.addActionListener(this);
	}

	private void themCongTrinhVaoTable(CongTrinh congTrinh) {
		modelTable
				.addRow(new Object[] { congTrinh.getMaCongTrinh(), congTrinh.getTenCongTrinh(), congTrinh.getDiaDiem(),
						congTrinh.getChuDauTu(), congTrinh.getGiayPhepXD(), Utils.formatDate(congTrinh.getNgayCap()),
						Utils.formatDate(congTrinh.getNgayKhoiCong()), Utils.formatDate(congTrinh.getNgayHT()) });
	}

	private void themCongTrinhVaoTable(List<CongTrinh> list) {
		list.forEach(congTrinh -> themCongTrinhVaoTable(congTrinh));
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		List<CongTrinh> listCongTrinh = congTrinh_DAO.getAllCongTrinh();

		themCongTrinhVaoTable(listCongTrinh);

		listCongTrinh.forEach(congTrinh -> cboTimKiem.addItem(congTrinh.getMaCongTrinh()));
		if (listCongTrinh.size() > 0) {
			Utils.selectAndScrollToRow(0, table);
			duaCongTrinhLenForm(getCongTrinhTuTable(0));
		} else {
			btnTim.setEnabled(false);
		}
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

	private CongTrinh getCongTrinhTuTable(int row) {
		String maCongTrinh = modelTable.getValueAt(row, 0).toString();
		String tenCongTrinh = modelTable.getValueAt(row, 1).toString();
		String diaDiem = modelTable.getValueAt(row, 2).toString();
		String chuDauTu = modelTable.getValueAt(row, 3).toString();
		String giayPhepXD = modelTable.getValueAt(row, 4).toString();
		LocalDate ngayCap = Utils.getLocalDate(modelTable.getValueAt(row, 5).toString());
		LocalDate ngayKhoiCong = Utils.getLocalDate(modelTable.getValueAt(row, 6).toString());
		LocalDate ngayHT = Utils.getLocalDate(modelTable.getValueAt(row, 7).toString());
		return new CongTrinh(maCongTrinh, tenCongTrinh, diaDiem, chuDauTu, giayPhepXD, ngayCap, ngayKhoiCong, ngayHT);
	}

	private void duaCongTrinhLenForm(CongTrinh congTrinh) {
		txtMaCT.setText(congTrinh.getMaCongTrinh());
		txtTenCT.setText(congTrinh.getTenCongTrinh());
		txtChuDT.setText(congTrinh.getChuDauTu());
		txtDiaDiem.setText(congTrinh.getDiaDiem());
		txtGiayPhep.setText(congTrinh.getGiayPhepXD());
		txtNgayCap.setText(Utils.formatDate(congTrinh.getNgayCap()));
		txtNgayKC.setText(Utils.formatDate(congTrinh.getNgayKhoiCong()));
		txtNgayHT.setText(Utils.formatDate(congTrinh.getNgayHT()));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		if (table.isEnabled()) {
			int row = table.getSelectedRow();
			duaCongTrinhLenForm(getCongTrinhTuTable(row));
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

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();

		if (o.equals(btnTim)) {
			handleBtnTim();
		} else if (o.equals(btnThem)) {
			handleBtnThem();
		} else if (o.equals(btnLamMoi)) {
			if (HandleButton.isBtnHuy(btnSua))
				xoaTrangSua();
			else {
				xoaTrang();
				txtMaCT.requestFocus();
			}
		} else if (o.equals(btnXoa)) {
			handleBtnXoa();
		} else if (o.equals(btnSua)) {
			handleBtnSua();
		}
	}

	private void handleBtnTim() {
		String maCongTrinh = (String) cboTimKiem.getSelectedItem();
		for (int i = 0; i < modelTable.getRowCount(); i++)
			if (modelTable.getValueAt(i, 0).equals(maCongTrinh)) {
				duaCongTrinhLenForm(getCongTrinhTuTable(i));
				Utils.selectAndScrollToRow(i, table);
				return;
			}
	}

	private void handleBtnThem() {
		if (HandleButton.isBtnHuy(btnThem)) {
			table.setEnabled(true);
			btnSua.setEnabled(true);
			btnTim.setEnabled(true);
			HandleButton.setBtnThem(btnThem);
			HandleButton.setBtnXoa(btnXoa);

			int row = table.getSelectedRow();

			if (row != -1)
				duaCongTrinhLenForm(getCongTrinhTuTable(row));
			else
				xoaTrang();
		} else {
			table.setEnabled(false);
			xoaTrang();
			txtMaCT.requestFocus();
			btnSua.setEnabled(false);
			btnTim.setEnabled(false);
			HandleButton.setBtnHuy(btnThem);
			HandleButton.setBtnLuu(btnXoa);
		}
	}

	private int getRow(String maCongTrinh) {
		for (int i = 0; i < table.getRowCount(); i++)
			if (modelTable.getValueAt(i, 0).toString().equalsIgnoreCase(maCongTrinh))
				return i;
		return -1;
	}

	private void huyKhiSuaCongTrinh() {
		txtMaCT.setEditable(true);
		table.setEnabled(true);
		btnTim.setEnabled(true);
		btnThem.setEnabled(true);

		HandleButton.setBtnSua(btnSua);
		HandleButton.setBtnXoa(btnXoa);

		int row = table.getSelectedRow();

		if (row != -1)
			duaCongTrinhLenForm(getCongTrinhTuTable(row));
	}

	private void handleBtnSua() {
		if (HandleButton.isBtnHuy(btnSua)) {
			huyKhiSuaCongTrinh();
		} else {
			int row = table.getSelectedRow();

			if (row == -1)
				JOptionPane.showMessageDialog(null, "Vui lòng chọn công trình cần sửa", "Sửa thông tin công trình",
						JOptionPane.WARNING_MESSAGE);
			else {
				txtMaCT.setEditable(false);
				table.setEnabled(false);
				btnTim.setEnabled(false);
				btnThem.setEnabled(false);

				HandleButton.setBtnHuy(btnSua);
				HandleButton.setBtnLuu(btnXoa);

				txtTenCT.requestFocus();
			}
		}
	}

	private boolean showMessAndFocus(String mess, String title, int messType, JTextField jTextField) {
		JOptionPane.showMessageDialog(null, mess, title, messType);
		jTextField.selectAll();
		jTextField.requestFocus();
		return false;
	}

	private boolean validator() {
		String maCongTrinh = txtMaCT.getText().trim();
		String tenCongTrinh = txtTenCT.getText().trim();
		String diaDiem = txtDiaDiem.getText().trim();
		String chuDauTu = txtChuDT.getText().trim();
		String giayPhepXD = txtGiayPhep.getText().trim();
		String ngayCap = txtNgayCap.getText().trim();
		String ngayKhoiCong = txtNgayKC.getText().trim();
		String ngayHoanThanh = txtNgayHT.getText().trim();

		if (maCongTrinh.equals(""))
			return showMessAndFocus("Vui lòng nhập mã công trình", "Lỗi", JOptionPane.ERROR_MESSAGE, txtMaCT);
		else if (!Pattern.matches("[A-Za-z0-9]+", maCongTrinh))
			return showMessAndFocus("Mã công trình chỉ chứa các ký tự chữ cái và số", "Lỗi", JOptionPane.ERROR_MESSAGE,
					txtMaCT);
		else if (!Pattern.matches("[A-Za-z0-9]{3,}", maCongTrinh))
			return showMessAndFocus("Mã công trình ít nhất là 3 ký tự", "Lỗi", JOptionPane.ERROR_MESSAGE, txtMaCT);
		else if (tenCongTrinh.equals(""))
			return showMessAndFocus("Vui lòng nhập tên công trình", "Lỗi", JOptionPane.ERROR_MESSAGE, txtTenCT);
		else if (diaDiem.equals(""))
			return showMessAndFocus("Vui lòng nhập địa điểm thi công công trình", "Lỗi", JOptionPane.ERROR_MESSAGE,
					txtDiaDiem);
		else if (chuDauTu.equals(""))
			return showMessAndFocus("Vui lòng nhập chủ đầu tư của công trình", "Lỗi", JOptionPane.ERROR_MESSAGE,
					txtChuDT);
		else if (giayPhepXD.equals(""))
			return showMessAndFocus("Vui lòng nhập giấy phép xây dựng của công trình", "Lỗi", JOptionPane.ERROR_MESSAGE,
					txtGiayPhep);
		else if (!Pattern.matches(".*[0-9]{1,}/GPXD.*", giayPhepXD))
			return showMessAndFocus("Giấy phép xây dựng phải chứa XXX/GPXD, X là các ký tự số", "Lỗi",
					JOptionPane.ERROR_MESSAGE, txtGiayPhep);
		else if (ngayCap.equals(""))
			return showMessAndFocus("Vui lòng nhập ngày cấp giấy phép xây dựng của công trình", "Lỗi",
					JOptionPane.ERROR_MESSAGE, txtNgayCap);
		else if (!Utils.isDate(ngayCap))
			return showMessAndFocus("Ngày cấp phép phải có dạng dd/MM/yyyy", "Lỗi", JOptionPane.ERROR_MESSAGE,
					txtNgayCap);
		else if (Utils.getLocalDate(ngayCap).isAfter(LocalDate.now()))
			return showMessAndFocus("Ngày cấp phải phép phải trước ngày hiện tại", "Lỗi", JOptionPane.ERROR_MESSAGE,
					txtNgayCap);
		else if (ngayKhoiCong.equals(""))
			return showMessAndFocus("Vui lòng nhập ngày khởi công của công trình", "Lỗi", JOptionPane.ERROR_MESSAGE,
					txtNgayKC);
		else if (!Utils.isDate(ngayKhoiCong))
			return showMessAndFocus("Ngày khởi công phải có dạng dd/MM/yyyy", "Lỗi", JOptionPane.ERROR_MESSAGE,
					txtNgayKC);
		else if (Utils.getLocalDate(ngayCap).isAfter(Utils.getLocalDate(ngayKhoiCong)))
			return showMessAndFocus("Ngày khởi công phải sau ngày cấp phép", "Lỗi", JOptionPane.ERROR_MESSAGE,
					txtNgayKC);
		else if (ngayHoanThanh.equals(""))
			return showMessAndFocus("Vui lòng nhập ngày hoàn thành công trình", "Lỗi", JOptionPane.ERROR_MESSAGE,
					txtNgayHT);
		else if (!Utils.isDate(ngayHoanThanh))
			return showMessAndFocus("Ngày hoàn thành phải có dạng dd/MM/yyyy", "Lỗi", JOptionPane.ERROR_MESSAGE,
					txtNgayHT);
		else if (Utils.getLocalDate(ngayKhoiCong).isAfter(Utils.getLocalDate(ngayHoanThanh)))
			return showMessAndFocus("Ngày hoàn thành phải sau ngày khởi công", "Lỗi", JOptionPane.ERROR_MESSAGE,
					txtNgayHT);
		return true;
	}

	private void handleBtnXoa() {
		if (HandleButton.isBtnXoa(btnXoa)) {
			int row = table.getSelectedRow();

			if (row == -1)
				JOptionPane.showMessageDialog(null, "Vui lòng chọn công trình cần xóa", "Xóa công trình",
						JOptionPane.WARNING_MESSAGE);
			else {
				int res = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa công trình này?",
						"Xóa công trình", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (res == JOptionPane.OK_OPTION) {
					String maCongTrinh = modelTable.getValueAt(row, 0).toString();
					if (congTrinh_DAO.xoaCongTrinh(maCongTrinh)) {
						cboTimKiem.removeItem(maCongTrinh);
						modelTable.removeRow(row);
						if (table.getRowCount() > 0) {
							duaCongTrinhLenForm(getCongTrinhTuTable(0));
							Utils.selectAndScrollToRow(0, table);
						} else {
							xoaTrang();
							btnTim.setEnabled(false);
						}
						JOptionPane.showMessageDialog(null, String.format("Xóa công trình %s thành công!", maCongTrinh),
								"Xóa công trình", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, String.format("Xóa công trình %s thất bại", maCongTrinh),
								"Xóa công trình", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		} else {
			if (validator()) {
				String maCongTrinh = txtMaCT.getText().trim();
				String tenCongTrinh = txtTenCT.getText().trim();
				String diaDiem = txtDiaDiem.getText().trim();
				String chuDauTu = txtChuDT.getText().trim();
				String giayPhepXD = txtGiayPhep.getText().trim();
				String ngayCap = txtNgayCap.getText().trim();
				String ngayKhoiCong = txtNgayKC.getText().trim();
				String ngayHoanThanh = txtNgayHT.getText().trim();
				CongTrinh congTrinh = new CongTrinh(maCongTrinh, tenCongTrinh, diaDiem, chuDauTu, giayPhepXD,
						Utils.getLocalDate(ngayCap), Utils.getLocalDate(ngayKhoiCong),
						Utils.getLocalDate(ngayHoanThanh));
				if (HandleButton.isBtnHuy(btnThem)) {
					if (congTrinh_DAO.themCongTrinh(congTrinh)) {
						themCongTrinhVaoTable(congTrinh);
						cboTimKiem.addItem(maCongTrinh);
						xoaTrang();
						txtMaCT.requestFocus();
						JOptionPane.showMessageDialog(null,
								String.format("Thêm công trình %s thành công", tenCongTrinh), "Thêm công trình",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Trùng mã công trình", "Lỗi", JOptionPane.ERROR_MESSAGE);
						txtMaCT.selectAll();
						txtMaCT.requestFocus();
					}
				} else if (HandleButton.isBtnHuy(btnSua)) {
					if (congTrinh_DAO.suaCongTrinh(congTrinh)) {
						int row = getRow(maCongTrinh);
						modelTable.setValueAt(tenCongTrinh, row, 1);
						modelTable.setValueAt(diaDiem, row, 2);
						modelTable.setValueAt(chuDauTu, row, 3);
						modelTable.setValueAt(giayPhepXD, row, 4);
						modelTable.setValueAt(ngayCap, row, 5);
						modelTable.setValueAt(ngayKhoiCong, row, 6);
						modelTable.setValueAt(ngayHoanThanh, row, 7);
						JOptionPane.showMessageDialog(null,
								String.format("Sửa thông tin công trình %s thành công", tenCongTrinh), "Sửa công trình",
								JOptionPane.INFORMATION_MESSAGE);
						huyKhiSuaCongTrinh();
					} else {
						JOptionPane.showMessageDialog(null, "Sửa thông tin công trình thất bại", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
	}

	private void xoaTrangSua() {
		txtChuDT.setText("");
		txtDiaDiem.setText("");
		txtGiayPhep.setText("");
		txtNgayCap.setText("");
		txtNgayHT.setText("");
		txtNgayKC.setText("");
		txtTenCT.setText("");
	}

	private void xoaTrang() {
		xoaTrangSua();
		txtMaCT.setText("");
	}
}
