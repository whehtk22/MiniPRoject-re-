package com.mini.sample;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Whisper extends JDialog implements ActionListener{
	JTextField tf;
	JButton b;
	RoomList rl;
	String receivename;

	public Whisper(RoomList rl, String receivename){
		super(rl.r);
		this.rl = rl;
		this.receivename=receivename;
		tf=new JTextField(25);
		tf.addActionListener(this);
		b=new JButton("whisper");
		b.addActionListener(this);
		add(tf,"Center");add(b,"East");
	}
	// 이벤트 처리
	public void actionPerformed(ActionEvent ae){
		rl.sendMsg("whisper/"+rl.roomtitle+"/"+receivename+"/"+rl.id+"/"+tf.getText());
		tf.setText("");
	}
}