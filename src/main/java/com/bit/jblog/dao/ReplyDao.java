package com.bit.jblog.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bit.jblog.utils.status.BlogStatusCode;
import com.bit.jblog.vo.ReplyVo;

@Repository
public class ReplyDao {

	@Autowired
	private SqlSession sqlSession;
	
	public int insertReply (ReplyVo replyVo) {
		return sqlSession.insert("reply.insertReply", replyVo);
	}
	
	public List<Map<String, String>> getReplies (int postNo){
		return sqlSession.selectList("reply.getReplies", postNo);
	}
	
	public int deleteReply(int replyNo) {
		return sqlSession.delete("reply.deleteReply", replyNo);
	}
	
	public int updateReply(ReplyVo vo) {
		
		return sqlSession.update("reply.updateReply", vo);
	}
	
}
