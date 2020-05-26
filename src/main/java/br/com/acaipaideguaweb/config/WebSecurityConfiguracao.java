package br.com.acaipaideguaweb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity
public class WebSecurityConfiguracao extends WebSecurityConfigurerAdapter{
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
			.authorizeRequests()
				.antMatchers("/css/**","/js/**", "/index").permitAll()		
				.antMatchers("/user/**").hasRole("USER")
				.antMatchers("/estabelecimentos/**").hasAnyRole("USER","ADMIN")
				.antMatchers("/usuarios/**").hasRole("ADMIN")
				.and()
			.formLogin()
				.loginPage("/login").failureUrl("/login-error");	
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
				.withUser("acaipde").password("@acaiPDE").roles("USER")
				.and()
				.withUser("jairo").password("j@1R0").roles("ADMIN");
				
	}

}
