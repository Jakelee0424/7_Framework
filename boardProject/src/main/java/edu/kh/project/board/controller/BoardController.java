package edu.kh.project.board.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import edu.kh.project.board.model.service.BoardService;

@Controller
@RequestMapping("/board")
@SessionAttributes({"loginMember"})
public class BoardController {

	@Autowired
	private BoardService service; 
	
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
	 * @PathVariable
	 * - url 경로에 있는 값을 매개변수로 이용할 수 있게 하는 어노테이션
	 *   + request scope에 세팅
	 * 
	 * - url 값을 '/' 로 구분 : 자원의 구분 혹은 식별 
	 *    ex) github.com/jaekyung
	 *    ex) github.com/test
	 *    
	 * - url 값을 쿼리스트링으로 구분 : 검색, 정렬, 필터링
	 * 	  ex) search.naver.com?query=날씨
	 * 	  ex) search.naver.com?query=맛집
	 * 	  ex) /board2/insert?code=1
	 * 	  ex) /board2/insert?code=2
	 * 	  ex) /board2/list?order=recent
	 * 	  ex) /board2/list?order=most
	 *   
	 * 
	 * 
	 * ** @PathVariable 사용시 문제점과 해결법
	 *  
	 * 문제점 : 요청주소와 @PathVariable의 주소의 레벨이 같으면 
	 * 			구분없이 모두 매핑
	 * 
	 * 해결법 : @PathVariable 지정 시 정규 표현식 사용
	 * 			{키 : 정규표현식}
	 * 
	 * 
	 * 
	 * */
	
	/** 게시글 목록조회 
	 * @param boardCode
	 * @param cp
	 * @return
	 */
	@GetMapping("/{boardCode:[0-9]+}") //boardCode는 1자리 이상 숫자
	public String selectBoardList( @PathVariable("boardCode") int boardCode,
								   @RequestParam(value="cp", required=false , defaultValue="1") int cp,
								   Model model
			) {

		// 게시글 목록 조회 서비스 호출 
		Map<String, Object> map = service.selectBoardList(boardCode, cp);
		
		// 조회 결과물 request scope에 세팅 후 forward
		model.addAttribute("map", map);
		
			
		return "board/boardList";
	}
	
	

	
}
