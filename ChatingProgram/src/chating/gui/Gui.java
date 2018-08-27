package chating.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *	Swing에서 사용하는 Frame : JFrame 
 */
class Gui extends JFrame{
	
//	컴포넌트를 배치할 영역을 JPanel로 구현
	private JPanel con = new JPanel();
	private JTextArea text = new JTextArea(); 
	private JButton summit = new JButton("보내기");
	private JLabel status = new JLabel("상태표시",JLabel.CENTER);
	/**
	 * 화면 구현 메소드
	 */
	public void display() {
		this.setContentPane(con);//con을 Component 설정 영역으로 등록
		con.setLayout(new BorderLayout());
		con.add(status,BorderLayout.NORTH);
		con.add(text, BorderLayout.CENTER);
		con.add(summit, BorderLayout.SOUTH);
		
	}

	public void event() {

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);//x누르면 창 소멸		
		
	}
	/**
	 * 메뉴 구현 메소드
	 */
	public void menu() {
		
	}
	public Gui() {
		this.display();
		this.event();
		this.menu();
		
		this.setTitle("Swing 예제");
		this.setLocationByPlatform(true);
		this.setSize(400, 400);
		this.setResizable(false);
		this.setVisible(true);
	}
	
}
