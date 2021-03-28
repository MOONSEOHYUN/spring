package com.spring.food.advice;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

//스프링에서 발생되는 예외 여기서 처리
@ControllerAdvice
public class CommonExceptionAdvice {
	@ExceptionHandler(Exception.class)
	public String common(Exception e) {
		System.out.println("예외발생");
		System.out.println(e.toString());
		e.printStackTrace();
		
		return "error";
	}
}
