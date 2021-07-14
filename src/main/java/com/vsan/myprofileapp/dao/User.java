package com.vsan.myprofileapp.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@NoArgsConstructor
@Getter
@Setter
public class User implements UserDetails{

//	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	private String name;
	private String lastname;
	@Column(unique = true)
	private String email;
	private String password;
	private boolean isEnabled;
	@Column(name = "user_link", nullable = true)
	private String userLink;
//	private String profileImage; TODO: how to store images in database?
	@OneToMany(mappedBy = "author")
	private List<Post> userPosts;
	@OneToMany(mappedBy = "userSender") 
	private List<Friendship> requestedFriends;
	@OneToMany(mappedBy = "userReceiver") 
	private List<Friendship> receivedFriends;
	private String registrationDate;
	
	
	
	public User(String name, String lastname, String email, String password) {
		this.name = name;
		this.lastname = lastname;
		this.email = email;
		this.password = password;
		this.isEnabled = false;
		this.userPosts = new ArrayList<Post>();
		this.requestedFriends = new ArrayList<Friendship>();
		this.receivedFriends = new ArrayList<Friendship>();
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}



	@Override
	public String getUsername() {
		return email;
	}



	@Override
	public boolean isAccountNonExpired() {
		return false;
	}



	@Override
	public boolean isAccountNonLocked() {
		return false;
	}



	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}
	
	
	
	
	
	
	
}
