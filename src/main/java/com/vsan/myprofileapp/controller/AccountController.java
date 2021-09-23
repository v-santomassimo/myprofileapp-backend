package com.vsan.myprofileapp.controller;


import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vsan.myprofileapp.dao.Post;
import com.vsan.myprofileapp.dao.User;
import com.vsan.myprofileapp.repository.PostRepository;
import com.vsan.myprofileapp.service.MyAccountService;


@Controller
@RequestMapping("/vsan/myprofileapp/myaccount")
public class AccountController {
	
	@Autowired
	private MyAccountService service;
	@Autowired
	private PostRepository postRepo;
	
	@GetMapping("/home")
	public String getHomePage(Model model) {
		//Principal userLogged = request.getUserPrincipal();
		User userLogged = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();  
		
		model.addAttribute("user", userLogged); 
		model.addAttribute("post", new Post()); //passo un oggetto Post vuoto per poter creare post dalla home;
		model.addAttribute("userPosts", postRepo.findByAuthor(userLogged));
		return "home.html";
		
		
		
//		Collections.sort(myList, new Comparator<MyObject>() {
//			  public int compare(MyObject o1, MyObject o2) {
//			      return o1.getDateTime().compareTo(o2.getDateTime());
//			  }
//			});
	}
	
	@PostMapping("/home/new-post")
	public String addNewPost(@ModelAttribute("post") Post newPost) {
		service.addPost(newPost);
		return "redirect:/vsan/myprofileapp/myaccount/home";
		
	}
	

}
