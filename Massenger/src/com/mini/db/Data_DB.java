package com.mini.db;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.mini.gui.Dial;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Data_DB {
		
		private File target = new File("Text","Text.db");
		
		
		public void saveClientText(String line){
				
			FileWriter fw;
			try {
				fw = new FileWriter(target);
				BufferedWriter bw = new BufferedWriter(fw);
				PrintWriter SaveText = new PrintWriter(bw);
				SaveText.write(line);
			} catch (IOException e) {
				
			}
		}
		
		public void saveImage(BufferedImage bf) {
			
			JFileChooser chooser = new JFileChooser(".");
			chooser.setFileFilter(new FileNameExtensionFilter("PNG �̹���", "png"));
			chooser.setFileFilter(new FileNameExtensionFilter("JPEG �̹���", "jpg"));
			chooser.setFileFilter(new FileNameExtensionFilter("GIF �̹���", "gif"));
			int choice = chooser.showSaveDialog(new Dial( ));
			System.out.println("�̹��� ����");
			if(choice == 0) {
				File target = chooser.getSelectedFile();
				
				String filename = target.getName();
				String extension = filename.substring(filename.lastIndexOf(".")+1);
				System.out.println("filename = "+filename);
				System.out.println("extension = "+extension);
				
				try {
					ImageIO.write(bf, extension, target);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}		
		}
		
		public void serverImage(BufferedImage bf) {
			
			
			
			System.out.println("�̹��� ����");
			
			File target = new File("image."+1);
			
			String filename = target.getName();
			String extension = filename.substring(filename.lastIndexOf(".")+1);
			System.out.println("filename = "+filename);
			System.out.println("extension = "+extension);
			
			try {
				ImageIO.write(bf, extension, target);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
		
		/**
		 * Ŭ���̾�Ʈ ����ڰ� ���� ������ List�� ������ ���Ͽ� �ٽ� �����ϴ� �޼ҵ�
		 * - �����ϴ� ����
		 * - ���� Id, password, �̸�, ģ�����
		 * @throws IOException
		 */
		public void clientUserSave() throws IOException {
			
			Map<String, Object> map = new HashMap<>();
			
			serverUserDb user = new serverUserDb();
			
			map.put(user.getName(), user);
			
//			���Ͽ�  yourDb.db�� �����մϴ�.
			File userFile = new File("files", "yourDb.db");
			
			
			ObjectOutputStream out = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream(userFile)));
					
				
			out.writeObject(map);
			
			out.flush();
		}
		
		public void serverUserOpen() {
			
			
			
		}
		

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}