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
	
}
