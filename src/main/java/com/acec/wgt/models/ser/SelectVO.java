/*
 * comany:th
 * 
 * 
 */

package com.acec.wgt.models.ser;


import com.bestnet.base.vo.BaseVO;



/**
 * @author th
 * @version 1.0
 * @since 1.0
 */


public class SelectVO extends BaseVO {
	
	
	//date formats
	public static final String FORMAT_BIRTH = DATE_FORMAT;
	
	//columns START
	private String id;
	private String name;
	
	private String type;
	
	private String value;
	
	private String value1;
	
	public SelectVO(){
	}
	
	public SelectVO(String name){
		this.name = name;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getValue1() {
		return value1;
	}

	public void setValue1(String value1) {
		this.value1 = value1;
	}
	
	
	
}

