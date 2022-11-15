package com.deputy.config;


import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().requestMatchers(PathRequest.toStaticResources().atCommonLocations())
					  .antMatchers("/error");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/deputy/main", "/deputy/join/**", "/deputy/login/**", "/deputy/logout/**").permitAll()
				.antMatchers("/deputy/employee/**").hasAnyRole("EMPLOYEE", "MANAGER", "ADMIN")
				.antMatchers("/deputy/manager/**").hasAnyRole("MANAGER", "ADMIN")
				.antMatchers("/**").hasRole("ADMIN")
				.anyRequest().authenticated() 
			.and()
				.formLogin()
				.loginPage("/deputy/login")
				.loginProcessingUrl("/deputy/login/loginProc")
				.defaultSuccessUrl("/deputy/main")
			.and()
				.logout().permitAll()
				.logoutUrl("/deputy/logout")
				.logoutSuccessUrl("/deputy/successLogout");
	}

	@Bean
	public BCryptPasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}

}
