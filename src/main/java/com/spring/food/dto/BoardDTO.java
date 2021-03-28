package com.spring.food.dto;

public class BoardDTO {
	private int bnum; 
	private String id;
	private String subject;
	private String content;
	private int readcount;
	private int likecnt;
	private int dislikecnt;
	private String ip;
	private String regdate;
	private String modifydate;
	
	public BoardDTO() {}
	public BoardDTO(String id, String subject, String content, String ip) {
		super();
		this.id = id;
		this.subject = subject;
		this.content = content;
		this.ip = ip;
	}
	
	public BoardDTO(int bnum, String subject, String content) {
		super();
		this.bnum = bnum;
		this.subject = subject;
		this.content = content;
	}
	public BoardDTO(int bnum, String id, String subject, String content, int readcount, int likecnt, int dislikecnt,
			String ip, String regdate, String modifydate) {
		super();
		this.bnum = bnum;
		this.id = id;
		this.subject = subject;
		this.content = content;
		this.readcount = readcount;
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
	public int getReadcount() {
		return readcount;
	}
	public void setReadcount(int readcount) {
		this.readcount = readcount;
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
		return "BoardDTO [bnum=" + bnum + ", id=" + id + ", subject=" + subject + ", content=" + content
				+ ", readcount=" + readcount + ", likecnt=" + likecnt + ", dislikecnt=" + dislikecnt + ", ip=" + ip
				+ ", regdate=" + regdate + ", modifydate=" + modifydate + "]";
	}
}
