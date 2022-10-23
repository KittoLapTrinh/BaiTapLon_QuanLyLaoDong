package utils;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeCellRenderer;

public class MyTreeCellRenderer extends DefaultTreeCellRenderer implements TreeCellRenderer {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<ImageIcon> listIcon;

	public MyTreeCellRenderer(ImageIcon icon) {
		this.listIcon = new ArrayList<>();
		this.listIcon.add(icon);
		this.setColor(Color.BLACK);
		this.setBackgroundColor(Color.WHITE);
	}

	public void addIcon(ImageIcon icon) {

	}

	public void setColor(Color color) {
		setForeground(color);
	}

	public void setBackgroundColor(Color color) {
		setBackground(color);
	}

	public java.awt.Component getTreeCellRendererComponent(javax.swing.JTree tree, Object value, boolean bSelected,
			boolean bExpanded, boolean bLeaf, int iRow, boolean bHasFocus) {

		// Find out which node we are rendering and get its text
		DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;	
		String labelText = (String) node.toString();
		setText(labelText);
		System.out.println(iRow);
		System.out.println(value);
		if (iRow >= listIcon.size())
			iRow = listIcon.size() - 1;
		setOpenIcon(listIcon.get(iRow));
		setClosedIcon(listIcon.get(iRow));
		setLeafIcon(listIcon.get(iRow));
		setIcon(listIcon.get(iRow));

		return this;
	}
}
