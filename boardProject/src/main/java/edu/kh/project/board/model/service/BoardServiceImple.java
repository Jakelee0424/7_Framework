package edu.kh.project.board.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	/** 게시글 상세 조회 서비스
	 *
	 */
	@Override
	public Board selectBoard(Map<String, Object> map) {

		return dao.selectBoard(map);
	}

	/** 좋아요 클릭 여부 조회 서비스
	 *
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int selectLike(Map<String, Object> map) {

		return dao.selectLike(map);
	}

	/** 조회 수 증가 서비스
	 *
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public int updateReadCount(int boardNo) {

		return dao.updateReadCount(boardNo);
	}
	
	/** 좋아요 처리 서비스
	 *
	 */
	@Override
	public int like(Map<String, Integer> paramMap) {

		int result = 0;
		int count = -1;
		
		if(paramMap.get("check") == 0) {
			
			result = dao.insertBoardLike(paramMap);
			
		} else {
			
			result = dao.deleteBoardLike(paramMap);
		
		}
		
		if(result > 0) {
			
			count =  dao.selectBoardLike(paramMap);
			
		}
		
		return count;
	}

	/** 게시글 검색
	 *
	 */
	@Override
	public Map<String, Object> selectBoardList(Map<String, Object> paramMap, int cp) {
		
		// 1. 특정 게시판의 삭제되지 않은 게시글 + 검색 조건이 일치하는 게시글 수 조회
		int listCount = dao.getListCount(paramMap);
		
		// 2. 1번 조회결과 + cp를 이용하여 pagination 객체 생성
		Pagination pagination = new Pagination(listCount, cp);
		
		// 3. 특정 게시판(boardCode)에서 현재 페이지(Pagination.currentPage)에 해당하는 부분에 대한 게시글 목록과
		//	  게시글 수(pagination.limit) 조회 
		//    단, 검색조건이 일치
		List<Board> boardList = dao.selectBoardList(pagination, paramMap);
		
		//4. pagination과 boardlist를 map에 담아 반환
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pagination", pagination);
		map.put("boardList", boardList);
		
		return map;
	}

	
	/** 이미지 목록 조회
	 *
	 */
	@Override
	public List<String> selectImageList() {

		return dao.selectImageList();
	}
	
	

}
