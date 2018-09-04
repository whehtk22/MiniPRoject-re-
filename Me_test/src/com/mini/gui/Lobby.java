package com.mini.gui;

import java.awt.*;
import javax.swing.*;

/**
 *	Swing에서 사용하는 Frame : JFrame 
 */
public class Lobby extends JFrame{
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
		
		});
		jt2.addActionListener(e->{
		});
	}
	
	public Lobby () {
		System.out.println("로비?");
		System.out.println("디스플레이?");
		this.display();
		this.event();
		this.setLocationByPlatform(true);
		this.setSize(300, 300);
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
}
