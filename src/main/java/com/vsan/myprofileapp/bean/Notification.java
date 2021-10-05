package com.vsan.myprofileapp.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Notification {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long notificationId;
	@ManyToOne
	@JoinColumn(name = "userId", nullable = false)
	private User user;
	private Date creationDate;
	private String message;
	private String evento; //richiesta di amicizia, likes, ecc; mi riporta al profilo della persona oppure al post;
	private boolean isSeen;

	
	public Notification(Date creationDate, String message, String evento) {
		this.creationDate = creationDate;
		this.message = message;
		this.isSeen = false;
		this.evento = evento;
	}
}
