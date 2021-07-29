package com.vsan.myprofileapp.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vsan.myprofileapp.dao.User;
import com.vsan.myprofileapp.email.EmailService;
import com.vsan.myprofileapp.repository.AppRepository;

import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class RegistrationService {
	
	@Autowired
	private AppRepository repository;
	
	@Autowired
	private EmailService email;
	
	
	//devo ritornare l'utente registrato?
	public void registration(User newUser, RedirectAttributes redirAtt) { 
		
			User freshUser = new User();
			Map<String, Object> emailModel = new HashMap<>();
			LocalDate registrationDate = LocalDate.now();
			
			freshUser.setName(newUser.getName());
			freshUser.setLastname((newUser.getLastname()));
			freshUser.setEmail(newUser.getEmail());
			freshUser.setPassword(newUser.getPassword());
			freshUser.setRegistrationDate(registrationDate.toString());
			
			//controllo che l'utente non esista già nel db;
			Optional<User> newUserRegistration = repository.findByEmail(freshUser.getEmail());
			
			if(newUserRegistration.isPresent()) {
				// se esiste, lancio una exception;
				log.info("User already present in the system");
				
				redirAtt.addFlashAttribute("userIsPresent", "Utente già registrato!");
				
				
			} else {
				//altrimenti, aggiungo il nuovo utente al db e invio la mail;
				
				String idUser = String.valueOf(Math.random()*1000).replace(".", "")
						+freshUser.getName().substring(0, 1).toUpperCase()
						+freshUser.getLastname().substring(0,1).toUpperCase();
				
				freshUser.setUserLink(idUser);
				
				repository.save(freshUser);
				log.info("User added correctly");
				
				String linkConfirmation = "http://localhost:8080/vsan/myprofileapp/confirm-your-account/user-"+idUser; //aggiungere un token?
				
				emailModel.put("link", linkConfirmation);
				emailModel.put("name", freshUser.getName());
					
				if(sendConfirmationEmail(freshUser.getEmail(), emailModel, redirAtt)) {
					redirAtt.addFlashAttribute("success", "Grazie per esserti registrato! Riceverai la mail di attivazione all'indirizzo email che ci hai fornito");

				} else {
					redirAtt.addFlashAttribute("error", "Qualcosa è andato storto. Riprova!");
				}
				
			}
		
	}
	
	
	//invio email con link di conferma registrazione;
	public boolean sendConfirmationEmail(String to, Map<String, Object> model, RedirectAttributes redirAtt) {
		if (email.send(to,model)) {
			return true;
		} else {
			return false;
		}
		
	}
	
	
	//chiamo questo service con chiamata @get del link di conferma nella mail;
	public void enableUser(String idUserLink, RedirectAttributes redAtt) {
		Optional<User> userLink = repository.findByUserLink(idUserLink);
		User registeredUser = repository.getByUserLink(idUserLink);
		
		if(userLink.isPresent()) {
			registeredUser.setEnabled(true);
			log.info("User registration enabled!", idUserLink);
			redAtt.addFlashAttribute("userEnabled", "Complimenti! Adesso sei parte della community. Effettua il login con le credenziali usate in fase di registrazione.");
		} else {
			log.error("Operazione non valida: l'utente "+idUserLink+" non ha effettuato la registrazione");
			redAtt.addFlashAttribute("userNotEnabled", "Ops! Non ti sei ancora registrato? Fallo adesso!");
		}
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
