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
    private String screen_name;

    // 用户注册时的GMT unix时间戳
    private Integer created_time;

    // 最后活动时间
    private Integer activated_time;

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

	public String getScreen_name() {
		return screen_name;
	}

	public void setScreen_name(String screen_name) {
		this.screen_name = screen_name;
	}

	public Integer getCreated_time() {
		return created_time;
	}

	public void setCreated_time(Integer created_time) {
		this.created_time = created_time;
	}

	public Integer getActivated_time() {
		return activated_time;
	}

	public void setActivated_time(Integer activated_time) {
		this.activated_time = activated_time;
	}
}
