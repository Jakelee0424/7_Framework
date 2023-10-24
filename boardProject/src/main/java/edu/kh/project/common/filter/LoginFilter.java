package edu.kh.project.common.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// filter : 클라이언트의 요청 또는 응답을 걸러내거나, 첨가하는 클래스
// 클라이언트 요청이 dispatcher servlet에 도달하기 전에 혹은 응답할때 

// request -> filter -> dispatcher servlet

//@WebFilter : 해당 클래스를 필터로 등록하고, 지정된 주소로 요청이 올때 마다 동작
// fiterName : 필터 별명 (순서 정할 때 필요)
// urlPatterns{""} : 요청주소 안의 메서드가 필터에 걸림

@WebFilter(filterName = "loginFilter", urlPatterns = {"/myPage/*"}) 
public class LoginFilter implements Filter {

	public void init(FilterConfig fConfig) throws ServletException { // 필터 초기화 메서드 : 서버 켜질 때, 필터 안의 코드가 변경되었을 때 실행
	
		System.out.println("--- 로그인 필터 생성 ---");
	}
	
	public void destroy() { // 필터 파괴 메서드 : 필터 코드가 변경되었을 때 이전 코드 파괴
		
		System.out.println("--- 이전 로그인 필터 파괴 ---");
	
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException { // 필터링 코드 작성

		// 1) ServlerRequest, ServletResponse 다운 캐스팅
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse resp = (HttpServletResponse)response;
		
		
		// 2) HttpServletRequest를 이용해서 HttpSession 얻어오기
		HttpSession session = req.getSession();
		
		
		// 3) session에서 "loginMember" key를 가진 속성을 얻어와 
		//    null인 경우 "/"로 리다이렉트
		if( session.getAttribute("loginMember") == null ) {
			
			resp.sendRedirect("/");
		
		} else {
		//4) 로그인 상태면 다음 필터 or dispatcherServlet으로 전달	
			
			chain.doFilter(request, response);
			
		}
		
		

	}


}
