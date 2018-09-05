package net.minitt.hero.core.security;

import java.io.Serializable;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class HeroPermissionEvaluator implements PermissionEvaluator {

	@Override
	public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
		List<GrantedAuthority> authorities = (List<GrantedAuthority>) authentication.getAuthorities();
		if(permission instanceof String&&StringUtils.isNotBlank((String)permission)){
			for(GrantedAuthority auth:authorities) {
				String authStr = auth.getAuthority().trim();
				if(((String)permission).trim().equals(authStr)){
					return true;
				}else if((((String)permission).split(":")[0]+":*").trim().equals(authStr)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType,
			Object permission) {
		return false;
	}

}
