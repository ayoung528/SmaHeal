package com.ssafy.smaheal.help;

import java.util.Date;

import javax.validation.constraints.*;


public class SignUpRequest {
    @NotBlank
    @Size(min = 4, max = 15)
    private String userId;

    @NotBlank
    @Size(min = 3, max = 15)
    private String name;

    @NotBlank
    @Size(min = 6, max = 20)
    private String password;
    
    @NotBlank
    @Size(max = 15)
    private String nickName;
    
    private Date birth;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	@Override
	public String toString() {
		return "SignUpRequest [userId=" + userId + ", name=" + name + ", password=" + password + ", nickName="
				+ nickName + ", birth=" + birth + "]";
	}

    
}
