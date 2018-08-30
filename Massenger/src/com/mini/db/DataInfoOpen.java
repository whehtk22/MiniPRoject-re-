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
			System.out.println("ÀÌ°Å?");
		}
	}
	
	
	public boolean searchId(String id) {
		
		if(user.keySet().equals(id)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public boolean login(String id, String pw) {
		try {
			if(user.keySet().equals(id) && user.values().equals(pw)) {
				return true;
			}
			else return false;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
}
