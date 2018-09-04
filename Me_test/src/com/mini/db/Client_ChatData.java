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
 * 클라이언트의 채팅 내역과 이미지 파일을 저장하는 클래스
 * @author user
 *
 */
public class Client_ChatData {
	private ClientUserDb user;
	private File target;
	private File folder;
	
	/*
	 * 
	 * 실행시 생성한 유저의 고유 이름으로 채팅 내역과 이미지 폴더를 생성
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
			System.err.println("채팅내역이 저장되지 않았습니다.");
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
				System.err.println("이미지 저장 실패");
			}	
		}
	}
	
}