package com.spring.food.dto;

public class ClassifyDTO {
	private String gubun;
	private int num;
	private String id;
	private String likegubun;
	
	public ClassifyDTO() {}

	public ClassifyDTO(String gubun, int num, String id, String likegubun) {
		super();
		this.gubun = gubun;
		this.num = num;
		this.id = id;
		this.likegubun = likegubun;
	}

	public String getGubun() {
		return gubun;
	}

	public void setGubun(String gubun) {
		this.gubun = gubun;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLikegubun() {
		return likegubun;
	}

	public void setLikegubun(String likegubun) {
		this.likegubun = likegubun;
	}

	@Override
	public String toString() {
		return "LikeUserDTO [gubun=" + gubun + ", num=" + num + ", id=" + id + ", likegubun=" + likegubun + "]";
	}
}
