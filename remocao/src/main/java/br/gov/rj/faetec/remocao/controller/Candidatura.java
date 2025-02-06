package br.gov.rj.faetec.remocao.controller;

import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

public class Candidatura {

	@GetMapping("/acompanhar_candidatura")
    public String home(HttpServletRequest request) {        
        return "acompanhar_candidatura";        
    }
}
