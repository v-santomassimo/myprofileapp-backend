package com.vsan.myprofileapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.vsan.myprofileapp.service.NewFriendServices;

@Controller
@RequestMapping("/vsan/myprofileapp/friends")
public class NewFriendController {
	
	@Autowired
	private NewFriendServices service;
	
//	@GetMapping("/add-friend/receiver={idreceiver}")
//	public String addFriend(@PathVariable("idreceiver") String idReceiver, RedirectAttributes att) {
//		service.addFriend(idReceiver, att);
//		return "redirect:/vsan/myprofileapp/myaccount/home";
//	}

}
