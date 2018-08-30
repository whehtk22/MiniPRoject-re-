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

/**
 * 유저의 아이디 비밀번호 이름을 임시로 저장해주는 메소드
 * 클라이언트만 사용
 * @author user
 *
 */
public class ClientUserDb implements Serializable{
	private String password;
	private String user_name;
	
	
	public ClientUserDb() {
		super();
		}
	
	public ClientUserDb( String password, String name) {
//		[2] id, pw, name 정보 입력
		this.setPassword(password);
		this.setUser_name(name);
		System.out.println(this.getUser_name());
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
