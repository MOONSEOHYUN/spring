package com.spring.food.dto;

public class BoardDTO {
	private int bnum; 
	private String id;
	private String subject;
	private String content;
	private int readcnt;
	private int likecnt;
	private int dislikecnt;
	private String ip;
	private String regdate;
	private String modifydate;
	
	public BoardDTO() {
		super();
	}

	public BoardDTO(int bnum, String id, String subject, String content, int readcnt, int likecnt, int dislikecnt,
			String ip, String regdate, String modifydate) {
		super();
		this.bnum = bnum;
		this.id = id;
		this.subject = subject;
		this.content = content;
		this.readcnt = readcnt;
		this.likecnt = likecnt;
		this.dislikecnt = dislikecnt;
		this.ip = ip;
		this.regdate = regdate;
		this.modifydate = modifydate;
	}

	public int getBnum() {
		return bnum;
	}

	public void setBnum(int bnum) {
		this.bnum = bnum;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getReadcnt() {
		return readcnt;
	}

	public void setReadcnt(int readcnt) {
		this.readcnt = readcnt;
	}

	public int getLikecnt() {
		return likecnt;
	}

	public void setLikecnt(int likecnt) {
		this.likecnt = likecnt;
	}

	public int getDislikecnt() {
		return dislikecnt;
	}

	public void setDislikecnt(int dislikecnt) {
		this.dislikecnt = dislikecnt;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getModifydate() {
		return modifydate;
	}

	public void setModifydate(String modifydate) {
		this.modifydate = modifydate;
	}

	@Override
	public String toString() {
		return "BoardDTO [bnum=" + bnum + ", id=" + id + ", subject=" + subject + ", content=" + content + ", readcnt="
				+ readcnt + ", likecnt=" + likecnt + ", dislikecnt=" + dislikecnt + ", ip=" + ip + ", regdate="
				+ regdate + ", modifydate=" + modifydate + "]";
	}
	
	
	
}
