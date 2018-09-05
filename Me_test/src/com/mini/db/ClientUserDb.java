package com.mini.db;

import java.io.Serializable;
import java.util.List;


public class ClientUserDb implements Serializable{
	private String id;
	private String password;
	private String user_name;
	private List friend_list;
	public ClientUserDb() {
		super();
		}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public ClientUserDb(String id, String password, String name) {
		this.id = id;
		this.setPassword(password);
		this.setUser_name(name);
//		System.out.println(this.getUser_name());
	}
	

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
}
