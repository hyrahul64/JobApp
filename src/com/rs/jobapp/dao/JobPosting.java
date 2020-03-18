package com.rs.jobapp.dao;

public class JobPosting {

	private Integer id;
	private String company;
	private String title;
	private String referenceId;
	private String postedOn;
	private String location;
	private int minExp;
	private int maxExp;
	private boolean deleted;
	private String skills;
	private String roles;
	private String qualifications;
	private String technologies;
	private String contact;
	private String mobile;
	private String email;
	private String apply;
	
	public JobPosting(Integer id) {
		this.id = id;
	}
	public JobPosting(String company, String title, String referenceId, String postedOn, String location, int minExp,
			int maxExp, boolean deleted, String skills, String roles, String qualifications, String technologies,
			String contact, String mobile, String email, String apply) {
		super();
		this.id = null;
		this.company = company;
		this.title = title;
		this.referenceId = referenceId;
		this.postedOn = postedOn;
		this.location = location;
		this.minExp = minExp;
		this.maxExp = maxExp;
		this.deleted = deleted;
		this.skills = skills;
		this.roles = roles;
		this.qualifications = qualifications;
		this.technologies = technologies;
		this.contact = contact;
		this.mobile = mobile;
		this.email = email;
		this.apply = apply;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	public String getPostedOn() {
		return postedOn;
	}

	public void setPostedOn(String postedOn) {
		this.postedOn = postedOn;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getMinExp() {
		return minExp;
	}

	public void setMinExp(int minExp) {
		this.minExp = minExp;
	}

	public int getMaxExp() {
		return maxExp;
	}

	public void setMaxExp(int maxExp) {
		this.maxExp = maxExp;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

	public String getQualifications() {
		return qualifications;
	}

	public void setQualifications(String qualifications) {
		this.qualifications = qualifications;
	}

	public String getTechnologies() {
		return technologies;
	}

	public void setTechnologies(String technologies) {
		this.technologies = technologies;
	}
	
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getApply() {
		return apply;
	}
	public void setApply(String apply) {
		this.apply = apply;
	}
	
	@Override
	public String toString() {
		return "JobPosting [id=" + id + ", company=" + company + ", title=" + title + ", referenceId=" + referenceId
				+ ", postedOn=" + postedOn + ", location=" + location + ", minExp=" + minExp + ", maxExp=" + maxExp
				+ ", deleted=" + deleted + ", skills=" + skills + ", roles=" + roles + ", qualifications="
				+ qualifications + ", technologies=" + technologies + ", contact=" + contact + ", mobile=" + mobile
				+ ", email=" + email + ", apply=" + apply + "]";
	}
	
}
