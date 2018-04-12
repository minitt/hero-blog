package net.minitt.hero.blog.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import net.minitt.hero.common.jpa.BaseEntity;

@Entity
public class User extends BaseEntity{
	private static final long serialVersionUID = 1L;

	// 用户名称
	@NotBlank
    private String username;

    /**
     * 密码
     * 永远不序列化密码
     */
    @JsonIgnore
    private String password;

    // 用户的邮箱
    private String email;

    // 用户显示的名称
    @NotBlank
    private String screenName;

    // 用户注册时的GMT unix时间戳
    private Long createdTime;

    // 最后活动时间
    private Long activatedTime;

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

	public Long getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Long createdTime) {
		this.createdTime = createdTime;
	}

	public Long getActivatedTime() {
		return activatedTime;
	}

	public void setActivatedTime(Long activatedTime) {
		this.activatedTime = activatedTime;
	}
	
}
