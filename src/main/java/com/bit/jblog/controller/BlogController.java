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

@Controller
@RequestMapping("/{id:(?!assets)(?!uploads).*}")
public class BlogController {
	
	@Autowired
	private BlogService service;
	
	private Logger logger = LoggerFactory.getLogger(BlogController.class);
	
	@RequestMapping("")
	public String forwardBlog(@PathVariable("id") String id, Model model) {
		return "blog/blog-main";
	}
	
	
	@RequestMapping("/{category}")
	public String forwardBlogForCategory(@PathVariable("id") String id, 
			@PathVariable("category") Optional<Long> category,
			Model model) {
		
		return null;
	}
	
	@RequestMapping("/{category}/{post}")
	public String forwardBlogForPost(@PathVariable("id") String id, 
			@PathVariable("category") Optional<Long> category,
			@PathVariable("post") Optional<Long> post,
			Model model) {
		
		return null;
	}
	
	

	
	
}
