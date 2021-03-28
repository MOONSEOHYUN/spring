package com.spring.food.advice;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

//logger.info 대신 사용
@Component
@Aspect
public class AopAdvice {
	
//	매개변수 출력
//	*, ..: 아무거나, 모든 것
//	*: 리턴형 / com.spring.board.service.LoginServiceImpl.: 클래스명 / *(): 메소드명 / ..: 매개변수
//	@Before("execution(* com.spring.board.service.LoginServiceImpl.*(..))")
	@Before("execution(* com.spring.food.controller.*.*(..))")
	public void startLogService(JoinPoint jp) {
		System.out.println("-------------------------------------------");
		System.out.println(jp.getSignature().toLongString());
		System.out.println("매개변수: "+Arrays.toString(jp.getArgs()));
		System.out.println("-------------------------------------------");
	}
	
//	리턴값 출력
//	적용대상 정상 수행 후
	@AfterReturning(pointcut="execution(* com.spring.food.controller.*.*(..))", returning = "rObj")	//returning 안쓰면 pointcut 생략 가능
	public void afterLogService(JoinPoint jp, Object rObj) {
//		반환값을 void로 할 때만 if문 달아줌
		if(rObj!=null) {
			System.out.println("-------------------------------------------");
			System.out.println(jp.getSignature().toLongString());
			System.out.println("리턴값: "+rObj.toString());
			System.out.println("-------------------------------------------");
		}
	}
}
