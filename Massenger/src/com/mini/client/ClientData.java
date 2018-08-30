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
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.mini.db.FriendsDb;
import com.mini.db.ClientUserDb;
import com.mini.gui.Dial;

import src.com.mini.client.clientUserDb;

public class ClientData {
	private ClientUserDb user;
	private File target = new File("Text","Text.db");
	private File folder;
	private File userDataFolder;
	
	
	/*
	 * joinmain에서 실행
	 * 실행시 생성한 유저의 고유 이름으로 폴더를 생성
	 */
	public ClientData(ClientUserDb user, String id) {
		super();
		this.user = user;
		this.folder = new File("Image");
		folder.mkdirs();
		userDataFolder = new File(id+"files");
		userDataFolder.mkdirs();
	}
	
	public void friendsSave(String friend) {
		
		FriendsDb fr = new FriendsDb(friend);
		
	}
	
	/**
	 * com.mini.db
	 * clientUserDb의 정보를 서버쪽에서 파일로 저장하는 메소드
	 * 안에 들어있는 파일 내용은 Map 형식으로 작성되어 있음
	 * @param user
	 */
	public void clientUserSave(ClientUserDb userInfo, String id)  throws IOException {
		
		Map<String, ClientUserDb> map = new HashMap<>();
		
		map.put(id, userInfo);
		
		
		File f = new File(userDataFolder, "yourdb.db");
		f.createNewFile();
		
		ObjectOutputStream out = new ObjectOutputStream(
				new BufferedOutputStream(new FileOutputStream(f)));
				
			
		out.writeObject(map);
		
		out.flush();
//		out.close();
	}
	
	
	/**
	 * 사용자의 대화내용을 저장하는 메소드
	 * 저장은 클라이언트 쪽에서만 하기로 결정
	 * @param line
	 */
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
	

	/**
	 * 사용자가 받은 이미지를 저장하는 메소드
	 * 클라이언트만 저장함
	 * @param bf 받은 이미지를 BufferedImage로 변환
	 */
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
