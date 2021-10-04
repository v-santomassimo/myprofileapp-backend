package com.vsan.myprofileapp.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "post")
@NoArgsConstructor
@Getter
@Setter
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long postId;
	private String postContent;
//	private String creationDate;
//	private String updateDate;
	private Date creationDate;
	private Date updateDate;
	private String numberLikes;
	@ManyToOne
	@JoinColumn(name = "userId", nullable = false)
	private User author;
	
	
	public Post(String postContent, Date creationDate, Date updateDate, String numberLikes, User author) {
		this.postContent = postContent;
		this.creationDate = creationDate;
		this.updateDate = updateDate;
		this.numberLikes = numberLikes;
		this.author = author;
	}
	
	
	
	
}
