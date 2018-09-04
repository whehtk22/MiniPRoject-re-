package com.mini.db;

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
import com.mini.gui.Dial;

/**
 * Ŭ���̾�Ʈ�� ä�� ������ �̹��� ������ �����ϴ� Ŭ����
 * @author user
 *
 */
public class Client_ChatData {
	private ClientUserDb user;
	private File target;
	private File folder;
	
	/*
	 * 
	 * ����� ������ ������ ���� �̸����� ä�� ������ �̹��� ������ ����
	 */
	public Client_ChatData(ClientUserDb user, String id) {
		super();
		this.user = user;
		this.target = new File(id+"chatlog");
		target.mkdirs();
		this.folder = new File(id+"Image");
		folder.mkdirs();
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
			System.err.println("ä�ó����� ������� �ʾҽ��ϴ�.");
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
				System.err.println("�̹��� ���� ����");
			}	
		}
	}
	
}