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
import javax.swing.ButtonGroup;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import dao.ChuyenMon_DAO;
import dao.CongNhan_DAO;
import entity.ChuyenMon;
import entity.CongNhan;
import utils.HandleButton;
import utils.Utils;

public class CongNhan_GUI extends JFrame implements ActionListener, MouseListener, WindowListener {
	private static final long serialVersionUID = 1L;
	private JTextField txtMa;
	private JTextField txtTen;
	private JTextField txtCCCD;
	private JTextField txtBac;
	private JTextField txtNamKN;
	private JTextField txtHo;
	private JTextField txtNgaysinh;
	private JTextField txtSDT;
	private JTextField txtTrinhdo;
	private JTextField txtDiachi;
	private JTable table;
	private JRadioButton radiNam;
	private JRadioButton radiNu;
	private DefaultTableModel model;
	private ButtonGroup btg;
	private JButton btnSua;
	private JButton btnLamMoi;
	private JButton btnXoa;
	private JButton btnThem;
	private JButton btnTim;
	private JComboBox<String> cbbTim;
	private CongNhan_DAO congNhan_DAO;
	private ChuyenMon_DAO chuyenMon_DAO;
	private JComboBox<String> cboChuyenMon;

	public CongNhan_GUI() {
		getContentPane().setBackground(new Color(244, 164, 96));
		this.setTitle("Quản lý công nhân");
		this.setIconImage(new ImageIcon("HinhAnh\\iconQuanLyCongNhan.png").getImage());
		this.setSize(1000, 650);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		new UI_JMenubar(this);

		JPanel container = new JPanel();
		getContentPane().add(container);

		JLabel lblTieuDe = new JLabel("QUẢN LÝ THÔNG TIN CÔNG NHÂN");
		lblTieuDe.setVerticalAlignment(SwingConstants.TOP);
		lblTieuDe.setForeground(SystemColor.controlDkShadow);
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 18));

		JPanel pBox = new JPanel();
		pBox.setBackground(new Color(245, 222, 179));
		pBox.setBorder(BorderFactory.createTitledBorder("Thông tin công nhân"));

		JPanel pTable = new JPanel();
		pTable.setBackground(new Color(245, 222, 179));
		pTable.setBorder(BorderFactory.createTitledBorder("Danh sách công nhân"));

		JPanel pTimKiem = new JPanel();
		pTimKiem.setBackground(new Color(245, 222, 179));
		pTimKiem.setBorder(BorderFactory.createTitledBorder("Tìm kiếm công nhân"));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(lblTieuDe, GroupLayout.DEFAULT_SIZE, 975, Short.MAX_VALUE).addContainerGap())
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(pTimKiem, GroupLayout.PREFERRED_SIZE, 985, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
				.addComponent(pTable, GroupLayout.DEFAULT_SIZE, 995, Short.MAX_VALUE)
				.addGroup(groupLayout.createSequentialGroup()
						.addComponent(pBox, GroupLayout.PREFERRED_SIZE, 984, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(34, Short.MAX_VALUE)));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup().addContainerGap()
						.addComponent(lblTieuDe, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(pBox, GroupLayout.PREFERRED_SIZE, 257, GroupLayout.PREFERRED_SIZE).addGap(7)
						.addComponent(pTable, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE).addGap(7)
						.addComponent(pTimKiem, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
		pTimKiem.setLayout(null);

		JLabel lblTimKiem = new JLabel("Chọn mã công nhân cần tìm:");
		lblTimKiem.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblTimKiem.setBounds(16, 24, 193, 13);
		pTimKiem.add(lblTimKiem);

		congNhan_DAO = new CongNhan_DAO();
		chuyenMon_DAO = new ChuyenMon_DAO();

		cbbTim = new JComboBox<String>();
		cbbTim.setBounds(230, 21, 100, 21);
		pTimKiem.add(cbbTim);

		btnTim = new JButton();
		HandleButton.setBtnTimKiem(btnTim);
		btnTim.setForeground(Color.BLACK);
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTim.setBackground(SystemColor.scrollbar);
		btnTim.setBounds(350, 21, 100, 21);
		pTimKiem.add(btnTim);
		pTable.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 20, 966, 182);
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
		table.setModel(model = new DefaultTableModel(new Object[][] {},
				new String[] { "Mã công nhân", "Họ", "Tên", "Giới tính", "Ngày sinh", "Địa chỉ", "Số điện thoại",
						"CCCD", "Bậc thợ", "Trình độ", "Chuyên môn", "Năm kinh nghiệm" }));
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer();
		dtcr.setHorizontalAlignment(SwingConstants.RIGHT);
		table.getColumnModel().getColumn(4).setCellRenderer(dtcr);
		table.getColumnModel().getColumn(6).setCellRenderer(dtcr);
		table.getColumnModel().getColumn(11).setCellRenderer(dtcr);
		scrollPane.setViewportView(table);
		pBox.setLayout(null);

		JLabel lblMa = new JLabel("Mã công nhân:");
		lblMa.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblMa.setBounds(109, 17, 120, 15);
		pBox.add(lblMa);

		txtMa = new JTextField();
		txtMa.setBounds(239, 17, 176, 20);
		pBox.add(txtMa);
		txtMa.setColumns(10);

		JLabel lblHo = new JLabel("Họ:");
		lblHo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblHo.setBounds(109, 39, 120, 15);
		pBox.add(lblHo);

		txtHo = new JTextField();
		txtHo.setColumns(10);
		txtHo.setBounds(239, 39, 176, 20);
		pBox.add(txtHo);

		JLabel lblTen = new JLabel("Tên:");
		lblTen.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblTen.setBounds(588, 39, 120, 15);
		pBox.add(lblTen);

		txtTen = new JTextField();
		txtTen.setColumns(10);
		txtTen.setBounds(718, 39, 176, 20);
		pBox.add(txtTen);

		JLabel lblGioiTinh = new JLabel("Giới tính:");
		lblGioiTinh.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblGioiTinh.setBounds(109, 62, 120, 15);
		pBox.add(lblGioiTinh);

		radiNam = new JRadioButton("Nam");
		radiNam.setBounds(239, 62, 56, 20);
		radiNam.setBackground(new Color(245, 222, 179));
		pBox.add(radiNam);

		radiNu = new JRadioButton("Nữ");
		radiNu.setBounds(300, 62, 56, 20);
		radiNu.setBackground(new Color(245, 222, 179));
		pBox.add(radiNu);

		btg = new ButtonGroup();
		btg.add(radiNam);
		btg.add(radiNu);

		JLabel lblNgaysinh = new JLabel("Ngày sinh:");
		lblNgaysinh.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblNgaysinh.setBounds(588, 62, 120, 15);
		pBox.add(lblNgaysinh);

		txtNgaysinh = new JTextField();
		txtNgaysinh.setColumns(10);
		txtNgaysinh.setBounds(718, 62, 176, 20);
		pBox.add(txtNgaysinh);

		JLabel lblDiachi = new JLabel("Địa chỉ:");
		lblDiachi.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblDiachi.setBounds(109, 86, 120, 15);
		pBox.add(lblDiachi);

		txtDiachi = new JTextField();
		txtDiachi.setColumns(10);
		txtDiachi.setBounds(239, 86, 176, 20);
		pBox.add(txtDiachi);

		JLabel lblSDT = new JLabel("Số điện thoại:");
		lblSDT.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblSDT.setBounds(588, 86, 120, 15);
		pBox.add(lblSDT);

		txtSDT = new JTextField();
		txtSDT.setColumns(10);
		txtSDT.setBounds(718, 86, 176, 20);
		pBox.add(txtSDT);

		JLabel lblCCCD = new JLabel("CCCD:");
		lblCCCD.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblCCCD.setBounds(109, 110, 120, 15);
		pBox.add(lblCCCD);

		txtCCCD = new JTextField();
		txtCCCD.setColumns(10);
		txtCCCD.setBounds(239, 110, 176, 20);
		pBox.add(txtCCCD);

		JLabel lblBac = new JLabel("Bậc thợ:");
		lblBac.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblBac.setBounds(588, 109, 120, 15);
		pBox.add(lblBac);

		txtBac = new JTextField();
		txtBac.setColumns(10);
		txtBac.setBounds(718, 110, 176, 20);
		pBox.add(txtBac);

		JLabel lblNamKN = new JLabel("Năm kinh nghiệm:");
		lblNamKN.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblNamKN.setBounds(588, 134, 120, 15);
		pBox.add(lblNamKN);

		txtNamKN = new JTextField();
		txtNamKN.setColumns(10);
		txtNamKN.setBounds(718, 135, 176, 20);
		pBox.add(txtNamKN);

		JLabel lblTrinhdo = new JLabel("Trình độ:");
		lblTrinhdo.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblTrinhdo.setBounds(109, 135, 120, 15);
		pBox.add(lblTrinhdo);

		txtTrinhdo = new JTextField();
		txtTrinhdo.setColumns(10);
		txtTrinhdo.setBounds(239, 135, 176, 20);
		pBox.add(txtTrinhdo);

		JLabel lblChuyenMon = new JLabel("Chuyên môn: ");
		lblChuyenMon.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblChuyenMon.setBounds(109, 159, 120, 15);
		pBox.add(lblChuyenMon);

		cboChuyenMon = new JComboBox<>();
		cboChuyenMon.setBounds(239, 159, 176, 20);
		pBox.add(cboChuyenMon);

		btnThem = new JButton();
		HandleButton.setBtnThem(btnThem);
		btnThem.setIconTextGap(0);
		btnThem.setBackground(SystemColor.scrollbar);
		btnThem.setForeground(new Color(0, 0, 0));
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnThem.setBounds(165, 214, 112, 30);
		pBox.add(btnThem);

		btnXoa = new JButton();
		HandleButton.setBtnXoa(btnXoa);
		btnXoa.setForeground(Color.BLACK);
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnXoa.setBackground(SystemColor.scrollbar);
		btnXoa.setBounds(355, 214, 90, 30);
		pBox.add(btnXoa);

		btnSua = new JButton();
		HandleButton.setBtnSua(btnSua);
		btnSua.setForeground(Color.BLACK);
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSua.setBackground(SystemColor.scrollbar);
		btnSua.setBounds(525, 214, 90, 30);
		pBox.add(btnSua);

		btnLamMoi = new JButton();
		HandleButton.setBtnLamMoi(btnLamMoi);
		btnLamMoi.setForeground(Color.BLACK);
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLamMoi.setBackground(SystemColor.scrollbar);
		btnLamMoi.setBounds(692, 214, 146, 30);
		pBox.add(btnLamMoi);

		getContentPane().setLayout(groupLayout);

		this.addWindowListener(this);
		this.addWindowListener(new utils.WindowListener());
		btnTim.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnSua.addActionListener(this);
		btnLamMoi.addActionListener(this);
		table.addMouseListener(this);
	}

	private void themCongNhanVaoTable(CongNhan congNhan) {
		model.addRow(new Object[] {
				congNhan.getMaCongNhan(), congNhan.getHo(), congNhan.getTen(), congNhan.isGioiTinh() ? "Nam" : "Nữ",
				Utils.formatDate(congNhan.getNgaySinh()), congNhan.getDiaChi(), congNhan.getSoDienThoai(),
				congNhan.getCccd(), congNhan.getBacTho(), congNhan.getTrinhDo(), String.format("%s - %s",
						congNhan.getChuyenMon().getMaChuyenMon(), congNhan.getChuyenMon().getTenChuyenMon()),
				congNhan.getNamKinhNghiem() });
	}

	private void themCongNhanVaoTable(List<CongNhan> list) {
		list.forEach(congViec -> themCongNhanVaoTable(congViec));
	}

	private void duaCongNhanLenForm(CongNhan congNhan) {
		txtMa.setText(congNhan.getMaCongNhan());
		txtBac.setText(congNhan.getBacTho());
		txtCCCD.setText(congNhan.getCccd());
		txtDiachi.setText(congNhan.getDiaChi());
		txtHo.setText(congNhan.getHo());
		txtNamKN.setText(congNhan.getNamKinhNghiem() + "");
		txtNgaysinh.setText(Utils.formatDate(congNhan.getNgaySinh()));
		txtSDT.setText(congNhan.getSoDienThoai());
		txtTen.setText(congNhan.getTen());
		txtTrinhdo.setText(congNhan.getTrinhDo());
		if (congNhan.isGioiTinh())
			radiNam.setSelected(true);
		else
			radiNu.setSelected(true);
		cboChuyenMon.setSelectedItem(String.format("%s - %s", congNhan.getChuyenMon().getMaChuyenMon(),
				congNhan.getChuyenMon().getTenChuyenMon()));
	}

	private CongNhan getCongNhanTuTable(int row) {
		String maCongNhan = model.getValueAt(row, 0).toString();
		String ho = model.getValueAt(row, 1).toString();
		String ten = model.getValueAt(row, 2).toString();
		boolean gioiTinh = model.getValueAt(row, 3).toString().equals("Nam");
		LocalDate ngaySinh = Utils.getLocalDate(model.getValueAt(row, 4).toString());
		String diaChi = model.getValueAt(row, 5).toString();
		String soDienThoai = model.getValueAt(row, 6).toString();
		String cccd = model.getValueAt(row, 7).toString();
		String bacTho = model.getValueAt(row, 8).toString();
		String trinhDo = model.getValueAt(row, 9).toString();
		String chuyenMon[] = model.getValueAt(row, 10).toString().split(" - ");
		int namKinhNghiem = Integer.parseInt(model.getValueAt(row, 11).toString());
		return new CongNhan(maCongNhan, ho, ten, gioiTinh, ngaySinh, diaChi, soDienThoai, cccd, bacTho, trinhDo,
				new ChuyenMon(chuyenMon[0], chuyenMon[1]), namKinhNghiem);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		List<ChuyenMon> listChuyenMon = chuyenMon_DAO.getAllChuyenMon();
		listChuyenMon.forEach(chuyenMon -> cboChuyenMon
				.addItem(String.format("%s - %s", chuyenMon.getMaChuyenMon(), chuyenMon.getTenChuyenMon())));

		List<CongNhan> listCongNhan = congNhan_DAO.getAllCongNhan();

		if (listCongNhan.size() > 0) {
			listCongNhan.forEach(congNhan -> cbbTim.addItem(congNhan.getMaCongNhan()));
			themCongNhanVaoTable(listCongNhan);
			Utils.selectAndScrollToRow(0, table);
			duaCongNhanLenForm(getCongNhanTuTable(0));
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

	@Override
	public void mouseClicked(MouseEvent e) {
		if (table.isEnabled()) {
			int row = table.getSelectedRow();
			duaCongNhanLenForm(getCongNhanTuTable(row));
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
				txtMa.requestFocus();
			}
		} else if (o.equals(btnXoa)) {
			handleBtnXoa();
		} else if (o.equals(btnSua)) {
			handleBtnSua();
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
				duaCongNhanLenForm(getCongNhanTuTable(row));
			else
				xoaTrang();
		} else {
			table.setEnabled(false);
			xoaTrang();
			txtMa.requestFocus();
			btnSua.setEnabled(false);
			btnTim.setEnabled(false);
			HandleButton.setBtnHuy(btnThem);
			HandleButton.setBtnLuu(btnXoa);
		}
	}

	private void huyKhiSuaCongNhan() {
		txtMa.setEditable(true);
		table.setEnabled(true);
		btnTim.setEnabled(true);
		btnThem.setEnabled(true);

		HandleButton.setBtnSua(btnSua);
		HandleButton.setBtnXoa(btnXoa);

		int row = table.getSelectedRow();

		if (row != -1)
			duaCongNhanLenForm(getCongNhanTuTable(row));
	}

	private void handleBtnSua() {
		if (HandleButton.isBtnHuy(btnSua)) {
			huyKhiSuaCongNhan();
		} else {
			int row = table.getSelectedRow();

			if (row == -1)
				JOptionPane.showMessageDialog(null, "Vui lòng chọn công nhân cần sửa", "Sửa thông tin công nhân",
						JOptionPane.WARNING_MESSAGE);
			else {
				CongNhan congNhan = getCongNhanTuTable(row);
				duaCongNhanLenForm(congNhan);
				txtMa.setEditable(false);
				table.setEnabled(false);
				btnTim.setEnabled(false);
				btnThem.setEnabled(false);

				HandleButton.setBtnHuy(btnSua);
				HandleButton.setBtnLuu(btnXoa);

				txtTen.requestFocus();
			}
		}
	}

	private void handleBtnTim() {
		String maCongNhan = (String) cbbTim.getSelectedItem();
		for (int i = 0; i < model.getRowCount(); i++)
			if (model.getValueAt(i, 0).equals(maCongNhan)) {
				duaCongNhanLenForm(getCongNhanTuTable(i));
				Utils.selectAndScrollToRow(i, table);
				return;
			}
	}

	private boolean validator() {
		String maCongNhan = txtMa.getText().trim();
		String ho = txtHo.getText().trim();
		String ten = txtTen.getText().trim();
		String ngaySinh = txtNgaysinh.getText().trim();
		String diaChi = txtDiachi.getText().trim();
		String soDienThoai = txtSDT.getText().trim();
		String cccd = txtCCCD.getText().trim();
		String bacTho = txtBac.getText().trim();
		String trinhDo = txtTrinhdo.getText().trim();
		String namKN = txtNamKN.getText().trim();

		if (maCongNhan.equals(""))
			return Utils.showMessAndFocus("Vui lòng nhập mã công nhân", "Lỗi", JOptionPane.ERROR_MESSAGE, txtMa);
		if (!Pattern.matches("[A-Za-z0-9]+", maCongNhan))
			return Utils.showMessAndFocus("Mã công nhân chỉ chứa các ký tự chữ cái và số", "Lỗi",
					JOptionPane.ERROR_MESSAGE, txtMa);
		if (!Pattern.matches("(CN)[0-9]{3}", maCongNhan))
			return Utils.showMessAndFocus("Mã công nhân phải bắt đầu bằng CN và theo sau là 3 ký tự số", "Lỗi",
					JOptionPane.ERROR_MESSAGE, txtMa);
		if (ho.equals(""))
			return Utils.showMessAndFocus("Vui lòng nhập họ và tên đệm của công nhân", "Lỗi", JOptionPane.ERROR_MESSAGE,
					txtHo);
		if (!Pattern.matches("[^!@#$%^&*()\\d]+", ho))
			return Utils.showMessAndFocus("Họ công nhân không chứa ký tự đặc biệt", "Lỗi", JOptionPane.ERROR_MESSAGE,
					txtHo);
		Pattern regexHo = Pattern.compile(String.format("([%sA-Z][a-z%s]*)( [%sA-Z][a-z%s]*)*",
				Utils.getVietnameseDiacriticCharacters(), Utils.getVietnameseDiacriticCharacters().toLowerCase(),
				Utils.getVietnameseDiacriticCharacters(), Utils.getVietnameseDiacriticCharacters().toLowerCase()),
				Pattern.CANON_EQ | Pattern.UNICODE_CASE);
		if (!regexHo.matcher(ho).matches())
			return Utils.showMessAndFocus("Họ và tên đệm phải bắt đầu bằng chữ hoa, mỗi từ cách nhau 1 khoảng trắng",
					"Lỗi", JOptionPane.ERROR_MESSAGE, txtHo);
		if (ten.equals(""))
			return Utils.showMessAndFocus("Vui lòng nhập tên của công nhân", "Lỗi", JOptionPane.ERROR_MESSAGE, txtTen);
		if (!Pattern.matches("[^!@#$%^&*()\\d]+", ten))
			return Utils.showMessAndFocus("Tên công nhân không chứa ký tự đặc biệt", "Lỗi", JOptionPane.ERROR_MESSAGE,
					txtTen);
		Pattern regexTen = Pattern.compile(
				String.format("([%sA-Z][a-z%s]*)", Utils.getVietnameseDiacriticCharacters(),
						Utils.getVietnameseDiacriticCharacters().toLowerCase()),
				Pattern.CANON_EQ | Pattern.UNICODE_CASE);
		if (!regexTen.matcher(ten).matches())
			return Utils.showMessAndFocus("Tên của công nhân phải bắt đầu bằng chữ hoa và chỉ có 1 từ", "Lỗi",
					JOptionPane.ERROR_MESSAGE, txtTen);
		if (!radiNam.isSelected() && !radiNu.isSelected()) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn giới tính", "Lỗi", JOptionPane.ERROR_MESSAGE);
			radiNam.requestFocus();
			return false;
		}
		if (ngaySinh.equals(""))
			return Utils.showMessAndFocus("Vui lòng nhập ngày sinh", "Lỗi", JOptionPane.ERROR_MESSAGE, txtNgaysinh);
		if (!Utils.isDate(ngaySinh))
			return Utils.showMessAndFocus("Ngày sinh phải theo dạng dd/MM/yyyy", "Lỗi", JOptionPane.ERROR_MESSAGE,
					txtNgaysinh);
		if (Utils.getLocalDate(ngaySinh).getYear() >= LocalDate.now().getYear() - 18)
			return Utils.showMessAndFocus("Công nhân phải lớn hơn 18 tuổi", "Lỗi", JOptionPane.ERROR_MESSAGE,
					txtNgaysinh);
		if (diaChi.equals(""))
			return Utils.showMessAndFocus("Vui lòng nhập địa chỉ", "Lỗi", JOptionPane.ERROR_MESSAGE, txtDiachi);
		if (!Pattern.matches("[^!@#$%^&*]+", diaChi))
			return Utils.showMessAndFocus("Địa chỉ không chứa ký tự đặc biệt", "Lỗi", JOptionPane.ERROR_MESSAGE,
					txtDiachi);
		if (soDienThoai.equals(""))
			return Utils.showMessAndFocus("Vui lòng nhập số điện thoại", "Lỗi", JOptionPane.ERROR_MESSAGE, txtSDT);
		if (!Pattern.matches("\\d+", soDienThoai))
			return Utils.showMessAndFocus("Số điện thoại chỉ chứa các ký tự số", "Lỗi", JOptionPane.ERROR_MESSAGE,
					txtSDT);
		if (!Pattern.matches("0\\d{9}", soDienThoai))
			return Utils.showMessAndFocus("Số điện thoại phải bắt đầu bằng 0 và phải có 10 chữ số", "Lỗi",
					JOptionPane.ERROR_MESSAGE, txtSDT);
		if (cccd.equals(""))
			return Utils.showMessAndFocus("Vui lòng nhập số CCCD", "Lỗi", JOptionPane.ERROR_MESSAGE, txtCCCD);
		if (!Pattern.matches("\\d{12}", cccd))
			return Utils.showMessAndFocus("Số CCCD phải có 12 số", "Lỗi", JOptionPane.ERROR_MESSAGE, txtCCCD);
		if (!Pattern.matches("(\\d{3}[0-3]" + (Utils.getLocalDate(ngaySinh).getYear() % 100 < 10
				? ('0' + Utils.getLocalDate(ngaySinh).getYear() % 100)
				: Utils.getLocalDate(ngaySinh).getYear() % 100) + ")((?!000000)[0-9]{6})", cccd))
			return Utils.showMessAndFocus(
					"Ký tụ thứ 4 nằm trong khoảng từ 0-3, ký tự 5 và 6 là 2 số cuối của năm sinh, 6 ký tự cuối phải từ 000001-999999",
					"Lỗi", JOptionPane.ERROR_MESSAGE, txtCCCD);
		if (congNhan_DAO.isCCCDTonTai(cccd))
			return Utils.showMessAndFocus("CCCD đã tồn tại", "Lỗi", JOptionPane.ERROR_MESSAGE, txtCCCD);
		if (bacTho.equals(""))
			return Utils.showMessAndFocus("Vui lòng nhập bậc thợ", "Lỗi", JOptionPane.ERROR_MESSAGE, txtBac);
		if (trinhDo.equals(""))
			return Utils.showMessAndFocus("Vui lòng nhập trình độ", "Lỗi", JOptionPane.ERROR_MESSAGE, txtTrinhdo);
		if (namKN.equals(""))
			return Utils.showMessAndFocus("Vui lòng nhập năm kinh nghiệm", "Lỗi", JOptionPane.ERROR_MESSAGE, txtNamKN);
		if (!Utils.isInteger(namKN))
			return Utils.showMessAndFocus("Năm kinh nghiệm phải là số nguyên", "Lỗi", JOptionPane.ERROR_MESSAGE,
					txtNamKN);
		if (Integer.parseInt(namKN) < 0)
			return Utils.showMessAndFocus("Năm kinh nghiệm phải >= 0", "Lỗi", JOptionPane.ERROR_MESSAGE, txtNamKN);
		if (cboChuyenMon.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn chuyên môn của công nhân", "Lỗi",
					JOptionPane.ERROR_MESSAGE);
			cboChuyenMon.requestFocus();
			return false;
		}
		return true;
	}

	private int getRow(String maCongNhan) {
		for (int i = 0; i < table.getRowCount(); i++)
			if (model.getValueAt(i, 0).toString().equalsIgnoreCase(maCongNhan))
				return i;
		return -1;
	}

	private void handleBtnXoa() {
		if (HandleButton.isBtnXoa(btnXoa)) {
			int row = table.getSelectedRow();

			if (row == -1)
				JOptionPane.showMessageDialog(null, "Vui lòng chọn công nhân cần xóa", "Xóa công nhân",
						JOptionPane.WARNING_MESSAGE);
			else {
				int res = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa công nhân này?",
						"Xóa công nhân", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (res == JOptionPane.OK_OPTION) {
					String maCongNhan = model.getValueAt(row, 0).toString();
					if (congNhan_DAO.xoa(maCongNhan)) {
						cbbTim.removeItem(maCongNhan);
						model.removeRow(row);
						if (table.getRowCount() > 0) {
							duaCongNhanLenForm(getCongNhanTuTable(0));
							Utils.selectAndScrollToRow(0, table);
						} else {
							xoaTrang();
							btnTim.setEnabled(false);
						}
						JOptionPane.showMessageDialog(null, String.format("Xóa công nhân %s thành công!", maCongNhan),
								"Xóa công nhân", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, String.format("Xóa công nhân %s thất bại", maCongNhan),
								"Xóa công nhân", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		} else {
			if (validator()) {
				String maCongNhan = txtMa.getText().trim();
				String ho = txtHo.getText().trim();
				String ten = txtTen.getText().trim();
				boolean gioiTinh = radiNam.isSelected();
				String ngaySinh = txtNgaysinh.getText().trim();
				String diaChi = txtDiachi.getText().trim();
				String soDienThoai = txtSDT.getText().trim();
				String cccd = txtCCCD.getText().trim();
				String bacTho = txtBac.getText().trim();
				String trinhDo = txtTrinhdo.getText().trim();
				String namKN = txtNamKN.getText().trim();
				String chuyenMon[] = cboChuyenMon.getSelectedItem().toString().split(" - ");
				CongNhan congNhan = new CongNhan(maCongNhan, ho, ten, gioiTinh, Utils.getLocalDate(ngaySinh), diaChi,
						soDienThoai, cccd, bacTho, trinhDo, new ChuyenMon(chuyenMon[0], chuyenMon[1]),
						Integer.parseInt(namKN));
				if (HandleButton.isBtnHuy(btnThem)) {
					if (congNhan_DAO.them(congNhan)) {
						themCongNhanVaoTable(congNhan);
						cbbTim.addItem(maCongNhan);
						xoaTrang();
						txtMa.requestFocus();
						JOptionPane.showMessageDialog(null, String.format("Thêm công nhân %s thành công", ten),
								"Thêm công nhân", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Trùng mã công nhân", "Lỗi", JOptionPane.ERROR_MESSAGE);
						txtMa.selectAll();
						txtMa.requestFocus();
					}
				} else if (HandleButton.isBtnHuy(btnSua)) {
					if (congNhan_DAO.sua(congNhan)) {
						int row = getRow(maCongNhan);
						model.setValueAt(ho, row, 1);
						model.setValueAt(ten, row, 2);
						model.setValueAt(radiNam.isSelected() ? "Nam" : "Nữ", row, 3);
						model.setValueAt(ngaySinh, row, 4);
						model.setValueAt(diaChi, row, 5);
						model.setValueAt(soDienThoai, row, 6);
						model.setValueAt(cccd, row, 7);
						model.setValueAt(bacTho, row, 8);
						model.setValueAt(trinhDo, row, 9);
						model.setValueAt(String.format("%s - %s", chuyenMon[0], chuyenMon[1]), row, 10);
						model.setValueAt(namKN, row, 11);
						JOptionPane.showMessageDialog(null, String.format("Sửa thông tin công nhân %s thành công", ten),
								"Sửa công nhân", JOptionPane.INFORMATION_MESSAGE);
						huyKhiSuaCongNhan();
					} else {
						JOptionPane.showMessageDialog(null, "Sửa thông tin công nhân thất bại", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
	}

	private void xoaTrangSua() {
		txtHo.setText("");
		txtTen.setText("");
		radiNam.setSelected(false);
		radiNu.setSelected(false);
		txtNgaysinh.setText("");
		txtDiachi.setText("");
		txtSDT.setText("");
		txtCCCD.setText("");
		txtBac.setText("");
		txtTrinhdo.setText("");
		cboChuyenMon.setSelectedIndex(-1);
		txtNamKN.setText("");
	}

	private void xoaTrang() {
		xoaTrangSua();
		txtMa.setText("");
	}
}
