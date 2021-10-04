package com.vsan.myprofileapp.controller;



import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vsan.myprofileapp.bean.Post;
import com.vsan.myprofileapp.bean.SearchForm;
import com.vsan.myprofileapp.bean.User;
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

		User userLogged = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
		List<Post> userPosts = postRepo.findByAuthor(userLogged);
		List<User> friendSuggested = service.getAllUsers();
		
		//rimuovo dalla lista degli amici suggeriti l'utente loggato;
		for(User u : friendSuggested) {
			if(u.getUserId() == userLogged.getUserId() ) {
				friendSuggested.remove(u);
				break;
			}
		}
		
		
		if(userPosts.size() != 0) {
			//riordino la lista di post per data;
			Collections.sort(userPosts, new Comparator<Post>() {
				@Override
				public int compare(Post p1, Post p2) {
					return p2.getCreationDate().compareTo(p1.getCreationDate());   
				}
			});
			
			//dopo il sorting dei post per data, prendo il primo post (il più recente) per mostrarlo top page;
			Post latestPost = userPosts.get(0);
			userPosts.remove(0);
			model.addAttribute("latestUserPost",latestPost);
		}
		
		model.addAttribute("friendsSuggested",friendSuggested);
		model.addAttribute("user", userLogged); 
		model.addAttribute("post", new Post()); //passo un oggetto Post vuoto per poter creare post dalla home;
		model.addAttribute("userPosts", userPosts);
		model.addAttribute("searchForm", new SearchForm());
		
		return "home2.html";
		
	}
	
	
	@PostMapping("/home/new-post")
	public String addNewPost(@ModelAttribute("post") Post newPost) {
		service.addPost(newPost);
		return "redirect:/vsan/myprofileapp/myaccount/home";
	}
	
	@GetMapping("/home/delete-post/{id}")
	public String deletePost(@PathVariable("id") Long id) {
		service.deletePost(id);
		return "redirect:/vsan/myprofileapp/myaccount/home";
	}
	
	@PostMapping("/home/edit-post/{id}")
	public String editPost() {
		return "";
	}
	
	@GetMapping("/home/like-it/{id}")
	public String likeThisPost(@PathVariable("id") Long id) {
		service.likeThisPost(id);
		return "redirect:/vsan/myprofileapp/myaccount/home";
	}
	
	@PostMapping("/home/search-it")
	public String searchUser(@ModelAttribute("searchResults") SearchForm form, Model model) {
		List<User> results = service.searchForUsers(form.getSearchString());
		User userLogged = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		model.addAttribute("user", userLogged);
		model.addAttribute("searchResults", results);
		model.addAttribute("searchForm", new SearchForm());
		return "searchResults.html";
	}
	
//	@GetMapping("/home/search-results")
//	public String getSearchResultsPage(Model model) {
//		User userLogged = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal(); 
//		List<User> results = (List<User>) model.getAttribute("results");
//		model.addAttribute("user", userLogged);
//		model.addAttribute("searchResults", results);
//		return "searchResults.html";
//	}

}
