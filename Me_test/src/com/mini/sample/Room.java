package com.mini.sample;
import java.net.*;
import java.util.StringTokenizer;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

public class Room extends JFrame implements ActionListener{
	JFrame f;
	JPanel pa1, pa2;
	JScrollPane sp1, sp2;
	JTextArea ta;
	JList li;
	JTextField tf;
	JButton b;
	JPopupMenu pm;
	JMenuItem mi; 

	RoomList rl;
	BufferedReader br;
	BufferedWriter bw;
	String roomtitle;

	public Room(RoomList rl, String roomtitle){
		super(" "+roomtitle+" Room("+rl.id+")");
		this.rl = rl;
		this.roomtitle = roomtitle;

		pa1 = new JPanel();
		pa2 = new JPanel();
		pa1.setLayout(new BorderLayout());
		pa2.setLayout(new BorderLayout());

		ta = new JTextArea(15, 30);
		li = new JList();
		pm = new JPopupMenu();
		mi = new JMenuItem("whisper");
		mi.addActionListener(this);
		pm.add(mi);
		li.setBorder(new TitledBorder("user List"));
		li.add(pm);
		li.addMouseListener(   // �ӼӸ� �޴�
				new MouseAdapter(){
					public void mouseClicked(MouseEvent me){
						if(me.getButton()==3){
							pm.show(li, me.getX(), me.getY());
						}
					}
				}
				);
		tf = new JTextField();
		tf.addActionListener(this);
		b = new JButton("exit room");
		b.addActionListener(this);
		sp1 = new JScrollPane(ta);
		sp2 = new JScrollPane(li);

		pa1.add(sp1, "Center");
		pa1.add(sp2, "East");
		pa2.add(tf, "Center");
		pa2.add(b, "East");

		Container container = getContentPane();
		container.add(pa1, "Center");
		container.add(pa2, "South");

		addWindowListener(   // ��ȭ�� ������
				new WindowAdapter(){
					public void windowClosing(WindowEvent we){
						Room.this.rl.sendMsg("exitroom/"+Room.this.rl.roomtitle+"/"+Room.this.rl.id);
						setVisible(false);
						Room.this.rl.setVisible(true);
					}
				}
				);

		pack();
		setVisible(true);
	}
	// �̺�Ʈ ó��
	public void actionPerformed(ActionEvent ae){
		if(ae.getSource()==tf){     // ��ȭ�ϱ�
			rl.sendMsg("say/"+rl.roomtitle+"/"+rl.id+"/"+tf.getText());
			tf.setText("");   
		}else if(ae.getSource()==b){   // ��ȭ�� ������
			rl.sendMsg("exitroom/"+rl.roomtitle+"/"+rl.id);
			setVisible(false);
			rl.setVisible(true);
		}else if(ae.getSource()==mi){   // �ӼӸ�
			Whisper w = new Whisper(rl, (String)li.getSelectedValue());
			w.pack();
			w.setVisible(true);
		}
	}
}