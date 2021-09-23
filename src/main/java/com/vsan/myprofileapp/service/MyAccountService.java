package com.vsan.myprofileapp.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.vsan.myprofileapp.dao.Post;
import com.vsan.myprofileapp.dao.User;
import com.vsan.myprofileapp.repository.PostRepository;
import com.vsan.myprofileapp.repository.UserRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class MyAccountService {
	
	@Autowired
	private PostRepository repository;
	
	
	public void addPost(Post newPost) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EE dd MMMM y, HH:mm");
		LocalDateTime creationDate = LocalDateTime.now(); 
		String dateCreation = creationDate.format(formatter);
		
		log.info(dateCreation);
		
		//setto la data di creazione del post;
		newPost.setCreationDate(dateCreation);
		//recupero l'utente loggato;
		User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();	
		
		//salvo l'autore del post;
		newPost.setAuthor(loggedUser);
		
		//salvo il post nell?utente loggato
		loggedUser.getUserPosts().add(newPost);
		
		//salvo il post sul db;
		repository.save(newPost);
		log.info("Post added correctly!");
		
	}
	

}
