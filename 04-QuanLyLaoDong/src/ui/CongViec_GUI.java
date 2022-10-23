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
import javax.swing.table.DefaultTableModel;

import dao.CongViec_DAO;
import entity.CongViec;
import utils.HandleButton;
import utils.Utils;

public class CongViec_GUI extends JFrame implements WindowListener, MouseListener, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField txtMa;
	private JTextField txtTen;
	private DefaultTableModel modelTable;

	private CongViec_DAO congViec_DAO;
	private JComboBox<String> cboTim;
	private JButton btnTim;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btnLamMoi;

	public CongViec_GUI() {
		getContentPane().setBackground(SystemColor.control);
		this.setTitle("Quản lý công việc");
		this.setIconImage(new ImageIcon("HinhAnh\\icon_CongViec.png").getImage());
		this.setSize(1000, 650);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(244, 164, 96));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		new UI_JMenubar(this);

		congViec_DAO = new CongViec_DAO();

		JLabel lblTieuDe = new JLabel("CÔNG VIỆC");
		lblTieuDe.setVerticalAlignment(SwingConstants.TOP);
		lblTieuDe.setForeground(SystemColor.controlDkShadow);
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTieuDe);

		JPanel pBox = new JPanel();
		pBox.setBackground(new Color(245, 222, 179));
		pBox.setBorder(BorderFactory.createTitledBorder("Thông tin các công việc"));

		JPanel pTable = new JPanel();
		pTable.setBackground(new Color(245, 222, 179));
		pTable.setBorder(BorderFactory.createTitledBorder("Danh sách các công việc"));

		JPanel pTimKiem = new JPanel();
		pTimKiem.setBackground(new Color(245, 222, 179));
		pTimKiem.setBorder(BorderFactory.createTitledBorder("Tìm kiếm công việc"));
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
						.addComponent(pBox, GroupLayout.PREFERRED_SIZE, 233, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(pTable, GroupLayout.PREFERRED_SIZE, 185, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED)
						.addComponent(pTimKiem, GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE)));
		pTimKiem.setLayout(null);

		JLabel lblTimKiem = new JLabel("Tìm theo mã công việc:");
		lblTimKiem.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblTimKiem.setBounds(10, 25, 193, 13);
		pTimKiem.add(lblTimKiem);

		cboTim = new JComboBox<String>();
		cboTim.setBounds(178, 23, 117, 19);
		pTimKiem.add(cboTim);

		btnTim = new JButton();
		HandleButton.setBtnTimKiem(btnTim);
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTim.setForeground(Color.BLACK);
		btnTim.setBackground(SystemColor.scrollbar);
		btnTim.setBounds(305, 16, 100, 33);
		pTimKiem.add(btnTim);
		pTable.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 20, 962, 159);
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
				new String[] { "Mã công việc", "Tên công việc" }));
		scrollPane.setViewportView(table);
		pBox.setLayout(null);

		JLabel lblMa = new JLabel("Mã công việc:");
		lblMa.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblMa.setBounds(75, 71, 120, 20);
		pBox.add(lblMa);

		txtMa = new JTextField();
		txtMa.setBounds(205, 71, 176, 20);
		pBox.add(txtMa);
		txtMa.setColumns(10);

		JLabel lblTen = new JLabel("Tên công việc:");
		lblTen.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblTen.setBounds(554, 71, 120, 20);

		pBox.add(lblTen);

		txtTen = new JTextField();
		txtTen.setBounds(684, 71, 200, 20);
		pBox.add(txtTen);
		txtTen.setColumns(10);

		btnThem = new JButton();
		HandleButton.setBtnThem(btnThem);
		btnThem.setForeground(Color.BLACK);
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnThem.setBackground(SystemColor.scrollbar);
		btnThem.setBounds(104, 171, 120, 30);
		pBox.add(btnThem);

		btnXoa = new JButton();
		HandleButton.setBtnXoa(btnXoa);
		btnXoa.setForeground(Color.BLACK);
		btnXoa.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnXoa.setBackground(SystemColor.scrollbar);
		btnXoa.setBounds(328, 171, 120, 30);
		pBox.add(btnXoa);

		btnSua = new JButton();
		HandleButton.setBtnSua(btnSua);
		btnSua.setForeground(Color.BLACK);
		btnSua.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSua.setBackground(SystemColor.scrollbar);
		btnSua.setBounds(552, 171, 120, 30);
		pBox.add(btnSua);

		btnLamMoi = new JButton();
		HandleButton.setBtnLamMoi(btnLamMoi);
		btnLamMoi.setForeground(Color.BLACK);
		btnLamMoi.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnLamMoi.setBackground(SystemColor.scrollbar);
		btnLamMoi.setBounds(776, 171, 120, 30);
		pBox.add(btnLamMoi);
		getContentPane().setLayout(groupLayout);

		this.addWindowListener(this);
		this.addWindowListener(new utils.WindowListener());
		table.addMouseListener(this);
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnTim.addActionListener(this);
		btnXoa.addActionListener(this);
	}

	private void themCongViecVaoTable(CongViec congViec) {
		modelTable.addRow(new Object[] { congViec.getMaCongViec(), congViec.getTenCongViec() });
	}

	private void themCongViecVaoTable(List<CongViec> list) {
		list.forEach(congViec -> themCongViecVaoTable(congViec));
	}

	private void duaCongViecLenForm(CongViec congViec) {
		txtMa.setText(congViec.getMaCongViec());
		txtTen.setText(congViec.getTenCongViec());
	}

	private CongViec getCongViecTuTable(int row) {
		String maCongViec = modelTable.getValueAt(row, 0).toString();
		String tenCongViec = modelTable.getValueAt(row, 1).toString();
		return new CongViec(maCongViec, tenCongViec);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		List<CongViec> list = congViec_DAO.getAllCongViec();

		if (list.size() > 0) {
			list.forEach(congViec -> cboTim.addItem(congViec.getMaCongViec()));
			themCongViecVaoTable(list);
			Utils.selectAndScrollToRow(0, table);
			duaCongViecLenForm(getCongViecTuTable(0));
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
		// TODO Auto-generated method stub
		if (table.isEnabled()) {
			int row = table.getSelectedRow();
			duaCongViecLenForm(getCongViecTuTable(row));
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

	private void huyKhiSuaCongViec() {
		txtMa.setEditable(true);
		table.setEnabled(true);
		btnTim.setEnabled(true);
		btnThem.setEnabled(true);

		HandleButton.setBtnSua(btnSua);
		HandleButton.setBtnXoa(btnXoa);

		int row = table.getSelectedRow();

		if (row != -1)
			duaCongViecLenForm(getCongViecTuTable(row));
	}

	private void handleBtnSua() {
		if (HandleButton.isBtnHuy(btnSua)) {
			huyKhiSuaCongViec();
		} else {
			int row = table.getSelectedRow();

			if (row == -1)
				JOptionPane.showMessageDialog(null, "Vui lòng chọn công việc cần sửa", "Sửa thông tin công việc",
						JOptionPane.WARNING_MESSAGE);
			else {
				CongViec congViec = getCongViecTuTable(row);
				txtMa.setText(congViec.getMaCongViec());
				txtTen.setText(congViec.getTenCongViec());
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

	private boolean validator() {
		String maCongViec = txtMa.getText().trim();
		String tenCongViec = txtTen.getText().trim();

		if (maCongViec.equals(""))
			return Utils.showMessAndFocus("Vui lòng nhập mã công việc", "Lỗi", JOptionPane.ERROR_MESSAGE, txtMa);
		else if (!Pattern.matches("[A-Za-z0-9]+", maCongViec))
			return Utils.showMessAndFocus("Mã công việc chỉ chứa các ký tự chữ cái và số", "Lỗi",
					JOptionPane.ERROR_MESSAGE, txtMa);
		else if (!Pattern.matches("[A-Za-z0-9]{3,}", maCongViec))
			return Utils.showMessAndFocus("Mã công việc ít nhất là 3 ký tự", "Lỗi", JOptionPane.ERROR_MESSAGE, txtMa);
		else if (tenCongViec.equals(""))
			return Utils.showMessAndFocus("Vui lòng nhập tên công việc", "Lỗi", JOptionPane.ERROR_MESSAGE, txtTen);
		return true;
	}

	private int getRow(String maCongViec) {
		for (int i = 0; i < table.getRowCount(); i++)
			if (modelTable.getValueAt(i, 0).toString().equalsIgnoreCase(maCongViec))
				return i;
		return -1;
	}

	private void handleBtnXoa() {
		if (HandleButton.isBtnXoa(btnXoa)) {
			int row = table.getSelectedRow();

			if (row == -1)
				JOptionPane.showMessageDialog(null, "Vui lòng chọn công việc cần xóa", "Xóa công việc",
						JOptionPane.WARNING_MESSAGE);
			else {
				int res = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa công việc này?",
						"Xóa công việc", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (res == JOptionPane.OK_OPTION) {
					String maCongViec = modelTable.getValueAt(row, 0).toString();
					if (congViec_DAO.xoaCongViec(maCongViec)) {
						cboTim.removeItem(maCongViec);
						modelTable.removeRow(row);
						if (table.getRowCount() > 0) {
							duaCongViecLenForm(getCongViecTuTable(0));
							Utils.selectAndScrollToRow(0, table);
						} else {
							xoaTrang();
							btnTim.setEnabled(false);
						}
						JOptionPane.showMessageDialog(null, String.format("Xóa công việc %s thành công!", maCongViec),
								"Xóa công việc", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, String.format("Xóa công việc %s thất bại", maCongViec),
								"Xóa công việc", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		} else {
			if (validator()) {
				String maCongViec = txtMa.getText().trim();
				String tenCongViec = txtTen.getText().trim();
				CongViec CongViec = new CongViec(maCongViec, tenCongViec);
				if (HandleButton.isBtnHuy(btnThem)) {
					if (congViec_DAO.themCongViec(CongViec)) {
						themCongViecVaoTable(CongViec);
						cboTim.addItem(maCongViec);
						xoaTrang();
						txtMa.requestFocus();
						JOptionPane.showMessageDialog(null, String.format("Thêm công việc %s thành công", tenCongViec),
								"Thêm công việc", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Trùng mã công việc", "Lỗi", JOptionPane.ERROR_MESSAGE);
						txtMa.selectAll();
						txtMa.requestFocus();
					}
				} else if (HandleButton.isBtnHuy(btnSua)) {
					if (congViec_DAO.suaCongViec(CongViec)) {
						int row = getRow(maCongViec);
						modelTable.setValueAt(tenCongViec, row, 1);
						JOptionPane.showMessageDialog(null,
								String.format("Sửa thông tin công việc %s thành công", tenCongViec), "Sửa công việc",
								JOptionPane.INFORMATION_MESSAGE);
						huyKhiSuaCongViec();
					} else {
						JOptionPane.showMessageDialog(null, "Sửa thông tin công việc thất bại", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
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
				duaCongViecLenForm(getCongViecTuTable(row));
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

	private void handleBtnTim() {
		String maCongViec = (String) cboTim.getSelectedItem();
		for (int i = 0; i < modelTable.getRowCount(); i++)
			if (modelTable.getValueAt(i, 0).equals(maCongViec)) {
				duaCongViecLenForm(getCongViecTuTable(i));
				Utils.selectAndScrollToRow(i, table);
				return;
			}
	}

	private void xoaTrangSua() {
		txtTen.setText("");
	}

	private void xoaTrang() {
		xoaTrangSua();
		txtMa.setText("");
	}
}
