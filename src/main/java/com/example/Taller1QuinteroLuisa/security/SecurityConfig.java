package com.example.Taller1QuinteroLuisa.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.Taller1QuinteroLuisa.model.person.UserType;


public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private LoggingAccessDeniedHandler accessDeniedHandler;


	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {		
		httpSecurity.formLogin().loginPage("/login").permitAll().and()
		.authorizeRequests()
		.antMatchers("/").authenticated()
		.antMatchers("/secure/**").authenticated()
		.antMatchers("/users/**").permitAll()
		.antMatchers("/admin/**").hasRole(UserType.administrator.toString())
		.antMatchers("/operator/**").hasRole(UserType.operator.toString())
		.anyRequest().authenticated().and()
		.httpBasic().and().logout().invalidateHttpSession(true).clearAuthentication(true)
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		.logoutSuccessUrl("/login?logout").permitAll().and().exceptionHandling()
		.accessDeniedHandler(accessDeniedHandler);
		
//		http
//    	.authorizeRequests()
//    	.antMatchers("/").authenticated()
//    	.antMatchers("/secure/**").authenticated()
//    	.antMatchers("/users/**").permitAll()
//    	.antMatchers("/admin/**").hasRole("ADMIN")
//    	.antMatchers("/operator/**").hasRole("OPERATOR")
//    	.anyRequest().authenticated()
//    	.and().httpBasic()
//    	.and().formLogin()
//        	.loginProcessingUrl("/login")
//			.permitAll().and().exceptionHandling().accessDeniedHandler(accessDeniedHandler);
	}
}