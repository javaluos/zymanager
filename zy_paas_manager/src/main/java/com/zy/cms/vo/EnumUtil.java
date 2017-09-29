package com.zy.cms.vo;

/**
 * 枚举业务类
 * 
 * @author allen.yuan
 * @date 2016-11-25
 *
 */
public class EnumUtil {

	private Integer id;
	private String name;

	public EnumUtil() {

	}

	public EnumUtil(Integer id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
