package com.vsan.myprofileapp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	@Autowired
	public UserDetailsServiceImpl userDetailService;
	

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() //da abilitare di nuovo in fase di produzione;
			.authorizeRequests()
			.antMatchers("/vsan/myprofileapp/registerUser").permitAll() //URL che consuma il RegistrationService
			.antMatchers("/vsan/myprofileapp/registration").anonymous() //URL accesso alla pagina di registrazione;
			.antMatchers("/vsan/myprofileapp/confirm-your-account/*").permitAll()
//			.antMatchers("/templates/*").permitAll()
//			.antMatchers("/static/css/*", "/static/js/*", "/static/img/*").permitAll()
			.antMatchers("/vsan/myprofileapp/myaccount/home").hasAnyAuthority("USER")
//			.anyRequest().authenticated() //--> se lo aggiungo, non riesco ad accedere se non faccio il login;
			.and()
			.formLogin().loginPage("/vsan/myprofileapp/loginpage")
			.loginProcessingUrl("/vsan/myprofileapp/login") //url che effettua l'azione del login di default è "/login";
		    .defaultSuccessUrl("/vsan/myprofileapp/myaccount/home", true)
			.failureUrl("/vsan/myprofileapp/login-error") //landing page per login fallito;
			.and()
			.logout().invalidateHttpSession(true).deleteCookies("JSESSIONID")
			.logoutUrl("/vsan/myprofileapp/myaccount/perform_logout")
			.clearAuthentication(true).permitAll();
	}



	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(daoAuthenticationProvider());
		auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder());
		provider.setUserDetailsService(userDetailService);
		
		return provider;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(8);
	}
	

}
