package com.vsan.myprofileapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable() //da abilitare di nuovo in fase di produzione;
			.authorizeRequests()
			.antMatchers("vsan/myprofileapp/registerUser").permitAll()
			.antMatchers("vsan/myprofileapp/registration").permitAll()
			.antMatchers("/templates/*").permitAll()
			.antMatchers("/static/css/*", "/static/js/*", "/static/img/*").permitAll()
			//.anyRequest().authenticated() --> se lo aggiungo, non riesco ad accedere se non faccio il login;
			.and()
			.formLogin()//.loginPage("url pagina login")
			.and()
			.logout().invalidateHttpSession(true)
			.clearAuthentication(true).permitAll();
	}

}
