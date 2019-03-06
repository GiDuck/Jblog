package jblog;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.bit.jblog.service.ReplyService;
import com.bit.jblog.utils.status.BlogStatusCode;
import com.bit.jblog.vo.MemberVo;
import com.bit.jblog.vo.ReplyVo;

import jdk.nashorn.internal.ir.annotations.Ignore;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/resources/application-context.xml",
		"file:webapp/WEB-INF/spring-servlet.xml"})
public class ReplyTest {

	private MemberVo memberVo;
	
	@Autowired
	private ReplyService service;
	
	@Before
	public void setup() {
		
		memberVo = new MemberVo();
		memberVo.setName("김기덕");
		memberVo.setId("gdtbgl93");
		memberVo.setPassword("kim1234");
		memberVo.setNo(7);
	}
	
	
	
	@Test
	@Ignore
	public void insertReplyTest() {
		
		ReplyVo vo = new ReplyVo();
		vo.setPost_no(2);
		vo.setContent("hello");
		Assert.assertEquals(BlogStatusCode.REPLY_WRITE_SUCCESS, service.insertReply(vo));
		
	}
	
	
	@Test
	@Ignore
	public void getReplyTest() {
	
		List<Map<String, String>> replies = service.getReplies(4);		
		Assert.assertNotNull(replies);
		
		for(Map<String, String> reply : replies) {
			System.out.println(reply);	
		}
		
	}
	
	@Test
	@Ignore
	public void updateReplyTest() {
		ReplyVo vo = new ReplyVo();
		vo.setNo(0);
		vo.setContent("수정한 내용임 ㅋ");
		Assert.assertEquals(BlogStatusCode.REPLY_UPDATE_SUCCESS, service.updateReply(vo));
	}
	
	
	
	@Test
	@Ignore
	public void deleteReplyTest() {
		//Assert.assertEquals(BlogStatusCode.REPLY_DEL_SUCCESS, service.deleteReply(2));
	}
	
	
}
