package net.minitt.hero.core.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class SecurityUser extends User{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private net.minitt.hero.core.entity.User user;
	
	public SecurityUser(Map userMap,Collection<? extends GrantedAuthority> authorities) {
		this((String)userMap.get("username"),"",true, true, true, true,authorities);
		Map u = (Map)userMap.get("user");
		this.user = new net.minitt.hero.core.entity.User();
		this.id = (Integer)u.get("id");
		this.user.setId((Integer)u.get("id"));
		this.user.setUsername((String)u.get("username"));
		this.user.setScreenName((String)u.get("screenName"));
		this.user.setEmail((String)u.get("email"));
		this.user.setCreatedTime((Long)u.get("createdTime"));
		this.user.setActivatedTime((Long)u.get("activatedTime"));
	}
	
	public SecurityUser(net.minitt.hero.core.entity.User account,List<GrantedAuthority> grantedAuthorities) {
		this(account.getUsername(),account.getPassword(),true, true, true, true,grantedAuthorities);
		this.id = account.getId();
		this.user = account;
		this.user.setRole(null);
	}

	public SecurityUser(String username, String password, boolean enabled,
			boolean accountNonExpired, boolean credentialsNonExpired,
			boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public net.minitt.hero.core.entity.User getUser() {
		return user;
	}

}
