package com.mini.server;

public class ServerRun {

	public static void main(String[] args) {
		UniServer server = new UniServer();
		try {
			server.makeChatRoom();
		} catch (Exception e) {
			e.printStackTrace();
		}
		MultiServer multi = new MultiServer(server.getChatPort());
		Thread t = new Thread() {
			@Override
			public void run() {
				try {
					multi.receive();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};

		t.start();
		Thread r = new Thread() {
			@Override
			public void run() {
				multi.broadcast(multi.getText());
			}
		};
		r.start();
	}

}
