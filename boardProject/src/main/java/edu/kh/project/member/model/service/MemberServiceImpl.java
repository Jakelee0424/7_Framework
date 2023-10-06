package edu.kh.project.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import edu.kh.project.member.model.dao.MemberDAO;
import edu.kh.project.member.model.dto.Member;

@Service // 비즈니스 로직(데이터 가공, DAO 호출, 트랜잭션 제어)을 처리하는 클래스 명시
		 //  + Bean으로 등록하는 어노테이션
public class MemberServiceImpl implements MemberService{

//	private MemberDAO dao = new MemberDAO(); Bean으로 등록되어 있기 때문에 객체생성할 필요가 없음
	
	// @Autowired : 작성된 필드와 Bean으로 등록된 객체 중 타입이 일치하는 Bean을 해당 필드에 자동 주입하는 어노테이션
	//			-> 객체를 직접 만들지 않고, Spring이 만든 걸 주입
	
	@Autowired // 자동 연결
	private MemberDAO dao; // DI

	@Autowired // bean으로 등록된 객체 중 타입이 일치하는 객체를 DI
	private BCryptPasswordEncoder bcrypt;


	/** 로그인 서비스
	 *
	 */
	@Override
	public Member login(Member inputMember) {
		
//		System.out.println("암호화 확인:" + bcrypt.encode( inputMember.getMemberPw() ));
//		System.out.println("암호화 확인:" + bcrypt.encode( inputMember.getMemberPw() ));
//		System.out.println("암호화 확인:" + bcrypt.encode( inputMember.getMemberPw() ));
//		System.out.println("암호화 확인:" + bcrypt.encode( inputMember.getMemberPw() ));
//		System.out.println("암호화 확인:" + bcrypt.encode( inputMember.getMemberPw() ));
		
		
		// dao 호출
		
		Member loginMember = dao.login(inputMember);
		
		if(loginMember != null) {
			
			// 입력한 pw, 암호화된 pw 같은지 확인
			if (bcrypt.matches(inputMember.getMemberPw(), loginMember.getMemberPw())) {
				
				// 비밀번호를 유지하지 않기 위해 로그인 정보에서 제거(세션에서 암호 정보 제거(암호화된 비밀번호도 지워야함))
				loginMember.setMemberPw(null);
				
			} else {

				loginMember = null;
			
			}
			
		}
		
		return loginMember;
	}
}
