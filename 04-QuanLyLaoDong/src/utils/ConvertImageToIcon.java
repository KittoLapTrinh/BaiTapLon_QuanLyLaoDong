package utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class ConvertImageToIcon {
	public static ImageIcon convertImageToIcon(String pathname, int width, int height) {
		ImageIcon icon = null;
		try {
			BufferedImage image = ImageIO.read(new File(pathname));

			icon = new ImageIcon(image.getScaledInstance(width, height, BufferedImage.SCALE_SMOOTH));
		} catch (IOException ex) {
			JOptionPane.showMessageDialog(null, "Lỗi hình ảnh");
		}
		return icon;
	}
}
