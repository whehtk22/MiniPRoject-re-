package chating.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *	Swing���� ����ϴ� Frame : JFrame 
 */
class Gui extends JFrame{
	
//	������Ʈ�� ��ġ�� ������ JPanel�� ����
	private JPanel con = new JPanel();
	private JTextArea text = new JTextArea(); 
	private JButton summit = new JButton("������");
	private JLabel status = new JLabel("����ǥ��",JLabel.CENTER);
	/**
	 * ȭ�� ���� �޼ҵ�
	 */
	public void display() {
		this.setContentPane(con);//con�� Component ���� �������� ���
		con.setLayout(new BorderLayout());
		con.add(status,BorderLayout.NORTH);
		con.add(text, BorderLayout.CENTER);
		con.add(summit, BorderLayout.SOUTH);
		
	}

	public void event() {

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);//x������ â �Ҹ�		
		
	}
	/**
	 * �޴� ���� �޼ҵ�
	 */
	public void menu() {
		
	}
	public Gui() {
		this.display();
		this.event();
		this.menu();
		
		this.setTitle("Swing ����");
		this.setLocationByPlatform(true);
		this.setSize(400, 400);
		this.setResizable(false);
		this.setVisible(true);
	}
	
}
