package edu.kh.project.member.model.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DuplicationCheckDAO {

	@Autowired 
	private SqlSessionTemplate sqlSession;
	
	
	public int duplicationCheck(String email) {
		
		return sqlSession.selectOne("ajaxMapper.duplicationCheck", email);
	}


	public int duplicationCheckNickname(String nickname) {
		
		
		return sqlSession.selectOne("ajaxMapper.duplicationCheckNickname", nickname);
	}

	
	
}
