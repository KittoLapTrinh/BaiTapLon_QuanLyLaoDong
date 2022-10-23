package ui;

import javax.swing.SwingUtilities;

public class Starting {
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				Login_GUI login_GUI = new Login_GUI();
				login_GUI.setVisible(true);
			}
		});
	}
}
