package com.spring.food.dto;

public class FoodAdditivesDTO {
	private String pcode;
	private String pname;
	
	public FoodAdditivesDTO() {
		super();
	}

	public FoodAdditivesDTO(String pcode, String pname) {
		super();
		this.pcode = pcode;
		this.pname = pname;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	@Override
	public String toString() {
		return "FoodAdditivesDTO [pcode=" + pcode + ", pname=" + pname + "]";
	}
	
}
