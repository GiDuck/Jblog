package member;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.Date;

import org.junit.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.bit.jblog.service.MemberService;
import com.bit.jblog.utils.status.MemberStatusCode;
import com.bit.jblog.vo.MemberVo;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:src/main/resources/application-context.xml",
		"file:webapp/WEB-INF/spring-servlet.xml"})
public class MemberTest {

	private Logger logger = LoggerFactory.getLogger(MemberTest.class);
	
	@Autowired
	MemberService service;
	
	private MemberVo vo;
	
	@Before
	public void setUp() {
		vo = new MemberVo();
		vo.setName("김기덕");
		vo.setId("gdtbgl93");
		vo.setPassword("kim1234");
	}
	
	@Ignore
	@Test
	public void testInsertMember() {
		Assert.assertEquals(MemberStatusCode.JOIN_SUCCESS, service.insertMember(vo));	
	}
	
	@Ignore
	@Test
	public void testCheckId() {
		vo.setId("kimchi");
		MemberStatusCode status = service.checkId(vo.getId());
		Assert.assertEquals(MemberStatusCode.AUTH_SUCCESS,status);
	}
	

	@Test
	public void testCheckAuth() {
		
		MemberStatusCode status = service.checkAuth(vo);
		Assert.assertEquals(MemberStatusCode.AUTH_SUCCESS,status);
		
	}
	
	@Ignore
	@Test
	public void testGetMember() {
		MemberVo result = service.getMember(vo);
		Assert.assertNotNull(result);
		logger.info(result.toString());	
	}
	
	@Ignore
	@Test
	public void testModMember() {
		Assert.assertEquals(MemberStatusCode.ACCOUNT_MOD_SUCCESS, service.updateMember(vo));
	}
	
	@Ignore
	@Test
	public void testDeleteMember() {
		Assert.assertEquals(MemberStatusCode.ACCOUNT_DEL_SUCCESS ,service.deleteMember(vo));
	}
	

	@Test
	public void testGetNo() {
		int num = service.getNoById("gdtbgl93");
		assertNotNull(num);
		
	}
	

}
