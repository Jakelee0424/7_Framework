package edu.kh.project.common.aop;

import org.aspectj.lang.annotation.Pointcut;

// pointcut 모아두는 클래스
public class CommonPointcut {

	@Pointcut("execution(* edu.kh.project..*Impl*.*(..))")
	public void serviceImplPointcut() {}
	
	
}
