package com.bit.jblog.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DefaultController {

	private static Logger logger = LoggerFactory.getLogger(DefaultController.class);
	
	@RequestMapping("/")
	public String welcome() {
		return "main/index";
	}
		
}
