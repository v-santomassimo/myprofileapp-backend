package com.vsan.myprofileapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vsan.myprofileapp.dao.Post;
import com.vsan.myprofileapp.dao.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
	
	List<Post> findByAuthor(User author);

}
