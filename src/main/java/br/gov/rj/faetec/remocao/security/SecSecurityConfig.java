package br.gov.rj.faetec.remocao.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecSecurityConfig {

	@Autowired	
	private UserDetailServiceImpl userDetailServiceImpl;
	
	@Bean 
	public PasswordEncoder passwordEncoder() { 
	    return new BCryptPasswordEncoder(); 
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	 
	    http.authorizeHttpRequests(
	            auth -> auth	            
	            .requestMatchers("/esqueci_minha_senha", "/cadastre_se", "/cadastrar", "/recuperar_senha").permitAll() 
	            .requestMatchers("/acompanhar_candidatura").hasAnyAuthority("servidor","administrador")	      
	            .requestMatchers("/cadastrar").hasAnyAuthority("servidor","administrador")	       
	            .anyRequest().authenticated() 
	           )
	            .formLogin(formLogin -> formLogin	   
	            		//Direciona para esse @controller quando o login está correto
	                    .defaultSuccessUrl("/acompanhar_candidatura", true) 
	                    .loginPage("/login")
	                    .usernameParameter("username") 
	                    .passwordParameter("password") 
	     	            .successForwardUrl("/postlogin")	 
	                    .permitAll()
	            )
	            .rememberMe(rememberMe -> rememberMe.key("AbcdEfghIjkl..."))
	            .logout(logout -> logout.logoutUrl("/sair").permitAll());	 
	    return http.build();
	}
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
	  throws Exception {
		//Serve de exemplo para gerar um senha criptografada
		BCryptPasswordEncoder b = new BCryptPasswordEncoder();
		System.out.println(b.encode("123456"));
		//Cripitografa a senha para salvar no banco de dados
		auth.userDetailsService(userDetailServiceImpl).passwordEncoder(new BCryptPasswordEncoder());   
		
	}

}