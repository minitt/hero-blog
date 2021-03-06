package net.minitt.hero.config;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import net.minitt.hero.core.entity.Resource;
import net.minitt.hero.core.security.HeroPermissionEvaluator;
import net.minitt.hero.core.security.JwtAuthenticationFilter;
import net.minitt.hero.core.security.JwtLoginFilter;
import net.minitt.hero.core.security.SecurityUser;
import net.minitt.hero.core.service.ResourceService;
import net.minitt.hero.core.service.UserService;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private UserService userService;
    
    @Autowired
    private ResourceService resourceService;
    
//    @Autowired
//    private HeroFilterSecurityInterceptor heroFilterSecurityInterceptor;

    @Value("{hero.signature-key}")
	private String signatureKey;
    
    

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	/**
    	 * 采用JWT登录方式
    	 */
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
					response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					response.setCharacterEncoding("UTF-8");
					response.setContentType("application/json; charset=utf-8");  
					PrintWriter out = response.getWriter();
				    out.write("{\"code\":40101,\"data\":{\"msg\":\"帐号密码错误\"}}");
				}
			}
    		
    	});
    	
    	/**
    	 * session模式登录
    	 */
        http.cors().and().csrf().disable()
        /**
         * jwt模式需要取消注释
         */
        	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    		.and()
    		.authorizeRequests()
    		.antMatchers("/admin/**")
    		.authenticated()
            .and()
            /**
             * 传统session ajax登录
             
            .formLogin().loginPage("/admin/login").successHandler((HttpServletRequest request, HttpServletResponse response, Authentication authentication) -> {
            	response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json; charset=utf-8");  
				PrintWriter out = response.getWriter();
				out.write("{\"code\":20000,\"data\":{\"msg\":\"登录成功\"}}");
				out.flush();
                out.close();
            })
            .failureHandler((HttpServletRequest request, HttpServletResponse response, AuthenticationException e) -> {
            	response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            	response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json; charset=utf-8");  
				PrintWriter out = response.getWriter();
				if(e instanceof BadCredentialsException) {
					out.write("{\"code\":40101,\"data\":{\"msg\":\"帐号密码错误\"}}");
				}
				out.flush();
                out.close();
            }).and().exceptionHandling().accessDeniedHandler((HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) -> {//登录后，权限不够
            	response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            	response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json; charset=utf-8");  
				PrintWriter out = response.getWriter();
				out.write("{\"code\":40301,\"data\":{\"msg\":\"无权访问\"}}");
				out.flush();
                out.close();
            }).authenticationEntryPoint((HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) -> {//未登录访问
            	response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            	response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json; charset=utf-8");  
				PrintWriter out = response.getWriter();
				out.write("{\"code\":40302,\"data\":{\"msg\":\"无权访问，请先登录\"}}");
				out.flush();
                out.close();
            }).and().logout().logoutUrl("/admin/logout").logoutSuccessHandler((HttpServletRequest request, HttpServletResponse response, Authentication authentication) -> {
            	response.setCharacterEncoding("UTF-8");
				response.setContentType("application/json; charset=utf-8");  
				PrintWriter out = response.getWriter();
				out.write("{\"code\":20000,\"data\":{\"msg\":\"登出成功\"}}");
				out.flush();
                out.close();
			}).permitAll();
			*/
            /**
             * jwt登录方式
             */
            .addFilter(loginFilter)
            .addFilter(new JwtAuthenticationFilter(authenticationManager(),signatureKey));
    }

    /*
     *   当前版本5新增支持加密方式：
     *   bcrypt - BCryptPasswordEncoder (Also used for encoding)
     *   ldap - LdapShaPasswordEncoder
     *   MD4 - Md4PasswordEncoder
     *   MD5 - new MessageDigestPasswordEncoder("MD5")
     *   noop - NoOpPasswordEncoder
     *   pbkdf2 - Pbkdf2PasswordEncoder
     *   scrypt - SCryptPasswordEncoder
     *   SHA-1 - new MessageDigestPasswordEncoder("SHA-1")
     *   SHA-256 - new MessageDigestPasswordEncoder("SHA-256")
     *   sha256 - StandardPasswordEncoder
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(buildUserDetailsService()).passwordEncoder(new BCryptPasswordEncoder());
    }
    
    
    
    @Bean
    UserDetailsService buildUserDetailsService() {
//    	AuthorityUtils.createAuthorityList("USER", "write")
        return username -> {
            net.minitt.hero.core.entity.User account = userService.findUser(username);
            if(account==null) {
            	throw new UsernameNotFoundException(username);
            }
            List<Resource> resources = resourceService.findAll(account.getId());
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (Resource res : resources) {
                if (res != null && res.getAuth()!=null) {
                	if(res.getAuth().indexOf(';')>=0) {
                		for(String x:res.getAuth().split(":")[1].split(";")) {
                			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(res.getAuth().split(":")[0]+":"+x);
                    		//1：此处将权限信息添加到 GrantedAuthority 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
                    		grantedAuthorities.add(grantedAuthority);
                		}
                	}else {
                		GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(res.getAuth());
                		grantedAuthorities.add(grantedAuthority);
                	}
                }
            }
            SecurityUser user = new SecurityUser(account,grantedAuthorities);
            return user;
        };
    }
    
    @Bean
    public DefaultWebSecurityExpressionHandler webSecurityExpressionHandler(){
        DefaultWebSecurityExpressionHandler handler = new DefaultWebSecurityExpressionHandler();
        handler.setPermissionEvaluator(new HeroPermissionEvaluator());
        return handler;
    }
}
