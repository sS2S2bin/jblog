package com.poscodx.jblog.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.poscodx.jblog.security.Auth;
import com.poscodx.jblog.service.BlogService;
import com.poscodx.jblog.service.FileUploadService;
import com.poscodx.jblog.vo.BlogVo;
import com.poscodx.jblog.vo.CategoryVo;
import com.poscodx.jblog.vo.PostVo;
import com.poscodx.jblog.vo.UserVo;

@Controller
@RequestMapping("/{id:(?!assets).*}")
public class BlogController {
	
	@Autowired
	private BlogService blogService;
	@Autowired
	private FileUploadService fileUploadService;
	
	@Autowired
	private ServletContext servletContext;
	
	
	@RequestMapping({"", "/{categoryNo}", "/{categoryNo}/{postNo}"})
	public String index(
			@PathVariable("id") String id,
			@PathVariable(value = "categoryNo") Optional<Long> categoryNumber,
	        @PathVariable(value = "postNo") Optional<Long> postNumber,
	        Model model) {
	
		
		Long categoryNo = 0L;
		Long postNo = 0L;
		
		// categoryList 가져오기 
		List<CategoryVo> categoryList = blogService.getCategoryList(id);
		
		// categoryNo 
		if(postNumber.isPresent()) {
			postNo = postNumber.get();
			categoryNo = categoryNumber.get();	  
		}
		else if (categoryNumber.isPresent()) {
			categoryNo = categoryNumber.get();	  
	    }else {
	    	categoryNo = categoryList.get(0).getNo();
	    }
	    
	    List<PostVo> postList = blogService.getPostList(categoryNo);
		
		if(postList.isEmpty()) {
		} 
		//post 는 존재하는데 id 입력하지 않은 상황
		else if(!postNumber.isPresent() && !postList.isEmpty()) { 
			postNo = postList.get(0).getNo();
			model.addAttribute("postNo",postNo);
		    PostVo postVo = blogService.getContent(id,categoryNo,postNo);
		    model.addAttribute("postVo",postVo);
		    model.addAttribute("postList",postList);	 
		} 
		//포스트도 존재하고 id도 있는 상황
		else { 
			model.addAttribute("postNo",postNo);
		    PostVo postVo = blogService.getContent(id,categoryNo,postNo);
		    model.addAttribute("postVo",postVo);
		    model.addAttribute("postList",postList);	    	
		}
		
		BlogVo blogVo = blogService.getBlogContent(id);
		model.addAttribute("blogVo",blogVo);
		model.addAttribute("categoryList",categoryList);
		model.addAttribute("categoryNo",categoryNo);
		model.addAttribute("id",id);
		
		return "blog/main";
	}
	
	@Auth
	@RequestMapping("/admin/basic")
	public String adminBasic(@PathVariable("id") String id,
			HttpSession session,
			Model model,
			RedirectAttributes redirectAttributes ){
		
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		String actualId = authUser.getId();
		
		if(id.equals(actualId)) {
			BlogVo blogVo = blogService.getBlogContent(id);
			model.addAttribute("blogVo",blogVo);			
			return "blog/admin-basic";
		}else {
			redirectAttributes.addAttribute("result", "badRequest");
	        return "redirect:/user/login";
		}
	}
	
	@Auth
	@RequestMapping("/admin/category")
	public String adminCategory(@PathVariable("id") String id,
			HttpSession session, Model model,
			RedirectAttributes redirectAttributes){
		
		// 본인의 id의 admin만 접근 할 수 있게
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		String actualId = authUser.getId();
		
		if(id.equals(actualId)) {
		List<CategoryVo> categoryList = blogService.getAdminCategoryList(id);
		model.addAttribute("categoryList",categoryList);
		
		BlogVo blogVo = blogService.getBlogContent(id);
		model.addAttribute("blogVo",blogVo);
		model.addAttribute("id",id);
		return "blog/admin-category";
		}
		else {
			redirectAttributes.addAttribute("result", "badRequest");
	        return "redirect:/user/login";
		}
	}
	
	@Auth
	@RequestMapping(value="/admin/category", method=RequestMethod.POST)
	public String adminCategory(@PathVariable("id") String id,CategoryVo category, 
			HttpSession session,RedirectAttributes redirectAttributes){

		UserVo authUser = (UserVo) session.getAttribute("authUser");
		String actualId = authUser.getId();
		
		if(id.equals(actualId)) {
			blogService.setCategory(category);
			return "redirect:/"+id+"/admin/category";
		}else {
			redirectAttributes.addAttribute("result", "badRequest");
	        return "redirect:/user/login";
		}
	}
	
	@Auth
	@RequestMapping("/admin/write")
	public String adminWrite(@PathVariable("id") String id, Model model,
			HttpSession session,RedirectAttributes redirectAttributes){
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		String actualId = authUser.getId();
		
		if(id.equals(actualId)) {
			List<CategoryVo> categoryList = blogService.getCategoryList(id);
			model.addAttribute("categoryList",categoryList);
			model.addAttribute("id",id);
			BlogVo blogVo = blogService.getBlogContent(id);
			model.addAttribute("blogVo",blogVo);
			return "blog/admin-write";
		}else {
			redirectAttributes.addAttribute("result", "badRequest");
	        return "redirect:/user/login";
		}
	}
	
	@Auth
	@RequestMapping(value="/admin/write", method=RequestMethod.POST)
	public String adminWrite(@PathVariable("id") String id, 
			Long category,PostVo postVo,
			HttpSession session,RedirectAttributes redirectAttributes){
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		String actualId = authUser.getId();
		
		if(id.equals(actualId)) {
			postVo.setCategoryNo(category);
			blogService.setPost(postVo);
			return "redirect:/"+id+"/admin/write";
		}else {
			redirectAttributes.addAttribute("result", "badRequest");
	        return "redirect:/user/login";
		}
	}
	
	@Auth
	@RequestMapping("/admin/update")
	public String updateBlog(@PathVariable("id") String id,
			BlogVo blogVo, MultipartFile file,
			HttpSession session,RedirectAttributes redirectAttributes){
		
		UserVo authUser = (UserVo) session.getAttribute("authUser");
		String actualId = authUser.getId();
		
		if(id.equals(actualId)) {
			String logo = fileUploadService.restore(file);
			if(logo!=null) { blogVo.setLogo(logo);}
			blogService.update(id,blogVo);
			servletContext.setAttribute("blogVo", blogVo);
			return "redirect:/"+id+"/admin/basic";
		}
		else {
			redirectAttributes.addAttribute("result", "badRequest");
	        return "redirect:/user/login";
		}
	}

}
