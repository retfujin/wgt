package com.acec.wgt.models;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 统一定义String类型的id的entity基类.
 * 
 * @author 
 */
//JPA Entity基类的标识
@MappedSuperclass
public class IdStrEntity {

	private String id;
	@Id
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	
	
}
