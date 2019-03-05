package jblog;

import static org.junit.Assert.*;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.multipart.MultipartFile;

import com.bit.jblog.service.BlogService;
import com.bit.jblog.utils.file.FileUploadUtil;
import com.bit.jblog.utils.status.BlogStatusCode;
import com.bit.jblog.vo.BlogVo;
import com.bit.jblog.vo.CategoryVo;
import com.bit.jblog.vo.MemberVo;
import com.bit.jblog.vo.PostVo;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/resources/application-context.xml",
		"file:webapp/WEB-INF/spring-servlet.xml"})
public class BlogTest {

	Logger logger = LoggerFactory.getLogger(BlogTest.class);
	
	@Autowired
	private BlogService service;
	
	private MemberVo memberVo;
	
	private MultipartFile mockFile;
	
	private final static String PARENT_PATH = "c:";
	private final static String UPLOAD_PATH = "/uploads/images";
	
	@Before
	public void setup() {
		
		memberVo = new MemberVo();
		memberVo.setName("김기덕");
		memberVo.setId("gdtbgl93");
		memberVo.setPassword("kim1234");
		memberVo.setNo(7);
	}
	
	private void mockup() {
		
		File file = new File("C:/uploads/cat.jpg");
		byte[] buf = new byte[ (int) file.length()];

		BufferedInputStream bis;
		try {
			bis = new BufferedInputStream(new FileInputStream(file));
			bis.read(buf);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		mockFile = new MockMultipartFile("cat", "cat.jpg", "jpg", buf);
		
		
	}
	
	@Ignore
	@Test
	public void makeBlogTest() {	
		BlogVo blogVo = new BlogVo();
		blogVo.setUser_no(memberVo.getNo());
		blogVo.setTitle(memberVo.getName() + " 님의 블로그 입니다.");
		blogVo.setLogo("/assets/images/logo.jpg");
		Assert.assertEquals(BlogStatusCode.BLOG_MAKE_SUCCESS, service.makeBlog(blogVo));
		
		
	}
	
	@Ignore
	@Test
	public void getBlogInfo() {
		BlogVo vo = service.getBlogInfo(memberVo.getNo());
		Assert.assertNotNull(vo);
		logger.info(vo.toString());
		
		
	}
	
	
	@Ignore
	@Test
	public void insertBasicInfoTest() {
		
		mockup();
		String url = FileUploadUtil.uploadFile(mockFile, "cat.jpg", PARENT_PATH, UPLOAD_PATH);
		
		BlogVo blogVo = new BlogVo();
		blogVo.setUser_no(memberVo.getNo());
		blogVo.setTitle("2019년 모두 복 많이 받으세요...");
		blogVo.setLogo(url);
		
		//service.updateBlogInfo(blogVo);
		
		
		
	}
	
	
	
	@Ignore
	@Test
	public void insertCategoryTest() {
		
		
		CategoryVo categoryVo = new CategoryVo();
		categoryVo.setName("요리");
		categoryVo.setDescription("치즈볼");
		categoryVo.setReg_date(new Date());
		categoryVo.setUser_no(memberVo.getNo());
		Assert.assertEquals(BlogStatusCode.CATEGORY_ADD_SUCCESS, service.insertCategory(categoryVo));
		
	}
	
	
	@Ignore
	@Test
	public void getCategoryTest() {
		
		List<CategoryVo> categories = service.getCategories(memberVo.getNo());
		for(CategoryVo vo : categories) {
			System.out.println(vo.toString());
		}
		
	}
	
	@Ignore
	@Test
	public void removeCategoryTest() {
		
		Assert.assertEquals(BlogStatusCode.CATEGORY_DEL_SUCCESS, service.removeCategory(1));
		
	}
	
	@Ignore
	@Test
	public void getCategoryByMapTest() {
		
		List<Map<String, String>> categories = service.getCategoriesByMap(memberVo.getNo());
		for(Map<String, String> map : categories) {
			System.out.println(map);
		}
		
	}
	
	@Test
	public void writeBoardTest() {
		
		
		PostVo vo = new PostVo();
		vo.setTitle("22 포스팅");
		vo.setCateogry_no(7);
		vo.setContent("12시에 올리는 첫번째 포스팅....");
		Assert.assertEquals(BlogStatusCode.POST_SUCCESS, service.insertPost(vo));
		
		
	}
	
	
	
	
	
	
	

}
