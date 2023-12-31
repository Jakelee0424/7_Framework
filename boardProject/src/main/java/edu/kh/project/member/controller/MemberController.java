package edu.kh.project.member.controller;

import java.sql.SQLException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import edu.kh.project.member.model.dto.Member;
import edu.kh.project.member.model.service.MemberService;

@Controller // 요청/응답 클래스 + bean 등록(Spring이 관리하는 객체)
@RequestMapping("/member") // 공통된 주소 앞부분 작성, member로 시작하는 요청은 해당 컨트롤러에서 처리
@SessionAttributes({"loginMember"}) // Model의 키값 작성하면 session으로 추가 
public class MemberController {

	@Autowired // MemberService를 구현한 MemberServiceImpl의 Bean 주입
	private MemberService service;
	
	
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
	
//	@PostMapping("/login")
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
	
	// 찐탱이
	/** 로그인
	 * @param inputMember
	 * @param model
	 * @param referer
	 * @param ra
	 * @param saveId
	 * @param resp
	 * @return
	 */
	@PostMapping("/login")
	public String login(Member inputMember, Model model,
						@RequestHeader(value = "referer") String referer, // value 생략가능
						RedirectAttributes ra,
						@RequestParam(value = "saveId", required = false) String saveId,
						HttpServletResponse resp
			) {
		
	// @RequestHeader(value="referer) String referer
		// -> 요청 HTTP header에서 "referer" (이전주소) 값을 얻어와
		// 매개변수 String referer에 저장
		
	// Model : 데이터 전달용 객체
		// -> 데이터를 K : V 형태로 담아 전달
		// -> 기본적으로 request scope
		// -> @SessionAttributes 어노테이션과 함께 사용시 Session scope로 사용 가능
		
	// @RequestParam(value="saveId", required=false) String saveId
		// -> name 속성값이 saveId인 파라미터를 전달받아서 저장
		// required 미작성 시 기본 값 ture -> 오류
		// required = false -> null 허용 
		
		// 로그인 서비스 호출
		Member loginMember = service.login(inputMember);
		
		// DB 조회 결과 확인
		// System.out.println(loginMember);
		
		
		// 로그인 결과에 따라 redirect 경로를 다르게 저장
		
		String path = "redirect:";
		
		
		if(loginMember != null) { // 로그인 성공

			// 메인페이지로 redirect
			path += "/";

			// session에 loginMember 추가
			
			// Servlet -> HttpSession.setAttribute("키", "밸류")
			// Spring -> Model + @SessionAttributes
			
			// 1) model에 로그인한 회원정보 추가
			model.addAttribute("loginMember", loginMember); // -> 현재는 request scope
			
			// 2) 클래스 위에 어노테이션(@SessionAttributes) 추가
			// -> 현재는 session scope
			
//------------------------------------------------------------------------------------
			// 아이디 저장(Cookie)
			
			/*
			 * cookie란 
			 * -> 클라이언트 측(브라우저)에서 관리하는 파일
			 * 
			 * - 쿠키파일에 등록된 주소 요청 시 마다
			 * 	 자동으로 요청에 첨부되어 서버로 전달됨 
			 * 
			 * - 서버로 전달된 쿠키에
			 * 	 값 추가, 수정, 삭제 등을 진행한 후, 다시 클라이언트에 반환
			 * 
			 * */
			
			// 쿠키 생성(해당 쿠키에 담을 데이터를 K:V형태로 지정)
			Cookie cookie = new Cookie("saveId", loginMember.getMemberEmail());
			
			if(saveId != null) { // 아이디 저장이 체크되었을 때
				
				// 한 달(30일) 간 유지되는 쿠키 생성
				cookie.setMaxAge(60*60*24*30);
				
				
			}else { // 아이디 저장이 체크안되었을 때
			
				// 0초 동안 유지되는 쿠키 생성 -> 기존에 존재하던 쿠키 삭제
				cookie.setMaxAge(0);

			}
			
			// 클라이언트가 어떤 요청을 할 때 쿠키가 첨부될지 경로 지정
			cookie.setPath("/"); // localhost:/ 이하 모든 주소, 모든 요청에 쿠키 첨부
			
			// 응답 객체(HttpServletResponse)를 이용하여 클라이언트로 전달
			resp.addCookie(cookie);
			
			
		} else { // 로그인 실패

			// 이전 페이지로
			path += referer;

			// 로그인 실패 메세지 추가

			/*
			 * redirect(재요청)시
			 * 기존 요청(request)이 사라지고
			 * 새로운 요청(request)을 만들게 되어
			 * redirect된 페이지에서는 이전 요청이 유지되지 않는다
			 * -> 유지하고 싶으면 어쩔 수 없이 session scope를 이용
			 * 
			 * RedirectAttributes를 스프링에서 재공 -> redirect시 데이터를 request scope로 전달할 수 있게 하는 객체
			 * 
			 * 
			 * 응답 전 : request scope
			 * 응답 중 : session scope로 이동
			 * 응답 후 : request scope로 복귀
			 * 
			 * 
			 * */
			
			//addFlashAttribute : 잠시 session에 추가
			ra.addFlashAttribute("msg", "아이디 또는 비밀번호 불일치");
		}
		
		
		return path;
	}
	
