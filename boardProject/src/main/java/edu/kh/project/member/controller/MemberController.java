package edu.kh.project.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import edu.kh.project.member.model.dto.Member;

@Controller // 요청/응답 클래스 + bean 등록(Spring이 관리하는 객체)
@RequestMapping("/member") // 공통된 주소 앞부분 작성, member로 시작하는 요청은 해당 컨트롤러에서 처리
public class MemberController {

	// 로그인 : /member/login
	// 로그아웃 : /member/logout
	
	// @RequestMapping(value="/login", method=RequestMethod.POST)
	
	// @RequestMapping : 요청 주소에 맞는 클래스/메서드 연결
	// @RequestMapping("요청주소") -> get/post 구분 X, but post쓸 떈 구분하는 것이 좋음
	
	
//	@RequestMapping(value="/login", method=RequestMethod.POST) // 기본이 get(생략가능)
	public String login(HttpServletRequest req) {
		
		// 파라미터 전달 방법 1 : HttpServletRequest 
		
		// -> Controller 메서드에 매개변수로 HttpServletRequest를 작성
		
		// 매개변수로 사용가능한 이유
		// spring에서 제공하는 Argument Resolver(매개변수해결사)가 자동이로 대입 & 해결
		
		String inputEmail = req.getParameter("inputEmail");
		String inputPw = req.getParameter("inputPw");
		
		System.out.println("inputEmail : " + inputEmail);
		System.out.println("inputPw : " + inputPw);

		// redirect 방법
		// "redirect : 요청주소"
		
		return "redirect:/";
		
	}
	
// @PostMapping : @RequestMapping의 자식 -> post 방식 요청을 연결하는 어노테이션
//	@PostMapping("/login")
	public String login(@RequestParam("inputEmail") String inputEmail, 
						/*@RequestParam("inputPw") */ String inputPw) {
		
		// 파라미터 전달 방법 2 : @RequestParam 어노테이션 사용(+생략방법)
		
		// @RequestParam 어노테이션 
		// - request 객체를 이용한 파라미터 전달 어노테이션
		// - 매개변수 앞에 해당 어노테이션 작성하면, 매개변수에 같이 주입됨
		
		// if 파라미터의 nema속성 == 매개변수명, @RequestParam 생략가능 
		
		
		
		//  **************************** [속성] **********************************
		// value : 전달 받은 input 태그의 name 속성값
		
		// required : 입력된 name 속성값 파라미터 필수 여부 지정(기본값 = true)
		// required = true인 파라미터 존재 X 경우 : 400 Bad Request에러
		// required = true인 파라미터 null인 경우 : 400 Bad Request에러
		
		// defaultValue : 파라미터 중 일치하는 name속성값이 없을 경우 대입 값 지정
		// required = false인 경우 사용
		
		
		System.out.println("inputEmail : " + inputEmail);
		System.out.println("inputPw : " + inputPw);
		
		return "redirect:/";
		
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute Member inputMember){
		
		// 파라미터 전달 방법 3 : @ModelAttribute 이용
		
		// - dto(또는 VO)와 같이 사용하는 어노테이션
		// 전달 받은 파라미터의 name 속성값이 같이 사용되는 DTO의 필드명과 같으면 
		// 자동으로 setter 호출해서 필드에 값을 세팅
		
		System.out.println(inputMember);
		
		// 주의 사항
		// -DTO에 기본 생성자가 필수로 존재해야함
		// -DTO에 setter가 필수로 존재해야함
		// 어노테이션 생략가능
	
		// @ModelAttribute로 필드에 세팅된 객체를 커맨드 객체라고 함
		
		return "redirect:/";
		
	}
	
	
}
