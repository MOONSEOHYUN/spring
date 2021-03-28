package com.spring.food.dto;

import org.springframework.web.multipart.MultipartFile;

public class MemberDTO {
	private String id;
	private String passwd;
	private String oldpasswd;
	private String email;
	private String zip;
	private String addr1;
	private String addr2;
	private String filename;
	private String admin;
	private String emailauth;
	private String simplejoin;
	private String regdate;
	private MultipartFile imgfile;
	
	public MemberDTO() {}

	public MemberDTO(String id, String passwd, String oldpasswd, String email, String zip, String addr1, String addr2,
			String filename, String admin, String emailauth, String simplejoin, String regdate, MultipartFile imgfile) {
		super();
		this.id = id;
		this.passwd = passwd;
		this.oldpasswd = oldpasswd;
		this.email = email;
		this.zip = zip;
		this.addr1 = addr1;
		this.addr2 = addr2;
		this.filename = filename;
		this.admin = admin;
		this.emailauth = emailauth;
		this.simplejoin = simplejoin;
		this.regdate = regdate;
		this.imgfile = imgfile;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getOldpasswd() {
		return oldpasswd;
	}

	public void setOldpasswd(String oldpasswd) {
		this.oldpasswd = oldpasswd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getAdmin() {
		return admin;
	}

	public void setAdmin(String admin) {
		this.admin = admin;
	}

	public String getEmailauth() {
		return emailauth;
	}

	public void setEmailauth(String emailauth) {
		this.emailauth = emailauth;
	}

	public String getSimplejoin() {
		return simplejoin;
	}

	public void setSimplejoin(String simplejoin) {
		this.simplejoin = simplejoin;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public MultipartFile getImgfile() {
		return imgfile;
	}

	public void setImgfile(MultipartFile imgfile) {
		this.imgfile = imgfile;
	}

	@Override
	public String toString() {
		return "MemberDTO [id=" + id + ", passwd=" + passwd + ", oldpasswd=" + oldpasswd + ", email=" + email + ", zip="
				+ zip + ", addr1=" + addr1 + ", addr2=" + addr2 + ", filename=" + filename + ", admin=" + admin
				+ ", emailauth=" + emailauth + ", simplejoin=" + simplejoin + ", regdate=" + regdate + ", imgfile="
				+ imgfile + "]";
	}
}
