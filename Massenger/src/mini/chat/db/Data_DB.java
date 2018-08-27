package mini.chat.db;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Data_DB {

		
		private File target = new File("Text","Text.db");
		
		public void TextDB() throws IOException {
				
			FileWriter fw = new FileWriter(target);
			BufferedWriter bw = new BufferedWriter(fw);
			PrintWriter SaveText = new PrintWriter(bw);
			
		}
		
		public void ImageDB() {
			
			JFileChooser chooser = new JFileChooser(".");
			chooser.setFileFilter(new FileNameExtensionFilter("PNG 이미지", "png"));
			chooser.setFileFilter(new FileNameExtensionFilter("JPEG 이미지", "jpg"));
			chooser.setFileFilter(new FileNameExtensionFilter("GIF 이미지", "gif"));
			
			int choice = chooser.showSaveDialog(panel);
			if(choice == 0) {
				File target = chooser.getSelectedFile();
				
				String filename = target.getName();
				String extension = filename.substring(filename.lastIndexOf(".")+1);
				System.out.println("filename = "+filename);
				System.out.println("extension = "+extension);
				
				ImageIcon icon = (ImageIcon) lb.getIcon();
				
				BufferedImage img = 
						new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), 
																		BufferedImage.TYPE_INT_ARGB);
				ImageIO.write(img, extension, target);
				
//				Graphics pen = img.getGraphics();
//				
//				pen.drawImage(icon.getImage(), 0, 0, img.getWidth(), img.getHeight(), null);
//				
//				try {
//					ImageIO.write(img, extension, target);
//				} catch (IOException e1) {
//					e1.printStackTrace();
//				}
			}
			
		}
		
		

	
}
