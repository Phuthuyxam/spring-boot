package com.appdeveloperblog.app.ws.security;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.appdeveloperblog.app.ws.service.UserService;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	private final UserService userDetailsService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	public WebSecurity(UserService userDetailsService , BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.userDetailsService = userDetailsService;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable().authorizeRequests()
		.antMatchers(HttpMethod.POST, SecurityConstants.SIGN_UP_URL)
		.permitAll()
		.anyRequest().authenticated().and()
		.addFilter(getAuthenticationFilter())
		.addFilter(new AuthorizationFilter(authenticationManager()))
		/* 
		 * session management :
		 *  always: session luôn luôn được tạo ra nếu hiện tại không session nào đang tồn tại;
		 *  ifRequired: session sẽ được tạo chỉ khi được yêu cầu; (mặc định)
		 *  never: session sẽ không bao giờ tạo ra bởi Spring Security nhưng framework này sẽ sử dụng session nếu đang tồn tại;
		 *  stateless: không session nào được tạo hoặc sử dụng bởi Spring Security 		 
		*/
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}
	
	// custom login url
	public AuthenticationFilter getAuthenticationFilter() throws Exception{
		final AuthenticationFilter filter = new AuthenticationFilter(authenticationManager());
		filter.setFilterProcessesUrl("/users/login");
		return filter;
	}
		
}
