package com.ssafy.smaheal.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "notices")
public class Notices{
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long noticeid;

    @NotBlank
    @Size(max = 50)
    private String title;


	@NotBlank
    @Size(max = 1000)
    private String content;
	
	private Date createdAt;
    
	public Notices() {
		
	}

	public Notices(Long noticeid, @NotBlank @Size(max = 50) String title, @NotBlank @Size(max = 1000) String content,
			Date createdAt) {
		super();
		this.noticeid = noticeid;
		this.title = title;
		this.content = content;
		this.createdAt = createdAt;
	}

	public Long getNoticeid() {
		return noticeid;
	}

	public void setNoticeid(Long noticeid) {
		this.noticeid = noticeid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date created_at) {
		this.createdAt = created_at;
	}

	@Override
	public String toString() {
		return "Notices [noticeid=" + noticeid + ", title=" + title + ", content=" + content + ", created_at="
				+ createdAt + "]";
	}

}