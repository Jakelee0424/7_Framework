package edu.kh.project.board.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.project.board.model.dto.Board;
import edu.kh.project.board.model.dto.Pagination;

@Repository
public class BoardDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	/** 게시판 조회 DAO
	 * @return
	 */
	public List<Map<String, Object>> selectBoardTypeList() {

		return sqlSession.selectList("boardMapper.selectBoardTypeList");
	}

	/** 특정 게시판의 전체 게시글 수 조회 DAO 
	 * @param boardCode
	 * @return listCount
	 */
	public int getListCount(int boardCode) {

		return sqlSession.selectOne("boardMapper.getListCount", boardCode);
	}

	/** 원하는 페이지의 게시글 수 조회
	 * @param pagination
	 * @param boardCode
	 * @return
	 */
	public List<Board> selectBoardList(Pagination pagination, int boardCode) {
	
		//RowBounds 작성 후 sql로 넘기기
		
		// RowBounds
		// - 마이바티스에서 페이징처리를 위해 제공하는 객체
		// - offset만큼 건너뛰고 그 다음 지정된 행 개수 만큼 조회
		
		// 1) offset 계산
		int offset = (pagination.getCurrentPage() -1 ) * pagination.getLimit();
		
		// 2) RowBounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		// 3) 특정 게시판(boardCode), rowbounds를 파라미터로 쿼리 작성 
		return sqlSession.selectList("boardMapper.selectBoardList", boardCode, rowBounds);
	
	}

	/** 게시판 상세 조회 DAO
	 * @param map
	 * @return
	 */
	public Board selectBoard(Map<String, Object> map) {
		
		return sqlSession.selectOne("boardMapper.selectBoard", map);
	}

	/** 좋아요 조회 DAO
	 * @param paramMap
	 * @return
	 */
	public int selectLike(Map<String, Object> map) {

		return sqlSession.selectOne("boardMapper.selectLike", map);
	}
	
	/** 조회수 증가 DAO
	 * @param boardNo
	 * @return
	 */
	public int updateReadCount(int boardNo) {

		return sqlSession.update("boardMapper.updateReadCount", boardNo);
	}

	/** 좋아요 추가 DAO
	 * @param paramMap
	 * @return
	 */
	public int insertBoardLike(Map<String, Integer> paramMap) {

		return sqlSession.insert("boardMapper.insertBoardLike", paramMap);
	}

	/** 좋아요 삭제 DAO
	 * @param paramMap
	 * @return
	 */
	public int deleteBoardLike(Map<String, Integer> paramMap) {

		return sqlSession.delete("boardMapper.deletetBoardLike", paramMap);
	}

	/** 좋아요 수 조회 DAO
	 * @param paramMap
	 */
	public int selectBoardLike(Map<String, Integer> paramMap) {

		return sqlSession.selectOne("boardMapper.selectBoardLike", paramMap);
	}

	/** 게시글 검색
	 * @param paramMap
	 * @return
	 */
	public int getListCount(Map<String, Object> paramMap) {

		return sqlSession.selectOne("boardMapper.getListCount_search", paramMap);
	}

	/** 게시글 검색(목록)
	 * @param pagination
	 * @param paramMap
	 * @return
	 */
	public List<Board> selectBoardList(Pagination pagination, Map<String, Object> paramMap) {

		//RowBounds 작성 후 sql로 넘기기
		
		// RowBounds
		// - 마이바티스에서 페이징처리를 위해 제공하는 객체
		// - offset만큼 건너뛰고 그 다음 지정된 행 개수 만큼 조회
		
		// 1) offset 계산
		int offset = (pagination.getCurrentPage() -1 ) * pagination.getLimit();
		
		// 2) RowBounds 객체 생성
		RowBounds rowBounds = new RowBounds(offset, pagination.getLimit());
		
		// 3) 특정 게시판(boardCode), rowbounds를 파라미터로 쿼리 작성 
		return sqlSession.selectList("boardMapper.selectBoardList_search", paramMap, rowBounds);
	}

	
}
