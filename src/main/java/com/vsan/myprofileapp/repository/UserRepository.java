package com.vsan.myprofileapp.repository;

import org.springframework.stereotype.Repository;
import com.vsan.myprofileapp.dao.User;

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
}
