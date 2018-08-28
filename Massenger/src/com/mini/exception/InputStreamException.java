package com.mini.exception;

import java.io.IOException;

public class InputStreamException extends Exception{
	public static String InputStreamException() {
		
		return "인풋에러입니다.";
	}
	
//<<<<<<< HEAD
//	public static String 
//=======
	public static String saveErrorException() {
		return "저장 실패입니다.";
	}
//>>>>>>> branch 'master' of https://github.com/whehtk22/MiniPRoject-re-.git
	@Override
	public void printStackTrace() {
		
	}
}
