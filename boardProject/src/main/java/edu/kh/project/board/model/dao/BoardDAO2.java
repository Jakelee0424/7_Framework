package edu.kh.project.board.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.project.board.model.dto.Board;
import edu.kh.project.board.model.dto.BoardImage;

@Repository
public class BoardDAO2 {

	@Autowired
	private SqlSessionTemplate sqlSession;

	/** 글쓰기 DAO
	 * @param board
	 * @return
	 */
	public int boardInsert(Board board) {

		int result = sqlSession.insert("boardMapper.boardInsert", board);
		
		if(result > 0 ) {
			result = board.getBoardNo();
		}
		
		return result;
	}

	/** 사진 업로드 DAO
	 * @param uploadList
	 * @return 
	 */
	public int insertImages(List<BoardImage> uploadList) {
		
		return sqlSession.insert("boardMapper.insertImages", uploadList);
	}

	/** 글 수정(제목, 내용) DAO
	 * @param board
	 * @return
	 */
	public int boardUpdate(Board board) {
		
		return sqlSession.update("boardMapper.boardUpdate", board);
	}

	/** 사진 삭제 DAO
	 * @param deleteMap
	 * @return
	 */
	public int imageDelete(Map<String, Object> deleteMap) {

		return sqlSession.delete("boardMapper.imageDelete", deleteMap);
	}

	/** 사진 업데이트 DAO
	 * @param img
	 * @return
	 */
	public int imageUpdate(BoardImage img) {

		return sqlSession.update("boardMapper.imageUpdate", img);
	}

	/** 사진 삽입 DAO
	 * @param img
	 * @return
	 */
	public int imageInsert(BoardImage img) {

		return sqlSession.insert("boardMapper.imageInsert", img);
	}

	/** 개시글 삭제 DAO
	 * @param map
	 * @return
	 */
	public int boardDelete(Map<String, Object> map) {

		return sqlSession.update("boardMapper.boardDelete", map);
	}
}
