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
			chooser.setFileFilter(new FileNameExtensionFilter("PNG 이미지", "png"));
			chooser.setFileFilter(new FileNameExtensionFilter("JPEG 이미지", "jpg"));
			chooser.setFileFilter(new FileNameExtensionFilter("GIF 이미지", "gif"));
			int choice = chooser.showSaveDialog(new Dial( ));
			System.out.println("이미지 저장");
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
			
			
			
			System.out.println("이미지 저장");
			
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
		 * 클라이언트 사용자가 직접 정보를 List로 저장후 파일에 다시 저장하는 메소드
		 * - 저장하는 정보
		 * - 유저 Id, password, 이름, 친구목록
		 * @throws IOException
		 */
		public void clientUserSave() throws IOException {
			
			Map<String, Object> map = new HashMap<>();
			
			serverUserDb user = new serverUserDb();
			
			map.put(user.getName(), user);
			
//			파일에  yourDb.db에 저장합니다.
			File userFile = new File("files", "yourDb.db");
			
			
			ObjectOutputStream out = new ObjectOutputStream(
					new BufferedOutputStream(new FileOutputStream(userFile)));
					
				
			out.writeObject(map);
			
			out.flush();
		}
		
		public void serverUserOpen() {
			
			
			
		}
		

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
}