package br.gov.rj.faetec.remocao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import br.gov.rj.faetec.remocao.entity.UnidadeEntity;
import br.gov.rj.faetec.remocao.repository.UnidadeRepository;
import br.gov.rj.faetec.remocao.repository.UsuarioRepository;
import br.gov.rj.faetec.remocao.service.CandidaturaService;
import br.gov.rj.faetec.remocao.service.UsuarioService;
import jakarta.servlet.http.HttpSession;

@Controller
public class CandidaturaController {
	
    @Autowired
	UnidadeRepository unidadeRepository;
    
    @Autowired
  	UsuarioRepository usuarioRepository;
    
	@Autowired
	UsuarioService usuarioService;
	
    @Autowired
	CandidaturaService candidaturaService;
    
    
    private String email;
    
    private String mensagem;

    @GetMapping(value="/candidatura/acompanhar_candidatura")
    public String candidatura(ModelMap model,HttpSession session) { 	
    	model.addAttribute("unidades", unidadeRepository.findAll());
    	
    	email = (String)session.getAttribute("email");
    	model.addAttribute("candidaturas", candidaturaService.findAllByEmail(email));
        return "candidatura/acompanhar_candidatura";  	
    	    	
    }
    @PostMapping("/candidatura/realizar_candidatura")
	public String salvar(@ModelAttribute("unidade") UnidadeEntity unidadeEntity, HttpSession session,Model model) throws Exception {
		    
		    email = (String)session.getAttribute("email"); 		    
		    mensagem = usuarioService.realizarCandidatura(unidadeEntity, email);	
		    model.addAttribute("unidades", unidadeRepository.findAll());
		    model.addAttribute("candidaturas", candidaturaService.findAllByEmail(email));
		    model.addAttribute("mensagem", mensagem);
		    
		    return "candidatura/acompanhar_candidatura"; 
    }
	@GetMapping(value="/candidatura/permutas")
    public String permutas(ModelMap model, HttpSession session) { 	
		email = (String)session.getAttribute("email"); 	
		model.addAttribute("candidaturas", usuarioService.listarPermutasUsuario(email));
        return "candidatura/permutas";  	
    	    	
    }
}
