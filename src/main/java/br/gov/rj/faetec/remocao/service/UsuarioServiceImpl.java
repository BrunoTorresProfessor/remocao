package br.gov.rj.faetec.remocao.service;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.gov.rj.faetec.remocao.email.Mailer;
import br.gov.rj.faetec.remocao.email.Mensagem;
import br.gov.rj.faetec.remocao.entity.UnidadeEntity;
import br.gov.rj.faetec.remocao.entity.UsuarioEntity;
import br.gov.rj.faetec.remocao.repository.CandidaturaRepository;
import br.gov.rj.faetec.remocao.repository.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {
	
	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	CandidaturaRepository candidaturaRepository;
	
	@Autowired
	private Mailer mailer;		
	
	private String mensagem;

	@Override
	public void save(UsuarioEntity usuarioEntity) throws Exception {
		
		usuarioRepository.save(usuarioEntity);
		
	}

	@Override
	public String recuperarSenha(UsuarioEntity usuarioEntity) throws Exception {
		
		usuarioEntity = this.usuarioRepository.getOneByEmail(usuarioEntity.getEmail());
		
		if (usuarioEntity == null) {
			
			this.mensagem = "Nenhum usuário encontrado com o email informado.";	
			
		}
		else
		{
			String novaSenha = getRandomPass(); //gera uma senha randomica de 8 digitos
			
			BCryptPasswordEncoder b = new BCryptPasswordEncoder();
			
			usuarioRepository.alterarSenha(b.encode(novaSenha),usuarioEntity.getEmail());
			
			this.mailer.enviar(new Mensagem("Remoção - Faetec <brunotorresorofessor@gmail.com>", 
			Arrays.asList("Recuperação de senha <"+ usuarioEntity.getEmail() +">")
			, "Recuperação de senha", "Olá! \n\n Sua nova senha é:  " + novaSenha  ));
			
			this.mensagem = "Senha enviada ao email indicado.";
		}
		
		return this.mensagem;
				
		
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

	@Override
	public String realizarCandidatura(UnidadeEntity unidadeEntity, String email) throws Exception {
		
		try 
		{
			   UsuarioEntity usuarioEntity = new UsuarioEntity();
			   usuarioEntity = this.usuarioRepository.getOneByEmail(email);
			   Timestamp dataDeHoje = new Timestamp(System.currentTimeMillis());


			   candidaturaRepository.realizarCandidatura(usuarioEntity.getIdUsuario(), unidadeEntity.getIdUnidade(), dataDeHoje);
			   
			
		} 
		catch (Exception e) {
			
			return e.getMessage();
			
		}
		
		return this.mensagem;
	}

	@Override
	public List<UsuarioEntity> listarPermutasUsuario(String email) {
		UsuarioEntity usuarioEntity = new UsuarioEntity();
		usuarioEntity = this.usuarioRepository.getOneByEmail(email);
		
		return usuarioRepository.listarPermutasUsuario(usuarioEntity.getIdUsuario());
	}

}