	/** 로그아웃
	 * @param status
	 * @return
	 */
	@GetMapping("/logout")
	public String logout(SessionStatus status /*HttpSession session*/) {
		
		// SessionStatus : 세션 상태 관리 객체
		
		status.setComplete();
		
		//session.invalidate(); // 세선 무효화		
		
		return "redirect:/";
	}
	
	/** 회원가입 페이지 이동
	 * @return
	 */
	@GetMapping("/signUp")
	public String signUp() {
		
		return "member/signUp";
	}
	
	/** 회원가입
	 * @param inputMember
	 * @param memberAddress
	 * @param ra
	 * @return
	 */
	@PostMapping("/signUp")
	public String signUp(Member inputMember,
						 String[] memberAddress,
						 RedirectAttributes ra

						 // Member inputMember = 커맨드 객체 (@ModelAttribute 생략)
						 // String[] memberAddress : input name = "memberAddress" 3개가 저장된 객체
						 // RedirectAttributes ra : 리다이렉트 시 데이터를 request-scope로 전달하는 객체
			) {
		
		if(inputMember.getMemberAddress().equals(",,")) {
			inputMember.setMemberAddress(null);
		
		} else {
			
			// String.join("구분자", String[])
			String addr = String.join("^^^", memberAddress);		
			inputMember.setMemberAddress(addr);
			
		}
		
		int result = service.signUp(inputMember);
		
		// 가입 성공 여부에 따라 주소 결정
		
		String path = "redirect:";
		String msg = null;
		
		if(result > 0) {
			
			path += "/"; // 가입 성공시 메인 페이지로 이동
			msg = inputMember.getMemberNickname() + "님의 가입을 환영합니다!";
			
		}else {
			
//			path += "/member/signUp"; //절대경로
			path += "signUp"; // 상대경로
			
			msg = "회원가입 실패";
			
		}
		
		ra.addFlashAttribute("msg", msg);
		
		return path;
	}
	
	
	/*
	 * 스프링의 예외 처리방법 (3종류, 중복 사용 가능)
	 * 
	 * 1순위 : 메서드 단위로 처리 -> try, catch / throws
	 * 
	 * 2순위 : 클래스 단위로 처리 -> @ExceptionHandler
	 * 
	 * 3순위 : 프로그램 단위(전역) -> @ControllerAdvice
	 * 
	 * */
	
	// 현재 클래스에서 발생하는 모든 예외를 모아서 처리
//	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e, Model model) {
								// Exception e : 예외 정보를 담고 있는 객체
								// Model model : 데이터 전달용 객체(request scope)
		
		e.printStackTrace(); // 예외 내용/발생 메서드 확인
		
		model.addAttribute("e", e); // 예외 발생시 forward되는 페이지로 e를 전달(request scope만으로 충분 -> session) 
		
		// viewResolver에 의해 페이지 이동 	
		return "common/error";
	}
	
	
	
	
}
