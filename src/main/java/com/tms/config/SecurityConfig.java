package com.tms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	PasswordEncoder passwordEncode() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
		.withUser("customer")
		.password(passwordEncode().encode("customer"))
		.roles("CUSTOMER")
		.and()
		.withUser("admin")
		.password(passwordEncode().encode("admin"))
		.roles("ADMIN");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		//AUTHENTICATION FOR TRAVELS
			.authorizeRequests()
			.antMatchers("/travels/view/all").permitAll()
			.antMatchers("/travels/view/**").permitAll()
			.antMatchers("/travels/add").hasRole("ADMIN")
			.antMatchers("/travels/update/**").hasRole("ADMIN")
			.antMatchers("/travels/delete/**").hasRole("ADMIN")
		//AUTHENTICATION FOR PACKAGE
			.antMatchers("/package/view/all").permitAll()
			.antMatchers("/package/view/**").permitAll()
			.antMatchers("/package/add").hasRole("ADMIN")
			.antMatchers("/package/delete/**").hasRole("ADMIN")
		//AUTHENTICATION FOR REPORT	
			.antMatchers("/report/view/all").hasRole("ADMIN")
			.antMatchers("/report/view/**").hasRole("ADMIN")
			.antMatchers("/report/add").hasRole("ADMIN")
			.antMatchers("/report/delete/**").hasRole("ADMIN")
		//AUTHENTICATION FOR ROUTE	
			.antMatchers("/route/view/all").permitAll()
			.antMatchers("/route/view/**").permitAll()
			.antMatchers("/route/add").hasRole("ADMIN")
			.antMatchers("/route/update/**").hasRole("ADMIN")
			.antMatchers("/route/delete/**").hasRole("ADMIN")
			
			//AUTHENTICATION FOR ROUTE	
			.antMatchers("/customer/view/all").hasRole("ADMIN")
			.antMatchers("/customer/view/**").permitAll()
			.antMatchers("/customer/add").permitAll()
			.antMatchers("/customer/update/**").permitAll()
			.antMatchers("/customer/delete/**").permitAll()
			
			
			.anyRequest().authenticated()
			.and()
			.httpBasic();
		http.csrf().disable();
	}
}
