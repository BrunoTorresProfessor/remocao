package br.gov.rj.faetec.remocao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.gov.rj.faetec.remocao.entity.AreaConhecimentoEntity;
import br.gov.rj.faetec.remocao.entity.CargaHorariaEntity;
import br.gov.rj.faetec.remocao.entity.CargoEntity;
import br.gov.rj.faetec.remocao.entity.UnidadeEntity;
import br.gov.rj.faetec.remocao.entity.UsuarioEntity;
import br.gov.rj.faetec.remocao.entity.VinculoEntity;
import br.gov.rj.faetec.remocao.repository.AreaConhecimentoRepository;
import br.gov.rj.faetec.remocao.repository.CargaHorariaRepository;
import br.gov.rj.faetec.remocao.repository.CargoRepository;
import br.gov.rj.faetec.remocao.repository.UnidadeRepository;
import br.gov.rj.faetec.remocao.repository.UsuarioRepository;
import br.gov.rj.faetec.remocao.repository.VinculoRepository;
import br.gov.rj.faetec.remocao.service.UsuarioService;
import jakarta.servlet.http.HttpSession;


@Controller
public class UsuarioController {
	
	  @Autowired
	  UsuarioService usuarioService;
	  
	  @Autowired
	  UsuarioRepository usuarioRepository;
	  
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
  
	  private String mensagem;
	  
	  private String email;
	   
	  @GetMapping(value="/cadastre_se")
	  public String cadastro(ModelMap model) { 	
	    
	    model.addAttribute("areas_conhecimentos", areaConhecimentoRepository.findAll()); 
	    model.addAttribute("cargas_horarias", cargaHorariaRepository.findAll()); 
	    model.addAttribute("cargos", cargoRepository.findAll()); 
	    model.addAttribute("unidades", unidadeRepository.findAll());
	    model.addAttribute("vinculos", vinculoRepository.findAll());
		  
	    return "cadastre_se";  	
	    	    	
	  }
		@PostMapping(value="/cadastrar")
		public String salvar(
				@ModelAttribute("usuarioEntity") UsuarioEntity usuarioEntity,
				@ModelAttribute("area_conhecimento") AreaConhecimentoEntity areaConhecimentoEntity,
				@ModelAttribute("cargo") CargoEntity cargoEntity,
				@ModelAttribute("carga_horaria") CargaHorariaEntity cargaHorariaEntity,
				@ModelAttribute("unidade") UnidadeEntity unidadeEntity,
				@ModelAttribute("vinculo") VinculoEntity vinculoEntity,
				Model model) throws Exception {
	
					usuarioEntity.setAreaConhecimentoEntity(areaConhecimentoEntity);
					usuarioEntity.setCargoEntity(cargoEntity);
					usuarioEntity.setCargaHorariaEntity(cargaHorariaEntity);
					usuarioEntity.setUnidadeEntity(unidadeEntity);
					usuarioEntity.setVinculoEntity(vinculoEntity);
					
					System.out.println("Email: " + usuarioEntity.getEmail());
					System.out.println(usuarioEntity.getNome());
					System.out.println(areaConhecimentoEntity.getNome());
					System.out.println(usuarioEntity.getCargoEntity().getNome());
					System.out.println(usuarioEntity.getCargaHorariaEntity().getCargaHoraria());
					System.out.println(usuarioEntity.getUnidadeEntity().getNome());
					System.out.println(usuarioEntity.getVinculoEntity().getNome());
					
			
					model.addAttribute("mensagem", mensagem);  
					usuarioService.save(usuarioEntity);
					return "cadastre_se"; 
			
		}
		@GetMapping(value="/recuperar_senha")
	    public String recuperarSenha() { 	
			
	        return "recuperar_senha";  	
	    	    	
	    }	
		@PostMapping({"/recuperar_senha"})
	    public ModelAndView recuperarSenhaEmail(UsuarioEntity usuario,Model model,RedirectAttributes atributes) throws Exception { 			
		
			ModelAndView mv = new ModelAndView("recuperar_senha");
		
			mensagem = usuarioService.recuperarSenha(usuario);
			model.addAttribute("mensagem", mensagem);
			
	        return mv;
	    	    	
	    }
	
		  @GetMapping(value="/candidatura/meus_dados")
		  public String meusDados(ModelMap model, HttpSession session) { 	
			  
			email = (String)session.getAttribute("email");			    
		    model.addAttribute("usuario", usuarioRepository.getOneByEmail(email)); 
		    model.addAttribute("areas_conhecimentos", areaConhecimentoRepository.findAll()); 
		    model.addAttribute("cargas_horarias", cargaHorariaRepository.findAll()); 
		    model.addAttribute("cargos", cargoRepository.findAll()); 
		    model.addAttribute("unidades", unidadeRepository.findAll());
		    model.addAttribute("vinculos", vinculoRepository.findAll());
			  
		    return "candidatura/meus_dados";  	
		    	    	
		  }
		  
		
		
		    		

}
