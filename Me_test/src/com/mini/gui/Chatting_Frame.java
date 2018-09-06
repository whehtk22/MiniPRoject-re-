package com.mini.gui;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.mini.client.Connection;
/**
 *	Swing에서 사용하는 Frame : JFrame 
 */
public class Chatting_Frame extends JFrame {
	private Connection client_Net;

	//컴포넌트를 배치할 영역을 JPanel로 구현
	//컴포넌트를 배치할 영역을 JPanel로 구현
	private JPanel con = new JPanel();

	private JMenuBar bar = new JMenuBar();
	private JMenu file_menu = new JMenu("파일");
	private JMenu friend_menu = new JMenu("친구");
	private JMenu help_menu = new JMenu("도움말");

	private JMenuItem item1 = new JMenuItem("파일전송");
	private JMenuItem item2 = new JMenuItem("사진 전송");
	private JMenuItem item3 = new JMenuItem("대화창 종료");

	private JMenuItem item4 = new JMenuItem("대화 상대 추가");
	private JMenuItem item5 = new JMenuItem("옵션2-2");
	private JMenuItem item6 = new JMenuItem("옵션2-3");

	private JMenuItem item7 = new JMenuItem("프로그램 정보");
	private JMenuItem item8 = new JMenuItem("옵션3-2");
	private JMenuItem item9 = new JMenuItem("옵션3-3");

	private JButton input_bt = new JButton("전송");
	private JButton bticon1 = new JButton("파일전송");
	private JButton bticon2 = new JButton("이모티콘 전송");
	private JButton bticon3 = new JButton("파일버튼");
	private JButton bticon4 = new JButton("파일버튼");


	private JTextArea input_message = new JTextArea();
	private JScrollPane plinput_message = new JScrollPane(input_message,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private JTextArea chat = new JTextArea("");
	private JScrollPane plchat = new JScrollPane(chat);
	private JLabel lb2 = new JLabel("내 이미지 상태 표시 영역");
	private JLabel lb3 = new JLabel("대화중인 상대방 표시영역 ");
	private Border b1 = BorderFactory.createLineBorder(Color.black, 1, true);


	public JTextArea getChat() {
		return chat;
	}
	public void setChat(JTextArea chat) {
		this.chat = chat;
	}
	/**
	 * 화면 구현 메소드
	 */
	public void display() {
		setContentPane(con); 
		//this 말고 con에 컴포넌트 해야함 

		con.setLayout(null);
		//전송버튼
		//x좌표 y 좌표 가로 세로
		input_bt.setBounds(380, 456, 90, 96);
		con.add(input_bt);

		//채팅입력창
		plinput_message.setBounds(12, 456, 360, 96);
		con.add(plinput_message);
		plinput_message.setBorder(b1);
		input_message.setLineWrap(true);
		
		

		//내용 표시창
		plchat.setBounds(12, 75, 355, 355);
		con.add(plchat);
		chat.setLineWrap(true);
		chat.setEditable(false);
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
		
		input_message.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					//컨트롤 + 엔터키 입력시 다음줄 입력 
					if(e.isControlDown()){
						input_message.append(System.lineSeparator());
//						System.out.println("컨드롤 엔터");
					}else {
						String str = input_message.getText();
						System.out.println("실행확인"+Connection.id);
						client_Net.send(Connection.id+" : "+str);
						e.consume(); // 엔터입력시 줄 초기화 
						input_message.setText("");
					}
				}
			}
		});
		
		//전송버튼 이벤트 설정 
		input_bt.addActionListener(e->{
			String str = input_message.getText();
			client_Net.send(Connection.id+" : "+str);
//			System.out.println("여기에요");
			input_message.setText("");
		});
		
		//파일 전송버튼
		item1.addActionListener(e->{
			JFileChooser chooser = new JFileChooser(".");
			int choice = chooser.showOpenDialog(con);
			if(choice == 0) {
				File target = chooser.getSelectedFile();
			}
		});
		
		
		//사진 전송 버튼 
		item2.addActionListener(e->{
			JFileChooser chooser = new JFileChooser(".");
			chooser.setFileFilter(new FileNameExtensionFilter("PNG 이미지", "png"));
			chooser.setFileFilter(new FileNameExtensionFilter("JPEG 이미지", "jpg"));
			chooser.setFileFilter(new FileNameExtensionFilter("GIF 이미지", "gif"));
			int choice = chooser.showOpenDialog(con);
			if(choice == 0) {
				File target = chooser.getSelectedFile();
			}

		});
		
		// 종료버튼  이벤트 설정  
		item3.addActionListener(e->{
			int b = JOptionPane.showConfirmDialog(con,"정말 종료하시겠습니까?","확인",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
			if(b == 0) {
				setVisible(false);
			}
		});
 
		// 프로그램 정보 이벤트 설정 
		item7.addActionListener(e->{
			JOptionPane.showMessageDialog(con, "프로그램 구성내용 ","프로그램 정보",JOptionPane.INFORMATION_MESSAGE);
		});
		WindowListener w = new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				client_Net.send("종료종료");
			}
		};
		addWindowListener(w);

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
		bar.add(file_menu);
		file_menu.add(item1);
		file_menu.add(item2);
		file_menu.add(item3);


		//친구
		bar.add(friend_menu);
		friend_menu.add(item4);
		friend_menu.add(item5);
		friend_menu.add(item6);

		//도움말
		bar.add(help_menu);
		help_menu.add(item7);
		help_menu.add(item8);
		help_menu.add(item9);
		
		
		
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
		
		//사진전송 단축키
		KeyStroke ctrlp = KeyStroke.getKeyStroke(KeyEvent.VK_P,KeyEvent.CTRL_DOWN_MASK);
		item2.setAccelerator(ctrlp);		

	}
	//스레드 설정 및 문자받고 설정


	public Chatting_Frame(Connection client_Net,String roomName) {
		this.client_Net=client_Net;
		this.display();
		this.event();
		this.menu();
		//스레드시작(문자받기)

		this.setTitle(roomName);
		this.setLocationByPlatform(true);
		this.setSize(500, 600);
		this.setResizable(false);
		this.setVisible(true);
		Thread t = new Thread(r);
		t.start();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	Runnable r =()->{
		System.out.println("데이터 수신중");
		while(true) {
			String str=client_Net.receive();
			if(!str.equals("123123")) {
				chat.append(str+"\n");
				chat.setCaretPosition(chat.getDocument().getLength());
				System.out.println("수신");
			}else {
				
			}
		}
	};


}