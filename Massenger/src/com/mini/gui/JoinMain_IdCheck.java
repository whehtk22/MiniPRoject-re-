package com.mini.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;



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
	private JButton idcheck = new JButton("�ߺ� �˻�");
	
	private JTextArea namearea = new JTextArea();
	private JTextArea idarea = new JTextArea();
	private JPasswordField pwfield = new JPasswordField();
	private JPasswordField pwofield = new JPasswordField();
	private Border b1 = BorderFactory.createLineBorder(Color.black, 1, true);
	private Font font = new Font("����",Font.BOLD,5);
	
	
	
	public Window03() {
		this.frame();
		this.display();
		this.menu();
		this.event();

		

	}
	
	
	public void frame() {
		this.setTitle("Swing ����");
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
		jointext.setBackground(Color.blue);
		
		
		//Ȯ�ι�ư
		btok.setBounds(118, 392, 97, 23);
		con.add(btok);
		
		//��ҹ�ư
		btcan.setBounds(238, 392, 97, 23);
		con.add(btcan);
		
		//�ߺ�üũ��ư 
		idcheck.setBounds(325, 195, 90, 23);
		con.add(idcheck);
		
		
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
		namearea.setBorder(b1);
		
		
		//���̵� �Է� �ؽ�Ʈ
		idarea.setBounds(110, 190, 200, 34);
		con.add(idarea);
		idarea.setColumns(15);
		idarea.setBorder(b1);
		
	
		//��й�ȣ �Է� �ؽ�Ʈ
		pwfield.setBounds(110, 266, 200, 34);
		con.add(pwfield);
		pwfield.setColumns(20);
		pwfield.setBorder(b1);
		
		//��й�ȣ Ȯ�� �ؽ�Ʈ 
		pwofield.setBounds(110, 324, 200, 34);
		con.add(pwofield);
		pwofield.setColumns(20);
		pwofield.setBorder(b1);
	}
	/**
	 * �̺�Ʈ ���� �޼ҵ�
	 */
	public void event() {

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);//x�� ������ â �Ҹ�
		
		//��� ��ư ������ ���� 
		btcan.addActionListener(e->{
			int b = JOptionPane.showConfirmDialog(null,"���� �����Ͻðڽ��ϱ�?","Ȯ��",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
			if(b == 0) {
				setVisible(false);
			}
		});

			

		
		//ȭ�� ��ư �Է½� 
		btok.addActionListener(e->{
			String id = idarea.getText();
			char[] pw = pwfield.getPassword();
			char[] pwo = pwofield.getPassword();
			String name = namearea.getText();
			StringBuffer sb1 = new StringBuffer(); 
			StringBuffer sb2 = new StringBuffer();
			sb1.append(pw);
			sb2.append(pwo);
			
			if(namearea.getText().isEmpty()){
				JOptionPane.showMessageDialog(null,  "�̸��� �Է��� �ּ���.", "�ȳ�", JOptionPane.ERROR_MESSAGE);
			}
			else if(idarea.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null,  "���̵� �Է��� �ּ���.", "�ȳ�", JOptionPane.ERROR_MESSAGE);	
			}
			else if(sb1.toString().isEmpty()) {
				JOptionPane.showMessageDialog(null,  "��й�ȣ�� �Է��� �ּ���.", "�ȳ�", JOptionPane.ERROR_MESSAGE);
			}
			else if(sb2.toString().isEmpty()) {
				JOptionPane.showMessageDialog(null,  "��й�ȣȮ�ζ���  �Է��� �ּ���.", "�ȳ�", JOptionPane.ERROR_MESSAGE);
			}
				
			else if(!sb1.toString().equals(sb2.toString())) {
				JOptionPane.showMessageDialog(null,  "��й�ȣ�� ���� �ʽ��ϴ�.", "�ȳ�", JOptionPane.ERROR_MESSAGE);
			}
			
			
			else {
				int join = JOptionPane.showConfirmDialog(null, "���� �Ͻðڽ��ϱ�?", "�ȳ�", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE);
				if(join == 0) {
//					ClientUserDb userInfo = new ClientUserDb(id, sb1.toString(), name);
//
//					/**
//					 * clientData �����ڸ� ���� ������ ���� ������ ����
//					 */
//					try {
//						ClientData data = new ClientData(userInfo);
////					[3] clientData ��ü ����
//						/**
//						 * clientUserData�� �ִ� ��ü�� �������� ���Ϸ� ����
//						 */
//						data.clientUserSave(userInfo);
//					} catch (Exception e1) {
//					}
				}
			}
		});

	}
	/**
	 * �޴� ���� �޼ҵ�
	 */
	public void menu() {
		
	
		
		
		
	}
	
}

public class JoinMain_IdCheck {

	public static void main(String[] args) {

		Window03 window = new Window03();

	}
}
