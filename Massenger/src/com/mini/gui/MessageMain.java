package com.mini.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

import com.mini.db.*;

/**
 *	Swing���� ����ϴ� Frame : JFrame 
 */
class Window01 extends JFrame{
	
//������Ʈ�� ��ġ�� ������ JPanel�� ����
	//private ImageIcon logo = new ImageIcon("image/cap.png");
	private JPanel con = new JPanel();
	private JLabel main = new JLabel(new ImageIcon("image/cap.png"));
	private JLabel id = new JLabel("���̵� : ");
	private JLabel pw = new JLabel("��й�ȣ : ");
	private JTextArea idtx = new JTextArea();
	private JPasswordField pwtx = new JPasswordField();
	private JButton login = new JButton("�α���");
	private JButton join = new JButton("ȸ������");
	private Border b1 = BorderFactory.createLineBorder(Color.BLACK, 2, true);
	
	
	public Window01() {
		this.display();
		this.event();
		this.menu();
		
		this.setTitle("Swing ����");
//		this.setLocation(100, 100);
		this.setLocationByPlatform(true);
		this.setSize(300, 400);
		this.setResizable(false);
		this.setVisible(true);
	}
	/**
	 * ȭ�� ���� �޼ҵ�
	 */
	public void display() {
		this.setContentPane(con); // con�� conponent ������������ ���
		
		//this ���� con�� ������Ʈ �ؾ��� 
		
		
		con.setLayout(null);
		
		//���� ȭ�� (������, �ΰ�, �̸�)
		//�ٸ������� ���� ����
		main.setBounds(12, 10, 260, 178);
		con.add(main);
		
		
		//���̵� 
		id.setBounds(12, 198, 260, 37);
		con.add(id);
		
		
		//��й�ȣ
		pw.setBounds(12, 245, 260, 37);
		con.add(pw);
		
		//���̵� �ؽ�Ʈ
		idtx.setBounds(100, 202, 151, 29);
		con.add(idtx);
		idtx.setColumns(20);
		idtx.setBorder(b1);
		
		
		// ��й�ȣ �ؽ�Ʈ
		pwtx.setBounds(100, 249, 151, 29);
		con.add(pwtx);
		pwtx.setColumns(20);
		pwtx.setBorder(b1);
		
		//�α��� ��ư
		login.setBounds(145, 292, 121, 37);
		con.add(login);
		
		//ȸ������ ��ư
		join.setBounds(22, 292, 121, 37);
		con.add(join);
	}
	/**
	 * �̺�Ʈ ���� �޼ҵ�
	 */
	public void event() {

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);//x�� ������ â �Ҹ�
			
		join.addActionListener(e->{
				JoinGui join = new JoinGui();
			});
		
		login.addActionListener(e->{
		
			String id = this.idtx.getText();
			String pw = this.pwtx.getText();
			String pw2 = pw.toString();
		
			DataInfoOpen open = new DataInfoOpen(id);
		
			if(open.login(id, pw)) {
				System.out.println("�α��� ����!");
				ChatWindow_1 chatWindow = new ChatWindow_1();
			}
			else {
				System.out.println("�α��� ����!");
			}
		});
		
	}
	/**
	 * �޴� ���� �޼ҵ�
	 */
	public void menu() {
		
	}
}

public class MessageMain {
	public static void main(String[] args) {

	
		Window01 window = new Window01();

	}
}
