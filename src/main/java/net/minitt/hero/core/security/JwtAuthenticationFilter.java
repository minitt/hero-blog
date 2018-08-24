package net.minitt.hero.core.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;

public class JwtAuthenticationFilter extends BasicAuthenticationFilter {

	public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
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
//				res.addHeader("Authorization", "123");
				chain.doFilter(req, res);
			}catch(ExpiredJwtException e) {
				res.sendError(HttpServletResponse.SC_UNAUTHORIZED, e.getMessage());//过期
			}
		}
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if (token != null) {
			Claims c = Jwts.parser().setSigningKey("XdYKq22i@L'3}BdW{J;p'wSaRZSQs''v").parseClaimsJws(token.replace("Bearer ", ""))
					.getBody();//ExpiredJwtException
			SecurityUser user = new SecurityUser(c.get("username", String.class),c.get("id", Integer.class));
			return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
		}
		return null;
	}

}
