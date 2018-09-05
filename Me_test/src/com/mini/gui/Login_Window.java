package com.mini.gui;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.Border;
import com.mini.client.Connection;
import com.mini.db.*;

/**
 * Swing에서 사용하는 Frame : JFrame
 */
public class Login_Window extends JFrame {


	private Connection serverCon;

	//컴포넌트를 배치할 영역을 JPanel로 구현
	// private ImageIcon logo = new ImageIcon("image/cap.png");
	private JPanel con = new JPanel();
	private JLabel main = new JLabel(new ImageIcon("image/cap.png"));
	private JLabel id = new JLabel("아이디 : ");
	private JLabel pw = new JLabel("비밀번호 : ");
	private JTextField idtx = new JTextField();
	private JPasswordField pwtx = new JPasswordField();
	private JButton login = new JButton("로그인");
	private JButton join = new JButton("회원가입");
	private Border b1 = BorderFactory.createLineBorder(Color.BLACK, 2, true);

	public Login_Window(Connection con) {
		this.serverCon =con;
		this.display();
		this.event();
		this.menu();

		this.setTitle("Swing 예제");
		this.setLocationByPlatform(true);
		this.setSize(300, 400);
		this.setResizable(false);
		this.setVisible(true);
	}

	/**
	 * 화면 구현 메소드
	 */
	public void display() {
		this.setContentPane(con);
		con.setLayout(null);

		// 메인 화면 (아이콘, 로고, 이름)
		// 다른아이콘 넣을 예정
		main.setBounds(12, 10, 260, 178);
		con.add(main);

		// 아이디
		id.setBounds(12, 198, 260, 37);
		con.add(id);

		// 비밀번호
		pw.setBounds(12, 245, 260, 37);
		con.add(pw);

		// 아이디 텍스트
		idtx.setBounds(100, 202, 151, 29);
		con.add(idtx);
		idtx.setColumns(20);
		idtx.setBorder(b1);

		// 비밀번호 텍스트
		pwtx.setBounds(100, 249, 151, 29);
		con.add(pwtx);
		pwtx.setColumns(20);
		pwtx.setBorder(b1);

		// 로그인 버튼
		login.setBounds(145, 292, 121, 37);
		con.add(login);

		// 회원가입 버튼
		join.setBounds(22, 292, 121, 37);
		con.add(join);
	}

	/**
	 * 이벤트 설정 메소드
	 */
	public void event() {

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);// x를 누르면 창 소멸

		join.addActionListener(e -> {
			try {
				serverCon.getOut().writeInt(Selection.JOIN);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Member_Join join = new Member_Join(serverCon);
		});

		login.addActionListener(e -> {
			try {
				serverCon.getOut().writeInt(Selection.LOGIN);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String id = this.idtx.getText();
			char[] p = pwtx.getPassword();
			StringBuffer bf = new StringBuffer();
			bf.append(p);
			Map<String, String> idCheck = new HashMap<>();
			idCheck.put(id, bf.toString());
			try {
				serverCon.getOut().writeObject(idCheck);
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			try {
			boolean ox= serverCon.getIn().readBoolean();
			System.out.println(ox);
				if(ox) {
						serverCon.setId(id);
						Lobby testLobby = new Lobby(serverCon);
//						serverCon.getOut().writeInt(Selection.CHAT);
//						Chatting_Frame g = new Chatting_Frame(serverCon);
					
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});

	}

	/**
	 * 메뉴 구현 메소드
	 */
	public void menu() {

	}
}
