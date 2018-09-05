package com.mini.db;

import java.io.*;
import java.util.*;

import com.mini.client.*;

public class DataInfoOpen {
	
	Map<String, ClientUserDb> user;
	File target = new File("server/client_list/userlist.db");
	
	public DataInfoOpen() {
//		System.out.println("객체 생성");
//		if(target.length()!=0) {
		try {
//			System.out.println("스트림전?");
			ObjectInputStream in = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream(target)));
			user = (Map<String, ClientUserDb>) in.readObject();
			for(String k:user.keySet()) {
//				System.out.println("keyset의 값"+k);
			};
			in.close();
			
		}catch (Exception e) {
			user = new HashMap<>();
		}
//		}
	}
	
	
	public void searchId(String id) {
		
		if(user.keySet().equals(id)) {
			System.out.println(id+"는 사용가능한 ID입니다.");
		}
		else {
			System.out.println(id+"는 이미 존재하는 ID입니다.");
		}
	}
	
	public boolean login(String id, String pw) {
		boolean bool = false;
		try {
			for(String k:user.keySet()) {
//				System.out.println("k ="+k);
				if(k.equals(id)) {
//					System.out.println("아이디가 같다.");
					if(user.get(k).getPassword().equals(pw)) {
						System.out.println("로그인 성공!");
						bool = true;break;
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("문제");
			bool = false;
		}
		return bool;
	}
	
	
}
