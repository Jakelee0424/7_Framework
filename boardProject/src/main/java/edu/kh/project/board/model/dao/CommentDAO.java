package edu.kh.project.board.model.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.project.board.model.dto.Comment;

@Repository
public class CommentDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	public List<Comment> select(int boardNo) {

		return sqlSession.selectList("boardMapper.selectCommentList", boardNo);
	}

	public int delete(int commentNo) {
		
		return sqlSession.update("boardMapper.deleteComment", commentNo);
	}

	public int insert(Map<String, Object> map) {
		
		return sqlSession.insert("boardMapper.insertComment", map);
	}

	public int update(Map<String, Object> map) {

		return sqlSession.update("boardMapper.updateComment",map);
	}

	public int insertComment(Comment comment) {
		
		return sqlSession.insert("boardMapper.insert", comment);
	}



}
