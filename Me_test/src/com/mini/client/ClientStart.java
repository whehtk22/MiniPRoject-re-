package com.mini.client;

import java.io.IOException;
import com.mini.gui.*;

import com.mini.gui.Chatting_Frame;

public class ClientStart {

	public static void main(String[] args) throws IOException {
		Connection con = new Connection();
		Login_Window start = new Login_Window(con);

	}

}
