package com.mini.exception;

import java.io.IOException;

public class InputStreamException extends Exception{
	public static String InputStreamException() {
		
		return "��ǲ�����Դϴ�.";
	}
	
	public static String saveErrorException() {
		return "���� �����Դϴ�.";
	}
	@Override
	public void printStackTrace() {
		
	}
}
