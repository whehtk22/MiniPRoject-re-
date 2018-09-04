package com.mini.gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;

public class Dial extends JDialog{
	
	public Dial() {
		display();
	}
	
	public void display() {
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setSize(800,800);
		setLocation((d.width-this.getWidth())/2,(d.height-this.getHeight()/2));
		setResizable(false);
		setVisible(true);
	}
}
