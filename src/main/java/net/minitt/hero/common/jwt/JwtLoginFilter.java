package net.minitt.hero.common.jwt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JwtLoginFilter extends UsernamePasswordAuthenticationFilter {
	private AuthenticationManager authenticationManager;

	public JwtLoginFilter(AuthenticationManager authenticationManager) {
		setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/admin/login", "POST"));
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {
		String username = null;
		String password = null;
		StringBuffer sb = new StringBuffer("");
		BufferedReader br = null;
		try {
			br = new BufferedReader(new InputStreamReader((ServletInputStream) req.getInputStream(), "utf-8"));
			String temp;
			while ((temp = br.readLine()) != null) {
				sb.append(temp);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(br!=null)
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		ObjectMapper objMap = new ObjectMapper();
		try {
			Map<String,String> map = objMap.readValue(sb.toString(), Map.class);
			username = (String) map.get("username");
			password = (String) map.get("password");
		} catch (JsonParseException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>()));
	}
}
