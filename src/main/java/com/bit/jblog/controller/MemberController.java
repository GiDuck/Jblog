package com.bit.jblog.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bit.jblog.service.MemberService;
import com.bit.jblog.utils.status.MemberStatusCode;
import com.bit.jblog.vo.MemberVo;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	private static Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/login")
	public String login() {
		return "user/login";
		
	}
	
	@RequestMapping("/join")
	public String join() {
		return "user/join";
		
	}
	
	@RequestMapping("/joinSuccess")
	public String joinSuccess() {
		return "user/joinsuccess";
	}
	
	@RequestMapping("/checkId")
	@ResponseBody
	public int checkId(@RequestParam("id") String id) {
		return memberService.checkId(id).getCode();
	}
	
	@RequestMapping(value = "/joinAction", method = RequestMethod.POST)
	@ResponseBody
	public int joinAction(@ModelAttribute MemberVo memberVo) {
		return memberService.insertMember(memberVo).getCode();
	}
	
	@RequestMapping(value = "/loginAction", method = RequestMethod.POST)
	@ResponseBody
	public int loginAction(@ModelAttribute MemberVo memberVo) {
		return memberService.checkAuth(memberVo).getCode();
	}
	
	@RequestMapping("/logout")
	public String logout(HttpServletRequest request) {
		return "redirect:/";
	}
	
	
	
	
	
	
}
