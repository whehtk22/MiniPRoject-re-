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
 * ������ ���������� �����ϴ� Ŭ����
 * 
 * @author user
 *
 */
public class Client_Private {
	private Map<String, ClientUserDb> map;

	private File userDataFolder = new File("server/client_list"); // ������ ���� ������ �����ϴ� ����
	private ObjectOutputStream out;

	/**
	 * com.mini.db clientUserDb�� ������ �����ʿ��� ���Ϸ� �����ϴ� �޼ҵ� �ȿ� ����ִ� ���� ������ Map �������� �ۼ��Ǿ�
	 * ����
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
				System.err.println("�����ϴ� ��ΰ� �������� �ʾҾ��!!");
			}
		}

	}

}
