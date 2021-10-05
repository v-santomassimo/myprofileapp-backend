package com.vsan.myprofileapp.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "friendship")
@NoArgsConstructor
@Getter
@Setter
public class Friendship {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idFriendship;
	@ManyToOne() //??
	@JoinColumn(name = "userId", nullable = false, insertable = false, updatable = false)
	private User userSender;
	@ManyToOne() //??
	@JoinColumn(name = "userId", nullable = false, insertable = false, updatable = false)
	private User userReceiver;
	private Date sendingDate;
	private Date acceptingDate;
	private boolean isApproved;
	
	
	public Friendship(User userSender, User userReceiver, Date sendingDate, Date acceptingDate, boolean isApproved) {
		this.userSender = userSender;
		this.userReceiver = userReceiver;
		this.sendingDate = sendingDate;
		this.acceptingDate = acceptingDate;
		this.isApproved = false;
	}
	
	
	
}
