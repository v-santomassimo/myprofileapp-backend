package com.vsan.myprofileapp.service;

import java.util.Date;
import java.util.List;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.vsan.myprofileapp.bean.Post;
import com.vsan.myprofileapp.bean.User;
import com.vsan.myprofileapp.repository.PostRepository;
import com.vsan.myprofileapp.repository.UserRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class MyAccountService {
	
	@Autowired
	private PostRepository postRepo;
	@Autowired
	private UserRepository userRepo;
	
	
	public void addPost(Post newPost) {
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EE dd MMMM y, HH:mm");
		LocalDateTime creationDate = LocalDateTime.now(); 
		//String dateCreation = creationDate.format(formatter);
		Instant instant;
		instant = creationDate.toInstant(ZoneOffset.UTC);
		Date date = Date.from(instant);
		
		//setto la data di creazione del post;
		newPost.setCreationDate(date);
		log.info(creationDate);
		
		//recupero l'utente loggato;
		User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();	
		
		//salvo l'autore del post;
		newPost.setAuthor(loggedUser);
		newPost.setNumberLikes("0");
		//salvo il post nell?utente loggato
		loggedUser.getUserPosts().add(newPost);
		
		//salvo il post sul db;
		postRepo.save(newPost);
		log.info("Post added correctly!");
		
	}
	
	public void deletePost(Long id) {
		Optional<Post> postIsThere = postRepo.findById(id);
		if(postIsThere.isPresent()) {
			postRepo.deleteById(id);
		} else {
			log.info("Post id: "+id+ " doesn't exist.");
		}
	}
	
	
	public void likeThisPost(Long id) {
		Integer likes = 0;
		Post post = postRepo.findByPostId(id);
		
		if(post.getNumberLikes().equals("0")) {
			likes = Integer.parseInt(post.getNumberLikes());
			likes++;
			post.setNumberLikes(String.valueOf(likes));
			postRepo.save(post);
		} else if(post.getNumberLikes().equals("1")) {
			post.setNumberLikes("0");
			postRepo.save(post);
		}
		
	}
	
	public List<User> getAllUsers(){
		return userRepo.findAll();
	}
	
	public List<User> getAllUsersByName(String name){
		return userRepo.findByName(name);
	}
	
	public List<User> getAllUsersByLastname (String lastname){
		return userRepo.findByLastname(lastname);
	}

	public User getUserByEmail (String email){
		return userRepo.getByEmail(email);
	}
	
	public List<User> searchForUsers(String search){
//		search = search.trim().substring(0, 1).toUpperCase() + search.substring(1);
//		String [] searchWords = search.split("\\s+");
//		if(searchWords.length > 0 && searchWords.length >= 3) {
//			return userRepo.findUser(searchWords[0], searchWords[1], searchWords[2]);
//		} else {
//			return userRepo.findUser(searchWords[0], searchWords[0], searchWords[0]);
//		}
		
		return userRepo.findUser(search);
		
	}
	
	
}
