package com.bit.jblog.utils;

import java.sql.Timestamp;

import org.springframework.ui.Model;

import com.bit.jblog.service.BlogService;
import com.bit.jblog.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonObjectFormatVisitor;

public class BlogHelper {

	public static void setBlogInfoInModel(String userId, Model model, MemberService mService, BlogService bService) {

		if (model == null)
			return;
		int no = mService.getNoById(userId);
		model.addAttribute("userId", userId);
		model.addAttribute("blog", bService.getBlogInfo(no));
	}

	public static void setBlogBasicModel(String userId, Model model, MemberService mService, BlogService bService) {
		if (model == null)
			return;

		int no = mService.getNoById(userId);
			
		model.addAttribute("userId", userId);
		model.addAttribute("blog", bService.getBlogInfo(no));
		model.addAttribute("posts", parseToJson(bService.getPosts(no)));
		model.addAttribute("categories", parseToJson(bService.getCategories(no)));
	}
	
	public static void setPostByCategory(String userId, int categoryNum, Model model, MemberService mService, BlogService bService) {
		if (model == null)
			return;

		int no = mService.getNoById(userId);
		model.addAttribute("userId", userId);
		model.addAttribute("blog", bService.getBlogInfo(no));
		model.addAttribute("posts", parseToJson(bService.getPostsByCategory(categoryNum)));
		model.addAttribute("categories", parseToJson(bService.getCategories(no)));
	}

	
	public static void setCategory(String userId, Model model, MemberService mService, BlogService bService) {
		int no = mService.getNoById(userId);
		model.addAttribute("userId", userId);
		model.addAttribute("blog", bService.getBlogInfo(no));
		model.addAttribute("categories", parseToJson(bService.getCategories(no)));
		
	}
	
	public static String parseToJson(Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		String str = null;

		try {
			str = mapper.writeValueAsString(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return str;

	}
	

}
