package edu.kh.project.member.model.service;

import edu.kh.project.member.model.dto.Member;

//  *** Service Interface 사용 이유 ***

// 1. 프로젝트에 규칙성을 부여

// 2. 클래스 간의 결합도 약화(객체 지향적 설계)
// 	-> 유지 보수성 향상

// 3. Spring AOP 사용을 위해서
//	 : AOP는 Spring-proxy를 이용해서 동작 -> 이 떄 service 인터페이스가 필요

public interface MemberService {

	Member login(Member inputMember);

	int signUp(Member inputMember);

	


	
}
