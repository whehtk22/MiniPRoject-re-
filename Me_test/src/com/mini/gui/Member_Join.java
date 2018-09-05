package com.mini.gui;

import java.awt.*;
import com.mini.*;
import com.mini.client.Connection;
import com.mini.db.Client_Private;
import com.mini.db.ClientUserDb;

import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

/**
 * Swing에서 사용하는 Frame : JDialog
 */
public class Member_Join extends JDialog {
	
	private Connection serverCon;

//컴포넌트를 배치할 영역을 JPanel로 구현
	private JPanel con = new JPanel();
	private JLabel jointext = new JLabel("회원가입");
	private JLabel namelabel = new JLabel("이름");
	private JLabel idlabel = new JLabel("아이디");
	private JLabel pwlabel = new JLabel("비밀번호");
	private JLabel pwolabel = new JLabel("비밀번호 확인");

	private JButton btok = new JButton("확인");
	private JButton btcan = new JButton("취소");

	private JTextField nameArea = new JTextField();
	private JTextField idarea = new JTextField();
	private JPasswordField pwfield = new JPasswordField();
	private JPasswordField pwfield1 = new JPasswordField();
	private JPasswordField pwofield = new JPasswordField();

	public Member_Join(Connection con) {
		this.serverCon=con;
		this.display();
		this.event();
		this.menu();

		this.setTitle("Swing 예제");
		this.setLocationByPlatform(true);
		this.setSize(450, 500);
		this.setResizable(false);
		this.setVisible(true);
	}

	/**
	 * 화면 구현 메소드
	 */
	public void display() {
		this.setContentPane(con); 
		con.setLayout(null);

		// 회원가입 글자 창
		jointext.setBounds(12, 10, 420, 59);
		con.add(jointext);
		jointext.setFont(new Font("굴림", Font.BOLD, 30));
		jointext.setHorizontalAlignment(JLabel.CENTER);

		// 확인버튼
		btok.setBounds(118, 392, 97, 23);
		con.add(btok);

		// 취소버튼
		btcan.setBounds(238, 392, 97, 23);
		con.add(btcan);

		// 이름안내
		namelabel.setBounds(12, 103, 460, 48);
		con.add(namelabel);

		// 아이디 안내
		idlabel.setBounds(12, 182, 460, 48);
		con.add(idlabel);

		// 비밀번호 안내
		pwlabel.setBounds(12, 258, 460, 48);
		con.add(pwlabel);

		// 비밀번호확인안내
		pwolabel.setBounds(12, 316, 460, 48);
		con.add(pwolabel);

		// 이름 입력 텍스트
		nameArea.setBounds(110, 111, 200, 34);
		con.add(nameArea);
		nameArea.setColumns(15);

		// 아이디 입력 텍스트
		idarea.setBounds(110, 190, 200, 34);
		con.add(idarea);
		idarea.setColumns(15);

		// 비밀번호 입력 텍스트
		pwfield.setBounds(110, 266, 200, 34);
		con.add(pwfield);
		pwfield.setColumns(20);

		// 비밀번호 확인 텍스트
		pwofield.setBounds(110, 324, 200, 34);
		con.add(pwofield);
		pwofield.setColumns(20);
	}

	/**
	 * 이벤트 설정 메소드
	 */
	public void event() {

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);// x를 누르면 창 소멸

		// 취소 버튼 누를시 종료
		btcan.addActionListener(e -> {
			System.exit(0);
		});

		// 확인 버튼 입력시
		btok.addActionListener(e -> {
			String id = idarea.getText();
			char[] pw = pwfield.getPassword();
			char[] pwo = pwofield.getPassword();
			String name = nameArea.getText();
			StringBuffer sb1 = new StringBuffer();
			StringBuffer sb2 = new StringBuffer();
			sb1.append(pw);
			sb2.append(pwo);
//			비밀번호를 똑같이 입력했는지 검사

				/**
				 * 회원가입 부분 아이디 비밀번호 이름 입력후 clientUserDb를 통해 저장
				 */
				try {

					ClientUserDb userInfo = new ClientUserDb(id, sb1.toString(), name);
					if(sb1.toString().equals(sb2.toString())) {
						serverCon.getOut().writeObject(userInfo);
						serverCon.getOut().flush();
						boolean ox = serverCon.getIn().readBoolean();
						System.out.println("여기 : "+ox);	
						if(ox) {
							JOptionPane.showMessageDialog(con, "이미 존재하는 아이디 입니다.");
						}
						else {
							JOptionPane.showMessageDialog(con, "회원가입을 성공하셨습니다.", "회원가입성공", JOptionPane.INFORMATION_MESSAGE);
							dispose();
						}
					}
					else {
						JOptionPane.showMessageDialog(con, "비밀번호가 일치하지 않습니다.");
					}
				
				}catch(Exception e1) {
					e1.printStackTrace();
				}
				/**
				 * Client_Private 생성자를 통해 유저의 고유 파일을 생성
				 */
				
		});

	}

	/**
	 * 메뉴 구현 메소드
	 */
	public void menu() {

	}

}
