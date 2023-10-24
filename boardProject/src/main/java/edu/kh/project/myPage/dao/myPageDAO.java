package edu.kh.project.myPage.dao;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.kh.project.member.model.dto.Member;

@Repository
public class myPageDAO {

	@Autowired
	private SqlSessionTemplate sqlSession;

	
	/** 정보 수정 DAO
	 * @param updateMember
	 * @return
	 */
	public int updateInfo(Member updateMember) {
	
		return sqlSession.update("myPageMapper.update", updateMember);
	}

	/** 비밀번호 조회 DAO
	 * @param string
	 * @return
	 */
	public String selectPw(Map<String, String> map) {

		return sqlSession.selectOne("myPageMapper.selectPw", map);
	}
	
	/** 비밀번호 변경 DAO
	 * @param map
	 * @return
	 */
	public int updatePw(Map<String, String> map) {

		return sqlSession.update("myPageMapper.updatePw", map);
	}

	/** 회원 탈퇴 DAO
	 * @param map
	 * @return
	 */
	public int delete(Map<String, String> map) {

		return sqlSession.update("myPageMapper.delete", map);
	}

	/** 프로필 사진 수정 DAO
	 * @param loginMember
	 * @return
	 */
	public int updateProfileImage(Member loginMember) {

		return sqlSession.update("myPageMapper.updateProfileImage", loginMember);

	}


}


