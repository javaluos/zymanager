package com.zy.cms.vo;


import java.io.Serializable;


public class FileType implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5856785560794959154L;

	/**
	 * ID
	 */
	private Integer id;
	
	/**
	 * NAME
	 */
	private String name;
	
	/**
	 * REMARK
	 */
	private String remark;
	
	/**
	 * CODE
	 */
	private String code;
	
	public Integer getId(){
		return this.id;
	}

	public void setId(Integer id){
		this.id = id;
	}
	
	public String getName(){
		return this.name;
	}

	public void setName(String name){
		this.name = name;
	}
	
	public String getRemark(){
		return this.remark;
	}

	public void setRemark(String remark){
		this.remark = remark;
	}
	
	public String getCode(){
		return this.code;
	}

	public void setCode(String code){
		this.code = code;
	}
	
}