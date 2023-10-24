package edu.kh.project.member.model.service;

public interface DuplicationCheckService {

	int duplicationCheck(String email);

	int duplicationCheckNickname(String nickname);

}
