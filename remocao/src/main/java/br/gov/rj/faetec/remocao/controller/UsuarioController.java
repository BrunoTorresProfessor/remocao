package br.gov.rj.faetec.remocao.controller;

import java.util.Arrays;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.gov.rj.faetec.remocao.email.Mailer;
import br.gov.rj.faetec.remocao.email.Mensagem;
import br.gov.rj.faetec.remocao.entity.UsuarioEntity;
import br.gov.rj.faetec.remocao.repository.AreaConhecimentoRepository;
import br.gov.rj.faetec.remocao.repository.CargaHorariaRepository;
import br.gov.rj.faetec.remocao.repository.CargoRepository;
import br.gov.rj.faetec.remocao.repository.UnidadeRepository;
import br.gov.rj.faetec.remocao.repository.VinculoRepository;
import br.gov.rj.faetec.remocao.service.UsuarioService;
import ch.qos.logback.core.model.Model;

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
	  
	  @Autowired
	  private Mailer mailer;		
	   
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
	    public ModelAndView save(ModelMap model,
	    		@ModelAttribute("usuarioEntity") UsuarioEntity usuarioEntity,
	    		RedirectAttributes atributes
	    		) throws Exception 
		{ 			
			System.out.println("Entrei");
					System.out.println("Email: " + usuarioEntity.getEmail());
					System.out.println(usuarioEntity.getNome());
					System.out.println(usuarioEntity.getAreaConhecimentoEntity().getNome());
					System.out.println(usuarioEntity.getCargoEntity().getNome());
					System.out.println(usuarioEntity.getCargaHorariaEntity().getCargaHoraria());
					System.out.println(usuarioEntity.getUnidadeEntity().getNome());
					System.out.println(usuarioEntity.getVinculoEntity().getNome());
					
					
					ModelAndView mv = new ModelAndView("redirect:/cadastre_se");			
					//atributes.addFlashAttribute("mensagem",docenteService.save(docenteEntity));
					
					//usuarioService.save(usuarioEntity);
					return mv;
			
		}
		@GetMapping(value="/recuperar_senha")
	    public String recuperarSenha() { 	
			
	        return "recuperar_senha";  	
	    	    	
	    }
		@PostMapping({"/recuperar_senha"})
	    public ModelAndView recuperarSenhaEmail(UsuarioEntity usuario,Model model,RedirectAttributes atributes) { 
			
		
			ModelAndView mv = new ModelAndView("recuperar_senha");
			
				
			//usuario = this.usuarioRepository.getOneByCpf(usuario.getCpf());
			
			String novaSenha = getRandomPass(); //gera uma senha randomica de 8 digitos
			
			BCryptPasswordEncoder b = new BCryptPasswordEncoder();
			
			//usuarioRepository.alterarSenha(b.encode(novaSenha),usuario.getCpf());
			
			this.mailer.enviar(new Mensagem("Remoção - Faetec <brunotorresorofessor@gmail.com>", 
			Arrays.asList("Recuperação de senha <"+ usuario.getEmail() +">")
			, "Recuperação de senha", "Olá! \n\n Sua nova senha é:  " + novaSenha  ));
					
			//model.addAttribute("mensagem", "Senha enviada com sucesso para o email cadastrado.");
			
			
			
	        return mv;
	    	    	
	    }
		public String getRandomPass() {
			char[] chart ={'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};

			char[] senha= new char[8];

			int chartLenght = chart.length;
			Random rdm = new Random();

			for (int x=0; x<8; x++)
			senha[x] = chart[rdm.nextInt(chartLenght)];

			return new String(senha);
		}
		
		
		    		

}
