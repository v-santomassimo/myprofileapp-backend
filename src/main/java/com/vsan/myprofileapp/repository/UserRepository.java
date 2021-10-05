package com.vsan.myprofileapp.repository;

import org.springframework.stereotype.Repository;

import com.vsan.myprofileapp.bean.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);
	
	Optional<User> findByUserLink(String userLink);
	
	@Query("SELECT u FROM User u WHERE u.userLink = :userLink")
	User getByUserLink(@Param("userLink") String userLink);
	
	@Query("SELECT u FROM User u WHERE u.email = :email")
	User getByEmail(@Param("email") String email);
	
	List<User> findByName(String name);
	
	List<User> findByLastname(String lastname);
	
	//@Query("SELECT u FROM User u WHERE u.name = :name OR u.lastname = :lastname OR u.email = :email")
	@Query("SELECT u FROM User u WHERE u.name LIKE %:search% OR u.lastname LIKE %:search% OR u.email LIKE %:search%")
	List<User> findUser(String search);
	
	User findByUserId(Long id);
}
