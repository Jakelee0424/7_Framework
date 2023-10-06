package edu.kh.project.member.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.kh.project.member.model.dto.Member;
import edu.kh.project.member.model.service.AjaxService;

@Controller // 요청/응답 제어 + bean 등록
public class AjaxController {

	@Autowired
	private AjaxService service;
	
	/** 닉네임 검색으로 전화번호 조회
	 * @param nickname
	 * @return
	 */
	@GetMapping("/selectMemberTel")
	@ResponseBody // controller의 결과로 데이터를 반환함을 알려줌
	public String selectMemberTel(String nickname) {
		
		
		// return 리다이렉트 -> 새로운 화면 : 동기식
		// return 데이터 -> 데이터 반환 : 비동기식
		return service.selectMemberTel(nickname);
	}
	
	/** 이메일로 회원정보 조회
	 * @param memberTel
	 * @return
	 */
	@PostMapping("/selectMember")
	@ResponseBody // 비동기 응답 + java데이터를 JSON, TEXT로 변환
	public Member selectMember(@RequestBody Map<String, Object> paramMap) {
		
		// @RequestBody Map<String, Object> paramMap
		// : 요청된 HTTP Body에 담긴 모든 데이터를 자바 객체인 Map으로 반환
		
		String email = (String) paramMap.get("email");
		
		return service.selectMember(email);
	}
	
	
	


}