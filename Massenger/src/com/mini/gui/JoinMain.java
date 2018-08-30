package com.mini.gui;

import java.awt.*;
import com.mini.*;
import com.mini.client.ClientData;
import com.mini.db.ClientUserDb;

import com.mini.db.*;

import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

/**
 *	Swing에서 사용하는 Frame : JFrame 
 */
class Window03 extends JFrame{
	
//컴포넌트를 배치할 영역을 JPanel로 구현
	private JPanel con = new JPanel();
	private JLabel jointext = new JLabel("회원가입");
	private JLabel namelabel = new JLabel("이름");
	private JLabel idlabel = new JLabel("아이디");
	private JLabel pwlabel = new JLabel("비밀번호");
	private JLabel pwolabel = new JLabel("비밀번호 확인");
	
	private JButton btok = new JButton("확인");
	private JButton btcan = new JButton("취소");
	
	private JTextField namearea = new JTextField();
	private JTextField idarea = new JTextField();
	private JPasswordField pwfield = new JPasswordField();
	private JPasswordField pwofield = new JPasswordField();
	
	
	public Window03() {
		this.display();
		this.event();
		this.menu();
		
		this.setTitle("Swing 예제");
//		this.setLocation(100, 100);
		this.setLocationByPlatform(true);
		this.setSize(450, 500);
		this.setResizable(false);
		this.setVisible(true);
	}
	/**
	 * 화면 구현 메소드
	 */
	public void display() {
		this.setContentPane(con); // con을 conponent 설정영역으로 등록
		
		//this 말고 con에 컴포넌트 해야함 
		
		
		con.setLayout(null);
		
		//회원가입 글자 창 
		jointext.setBounds(12, 10, 420, 59);
		con.add(jointext);
		jointext.setFont(new Font("굴림",Font.BOLD,30));
		jointext.setHorizontalAlignment(JLabel.CENTER);
		
		//확인버튼
		btok.setBounds(118, 392, 97, 23);
		con.add(btok);
		
		//취소버튼
		btcan.setBounds(238, 392, 97, 23);
		con.add(btcan);
		
		//이름안내
		namelabel.setBounds(12, 103, 460, 48);
		con.add(namelabel);
		
		//아이디 안내
		idlabel.setBounds(12, 182, 460, 48);
		con.add(idlabel);
		
		//비밀번호 안내
		pwlabel.setBounds(12, 258, 460, 48);
		con.add(pwlabel);
		
		//비밀번호확인안내
		pwolabel.setBounds(12, 316, 460, 48);
		con.add(pwolabel);
		
		//이름 입력 텍스트
		namearea.setBounds(110, 111, 200, 34);
		con.add(namearea);
		namearea.setColumns(15);
		
		
		//아이디 입력 텍스트
		idarea.setBounds(110, 190, 200, 34);
		con.add(idarea);
		idarea.setColumns(15);
		
	
		//비밀번호 입력 텍스트
		pwfield.setBounds(110, 266, 200, 34);
		con.add(pwfield);
		pwfield.setColumns(20);
		
		//비밀번호 확인 텍스트 
		pwofield.setBounds(110, 324, 200, 34);
		con.add(pwofield);
		pwofield.setColumns(20);
	}
	/**
	 * 이벤트 설정 메소드
	 */
	public void event() {

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);//x를 누르면 창 소멸
		
		//취소 버튼 누를시 종료 
		btcan.addActionListener(e->{
			System.exit(0);
		});
		
		//확인 버튼 입력시 
		btok.addActionListener(e->{
			String id = idarea.getText();
			String pw = pwfield.getText();
			String name = namearea.getText();
			System.out.println(name);
			
//			비밀번호를 똑같이 입력했는지 검사
			if(pwfield.getText().equals(pwofield.getText())) {
			/**
			 * 회원가입 부분
			 * 아이디 비밀번호 이름 입력후 clientUserDb를 통해 저장
			 */
				ClientUserDb userInfo = new ClientUserDb(pw, name);
				
				/**
				 * clientData 생성자를 통해 유저의 고유 파일을 생성
				 */
				try {
					ClientData data = new ClientData(userInfo, id);
//					[3] clientData 객체 생성
					/**
					 * clientUserData에 있는 객체를 서버에서 파일로 저장
					 */
					data.clientUserSave(userInfo, id);
				} catch (Exception e1) {}
				
				}
				else {
					System.out.println("비밀번호가 일치하지 않습니다..");
				}
			
		});

	}
	/**
	 * 메뉴 구현 메소드
	 */
	public void menu() {
		
	}
	
}

public class JoinMain {

	public static void main(String[] args) {

		Window03 window = new Window03();

	}
}
