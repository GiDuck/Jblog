package com.bit.jblog.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bit.jblog.service.BlogService;
import com.bit.jblog.service.MemberService;
import com.bit.jblog.utils.BlogHelper;

@Controller
@RequestMapping("/{id:(?![assets|uploads]).*}")
public class BlogController {
	
	@Autowired
	private BlogService service;
	
	@Autowired
	private MemberService memService;
	
	private Logger logger = LoggerFactory.getLogger(BlogController.class);
	
	@RequestMapping("")
	public String forwardBlog(@PathVariable("id") String id, Model model) {
		
		BlogHelper.setBlogBasicModel(id, model, memService, service);
		
		return "blog/blog-main";
	}
	
	
	@RequestMapping("/{category}")
	public String forwardBlogForCategory(@PathVariable("id") String id, 
			@PathVariable("category") Optional<Integer> category,
			Model model) {
		
		if(category.isPresent()) {
			BlogHelper.setPostByCategory(id, category.get(), model, memService, service);
		}else {
			BlogHelper.setBlogBasicModel(id, model, memService, service);
		}
		return "blog/blog-main";
	}
	
	@RequestMapping("/{category}/{post}")
	public String forwardBlogForPost(@PathVariable("id") String id, 
			@PathVariable("category") Optional<Integer> category,
			@PathVariable("post") Optional<Integer> post,
			Model model) {
		
		if(category.isPresent()) {
			BlogHelper.setPostByCategory(id, category.get(), model, memService, service);
		}
		
		if(post.isPresent()) {
			
			System.out.println(service.getSinglePost(post.get()).toString());
			model.addAttribute("singlePost", service.getSinglePost(post.get()));
		}
		
		
		
		return "blog/blog-main";
		
	}
	
	

	
	
}
