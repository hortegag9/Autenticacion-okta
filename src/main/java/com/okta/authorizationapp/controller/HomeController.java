package com.okta.authorizationapp.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	private Map<String,LocalDateTime> userLastAcces = new HashMap<>();
	
	/*Administracion propia, no es necesaria la clase SecurityConfiguration
	@GetMapping("/")
	public String getCurrentUser(@AuthenticationPrincipal User user, Model model) {
	
	String userName = user.getUsername();

	model.addAttribute("username",userName);
	model.addAttribute("lastAcces",userLastAcces.get(userName));	
	userLastAcces.put(userName, LocalDateTime.now());
	
	return "home";
		
	}*/
	
	@GetMapping("/")
	public String getCurrentUser(@AuthenticationPrincipal OidcUser user, Model model) {
	
	String email = user.getEmail();

	model.addAttribute("email",email);
	model.addAttribute("lastAcces",userLastAcces.get(email));
	model.addAttribute("firstName",user.getGivenName());
	model.addAttribute("lastName",user.getFamilyName());
	
	userLastAcces.put(email, LocalDateTime.now());
		
	return "home";
		
	}

}
