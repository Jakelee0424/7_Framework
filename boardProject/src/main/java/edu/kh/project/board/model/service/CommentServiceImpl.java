package edu.kh.project.board.model.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.project.board.model.dao.CommentDAO;
import edu.kh.project.board.model.dto.Comment;
import edu.kh.project.common.utility.Util;

@Service
public class CommentServiceImpl implements CommentService{

	@Autowired
	private CommentDAO dao;
	
	@Override
	public List<Comment> select(int boardNo) {

		return dao.select(boardNo);
	}

	@Override
	public int delete(int commentNo) {

		return dao.delete(commentNo);
	}

	@Override
	public int insert(Map<String, Object> map) {

		return dao.insert(map);
	}

	@Override
	public int update(Map<String, Object> map) {

		return dao.update(map);
	}


}
