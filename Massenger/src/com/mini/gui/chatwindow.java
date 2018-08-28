package com.mini.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.io.*;
import java.net.*;
import javax.imageio.*;
/**
 *	Swing에서 사용하는 Frame : JFrame 
 */
class Window02 extends JFrame{
	
//컴포넌트를 배치할 영역을 JPanel로 구현
	private JPanel con = new JPanel();
	
	
	private JMenuBar bar = new JMenuBar();
	private JMenu menu1 = new JMenu("파일");
	private JMenu menu2 = new JMenu("친구");
	private JMenu menu3 = new JMenu("도움말");
	
	private JMenuItem item1 = new JMenuItem("옵션1-1");
	private JMenuItem item2 = new JMenuItem("옵션1-2");
	private JMenuItem item3 = new JMenuItem("옵션1-3");
	
	private JMenuItem item4 = new JMenuItem("옵션2-1");
	private JMenuItem item5 = new JMenuItem("옵션2-2");
	private JMenuItem item6 = new JMenuItem("옵션2-3");
	
	private JMenuItem item7 = new JMenuItem("옵션3-1");
	private JMenuItem item8 = new JMenuItem("옵션3-2");
	private JMenuItem item9 = new JMenuItem("옵션3-3");
	
	private JButton btsend = new JButton("전송");
	private JButton bticon1 = new JButton("file");
	private JButton bticon2 = new JButton("파일버튼");
	private JButton bticon3 = new JButton("파일버튼");
	private JButton bticon4 = new JButton("파일버튼");


	private JTextArea tx = new JTextArea("보낼 메세지 입력");
	private JTextArea chat = new JTextArea("주고받은 내용 표시영역");
	private JLabel lb2 = new JLabel("내 이미지 상태 표시 영역");
	private JLabel lb3 = new JLabel("대화중인 상대방 표시영역 ");
	
	private Border b1 = BorderFactory.createLineBorder(Color.WHITE, 2, true);
	
	
	
	public Window02() {
		this.display();
		this.event();
		this.menu();
		
		this.setTitle("Swing 예제");
//		this.setLocation(100, 100);
		this.setLocationByPlatform(true);
		this.setSize(500, 600);
		this.setResizable(false);
		this.setVisible(true);
	}
	/**
	 * 화면 구현 메소드
	 */
	public void display() {
		this.setContentPane(con); 
		
		//this 말고 con에 컴포넌트 해야함 
		
		con.setLayout(null);
		//con.setBackground(Color.BLACK);
		
		//메뉴바
		bar.setBounds(0, 0, 495, 25);
		con.add(bar);
		
		//메뉴 추가버튼
		//파일
		bar.add(menu1);
		menu1.add(item1);
		menu1.add(item2);
		menu1.add(item3);
		
		
		//친구
		bar.add(menu2);
		menu2.add(item4);
		menu2.add(item5);
		menu2.add(item6);
		
		//도움말
		bar.add(menu3);
		menu3.add(item7);
		menu3.add(item8);
		menu3.add(item9);
		
		
		//전송버튼
		//x좌표 y 좌표 가로 세로
		btsend.setBounds(380, 456, 90, 96);
		con.add(btsend);
		
		//채팅입력창
		tx.setText("text");
		tx.setBounds(12, 456, 360, 96);
		con.add(tx);

		tx.setColumns(80);
		
		//내용 표시창
		//chat.setBackground(Color.WHITE);
		chat.setBounds(12, 75, 355, 355);
		con.add(chat);		
		chat.setBorder(b1);

		
		
		
		//내상태 표시창
		lb2.setBounds(12, 29, 458, 40);
		con.add(lb2);
		lb2.setBorder(b1);
		
		// 상대 표시창 
		lb3.setBounds(380, 75, 90, 355);
		con.add(lb3);
		lb3.setBorder(b1);
		
		
		//옵션아이콘 1번
		bticon1.setBounds(12, 431, 36, 23);
		con.add(bticon1);		


		
		//옵션아이콘 2번
		bticon2.setBounds(52, 431, 36, 23);
		con.add(bticon2);
		bticon2.setIcon(null);
		
		//옵션아이콘 3번
		bticon3.setBounds(92, 431, 36, 23);
		con.add(bticon3);
		bticon3.setIcon(null);
		
		
		//옵션아이콘 4번
		bticon4.setBounds(132, 431, 36, 23);
		con.add(bticon4);
		bticon4.setIcon(null);
		

	}
	/**
	 * 이벤트 설정 메소드
	 */
	public void event() {
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);//x를 누르면 창 소멸
		
		
	}
	/**
	 * 메뉴 구현 메소드
	 */
	public void menu() {
		
	}
}

public class chatwindow {

	public static void main(String[] args) {
	
		Window02 window = new Window02();

		


	}
}
