package br.com.acaipaideguaweb.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.acaipaideguaweb.security.CustomUserDetailsService;

@Configuration
@ComponentScan(basePackageClasses = CustomUserDetailsService.class)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordencoder());
	}
	
	@Override
 	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()
				.antMatchers("/estabelecimentos/**").hasAnyRole("USER","ADMIN")
				.antMatchers("/horarios/**").hasAnyRole("USER","ADMIN")
				.antMatchers("/item/**").hasAnyRole("USER","ADMIN")
				.antMatchers("/perfil/**").hasAnyRole("USER","ADMIN")
				.antMatchers("/produtos/**").hasAnyRole("USER","ADMIN")
				.antMatchers("/tabelas/**").hasAnyRole("USER","ADMIN")
				.antMatchers("/telefones/**").hasAnyRole("USER","ADMIN")
				.antMatchers("/usuarios/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/clientes/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/pre-cadastro/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/vendas/**").access("hasRole('ROLE_ADMIN')")
				.anyRequest().permitAll()
				.and()
					.formLogin().loginPage("/login").failureUrl("/login-error").usernameParameter("username").passwordParameter("password").and()
					.logout().logoutSuccessUrl("/login?logout").and().exceptionHandling().accessDeniedPage("/403").and()
					.csrf().disable();
	}
	
	@Bean(name = "passwordEncoder")
		public PasswordEncoder passwordencoder() {
			return new BCryptPasswordEncoder();
	 	}
	
}
