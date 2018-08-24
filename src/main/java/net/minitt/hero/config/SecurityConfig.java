package net.minitt.hero.config;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.minitt.hero.blog.service.UserService;
import net.minitt.hero.core.security.JwtAuthenticationFilter;
import net.minitt.hero.core.security.JwtLoginFilter;
import net.minitt.hero.core.security.SecurityUser;

@Configuration
@Order(SecurityProperties.DEFAULT_FILTER_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private UserService userService;

    @Value("{hero.signature-key}")
	private String signatureKey;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	JwtLoginFilter loginFilter = new JwtLoginFilter(authenticationManager());
    	loginFilter.setAuthenticationSuccessHandler(new AuthenticationSuccessHandler() {
			
			@Override
			public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
					Authentication authentication) throws IOException, ServletException {
				SecurityUser u = (SecurityUser) authentication.getPrincipal();
				String token = Jwts.builder().claim("loginuser", u)
						.setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))//过期时间30分钟
						.signWith(SignatureAlgorithm.HS512, signatureKey).compact();
				response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json; charset=utf-8");  
				PrintWriter out = response.getWriter();
			    out.write("{\"code\":20000,\"data\":{\"token\":\"Bearer "+token+"\"}}");
			}
		});
    	loginFilter.setAuthenticationFailureHandler(new AuthenticationFailureHandler() {

			@Override
			public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
					AuthenticationException exception) throws IOException, ServletException {
				if(exception instanceof BadCredentialsException) {
					response.setCharacterEncoding("UTF-8");
					response.setContentType("application/json; charset=utf-8");  
					PrintWriter out = response.getWriter();
				    out.write("{\"code\":40100,\"data\":{\"msg\":\"帐号密码错误\"}}");
				}
			}
    		
    	});
        http.cors().and().csrf().disable()
        		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        		.and()
        		.authorizeRequests()
        		.antMatchers("/admin/**").authenticated()
                //.antMatchers(HttpMethod.POST, "/admin/login").permitAll()
                //.anyRequest().authenticated()
                .and()
                .addFilter(loginFilter)
                .addFilter(new JwtAuthenticationFilter(authenticationManager(),signatureKey));
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(buildUserDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
    }
    
    @Bean
    UserDetailsService buildUserDetailsService() {
    	//AuthorityUtils.createAuthorityList("USER", "write")
        return username -> {
            net.minitt.hero.blog.entity.User account = userService.findUser(username);
            if(account==null) {
            	throw new UsernameNotFoundException(username);
            }
            SecurityUser user = new SecurityUser(account);
            return user;
        };
    }
}
