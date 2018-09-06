package com.mini.db;

import java.io.*;
import java.util.*;

import com.mini.client.*;

public class DataInfoOpen {
	
	Map<String, ClientUserDb> user;
	File target = new File("server/client_list/userlist.db");
	
	public DataInfoOpen() {
//		System.out.println("��ü ����");
//		if(target.length()!=0) {
		try {
//			System.out.println("��Ʈ����?");
			ObjectInputStream in = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream(target)));
			user = (Map<String, ClientUserDb>) in.readObject();
			for(String k:user.keySet()) {
//				System.out.println("k : "+k);
			};
			in.close();
			
		}catch (Exception e) {
			user = new HashMap<>();
		}
//		}
	}
	
	
	public boolean searchId(String id) {
		boolean bool = false;
		for(String k : user.keySet()) {
//			System.out.println("k : "+k);
			if(k.equals(id)) {
				bool = true;
				break;
			}else if(!k.equals(id)) {
//				System.out.println("serchId ��"+bool);
				bool = false;
			}
		}
		return bool;
		
	}
	
	public boolean login(String id, String pw) {
		boolean bool = false;
		try {
			for(String k:user.keySet()) {
//				System.out.println("k ="+k);
				if(k.equals(id)) {
					System.out.println("���̵� ����.");
					if(user.get(k).getPassword().equals(pw)) {
						System.out.println("�α��� ����!");
						bool = true;break;
					}
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("����");
			bool = false;
		}
		return bool;
	}
	
	
}
