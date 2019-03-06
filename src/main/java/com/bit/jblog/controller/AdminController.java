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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bit.jblog.service.BlogService;
import com.bit.jblog.service.MemberService;
import com.bit.jblog.utils.BlogHelper;
import com.bit.jblog.utils.auth.AuthUser;
import com.bit.jblog.utils.status.BlogStatusCode;
import com.bit.jblog.vo.BlogVo;
import com.bit.jblog.vo.CategoryVo;
import com.bit.jblog.vo.MemberVo;
import com.bit.jblog.vo.PostVo;

@Controller
@RequestMapping("/{id:(?![assets|uploads]).*}/admin")
public class AdminController {
	
	@Autowired
	private BlogService service;
	
	@Autowired
	private MemberService memService;
	
	private Logger logger = LoggerFactory.getLogger(AdminController.class);
	

	@RequestMapping("/basic")
	public String forwardManagement(Model model, @AuthUser MemberVo authUser) {
		BlogHelper.setBlogBasicModel(authUser.getId(), model, memService, service);
		return "blog/blog-admin-basic";
	}
	
	@RequestMapping("/category")
	public String forwardManagementCategory(@AuthUser MemberVo authUser, Model model) {
		BlogHelper.setBlogInfoInModel(authUser.getId(), model, memService, service);
		return "blog/blog-admin-category";
	}

	@RequestMapping("/write")
	public String forwardManagementWrite(Model model, @AuthUser MemberVo authUser) {
		BlogHelper.setCategory(authUser.getId(), model, memService, service);
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
	
	
	@RequestMapping("/getCategoryList")
	@ResponseBody
	public List<Map<String, String>> getCategoryList(@RequestParam("userNo") int userNo) {
		return service.getCategoriesByMap(userNo);
	}
	
	@RequestMapping("/removeCategory")
	@ResponseBody
	public int removeCategory(@RequestParam("categoryNo") int categoryNo) {
		return service.removeCategory(categoryNo).getCode();
	}
	
	@RequestMapping("getPosts")
	@ResponseBody
	public List<Map<String, String>> getPosts(@RequestParam("userNo") int userNo){
		return service.getPosts(userNo);
	}
	
	@RequestMapping("getPostsByCategory")
	@ResponseBody
	public List<Map<String, String>> getPostsByCategory(@RequestParam("categoryNo") int categoryNo){
		return service.getPostsByCategory(categoryNo);
	
	}
	
	@RequestMapping(value="writeAction", method=RequestMethod.POST)
	@ResponseBody
	public int writePost(@ModelAttribute PostVo postVo) {
		return service.insertPost(postVo).getCode();
	}
	

}
