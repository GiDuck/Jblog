package com.bit.jblog.controller;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bit.jblog.service.BlogService;
import com.bit.jblog.utils.auth.AuthUser;
import com.bit.jblog.utils.status.BlogStatusCode;
import com.bit.jblog.vo.BlogVo;
import com.bit.jblog.vo.CategoryVo;
import com.bit.jblog.vo.MemberVo;

@Controller
@RequestMapping("/{id:(?!assets)(?!uploads).*}/admin")
public class AdminController {
	
	@Autowired
	private BlogService service;
	
	private Logger logger = LoggerFactory.getLogger(AdminController.class);
	

	@RequestMapping("/basic")
	public String forwardManagement(Model model, @AuthUser MemberVo authUser) {
		model.addAttribute("admin", service.getBlogInfo(authUser.getNo()));
		return "blog/blog-admin-basic";
	}
	
	@RequestMapping("/category")
	public String forwardManagementCategory() {
		return "blog/blog-admin-category";
	}

	@RequestMapping("/write")
	public String forwardManagementWrite() {
		logger.info(":::write");
		return "blog/blog-admin-write";
	}
	
	@RequestMapping(value = "/updateInfo", method = RequestMethod.POST)
	@ResponseBody
	public int updateBlogInfo(@ModelAttribute BlogVo blogVo, @RequestPart(name="logo-file", required=false) MultipartFile image) {
		return service.updateBlogInfo(blogVo, image).getCode();
	}
	
	@RequestMapping(value="/regCategory", method=RequestMethod.POST)
	@ResponseBody
	public int regCategory(@ModelAttribute CategoryVo categoryVo) {
		return service.insertCategory(categoryVo).getCode();
	}
	
	
	@RequestMapping(value="/getCategoryList")
	@ResponseBody
	public List<Map<String, String>> getCategoryList(@RequestParam("userNo") int userNo) {
		return service.getCategoriesByMap(userNo);
	}
	
	@RequestMapping(value="/removeCategory")
	@ResponseBody
	public int removeCategory(@RequestParam("categoryNo") int categoryNo) {
		return service.removeCategory(categoryNo).getCode();
	}
	
	
	
	

}
