package br.gov.rj.faetec.remocao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import br.gov.rj.faetec.remocao.entity.UsuarioEntity;
import br.gov.rj.faetec.remocao.repository.AreaConhecimentoRepository;
import br.gov.rj.faetec.remocao.repository.CargaHorariaRepository;
import br.gov.rj.faetec.remocao.repository.CargoRepository;
import br.gov.rj.faetec.remocao.repository.UnidadeRepository;
import br.gov.rj.faetec.remocao.repository.VinculoRepository;
import br.gov.rj.faetec.remocao.service.UsuarioService;

@Controller
public class UsuarioController {
	
	  @Autowired
	  UsuarioService usuarioService;
	  
	  @Autowired
	  AreaConhecimentoRepository areaConhecimentoRepository;
	  
	  @Autowired
	  CargaHorariaRepository cargaHorariaRepository;
	  
	  @Autowired
	  CargoRepository cargoRepository;
	  
	  @Autowired
	  UnidadeRepository unidadeRepository;
	  
	  @Autowired
	  VinculoRepository vinculoRepository;
	   
	  @PostMapping("/cadastrar")
	  public void salvar(@ModelAttribute("usuario") UsuarioEntity usuarioEntity) throws Exception {		    
		  usuarioService.save(usuarioEntity);
	  }
	  @GetMapping(value="/cadastre_se")
	  public String cadastro(ModelMap model) { 	
	    
	    model.addAttribute("areas_conhecimentos", areaConhecimentoRepository.findAll()); 
	    model.addAttribute("cargas_horarias", cargaHorariaRepository.findAll()); 
	    model.addAttribute("cargos", cargoRepository.findAll()); 
	    model.addAttribute("unidades", unidadeRepository.findAll());
	    model.addAttribute("vinculos", vinculoRepository.findAll());
		  
	    return "cadastre_se";  	
	    	    	
	  }
		    		

}
