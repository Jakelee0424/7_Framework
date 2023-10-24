package edu.kh.project.member.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.kh.project.member.model.dao.DuplicationCheckDAO;

@Service
public class DuplicationCheckServiceImpl implements DuplicationCheckService{

	@Autowired
	private DuplicationCheckDAO dao;
	
	
	@Override
	public int duplicationCheck(String email) {

		
		return dao.duplicationCheck(email);
	}


	@Override
	public int duplicationCheckNickname(String nickname) {
		
		
		return dao.duplicationCheckNickname(nickname);
	}


}
