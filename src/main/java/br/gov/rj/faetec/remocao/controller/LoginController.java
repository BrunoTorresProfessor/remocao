package br.gov.rj.faetec.remocao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;



@Controller
public class LoginController {
	
	@GetMapping("/")
    public String home(HttpServletRequest request) {        
        return "home";        
    }
	
    @GetMapping(value="/login")
    public String login() { 	
    	   
         	return "login";  	
    	    	
    }
    @GetMapping(value="/sair")
    public String sair() { 	
    	   
         	return "login";  	
    	    	
    }
    @GetMapping(value="/contato")
    public String contato() { 	
    	   
         	return "contato";  	
    	    	
    }
    @RequestMapping(value = "/postlogin", method = RequestMethod.POST)
    public String postLogin(Model model, HttpSession session) throws Exception {       
       
    	System.out.println("Entrei");
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String login = auth.getName(); //recupera o login do usuario logado
        session.setAttribute("email", login); 
        
        return "home";

    }   

    
}
