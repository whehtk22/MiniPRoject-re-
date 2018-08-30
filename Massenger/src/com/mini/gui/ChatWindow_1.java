package com.mini.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

/**
 *	Swing에서 사용하는 Frame : JFrame 
 */
class Window07 extends JFrame{
	
//컴포넌트를 배치할 영역을 JPanel로 구현
	private JPanel con = new JPanel();
	private JMenuBar bar = new JMenuBar();
	private JMenu menu1 = new JMenu("파일");
	private JMenu menu2 = new JMenu("친구");
	private JMenu menu3 = new JMenu("도움말");
	
	private JMenuItem item1 = new JMenuItem("파일전송");
	private JMenuItem item2 = new JMenuItem("사진 전송");
	private JMenuItem item3 = new JMenuItem("대화창 종료");
	
	private JMenuItem item4 = new JMenuItem("대화 상대 추가");
	private JMenuItem item5 = new JMenuItem("옵션2-2");
	private JMenuItem item6 = new JMenuItem("옵션2-3");
	
	private JMenuItem item7 = new JMenuItem("프로그램 정보");
	private JMenuItem item8 = new JMenuItem("옵션3-2");
	private JMenuItem item9 = new JMenuItem("옵션3-3");
	
	private JButton btsend = new JButton("전송");
	private JButton bticon1 = new JButton("파일전송");
	private JButton bticon2 = new JButton("이모티콘 전송");
	private JButton bticon3 = new JButton("파일버튼");
	private JButton bticon4 = new JButton("파일버튼");

	private JTextArea tx = new JTextArea("보낼 메세지 입력");
	private JScrollPane pltx = new JScrollPane(tx, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private JTextField chat = new JTextField("주고받은 내용 표시영역");
	private JScrollPane plchat = new JScrollPane(chat);
	private JLabel lb2 = new JLabel("내 이미지 상태 표시 영역");
	private JLabel lb3 = new JLabel("대화중인 상대방 표시영역 ");
	private Border b1 = BorderFactory.createLineBorder(Color.black, 1, true);
	
	
	
	public Window07() {
		this.frame();
		this.display();
		this.menu();
		this.event();
		
		
	}
	
	public void frame() {
		this.setTitle("xxx 님과 대화중");
//		this.setLocation(100, 100);
		this.setLocationByPlatform(true);
		this.setSize(500, 600);
		this.setResizable(true);
		this.setVisible(true);
	}
	
	/**
	 * 화면 구현 메소드
	 */
	public void display() {
		this.setContentPane(con); 

		//this 말고 con에 컴포넌트 해야함 
		
		con.setLayout(null);

		//전송버튼
		btsend.setBounds(380, 456, 90, 96);
		con.add(btsend);
		
		//텍스트 입력창 
		con.add(pltx);
		pltx.setBounds(12, 456, 360, 96);
		pltx.setBorder(b1);
		
		//내용 표시창

		plchat.setBounds(12, 75, 355, 355);
		con.add(plchat);			
		plchat.setBorder(b1);
		tx.setLineWrap(true);

		
		
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
		
		btsend.addActionListener(e->{
			//String s = tx.getText();
			String s = tx.getText();
			System.out.println(s);

			tx.setText("");
		});
		
		item2.addActionListener(e->{
			JOptionPane.showConfirmDialog(null,"옵션1","옵션2",JOptionPane.YES_NO_OPTION);
		});
		
		item3.addActionListener(e->{
			int b = JOptionPane.showConfirmDialog(null,"정말 종료하시겠습니까?","확인",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
			if(b == 0) {
				setVisible(false);
			}
		});
		
		bticon1.addActionListener(e->{
			
		});
		
		item7.addActionListener(e->{
			JOptionPane.showMessageDialog(null, "프로그램 구성내용 ","프로그램 정보",JOptionPane.INFORMATION_MESSAGE);
		});


		
	}


	/**
	 * 메뉴 구현 메소드
	 */
	public void menu() {
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
		
		//종료버튼 단축키
		KeyStroke esc = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0);
		item3.setAccelerator(esc);
		
		//파일전송 버튼 단축키
		KeyStroke ctrls = KeyStroke.getKeyStroke(KeyEvent.VK_S,KeyEvent.CTRL_DOWN_MASK);
		item1.setAccelerator(ctrls);
		
		//친구 추가 단축키
		
		KeyStroke ctrli = KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.CTRL_DOWN_MASK);
		item4.setAccelerator(ctrli);
		
		//프로그램 정보 단축키
		KeyStroke fun11 = KeyStroke.getKeyStroke(KeyEvent.VK_F11,0);
		item7.setAccelerator(fun11);
		
		//전송버튼 단축키
		KeyStroke ent = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
	
		
	}
}

public class ChatWindow_1 {

	public static void main(String[] args) {
	
		Window07 window = new Window07();

		


	}
}
