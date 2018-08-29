package com.mini.db;

import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class clientUserDb implements Serializable{
	
	private Map<String, String> User;
	private String user_name;
	private ArrayList friends = new ArrayList<>();
	
	
	public clientUserDb() {
		super();
		}
	
	public clientUserDb(String id, String password, String name) {
//		[2] id, pw, name 정보 입력
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
