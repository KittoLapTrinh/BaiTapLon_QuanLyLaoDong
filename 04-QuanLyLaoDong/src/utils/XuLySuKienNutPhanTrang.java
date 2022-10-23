package utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;

public class XuLySuKienNutPhanTrang implements ActionListener {

	private JTextField txtLabel;
	private int trangHienTai;
	private int soTrang;
	private JButton btnFirst;
	private JButton btnPrev;
	private JButton btnNext;
	private JButton btnLast;
	private JTable table;

	public XuLySuKienNutPhanTrang(JTextField txtLabel, JButton btnFirst, JButton btnPrev, JButton btnNext,
			JButton btnLast, JTable table) {
		super();
		this.txtLabel = txtLabel;
		this.btnFirst = btnFirst;
		this.btnPrev = btnPrev;
		this.btnNext = btnNext;
		this.btnLast = btnLast;
		this.table = table;
		this.setSoTrang(0);
		this.btnFirst.addActionListener(this);
		this.btnPrev.addActionListener(this);
		this.btnNext.addActionListener(this);
		this.btnLast.addActionListener(this);
	}

	public JTextField getTxtLabel() {
		return txtLabel;
	}

	public void setTxtLabel(JTextField txtLabel) {
		this.txtLabel = txtLabel;
	}

	public int getTrangHienTai() {
		return trangHienTai;
	}

	public void setTrangHienTai(int trangHienTai) {
		this.trangHienTai = trangHienTai;
		if (trangHienTai > 0)
			Utils.selectAndScrollToRow(trangHienTai - 1, table);
		this.xuLiBtnPhanTrang();
		this.txtLabel.setText(String.format("%d/%d", trangHienTai, soTrang));
	}

	public int getSoTrang() {
		return soTrang;
	}

	public void setSoTrang(int soTrang) {
		this.soTrang = soTrang;
		if (soTrang == 0)
			this.setTrangHienTai(0);
		else
			this.setTrangHienTai(1);
	}

	public JButton getBtnFirst() {
		return btnFirst;
	}

	public void setBtnFirst(JButton btnFirst) {
		this.btnFirst = btnFirst;
	}

	public JButton getBtnPrev() {
		return btnPrev;
	}

	public void setBtnPrev(JButton btnPrev) {
		this.btnPrev = btnPrev;
	}

	public JButton getBtnNext() {
		return btnNext;
	}

	public void setBtnNext(JButton btnNext) {
		this.btnNext = btnNext;
	}

	public JButton getBtnLast() {
		return btnLast;
	}

	public void setBtnLast(JButton btnLast) {
		this.btnLast = btnLast;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(btnFirst)) {
			this.setTrangHienTai(1);
		} else if (o.equals(btnPrev))
			this.setTrangHienTai(trangHienTai - 1);
		else if (o.equals(btnNext))
			this.setTrangHienTai(trangHienTai + 1);
		else if (o.equals(btnLast))
			this.setTrangHienTai(soTrang);
	}

	private void xuLiBtnPhanTrang() {
		if (soTrang <= 1) {
			btnFirst.setEnabled(false);
			btnPrev.setEnabled(false);
			btnNext.setEnabled(false);
			btnLast.setEnabled(false);
		} else if (trangHienTai == 1) {
			btnFirst.setEnabled(false);
			btnPrev.setEnabled(false);
			btnNext.setEnabled(true);
			btnLast.setEnabled(true);
		} else if (trangHienTai == soTrang) {
			btnFirst.setEnabled(true);
			btnPrev.setEnabled(true);
			btnNext.setEnabled(false);
			btnLast.setEnabled(false);
		} else {
			btnFirst.setEnabled(true);
			btnPrev.setEnabled(true);
			btnNext.setEnabled(true);
			btnLast.setEnabled(true);
		}
	}

}
