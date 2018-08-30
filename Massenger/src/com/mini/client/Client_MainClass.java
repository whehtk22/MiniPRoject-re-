package com.mini.client;

import com.mini.gui.*;

public class Client_MainClass {
	public static void main(String[] args) {
		Client_cennection cm = new Client_cennection(41000, "localhost");
		Lobby lb = new Lobby(cm);
//		Gui3 gui = new Gui3();
	}
}
