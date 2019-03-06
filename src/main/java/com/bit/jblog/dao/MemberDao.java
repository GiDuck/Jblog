package com.bit.jblog.dao;

import java.sql.SQLException;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit.jblog.utils.status.MemberStatusCode;
import com.bit.jblog.vo.MemberVo;

@Repository
public class MemberDao {

	@Autowired
	private SqlSession sqlSession;
	

	private Logger logger = LoggerFactory.getLogger(MemberDao.class);

	public int insertMember(MemberVo vo) {
		sqlSession.insert("member.insertMember", vo);
		return vo.getNo();
	}

	public MemberVo getMember(MemberVo vo) {
		return sqlSession.selectOne("member.getMember", vo);
	}

	public int updateMember(MemberVo vo) {
		return sqlSession.update("member.updateMember", vo);
	}

	public int deleteMember(int no) {
		return sqlSession.delete("member.deleteMember", no);
	}
	
	public int checkId(String id) {
		return sqlSession.selectOne("member.checkId", id);
	}
	
	public int checkAuth(MemberVo vo) {
		return sqlSession.selectOne("member.checkAuth", vo);
	}
	
	public int getNoById(String id) {
		return sqlSession.selectOne("member.getNoById", id);
	}

}
