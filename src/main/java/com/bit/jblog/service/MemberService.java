package com.bit.jblog.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bit.jblog.dao.BlogDao;
import com.bit.jblog.dao.MemberDao;
import com.bit.jblog.utils.status.BlogStatusCode;
import com.bit.jblog.utils.status.MemberStatusCode;
import com.bit.jblog.vo.BlogVo;
import com.bit.jblog.vo.MemberVo;

@Service
public class MemberService {

	private static Logger logger = LoggerFactory.getLogger(MemberService.class);
	
	@Autowired
	private MemberDao dao;
	
	@Autowired
	private BlogDao blogDao;
	
	@Transactional
	public MemberStatusCode insertMember (MemberVo vo) { 
	
		if(dao.insertMember(vo) == 1) {
			BlogVo blogVo = new BlogVo();
			blogVo.setTitle(vo.getName() + " 님의 블로그 입니다.");
			blogVo.setLogo("/assets/images/logo.jpg\"");
			blogVo.setUser_no(vo.getNo());
			if(blogDao.makeBlog(blogVo) > 0) return MemberStatusCode.JOIN_SUCCESS;
		}
		return MemberStatusCode.JOIN_FAIL;
		
	}
	
	public MemberStatusCode checkId (String id) {
		
		if(dao.checkId(id) == 0) {
			return MemberStatusCode.AUTH_SUCCESS;
		}

		return MemberStatusCode.AUTH_FAIL;
		
	}
	
	public MemberStatusCode checkAuth (MemberVo vo) {
		
		int idCheckFlag = dao.checkId(vo.getId());
		int authFlag = dao.checkAuth(vo);
		
		if( idCheckFlag == 0) return MemberStatusCode.ID_NOT_VALID;
		else if(authFlag == 0) return MemberStatusCode.PWD_NOT_VALID;
		else if(idCheckFlag == 1 && authFlag == 1) return MemberStatusCode.AUTH_SUCCESS;
		else return MemberStatusCode.AUTH_FAIL;
		
	}
	
	public MemberVo getMember(MemberVo vo) { return dao.getMember(vo); }
	
	public MemberStatusCode updateMember(MemberVo vo) {
		
		if(dao.updateMember(vo) == 1) return MemberStatusCode.ACCOUNT_MOD_SUCCESS;
		return MemberStatusCode.ACCOUNT_MOD_FAIL;
		
	}
	
	public MemberStatusCode deleteMember(MemberVo vo) {
		
		if(dao.deleteMember(vo.getNo()) == 1) return MemberStatusCode.ACCOUNT_DEL_SUCCESS;
		return MemberStatusCode.ACCOUNT_DEL_FAIL;
		
	}
	
	
	
}
