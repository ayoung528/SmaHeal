package com.ssafy.smaheal.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

import com.ssafy.smaheal.model.audit.DateAudit;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
            "userId"
        }),
        @UniqueConstraint(columnNames = {
            "nickName"
        })
})
public class MemberUser extends DateAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long num;

    @NotBlank
    @Size(max = 15)
    private String userId;

    @NotBlank
    @Size(max = 15)
    private String name;

    @NotBlank
    @Size(max = 100)
    private String password;
    
    @NotBlank
    @Size(max = 15)
    private String nickName;
    
    private Date birth;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_num"),
            inverseJoinColumns = @JoinColumn(name = "role_num"))
    private Set<Role> roles = new HashSet<>();
    
    public MemberUser() {

    }

	public MemberUser(Long num, @NotBlank @Size(max = 40) String userId, @NotBlank @Size(max = 15) String name,
			@NotBlank @Size(max = 100) String password, @NotBlank @Size(max = 15) String nickName, Date birth,
			Set<Role> roles) {
		super();
		this.num = num;
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.nickName = nickName;
		this.birth = birth;
		this.roles = roles;
	}

	public Long getNum() {
		return num;
	}

	public void setNum(Long num) {
		this.num = num;
	}

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

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "MemberUser [num=" + num + ", userId=" + userId + ", name=" + name + ", password=" + password
				+ ", nickName=" + nickName + ", birth=" + birth + ", roles=" + roles + "]";
	}

}