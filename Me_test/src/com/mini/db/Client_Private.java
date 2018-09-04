package com.mini.db;

import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 유저의 개인정보를 저장하는 클래스
 * 
 * @author user
 *
 */
public class Client_Private {
	private Map<String, ClientUserDb> map;

	private File userDataFolder = new File("server/client_list"); // 유저의 개인 정보를 저장하는 폴더
	private ObjectOutputStream out;

	/**
	 * com.mini.db clientUserDb의 정보를 서버쪽에서 파일로 저장하는 메소드 안에 들어있는 파일 내용은 Map 형식으로 작성되어
	 * 있음
	 * 
	 * @param user
	 */
	public void clientUserSave(ClientUserDb userInfo, String id) {

		DataInfoOpen testopen = new DataInfoOpen();
		map= testopen.user;

		map.put(id, userInfo);

		File f = new File(userDataFolder, "userlist.db");
		try {
			f.createNewFile();
			out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(f)));

			out.writeObject(map);

			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				out.close();
			} catch (IOException e) {
				System.err.println("저장하는 통로가 끊어지지 않았어요!!");
			}
		}

	}

}
