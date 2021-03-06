package net.minitt.hero.core.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {
	
	private String signatureKey;

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager,String signatureKey) {
		super(authenticationManager);
		this.signatureKey = signatureKey;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		String header = req.getHeader("Authorization");

		if (header == null || !header.startsWith("Bearer ")) {
			chain.doFilter(req, res);
		}else {
			try {
				UsernamePasswordAuthenticationToken authentication = getAuthentication(req);
				SecurityContextHolder.getContext().setAuthentication(authentication);
				chain.doFilter(req, res);
			}catch(ExpiredJwtException e) {
				res.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());//过期
			}
		}
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token != null) {
			Claims c = Jwts.parser().setSigningKey(signatureKey).parseClaimsJws(token.replace("Bearer ", ""))
					.getBody();//ExpiredJwtException
			Map loginuser = c.get("loginuser",Map.class);
			List<Map> authorities = (List<Map>)loginuser.get("authorities");
			List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
			for(Map m:authorities) {
				grantedAuthorities.add(new SimpleGrantedAuthority(m.get("authority").toString()));
			}
			SecurityUser user = new SecurityUser(loginuser,grantedAuthorities);
			return new UsernamePasswordAuthenticationToken(user, null, grantedAuthorities);
		}
		return null;
	}

}
