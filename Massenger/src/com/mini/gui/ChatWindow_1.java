package com.mini.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;

/**
 *	Swing���� ����ϴ� Frame : JFrame 
 */
class Window07 extends JFrame{
	
//������Ʈ�� ��ġ�� ������ JPanel�� ����
	private JPanel con = new JPanel();
	private JMenuBar bar = new JMenuBar();
	private JMenu menu1 = new JMenu("����");
	private JMenu menu2 = new JMenu("ģ��");
	private JMenu menu3 = new JMenu("����");
	
	private JMenuItem item1 = new JMenuItem("��������");
	private JMenuItem item2 = new JMenuItem("���� ����");
	private JMenuItem item3 = new JMenuItem("��ȭâ ����");
	
	private JMenuItem item4 = new JMenuItem("��ȭ ��� �߰�");
	private JMenuItem item5 = new JMenuItem("�ɼ�2-2");
	private JMenuItem item6 = new JMenuItem("�ɼ�2-3");
	
	private JMenuItem item7 = new JMenuItem("���α׷� ����");
	private JMenuItem item8 = new JMenuItem("�ɼ�3-2");
	private JMenuItem item9 = new JMenuItem("�ɼ�3-3");
	
	private JButton btsend = new JButton("����");
	private JButton bticon1 = new JButton("��������");
	private JButton bticon2 = new JButton("�̸�Ƽ�� ����");
	private JButton bticon3 = new JButton("���Ϲ�ư");
	private JButton bticon4 = new JButton("���Ϲ�ư");

	private JTextArea tx = new JTextArea("���� �޼��� �Է�");
	private JScrollPane pltx = new JScrollPane(tx, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private JTextField chat = new JTextField("�ְ���� ���� ǥ�ÿ���");
	private JScrollPane plchat = new JScrollPane(chat);
	private JLabel lb2 = new JLabel("�� �̹��� ���� ǥ�� ����");
	private JLabel lb3 = new JLabel("��ȭ���� ���� ǥ�ÿ��� ");
	private Border b1 = BorderFactory.createLineBorder(Color.black, 1, true);
	
	
	
	public Window07() {
		this.frame();
		this.display();
		this.menu();
		this.event();
		
		
	}
	
	public void frame() {
		this.setTitle("xxx �԰� ��ȭ��");
//		this.setLocation(100, 100);
		this.setLocationByPlatform(true);
		this.setSize(500, 600);
		this.setResizable(true);
		this.setVisible(true);
	}
	
	/**
	 * ȭ�� ���� �޼ҵ�
	 */
	public void display() {
		this.setContentPane(con); 

		//this ���� con�� ������Ʈ �ؾ��� 
		
		con.setLayout(null);

		//���۹�ư
		btsend.setBounds(380, 456, 90, 96);
		con.add(btsend);
		
		//�ؽ�Ʈ �Է�â 
		con.add(pltx);
		pltx.setBounds(12, 456, 360, 96);
		pltx.setBorder(b1);
		
		//���� ǥ��â

		plchat.setBounds(12, 75, 355, 355);
		con.add(plchat);			
		plchat.setBorder(b1);
		tx.setLineWrap(true);

		
		
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
		
		btsend.addActionListener(e->{
			//String s = tx.getText();
			String s = tx.getText();
			System.out.println(s);

			tx.setText("");
		});
		
		item2.addActionListener(e->{
			JOptionPane.showConfirmDialog(null,"�ɼ�1","�ɼ�2",JOptionPane.YES_NO_OPTION);
		});
		
		item3.addActionListener(e->{
			int b = JOptionPane.showConfirmDialog(null,"���� �����Ͻðڽ��ϱ�?","Ȯ��",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
			if(b == 0) {
				setVisible(false);
			}
		});
		
		bticon1.addActionListener(e->{
			
		});
		
		item7.addActionListener(e->{
			JOptionPane.showMessageDialog(null, "���α׷� �������� ","���α׷� ����",JOptionPane.INFORMATION_MESSAGE);
		});


		
	}


	/**
	 * �޴� ���� �޼ҵ�
	 */
	public void menu() {
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
		
		//�����ư ����Ű
		KeyStroke esc = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,0);
		item3.setAccelerator(esc);
		
		//�������� ��ư ����Ű
		KeyStroke ctrls = KeyStroke.getKeyStroke(KeyEvent.VK_S,KeyEvent.CTRL_DOWN_MASK);
		item1.setAccelerator(ctrls);
		
		//ģ�� �߰� ����Ű
		
		KeyStroke ctrli = KeyStroke.getKeyStroke(KeyEvent.VK_I, KeyEvent.CTRL_DOWN_MASK);
		item4.setAccelerator(ctrli);
		
		//���α׷� ���� ����Ű
		KeyStroke fun11 = KeyStroke.getKeyStroke(KeyEvent.VK_F11,0);
		item7.setAccelerator(fun11);
		
		//���۹�ư ����Ű
		KeyStroke ent = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
	
		
	}
}

public class ChatWindow_1 {

	public static void main(String[] args) {
	
		Window07 window = new Window07();

		


	}
}
