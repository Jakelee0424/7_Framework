package edu.kh.project.board.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.project.board.model.dao.BoardDAO;
import edu.kh.project.board.model.dto.Board;
import edu.kh.project.board.model.dto.Pagination;

@Service
public class BoardServiceImple implements BoardService {

	@Autowired
	private BoardDAO dao;
	
	/** 게시판 조회 서비스
	 *
	 */
	@Override
	public List<Map<String, Object>> selectBoardTypeList() {

		return dao.selectBoardTypeList();
	}

	/** 게시글 목록 조회 서비스
	 *
	 */
	@Override
	public Map<String, Object> selectBoardList(int boardCode, int cp) {
		
		// 1. 특정 게시판의 삭제되지 않은 게시글 수 조회
		int listCount = dao.getListCount(boardCode);
		
		// 2. 1번 조회결과 + cp를 이용하여 pagination 객체 생성
		Pagination pagination = new Pagination(listCount, cp);
		
		// 3. 특정 게시판(boardCode)에서 원하는 페이지(Pagination.currentPage)에 해당하는 부분에 대한 게시글 목록과
		//	  게시굴 수(pagination.limit) 조회
		List<Board> boardList = dao.selectBoardList(pagination, boardCode);
		
		//4. pagination과 boardlist를 map에 담아 반환
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagination", pagination);
		map.put("boardList", boardList);
		
		return map;
		
	}

}
