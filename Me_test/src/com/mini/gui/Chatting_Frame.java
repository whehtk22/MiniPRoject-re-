package com.mini.gui;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.border.Border;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.mini.client.Connection;
/**
 *	Swing���� ����ϴ� Frame : JFrame 
 */
public class Chatting_Frame extends JFrame {
	private Connection client_Net;

	//������Ʈ�� ��ġ�� ������ JPanel�� ����
	//������Ʈ�� ��ġ�� ������ JPanel�� ����
	private JPanel con = new JPanel();

	private JMenuBar bar = new JMenuBar();
	private JMenu file_menu = new JMenu("����");
	private JMenu friend_menu = new JMenu("ģ��");
	private JMenu help_menu = new JMenu("����");

	private JMenuItem item1 = new JMenuItem("��������");
	private JMenuItem item2 = new JMenuItem("���� ����");
	private JMenuItem item3 = new JMenuItem("��ȭâ ����");

	private JMenuItem item4 = new JMenuItem("��ȭ ��� �߰�");
	private JMenuItem item5 = new JMenuItem("�ɼ�2-2");
	private JMenuItem item6 = new JMenuItem("�ɼ�2-3");

	private JMenuItem item7 = new JMenuItem("���α׷� ����");
	private JMenuItem item8 = new JMenuItem("�ɼ�3-2");
	private JMenuItem item9 = new JMenuItem("�ɼ�3-3");

	private JButton input_bt = new JButton("����");
	private JButton bticon1 = new JButton("��������");
	private JButton bticon2 = new JButton("�̸�Ƽ�� ����");
	private JButton bticon3 = new JButton("���Ϲ�ư");
	private JButton bticon4 = new JButton("���Ϲ�ư");


	private JTextArea input_message = new JTextArea();
	private JScrollPane plinput_message = new JScrollPane(input_message,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	private JTextArea chat = new JTextArea("");
	private JScrollPane plchat = new JScrollPane(chat);
	private JLabel lb2 = new JLabel("�� �̹��� ���� ǥ�� ����");
	private JLabel lb3 = new JLabel("��ȭ���� ���� ǥ�ÿ��� ");
	private Border b1 = BorderFactory.createLineBorder(Color.black, 1, true);


	public JTextArea getChat() {
		return chat;
	}
	public void setChat(JTextArea chat) {
		this.chat = chat;
	}
	/**
	 * ȭ�� ���� �޼ҵ�
	 */
	public void display() {
		setContentPane(con); 
		//this ���� con�� ������Ʈ �ؾ��� 

		con.setLayout(null);
		//���۹�ư
		//x��ǥ y ��ǥ ���� ����
		input_bt.setBounds(380, 456, 90, 96);
		con.add(input_bt);

		//ä���Է�â
		plinput_message.setBounds(12, 456, 360, 96);
		con.add(plinput_message);
		plinput_message.setBorder(b1);
		input_message.setLineWrap(true);
		
		

		//���� ǥ��â
		plchat.setBounds(12, 75, 355, 355);
		con.add(plchat);
		chat.setLineWrap(true);
		chat.setEditable(false);
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
		
		input_message.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					//��Ʈ�� + ����Ű �Է½� ������ �Է� 
					if(e.isControlDown()){
						input_message.append(System.lineSeparator());
//						System.out.println("����� ����");
					}else {
						String str = input_message.getText();
						System.out.println("����Ȯ��"+Connection.id);
						client_Net.send(Connection.id+" : "+str);
						e.consume(); // �����Է½� �� �ʱ�ȭ 
						input_message.setText("");
					}
				}
			}
		});
		
		//���۹�ư �̺�Ʈ ���� 
		input_bt.addActionListener(e->{
			String str = input_message.getText();
			client_Net.send(Connection.id+" : "+str);
//			System.out.println("���⿡��");
			input_message.setText("");
		});
		
		//���� ���۹�ư
		item1.addActionListener(e->{
			JFileChooser chooser = new JFileChooser(".");
			int choice = chooser.showOpenDialog(con);
			if(choice == 0) {
				File target = chooser.getSelectedFile();
			}
		});
		
		
		//���� ���� ��ư 
		item2.addActionListener(e->{
			JFileChooser chooser = new JFileChooser(".");
			chooser.setFileFilter(new FileNameExtensionFilter("PNG �̹���", "png"));
			chooser.setFileFilter(new FileNameExtensionFilter("JPEG �̹���", "jpg"));
			chooser.setFileFilter(new FileNameExtensionFilter("GIF �̹���", "gif"));
			int choice = chooser.showOpenDialog(con);
			if(choice == 0) {
				File target = chooser.getSelectedFile();
			}

		});
		
		// �����ư  �̺�Ʈ ����  
		item3.addActionListener(e->{
			int b = JOptionPane.showConfirmDialog(con,"���� �����Ͻðڽ��ϱ�?","Ȯ��",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
			if(b == 0) {
				setVisible(false);
			}
		});
 
		// ���α׷� ���� �̺�Ʈ ���� 
		item7.addActionListener(e->{
			JOptionPane.showMessageDialog(con, "���α׷� �������� ","���α׷� ����",JOptionPane.INFORMATION_MESSAGE);
		});
		WindowListener w = new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent arg0) {
				client_Net.send("��������");
			}
		};
		addWindowListener(w);

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
		bar.add(file_menu);
		file_menu.add(item1);
		file_menu.add(item2);
		file_menu.add(item3);


		//ģ��
		bar.add(friend_menu);
		friend_menu.add(item4);
		friend_menu.add(item5);
		friend_menu.add(item6);

		//����
		bar.add(help_menu);
		help_menu.add(item7);
		help_menu.add(item8);
		help_menu.add(item9);
		
		
		
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
		
		//�������� ����Ű
		KeyStroke ctrlp = KeyStroke.getKeyStroke(KeyEvent.VK_P,KeyEvent.CTRL_DOWN_MASK);
		item2.setAccelerator(ctrlp);		

	}
	//������ ���� �� ���ڹް� ����


	public Chatting_Frame(Connection client_Net,String roomName) {
		this.client_Net=client_Net;
		this.display();
		this.event();
		this.menu();
		//���������(���ڹޱ�)

		this.setTitle(roomName);
		this.setLocationByPlatform(true);
		this.setSize(500, 600);
		this.setResizable(false);
		this.setVisible(true);
		Thread t = new Thread(r);
		t.start();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	Runnable r =()->{
		System.out.println("������ ������");
		while(true) {
			String str=client_Net.receive();
			if(!str.equals("123123")) {
				chat.append(str+"\n");
				chat.setCaretPosition(chat.getDocument().getLength());
				System.out.println("����");
			}else {
				
			}
		}
	};


}