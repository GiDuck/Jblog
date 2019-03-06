package com.bit.jblog.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bit.jblog.dao.ReplyDao;
import com.bit.jblog.utils.status.BlogStatusCode;
import com.bit.jblog.vo.ReplyVo;

@Service
public class ReplyService {

	@Autowired
	private ReplyDao dao;
	
	public BlogStatusCode insertReply(ReplyVo replyVo) {
		if(dao.insertReply(replyVo) > 0) return BlogStatusCode.REPLY_WRITE_SUCCESS;
		return BlogStatusCode.REPLY_WRITE_FAIL;
	}
	
	public List<Map<String, String>> getReplies (int postNo){
		return dao.getReplies(postNo);
	}
	
	public BlogStatusCode deleteReply(int replyNo) {
		if(dao.deleteReply(replyNo) > 0) return BlogStatusCode.REPLY_DEL_SUCCESS;
		return BlogStatusCode.REPLY_DEL_FAIL;
	}
	
	public BlogStatusCode updateReply(ReplyVo replyVo) {
		if(dao.updateReply(replyVo) > 0) return BlogStatusCode.REPLY_UPDATE_SUCCESS;
		return BlogStatusCode.REPLY_UPDATE_FAIL;
	}
	
}
