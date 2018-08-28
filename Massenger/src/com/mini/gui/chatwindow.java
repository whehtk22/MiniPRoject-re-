package com.mini.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.io.*;
import java.net.*;
import javax.imageio.*;
/**
 *	Swing���� ����ϴ� Frame : JFrame 
 */
class Window02 extends JFrame{
	
//������Ʈ�� ��ġ�� ������ JPanel�� ����
	private JPanel con = new JPanel();
	
	
	private JMenuBar bar = new JMenuBar();
	private JMenu menu1 = new JMenu("����");
	private JMenu menu2 = new JMenu("ģ��");
	private JMenu menu3 = new JMenu("����");
	
	private JMenuItem item1 = new JMenuItem("�ɼ�1-1");
	private JMenuItem item2 = new JMenuItem("�ɼ�1-2");
	private JMenuItem item3 = new JMenuItem("�ɼ�1-3");
	
	private JMenuItem item4 = new JMenuItem("�ɼ�2-1");
	private JMenuItem item5 = new JMenuItem("�ɼ�2-2");
	private JMenuItem item6 = new JMenuItem("�ɼ�2-3");
	
	private JMenuItem item7 = new JMenuItem("�ɼ�3-1");
	private JMenuItem item8 = new JMenuItem("�ɼ�3-2");
	private JMenuItem item9 = new JMenuItem("�ɼ�3-3");
	
	private JButton btsend = new JButton("����");
	private JButton bticon1 = new JButton("file");
	private JButton bticon2 = new JButton("���Ϲ�ư");
	private JButton bticon3 = new JButton("���Ϲ�ư");
	private JButton bticon4 = new JButton("���Ϲ�ư");


	private JTextArea tx = new JTextArea("���� �޼��� �Է�");
	private JTextArea chat = new JTextArea("�ְ���� ���� ǥ�ÿ���");
	private JLabel lb2 = new JLabel("�� �̹��� ���� ǥ�� ����");
	private JLabel lb3 = new JLabel("��ȭ���� ���� ǥ�ÿ��� ");
	
	private Border b1 = BorderFactory.createLineBorder(Color.WHITE, 2, true);
	
	
	
	public Window02() {
		this.display();
		this.event();
		this.menu();
		
		this.setTitle("Swing ����");
//		this.setLocation(100, 100);
		this.setLocationByPlatform(true);
		this.setSize(500, 600);
		this.setResizable(false);
		this.setVisible(true);
	}
	/**
	 * ȭ�� ���� �޼ҵ�
	 */
	public void display() {
		this.setContentPane(con); 
		
		//this ���� con�� ������Ʈ �ؾ��� 
		
		con.setLayout(null);
		//con.setBackground(Color.BLACK);
		
		//�޴���
		bar.setBounds(0, 0, 495, 25);
		con.add(bar);
		
		//�޴� �߰���ư
		//����
		bar.add(menu1);
		menu1.add(item1);
		menu1.add(item2);
		menu1.add(item3);
		
		
		//ģ��
		bar.add(menu2);
		menu2.add(item4);
		menu2.add(item5);
		menu2.add(item6);
		
		//����
		bar.add(menu3);
		menu3.add(item7);
		menu3.add(item8);
		menu3.add(item9);
		
		
		//���۹�ư
		//x��ǥ y ��ǥ ���� ����
		btsend.setBounds(380, 456, 90, 96);
		con.add(btsend);
		
		//ä���Է�â
		tx.setText("text");
		tx.setBounds(12, 456, 360, 96);
		con.add(tx);

		tx.setColumns(80);
		
		//���� ǥ��â
		//chat.setBackground(Color.WHITE);
		chat.setBounds(12, 75, 355, 355);
		con.add(chat);		
		chat.setBorder(b1);

		
		
		
		//������ ǥ��â
		lb2.setBounds(12, 29, 458, 40);
		con.add(lb2);
		lb2.setBorder(b1);
		
		// ��� ǥ��â 
		lb3.setBounds(380, 75, 90, 355);
		con.add(lb3);
		lb3.setBorder(b1);
		
		
		//�ɼǾ����� 1��
		bticon1.setBounds(12, 431, 36, 23);
		con.add(bticon1);		


		
		//�ɼǾ����� 2��
		bticon2.setBounds(52, 431, 36, 23);
		con.add(bticon2);
		bticon2.setIcon(null);
		
		//�ɼǾ����� 3��
		bticon3.setBounds(92, 431, 36, 23);
		con.add(bticon3);
		bticon3.setIcon(null);
		
		
		//�ɼǾ����� 4��
		bticon4.setBounds(132, 431, 36, 23);
		con.add(bticon4);
		bticon4.setIcon(null);
		

	}
	/**
	 * �̺�Ʈ ���� �޼ҵ�
	 */
	public void event() {
		
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);//x�� ������ â �Ҹ�
		
		
	}
	/**
	 * �޴� ���� �޼ҵ�
	 */
	public void menu() {
		
	}
}

public class chatwindow {

	public static void main(String[] args) {
	
		Window02 window = new Window02();

		


	}
}
