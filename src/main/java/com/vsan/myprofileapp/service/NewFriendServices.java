package com.vsan.myprofileapp.service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vsan.myprofileapp.bean.Friendship;
import com.vsan.myprofileapp.bean.Notification;
import com.vsan.myprofileapp.bean.User;
import com.vsan.myprofileapp.repository.FriendshipRepository;
import com.vsan.myprofileapp.repository.UserRepository;

@Service
public class NewFriendServices {
	
	@Autowired
	private FriendshipRepository friendRepo;
	@Autowired
	private UserRepository userRepo;
	
	
	//mandare richiesta amicizia:
	/* creare un oggetto "FriendShip" con l'id dell'utente loggato e l'id dell'utente al quale sto inviando la richiesta. Inviare un oggetto "notifica".
	 * 
	 */
	
//	public void addFriend(String idReceiver, RedirectAttributes rdrAtt) {
//		Friendship newRequest = new Friendship();
//		User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();	
//		User receiver = userRepo.findByUserId(Long.valueOf(idReceiver));
//		LocalDateTime creationDate = LocalDateTime.now();
//		Instant instant;
//		instant = creationDate.toInstant(ZoneOffset.UTC);
//		Date date = Date.from(instant);
//		
//		newRequest.setAcceptingDate(date);
//		newRequest.setUserSender(loggedUser);
//		newRequest.setUserReceiver(receiver);
//		
//		friendRepo.save(newRequest);
//		
//		//User sender = userRepo.findByUserId(loggedUser.getUserId());
//		Notification notification = new Notification(date, loggedUser.getName() + " ti ha inviato una richiesta di amicizia", "");
//		loggedUser.setNotifications(notification);
//		userRepo.save(loggedUser);
//		
//		rdrAtt.addFlashAttribute("friendshipRequestSended", "Hai inviato una richiesta di amicizia a " + receiver.getName() + " " + receiver.getLastname());
//		//rdrAtt.addFlashAttribute("newFriendNotification", notification);
//		
//	}
	
	//accettare richiesta amicizia:
	/* cliccare sulla notifica: se isApproved è falso, allora lo setto a true e aggiungo l'utente alla lista di amici dell'utente loggato;
	 * */
	

}
