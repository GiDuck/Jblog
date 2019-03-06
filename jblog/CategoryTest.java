package jblog;

import static org.junit.Assert.*;

import java.util.Date;
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
import com.bit.jblog.vo.CategoryVo;
import com.bit.jblog.vo.MemberVo;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/resources/application-context.xml",
		"file:webapp/WEB-INF/spring-servlet.xml"})
public class CategoryTest {
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

}
