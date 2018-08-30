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
	 * joinmain���� ����
	 * ����� ������ ������ ���� �̸����� ������ ����
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
	 * clientUserDb�� ������ �����ʿ��� ���Ϸ� �����ϴ� �޼ҵ�
	 * �ȿ� ����ִ� ���� ������ Map �������� �ۼ��Ǿ� ����
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
	 * ������� ��ȭ������ �����ϴ� �޼ҵ�
	 * ������ Ŭ���̾�Ʈ �ʿ����� �ϱ�� ����
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
	 * ����ڰ� ���� �̹����� �����ϴ� �޼ҵ�
	 * Ŭ���̾�Ʈ�� ������
	 * @param bf ���� �̹����� BufferedImage�� ��ȯ
	 */
	public void saveImage(BufferedImage bf) {
			
			
			
			JFileChooser chooser = new JFileChooser(folder);
			
			chooser.setFileFilter(new FileNameExtensionFilter("PNG �̹���", "png"));
			chooser.setFileFilter(new FileNameExtensionFilter("JPEG �̹���", "jpg"));
			chooser.setFileFilter(new FileNameExtensionFilter("GIF �̹���", "gif"));
			int choice = chooser.showSaveDialog(new Dial());
			System.out.println("�̹��� ����");
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
