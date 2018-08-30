package com.mini.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import com.mini.client.Client_cennection;
import com.mini.client.JoinGroups;
import com.mini.client.UniClient;

/**
 *	Swing에서 사용하는 Frame : JFrame 
 */
public class Lobby extends JFrame{
	private Client_cennection cm;
	//컴포넌트를 배치할 영역을 JPanel로 구현
	private JPanel con = new JPanel();
	private JLabel jl = new JLabel("메신저 테스트");
	private JButton jt1 = new  JButton("채팅창 생성");
	private JButton jt2 = new JButton("채팅방 찾기");
	/**
	 * 화면 구현 메소드
	 */
	public void display() {
		this.setContentPane(con); // con을 conponent 설정영역으로 등록

		con.setLayout(null);


		jl.setBounds(12, 10, 260, 72);
		con.add(jl);
		jl.setFont(new Font("굴림",Font.BOLD,20));


		jt1.setBounds(12, 123, 110, 72);
		con.add(jt1);


		jt2.setBounds(162, 123, 110, 72);
		con.add(jt2);
	}
	public void event() {
		jt1.addActionListener(e->{
			cm.receiveNewRoomPort();//채팅방 이름을 전송하고 해당 채팅방의 port번호를 받는다.
			JoinGroups jg =new JoinGroups(cm.getChatPort());//받은 포트 전달하여 채팅방에 연결되기 위해 필요한 group정보와 port번호를 부여
//			System.out.println(cm.getChatPort());
			cm.createRoom(jg);//클라이언트가 채팅방에 연결되게설정
			this.dispose();//기존 창 닫음
			chatwindow guiMain = new chatwindow(cm);//채팅창 업로드
		});
		jt2.addActionListener(e->{
			SelectToJoinGroupList stjg = new SelectToJoinGroupList(cm);
		});
	}
	
	public Lobby (Client_cennection cm) {
		this.cm= cm;
		this.display();
		this.event();
		this.setLocationByPlatform(true);
		this.setSize(300, 300);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
