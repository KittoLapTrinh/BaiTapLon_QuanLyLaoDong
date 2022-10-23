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
import javax.swing.ImageIcon;
import javax.swing.GroupLayout.Alignment;
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

import dao.ChuyenMon_DAO;
import entity.ChuyenMon;
import utils.HandleButton;
import utils.Utils;

public class ChuyenMon_GUI extends JFrame implements WindowListener, MouseListener, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<String> cboTim;
	private JButton btnTim;
	private JTable table;
	private DefaultTableModel modelTable;
	private JTextField txtMa;
	private JTextField txtTen;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btnLamMoi;

	private ChuyenMon_DAO chuyenMon_DAO;

	public ChuyenMon_GUI() {
		getContentPane().setBackground(SystemColor.control);
		this.setTitle("Quản lý chuyên môn");
		this.setIconImage(new ImageIcon("HinhAnh\\iconPhanCongLaoDong.png").getImage());
		this.setSize(1000, 650);
		JPanel contentPane = new JPanel();
		contentPane.setBackground(new Color(244, 164, 96));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		new UI_JMenubar(this);
		chuyenMon_DAO = new ChuyenMon_DAO();

		JLabel lblTieuDe = new JLabel("CHUYÊN MÔN");
		lblTieuDe.setVerticalAlignment(SwingConstants.TOP);
		lblTieuDe.setForeground(SystemColor.controlDkShadow);
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblTieuDe.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblTieuDe);

		JPanel pBox = new JPanel();
		pBox.setBackground(new Color(245, 222, 179));
		pBox.setBorder(BorderFactory.createTitledBorder("Thông tin các chuyên môn"));

		JPanel pTable = new JPanel();
		pTable.setBackground(new Color(245, 222, 179));
		pTable.setBorder(BorderFactory.createTitledBorder("Danh sách các chuyên môn"));

		JPanel pTimKiem = new JPanel();
		pTimKiem.setBackground(new Color(245, 222, 179));
		pTimKiem.setBorder(BorderFactory.createTitledBorder("Tìm kiếm chuyên môn"));
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

		JLabel lblTimKiem = new JLabel("Tìm theo mã chuyên môn:");
		lblTimKiem.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblTimKiem.setBounds(10, 25, 193, 13);
		pTimKiem.add(lblTimKiem);

		cboTim = new JComboBox<String>();
		cboTim.setBounds(185, 23, 117, 19);
		pTimKiem.add(cboTim);

		btnTim = new JButton();
		HandleButton.setBtnTimKiem(btnTim);
		btnTim.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTim.setForeground(Color.BLACK);
		btnTim.setBackground(SystemColor.scrollbar);
		btnTim.setBounds(320, 16, 100, 33);
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
				new String[] { "Mã chuyên môn", "Tên chuyên môn" }));
		scrollPane.setViewportView(table);
		pBox.setLayout(null);

		JLabel lblMa = new JLabel("Mã chuyên môn:");
		lblMa.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblMa.setBounds(75, 71, 120, 20);
		pBox.add(lblMa);

		txtMa = new JTextField();
		txtMa.setBounds(205, 71, 176, 20);
		pBox.add(txtMa);
		txtMa.setColumns(10);

		JLabel lblTen = new JLabel("Tên chuyên môn:");
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
		btnTim.addActionListener(this);
		btnLamMoi.addActionListener(this);
		btnSua.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
	}

	private void themChuyenMonVaoTable(ChuyenMon chuyenMon) {
		modelTable.addRow(new Object[] { chuyenMon.getMaChuyenMon(), chuyenMon.getTenChuyenMon() });
	}

	private void themChuyenMonVaoTable(List<ChuyenMon> list) {
		list.forEach(chuyenMon -> themChuyenMonVaoTable(chuyenMon));
	}

	private ChuyenMon getChuyenMonTuTable(int row) {
		String maChuyenMon = modelTable.getValueAt(row, 0).toString();
		String tenChuyenMon = modelTable.getValueAt(row, 1).toString();
		return new ChuyenMon(maChuyenMon, tenChuyenMon);
	}

	private void duaChuyenMonLenForm(ChuyenMon chuyenMon) {
		txtMa.setText(chuyenMon.getMaChuyenMon());
		txtTen.setText(chuyenMon.getTenChuyenMon());
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		List<ChuyenMon> list = chuyenMon_DAO.getAllChuyenMon();

		themChuyenMonVaoTable(list);

		list.forEach(chuyenMon -> cboTim.addItem(chuyenMon.getMaChuyenMon()));
		if (list.size() > 0) {
			Utils.selectAndScrollToRow(0, table);
			duaChuyenMonLenForm(getChuyenMonTuTable(0));
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

	@Override
	public void mouseClicked(MouseEvent e) {
		if (table.isEnabled()) {
			int row = table.getSelectedRow();
			duaChuyenMonLenForm(getChuyenMonTuTable(row));
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

	private void handleBtnTim() {
		String maChuyenMon = (String) cboTim.getSelectedItem();
		for (int i = 0; i < modelTable.getRowCount(); i++)
			if (modelTable.getValueAt(i, 0).equals(maChuyenMon)) {
				duaChuyenMonLenForm(getChuyenMonTuTable(i));
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
				duaChuyenMonLenForm(getChuyenMonTuTable(row));
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

	private void huyKhiSuaChuyenMon() {
		txtMa.setEditable(true);
		table.setEnabled(true);
		btnTim.setEnabled(true);
		btnThem.setEnabled(true);

		HandleButton.setBtnSua(btnSua);
		HandleButton.setBtnXoa(btnXoa);

		int row = table.getSelectedRow();

		if (row != -1)
			duaChuyenMonLenForm(getChuyenMonTuTable(row));
	}

	private void handleBtnSua() {
		if (HandleButton.isBtnHuy(btnSua)) {
			huyKhiSuaChuyenMon();
		} else {
			int row = table.getSelectedRow();

			if (row == -1)
				JOptionPane.showMessageDialog(null, "Vui lòng chọn chuyên môn cần sửa", "Sửa thông tin chuyên môn",
						JOptionPane.WARNING_MESSAGE);
			else {
				ChuyenMon chuyenMon = getChuyenMonTuTable(row);
				txtMa.setText(chuyenMon.getMaChuyenMon());
				txtTen.setText(chuyenMon.getTenChuyenMon());
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
		String maChuyenMon = txtMa.getText().trim();
		String tenChuyenMon = txtTen.getText().trim();

		if (maChuyenMon.equals(""))
			return Utils.showMessAndFocus("Vui lòng nhập mã chuyên môn", "Lỗi", JOptionPane.ERROR_MESSAGE, txtMa);
		else if (!Pattern.matches("[A-Za-z0-9]+", maChuyenMon))
			return Utils.showMessAndFocus("Mã chuyên môn chỉ chứa các ký tự chữ cái và số", "Lỗi",
					JOptionPane.ERROR_MESSAGE, txtMa);
		else if (!Pattern.matches("[A-Za-z0-9]{3,}", maChuyenMon))
			return Utils.showMessAndFocus("Mã chuyên môn ít nhất là 3 ký tự", "Lỗi", JOptionPane.ERROR_MESSAGE, txtMa);
		else if (tenChuyenMon.equals(""))
			return Utils.showMessAndFocus("Vui lòng nhập tên chuyên môn", "Lỗi", JOptionPane.ERROR_MESSAGE, txtTen);
		return true;
	}

	private int getRow(String maChuyenMon) {
		for (int i = 0; i < table.getRowCount(); i++)
			if (modelTable.getValueAt(i, 0).toString().equalsIgnoreCase(maChuyenMon))
				return i;
		return -1;
	}

	private void handleBtnXoa() {
		if (HandleButton.isBtnXoa(btnXoa)) {
			int row = table.getSelectedRow();

			if (row == -1)
				JOptionPane.showMessageDialog(null, "Vui lòng chọn chuyên môn cần xóa", "Xóa chuyên môn",
						JOptionPane.WARNING_MESSAGE);
			else {
				int res = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa chuyên môn này?",
						"Xóa chuyên môn", JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (res == JOptionPane.OK_OPTION) {
					String maChuyenMon = modelTable.getValueAt(row, 0).toString();
					if (chuyenMon_DAO.xoaChuyenMon(maChuyenMon)) {
						cboTim.removeItem(maChuyenMon);
						modelTable.removeRow(row);
						if (table.getRowCount() > 0) {
							duaChuyenMonLenForm(getChuyenMonTuTable(0));
							Utils.selectAndScrollToRow(0, table);
						} else {
							xoaTrang();
							btnTim.setEnabled(false);
						}
						JOptionPane.showMessageDialog(null, String.format("Xóa chuyên môn %s thành công!", maChuyenMon),
								"Xóa chuyên môn", JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, String
								.format("Xóa chuyên môn %s thất bại vì có công nhân có chuyên môn này", maChuyenMon),
								"Xóa chuyên môn", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		} else {
			if (validator()) {
				String maChuyenMon = txtMa.getText().trim();
				String tenChuyenMon = txtTen.getText().trim();
				ChuyenMon chuyenMon = new ChuyenMon(maChuyenMon, tenChuyenMon);
				if (HandleButton.isBtnHuy(btnThem)) {
					if (chuyenMon_DAO.themChuyenMon(chuyenMon)) {
						themChuyenMonVaoTable(chuyenMon);
						cboTim.addItem(maChuyenMon);
						xoaTrang();
						txtMa.requestFocus();
						JOptionPane.showMessageDialog(null,
								String.format("Thêm chuyên môn %s thành công", tenChuyenMon), "Thêm chuyên môn",
								JOptionPane.INFORMATION_MESSAGE);
					} else {
						JOptionPane.showMessageDialog(null, "Trùng mã chuyên môn", "Lỗi", JOptionPane.ERROR_MESSAGE);
						txtMa.selectAll();
						txtMa.requestFocus();
					}
				} else if (HandleButton.isBtnHuy(btnSua)) {
					if (chuyenMon_DAO.suaChuyenMon(chuyenMon)) {
						int row = getRow(maChuyenMon);
						modelTable.setValueAt(tenChuyenMon, row, 1);
						JOptionPane.showMessageDialog(null,
								String.format("Sửa thông tin chuyên môn %s thành công", tenChuyenMon), "Sửa chuyên môn",
								JOptionPane.INFORMATION_MESSAGE);
						huyKhiSuaChuyenMon();
					} else {
						JOptionPane.showMessageDialog(null, "Sửa thông tin chuyên môn thất bại", "Lỗi",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
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
