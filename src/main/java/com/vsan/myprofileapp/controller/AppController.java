package com.vsan.myprofileapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vsan.myprofileapp.dao.User;
import com.vsan.myprofileapp.service.RegistrationService;



@Controller
@RequestMapping("/vsan/myprofileapp")
public class AppController {
	
	@Autowired
	private RegistrationService service;
	
	@GetMapping("/registration")
	public String getRegistrationPage(Model model) {
		model.addAttribute("user", new User());  //passo al form un oggetto vuoto;
		return "login.html";
	}
	
	@PostMapping("/registerUser")
	public String registration(@ModelAttribute("user") User newUser, RedirectAttributes redirAtt) {
		service.registration(newUser, redirAtt);
		return "redirect:/vsan/myprofileapp/registration";
	}
	
	@GetMapping("/confirm-your-account/user-{idUserLink}")
	public void confirmAccount(@PathVariable String idUserLink) {
		service.enableUser(idUserLink);
	}

}
