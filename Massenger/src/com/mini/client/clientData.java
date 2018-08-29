package com.mini.client;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.mini.db.clientUserDb;
import com.mini.gui.Dial;

public class clientData {
	
	private clientUserDb user;
	private File target = new File("Text","Text.db");
	private File folder;
	private File userDataFolder;
	
	
	
	public clientData(clientUserDb user) {
		super();
		this.user = user;
		this.folder = new File("Image");
		folder.mkdirs();
		userDataFolder = new File(user.getUser_name()+"files");
		userDataFolder.mkdirs();
	}
	public void clientUserSave(clientUserDb userInfo)  throws IOException {
		
		Map<String, clientUserDb> map = new HashMap<>();
		
		map.put(userInfo.getUser_name(), userInfo);
		
		File f = new File(userDataFolder, "yourdb.db");
		f.createNewFile();
		
		ObjectOutputStream out = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(f)));
				
			
		out.writeObject(map);
		
		out.flush();
//		out.close();
	}
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
			
			
			
			JFileChooser chooser = new JFileChooser(folder);
			
			chooser.setFileFilter(new FileNameExtensionFilter("PNG 이미지", "png"));
			chooser.setFileFilter(new FileNameExtensionFilter("JPEG 이미지", "jpg"));
			chooser.setFileFilter(new FileNameExtensionFilter("GIF 이미지", "gif"));
			int choice = chooser.showSaveDialog(new Dial());
			System.out.println("이미지 저장");
			if(choice == 0) {
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
	
}
