package com.mini.client;

import java.awt.image.BufferedImage;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class clientUserDb {
	
	private Map<String, String> User;
	private String user_name = null;
	private ArrayList friends = new ArrayList<>();
	
	public clientUserDb() {

	}
	
	
	
	public clientUserDb(String id, String password, String name) {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put(id, password);
		this.setUser(map);
		this.setUser_name(name);
		System.out.println(this.getUser_name());
		
	}
	
	public Map<String, String> getUser() {
		return User;
	}

	public void setUser(Map<String, String> user) {
		User = user;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public ArrayList getFriends() {
		return friends;
	}

	public void setFriends(ArrayList friends) {
		this.friends = friends;
	}
	
	
	
}
