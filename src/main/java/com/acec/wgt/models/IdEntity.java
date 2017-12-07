package com.acec.wgt.models;

import java.text.ParseException;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 统一定义id的entity基类.
 * 
 * @author
 */
// JPA Entity基类的标识
@MappedSuperclass
public class IdEntity {

	protected static final String DATE_FORMAT = "yyyy-MM-dd";

	protected static final String TIME_FORMAT = "HH:mm:ss";
	protected static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	protected static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.S";

	protected String date2String(Date date, String FORMAT) {
		return new java.text.SimpleDateFormat(FORMAT).format(date);
	}
	
	protected Date string2Date(String value, String FORMAT) {
		
		try {
			return new java.text.SimpleDateFormat(FORMAT).parse(value);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	private Integer id;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
}
