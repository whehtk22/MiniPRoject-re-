package com.mini.gui;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.*;
import javax.swing.border.Border;
import com.mini.client.Connection;
import com.mini.db.*;

/**
 * Swing���� ����ϴ� Frame : JFrame
 */
public class Login_Window extends JFrame {


	private Connection serverCon;

	//������Ʈ�� ��ġ�� ������ JPanel�� ����
	// private ImageIcon logo = new ImageIcon("image/cap.png");
	private JPanel con = new JPanel();
	private JLabel main = new JLabel(new ImageIcon("image/cap.png"));
	private JLabel id = new JLabel("���̵� : ");
	private JLabel pw = new JLabel("��й�ȣ : ");
	private JTextField idtx = new JTextField();
	private JPasswordField pwtx = new JPasswordField();
	private JButton login = new JButton("�α���");
	private JButton join = new JButton("ȸ������");
	private Border b1 = BorderFactory.createLineBorder(Color.BLACK, 2, true);

	public Login_Window(Connection con) {
		this.serverCon =con;
		this.display();
		this.event();
		this.menu();

		this.setTitle("Swing ����");
		this.setLocationByPlatform(true);
		this.setSize(300, 400);
		this.setResizable(false);
		this.setVisible(true);
	}

	/**
	 * ȭ�� ���� �޼ҵ�
	 */
	public void display() {
		this.setContentPane(con);
		con.setLayout(null);

		// ���� ȭ�� (������, �ΰ�, �̸�)
		// �ٸ������� ���� ����
		main.setBounds(12, 10, 260, 178);
		con.add(main);

		// ���̵�
		id.setBounds(12, 198, 260, 37);
		con.add(id);

		// ��й�ȣ
		pw.setBounds(12, 245, 260, 37);
		con.add(pw);

		// ���̵� �ؽ�Ʈ
		idtx.setBounds(100, 202, 151, 29);
		con.add(idtx);
		idtx.setColumns(20);
		idtx.setBorder(b1);

		// ��й�ȣ �ؽ�Ʈ
		pwtx.setBounds(100, 249, 151, 29);
		con.add(pwtx);
		pwtx.setColumns(20);
		pwtx.setBorder(b1);

		// �α��� ��ư
		login.setBounds(145, 292, 121, 37);
		con.add(login);

		// ȸ������ ��ư
		join.setBounds(22, 292, 121, 37);
		con.add(join);
	}

	/**
	 * �̺�Ʈ ���� �޼ҵ�
	 */
	public void event() {

		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);// x�� ������ â �Ҹ�

		join.addActionListener(e -> {
			try {
				serverCon.getOut().writeInt(Selection.JOIN);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			Member_Join join = new Member_Join(serverCon);
		});

		login.addActionListener(e -> {
			try {
				serverCon.getOut().writeInt(Selection.LOGIN);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			String id = this.idtx.getText();
			char[] p = pwtx.getPassword();
			StringBuffer bf = new StringBuffer();
			bf.append(p);
			Map<String, String> idCheck = new HashMap<>();
			idCheck.put(id, bf.toString());
			try {
				serverCon.getOut().writeObject(idCheck);
			} catch (IOException e2) {
				e2.printStackTrace();
			}
			try {
			boolean ox= serverCon.getIn().readBoolean();
			System.out.println(ox);
				if(ox) {
						serverCon.setId(id);
						Lobby testLobby = new Lobby(serverCon);
//						serverCon.getOut().writeInt(Selection.CHAT);
//						Chatting_Frame g = new Chatting_Frame(serverCon);
					
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		});

	}

	/**
	 * �޴� ���� �޼ҵ�
	 */
	public void menu() {

	}
}
