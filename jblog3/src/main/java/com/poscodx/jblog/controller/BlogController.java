package com.poscodx.jblog.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	
	@RequestMapping({"", "/{categoryNo}", "/{categoryNo}/{postNo}"})
	public String index(
			@PathVariable("id") String id,
			@PathVariable("categoryNo") Optional<Long> categoryNo,
			@PathVariable("postNo") Optional<Long> postNo //required=ture, default value 셋
			) {
		
		
		return "blog/main";
	}
	
	//@Auth
	@RequestMapping("/admin/basic")
	public String adminBasic(@PathVariable("id") String id){
		return "blog/admin-basic";
	}
	
	//@Auth
	@RequestMapping("/admin/category")
	public String adminCategory(@PathVariable("id") String id){
		return "blog/admin-category";
	}
	
	//@Auth
	@RequestMapping("/admin/write")
	public String adminWrite(@PathVariable("id") String id){
		return "blog/admin-write";
	}

}
