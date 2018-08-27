package com.mini.exception;

import java.io.IOException;

public class InputStreamException extends Exception{
	public static String InputStreamException() {
		
		return "인풋에러입니다.";
	}
	
	public static String saveErrorException() {
		return "저장 실패입니다.";
	}
	@Override
	public void printStackTrace() {
		
	}
}
