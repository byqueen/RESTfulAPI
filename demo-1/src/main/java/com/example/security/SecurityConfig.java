package com.example.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
	SecurityConfig() {
		System.out.println("===> SecurityConfig() 생성자");
	}

	@Autowired
	SecurityUserDetailsService securityUserDetail;

	@SuppressWarnings("removal")
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http.authorizeHttpRequests(authorize ->authorize
	    			.requestMatchers("/board/boardList").authenticated()
	    			.requestMatchers("/portone/**").hasAnyRole("MANAGER", "ADMIN")
	    			.requestMatchers("/exam/**").hasRole("ADMIN")
	    			.anyRequest().permitAll())
				
		.csrf(csrf ->csrf.disable())	
	    
		.formLogin(login ->login.loginPage("/login/loginform").defaultSuccessUrl("/login/success", true))
		.exceptionHandling(handling ->handling.accessDeniedPage("/login/accessDenied"))
		.logout(logout ->logout.invalidateHttpSession(true).logoutSuccessUrl("/"))
		.userDetailsService(securityUserDetail);
		return http.build(); 
	}	
	
    @Bean
    public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();		
	}
}
