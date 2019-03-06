package jblog;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.bit.jblog.service.BlogService;
import com.bit.jblog.utils.status.BlogStatusCode;
import com.bit.jblog.vo.MemberVo;
import com.bit.jblog.vo.PostVo;
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/resources/application-context.xml",
		"file:webapp/WEB-INF/spring-servlet.xml"})
public class PostingTest {
	
	@Autowired
	private BlogService service;
	
	private MemberVo memberVo;
	
	
	@Before
	public void setup() {
		
		memberVo = new MemberVo();
		memberVo.setName("김기덕");
		memberVo.setId("gdtbgl93");
		memberVo.setPassword("kim1234");
		memberVo.setNo(7);
	}

	
	@Ignore
	@Test
	public void writePostTest() {
		
		
		PostVo vo = new PostVo();
		vo.setTitle("22 포스팅");
		vo.setCategory_no(7);
		vo.setContent("12시에 올리는 첫번째 포스팅....");
		Assert.assertEquals(BlogStatusCode.POST_SUCCESS, service.insertPost(vo));
		
		
	}
	

	@Ignore
	@Test
	public void getPostTest() {
		
		List<Map<String, String>> posts = service.getPosts(memberVo.getNo());
		Assert.assertNotNull(posts);
		for(Map<String, String> map : posts) {
			System.out.println(map);
		}	
		
	}

	@Ignore
	@Test
	public void getPostByCategory() {
		
		List<Map<String, String>> posts = service.getPostsByCategory(2);
		Assert.assertNotNull(posts);
		for(Map<String, String> map : posts) {
			System.out.println(map);
		}	

	}
	
	
	@Ignore
	@Test
	public void deletePost() {
		Assert.assertEquals(BlogStatusCode.POST_DEL_SUCCESS, service.deletePost(1));
	}
	
	
	@Ignore
	@Test
	public void updatePost() {
		
		PostVo vo = new PostVo();
		vo.setNo(2);
		vo.setTitle("수정한 제목");
		vo.setContent("수정한 내용");
		vo.setCategory_no(6);
		Assert.assertEquals(BlogStatusCode.POST_UPDATE_SUCCESS, service.updatePost(vo));
		
	}
	
	@Test
	public void getSinglePost() {
		PostVo vo = service.getSinglePost(3);
		Assert.assertNotNull(vo);
		System.out.println(vo.toString());
	}
	
	
	
	
	

}
