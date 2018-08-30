package com.mini.gui;

import java.awt.*;
import com.mini.*;
import com.mini.client.ClientData;
import com.mini.db.ClientUserDb;

import java.awt.event.*;
import java.io.IOException;

import javax.swing.*;

/**
 *	Swing���� ����ϴ� Frame : JFrame 
 */
class Window03 extends JFrame{
	
//������Ʈ�� ��ġ�� ������ JPanel�� ����
	private JPanel con = new JPanel();
	private JLabel jointext = new JLabel("ȸ������");
	private JLabel namelabel = new JLabel("�̸�");
	private JLabel idlabel = new JLabel("���̵�");
	private JLabel pwlabel = new JLabel("��й�ȣ");
	private JLabel pwolabel = new JLabel("��й�ȣ Ȯ��");
	
	private JButton btok = new JButton("Ȯ��");
	private JButton btcan = new JButton("���");
	
	private JTextField namearea = new JTextField();
	private JTextField idarea = new JTextField();
	private JPasswordField pwfield = new JPasswordField();
	private JPasswordField pwofield = new JPasswordField();
	
	
	public Window03() {
		this.display();
		this.event();
		this.menu();
		
		this.setTitle("Swing ����");
//		this.setLocation(100, 100);
		this.setLocationByPlatform(true);
		this.setSize(450, 500);
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
		
		//ȸ������ ���� â 
		jointext.setBounds(12, 10, 420, 59);
		con.add(jointext);
		jointext.setFont(new Font("����",Font.BOLD,30));
		jointext.setHorizontalAlignment(JLabel.CENTER);
		
		//Ȯ�ι�ư
		btok.setBounds(118, 392, 97, 23);
		con.add(btok);
		
		//��ҹ�ư
		btcan.setBounds(238, 392, 97, 23);
		con.add(btcan);
		
		//�̸��ȳ�
		namelabel.setBounds(12, 103, 460, 48);
		con.add(namelabel);
		
		//���̵� �ȳ�
		idlabel.setBounds(12, 182, 460, 48);
		con.add(idlabel);
		
		//��й�ȣ �ȳ�
		pwlabel.setBounds(12, 258, 460, 48);
		con.add(pwlabel);
		
		//��й�ȣȮ�ξȳ�
		pwolabel.setBounds(12, 316, 460, 48);
		con.add(pwolabel);
		
		//�̸� �Է� �ؽ�Ʈ
		namearea.setBounds(110, 111, 200, 34);
		con.add(namearea);
		namearea.setColumns(15);
		
		
		//���̵� �Է� �ؽ�Ʈ
		idarea.setBounds(110, 190, 200, 34);
		con.add(idarea);
		idarea.setColumns(15);
		
	
		//��й�ȣ �Է� �ؽ�Ʈ
		pwfield.setBounds(110, 266, 200, 34);
		con.add(pwfield);
		pwfield.setColumns(20);
		
		//��й�ȣ Ȯ�� �ؽ�Ʈ 
		pwofield.setBounds(110, 324, 200, 34);
		con.add(pwofield);
		pwofield.setColumns(20);
	}
	/**
	 * �̺�Ʈ ���� �޼ҵ�
	 */
	public void event() {

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);//x�� ������ â �Ҹ�
		
		//��� ��ư ������ ���� 
		btcan.addActionListener(e->{
			System.exit(0);
		});
		
		//Ȯ�� ��ư �Է½� 
		btok.addActionListener(e->{
			String id = idarea.getText();
			String pw = pwfield.getText();
			String name = namearea.getText();
			System.out.println(name);
			
//			��й�ȣ�� �Ȱ��� �Է��ߴ��� �˻�
			if(pwfield.getText().equals(pwofield.getText())) {
			/**
			 * ȸ������ �κ�
			 * ���̵� ��й�ȣ �̸� �Է��� clientUserDb�� ���� ����
			 */
			ClientUserDb userInfo = new ClientUserDb(id, pw, name);
			
			/**
			 * clientData �����ڸ� ���� ������ ���� ������ ����
			 */
			try {
				ClientData data = new ClientData(userInfo);
//				[3] clientData ��ü ����
				/**
				 * clientUserData�� �ִ� ��ü�� �������� ���Ϸ� ����
				 */
				data.clientUserSave(userInfo);
			} catch (Exception e1) {}
			
			}
			else {
				System.out.println("��й�ȣ�� ��ġ���� �ʽ��ϴ�.");
			}
			
			
			
		});

	}
	/**
	 * �޴� ���� �޼ҵ�
	 */
	public void menu() {
		
	}
	
}

public class JoinMain {

	public static void main(String[] args) {

		Window03 window = new Window03();

	}
}