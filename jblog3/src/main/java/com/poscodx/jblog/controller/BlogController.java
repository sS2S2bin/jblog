package com.poscodx.jblog.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.poscodx.jblog.service.BlogService;
import com.poscodx.jblog.vo.BlogVo;
import com.poscodx.jblog.vo.CategoryVo;
import com.poscodx.jblog.vo.PostVo;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	@Autowired
	private BlogService blogService;
	
	@RequestMapping({"", "/{categoryNo}", "/{categoryNo}/{postNo}"})
	public String index(
			@PathVariable("id") String id,
			@PathVariable(value = "categoryNo", required=false) Long categoryNo,
	        @PathVariable(value = "postNo" , required=false) Long postNo,
	        Model model) {
	
		List<CategoryVo> categoryList = blogService.getCategoryList(id);
		
	    if (categoryNo==null) {
	    	categoryNo = categoryList.get(0).getNo();
	    	System.out.println(categoryList.get(0).getNo());
	    }
		
		List<PostVo> postList = blogService.getPostList(id,categoryNo);
		
	    if (postNo==null) {
	    	postNo = postList.get(0).getNo();
	    	System.out.println(postList.get(0).getNo());
	    }
		
		PostVo vo = blogService.getContent(id,categoryNo,postNo);
		BlogVo blogVo = blogService.getBlogContent(id);
		
		model.addAttribute("postVo",vo);
		model.addAttribute("blogVo",blogVo);
		model.addAttribute("categoryList",categoryList);
		model.addAttribute("postList",postList);
		model.addAttribute("postNo",postNo);
		model.addAttribute("categoryNo",categoryNo);
		model.addAttribute("id",id);
		
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
