package edu.kh.project.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping("/board")
@SessionAttributes({"loginMember"})
public class BoardController {

	/*
	 * 	ex) 조회 주소 예시
	 * 
	 *  목록조회 : /board/1?cp=1 (current page)
	 *  상세조회 : /board/1/1500?cp=1 
	 * 
	 * 	컨트롤러 따로 생성
	 *  삽입 : /board2/1/insert
	 * 	수정 : /board2/1/1500/update
	 * 	삭제 : /board2/1/1500/delete
	 * 
	 * 
	 * */
	
	/*
	 * ** pathVariable 사용시 문제점과 해결법
	 * 
	 * 문제점 : 요청주소와 @PathVariable의 주소의 레벨이 같으면 
	 * 			구분없이 모두 매핑
	 * 
	 * 해결법 : @PathVariable 지정 시 정규 표현식 사용
	 * 			{키 : 정규표현식}
	 * 
	 * 
	 * */
	
	@GetMapping("/{boardCode:[0-9]+}") //boardCode는 1자리 이상 숫자
	public String selectBoardList( @PathVariable("boardCode") int boardCode ) {
	
		// boardCode 확인
		System.out.println("boardCode : " + boardCode);
		
		return "board/boardList";
	}
	
	

	
}
