package com.mini.db;

import java.io.*;
import java.util.*;

import com.mini.client.*;

public class DataInfoOpen {
	
	Map<String, ClientUserDb> user;
	
	public DataInfoOpen() {
		super();
	}
	
	public DataInfoOpen(String userId) {
		File target = new File(userId+"files", "yourdb.db");
		
		try {
			ObjectInputStream in = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream(target)));
			
			
			user = (Map<String, ClientUserDb>) in.readObject();
			
			in.close();
			
		}catch (Exception e) {
			System.out.println("이거?");
		}
	}
	
	
	public void searchId(String id) {
		
		if(user.keySet().equals(id)) {
			System.out.println(id+"는 사용가능한 ID입니다.");
		}
		else {
			System.out.println(id+"는 이미 존재하는 ID입니다.");
		}
	}
	
	public void login(String id, String pw) {
		
		try {
			StringBuffer bf = new StringBuffer();
			
			bf.append(user.keySet());
			System.out.println(user.keySet().equals(pw));
			System.out.println(bf.toString());
			System.out.println(pw);
			System.out.println(user);
			
			if(user.keySet().equals(id) && user.values().equals(pw)) {
				System.out.println("로그인 성공!");
			}
			else System.out.println("로그인 실패!");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("문제");
		}
	}
	
	
}
