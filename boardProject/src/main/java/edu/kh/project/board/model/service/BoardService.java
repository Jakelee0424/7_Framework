package edu.kh.project.board.model.service;

import java.util.List;
import java.util.Map;

import edu.kh.project.board.model.dto.Board;

public interface BoardService {

	/** 게시글 타입 조회
	 * @return
	 */
	List<Map<String, Object>> selectBoardTypeList();

	/** 게시글 목록 조회
	 * @param boardCode
	 * @param cp
	 * @return
	 */
	Map<String, Object> selectBoardList(int boardCode, int cp);

	Board selectBoard(Map<String, Object> map);

	int selectLike(Map<String, Object> map);

	int updateReadCount(int boardNo);

	int like(Map<String, Integer> paramMap);

}
