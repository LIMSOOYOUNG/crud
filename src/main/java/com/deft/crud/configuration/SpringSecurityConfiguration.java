package com.deft.crud.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.deft.crud.member.model.service.MemberService;

@EnableWebSecurity
public class SpringSecurityConfiguration extends WebSecurityConfigurerAdapter{

	private final MemberService memberService;
	
	@Autowired
	public SpringSecurityConfiguration(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/css/**", "/js/**", "/static/images/**", "/lib/**");
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable()
			.authorizeRequests()
				.antMatchers("/menu/**").authenticated()
				.antMatchers("/dashboard/**").permitAll()
				.antMatchers(HttpMethod.GET, "menu/**").hasRole("MEMBER")
				.antMatchers(HttpMethod.POST, "menu/**").hasRole("ADMIN")
				.antMatchers("/admin/**").hasRole("ADMIN")
				.anyRequest().permitAll()
			.and()
				.formLogin()
				.loginPage("/member/login")
				.successForwardUrl("/")
			.and()
				.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
				.deleteCookies("JSESSIONID")
				.invalidateHttpSession(true)
				.logoutSuccessUrl("/")
			.and()
				.exceptionHandling();
//				.accessDeniedPage("/common/denied");
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(memberService).passwordEncoder(passwordEncoder());
	}
	
}
