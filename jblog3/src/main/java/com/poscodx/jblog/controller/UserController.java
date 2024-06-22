package com.poscodx.jblog.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.poscodx.jblog.dto.JsonResult;
import com.poscodx.jblog.service.BlogService;
import com.poscodx.jblog.service.UserService;
import com.poscodx.jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
	
	private UserService userService;
	private BlogService boardService;
	
	public UserController(UserService userService,BlogService boardService) {
		this.userService = userService;
		this.boardService = boardService;
	}
	
	@RequestMapping("/checkid")
	@ResponseBody
	public JsonResult checkId(@RequestParam(value="id", required=true, defaultValue="") String id) {
		boolean exists = userService.checkId(id);
		if(exists) {
			return JsonResult.fail("이미 존재하는 아이디 입니다.");
		}else {
			return JsonResult.success(id!=null);			
		}
	}
	
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(@RequestParam(value="result",required=false) String result ,  Model model) {
		model.addAttribute("result",result);
		return "user/login";
	}
	
	
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join() {
		return "user/join";
	}
	
	@RequestMapping(value="/joinsuccess", method=RequestMethod.GET)
	public String joinsuccess() {
		return "user/joinsuccess";
	}
	
	@RequestMapping(value="/join", method=RequestMethod.POST)
	public String join(UserVo vo) {
		System.out.println("join "+vo);
		userService.join(vo);
		boardService.join(vo.getId());
		return "redirect:/user/joinsuccess";
	}
	
}
