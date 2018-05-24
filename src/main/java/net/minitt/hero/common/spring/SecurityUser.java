package net.minitt.hero.common.spring;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class SecurityUser extends User{
	
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	public SecurityUser(String username,Integer id) {
		this(username,"",true, true, true, true,new ArrayList<>());
		this.id = id;
	}
	
	public SecurityUser(net.minitt.hero.blog.entity.User account) {
		this(account.getUsername(),account.getPassword(),true, true, true, true,new ArrayList<>());
		this.id = account.getId();
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
	
	public net.minitt.hero.blog.entity.User getUser() {
		net.minitt.hero.blog.entity.User u = new net.minitt.hero.blog.entity.User();
		u.setId(id);
		u.setUsername(getUsername());
		return u;
	}

}
