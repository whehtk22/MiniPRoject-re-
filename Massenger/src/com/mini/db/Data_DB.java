package com.mini.db;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Data_DB {

		
		private File target = new File("Text","Text.db");
		
		public void textDb(String line){
				
			FileWriter fw;
			try {
				fw = new FileWriter(target);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter SaveText = new PrintWriter(bw);
				SaveText.write(line);
			} catch (IOException e) {
				
			}
			
		}
		
		public void imageDb(BufferedImage bf) {
			
			JFileChooser chooser = new JFileChooser(".");
			chooser.setFileFilter(new FileNameExtensionFilter("PNG 이미지", "png"));
			chooser.setFileFilter(new FileNameExtensionFilter("JPEG 이미지", "jpg"));
			chooser.setFileFilter(new FileNameExtensionFilter("GIF 이미지", "gif"));
			int choice = chooser.showSaveDialog(new Dialog());
			System.out.println("이미지 저장");
			if(choice == 0) {
				File target = chooser.getSelectedFile();
				
				String filename = target.getName();
				String extension = filename.substring(filename.lastIndexOf(".")+1);
				System.out.println("filename = "+filename);
				System.out.println("extension = "+extension);
				
				BufferedImage img = 
						new BufferedImage(bf.getWidth(),bf.getHeight(), 
										5);
				Graphics pen = img.getGraphics();
				
				pen.drawImage(img, 0, 0, bf.getWidth(), bf.getHeight(), null);
				
				try {
					ImageIO.write(img, "jpg", new File("image.jpg"));
				} catch (IOException e1) {
					e1.printStackTrace();
				}
//				try {
//					ImageIO.write(img, "jpg", new File("image.jpg"));
//				} catch (IOException e) {
//					
//				}
				
			}
			
		}
		
		

	
}
