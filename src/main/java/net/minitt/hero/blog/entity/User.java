package net.minitt.hero.blog.entity;

import javax.persistence.Entity;

import net.minitt.hero.common.jpa.BaseEntity;

@Entity
public class User extends BaseEntity{
	private static final long serialVersionUID = 1L;

	// 用户名称
    private String username;

    // 用户密码
    private String password;

    // 用户的邮箱
    private String email;

    // 用户显示的名称
    private String screenName;

    // 用户注册时的GMT unix时间戳
    private Integer createdTime;

    // 最后活动时间
    private Integer activatedTime;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getScreenName() {
		return screenName;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public Integer getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Integer createdTime) {
		this.createdTime = createdTime;
	}

	public Integer getActivatedTime() {
		return activatedTime;
	}

	public void setActivatedTime(Integer activatedTime) {
		this.activatedTime = activatedTime;
	}
	
}
