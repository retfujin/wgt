package com.acec.wgt.models.contract;
 
import java.sql.Date;

import javax.persistence.Entity; 
import javax.persistence.Table;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import com.acec.wgt.models.IdEntity;

@Entity
@Table(name = "tb_contract_notice")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class NoticeEO extends IdEntity {
	//培训通知
    private String title;
    private String begintime;
    private String address;
    private String teacher;
    private String material;
    private String method;
    private String usernames;
    
    private String isdel;
    private Integer recordid;
    private String recordname;
    private String recordtime;
    
    
    
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBegintime() {
		return begintime;
	}
	public void setBegintime(String begintime) {
		this.begintime = begintime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTeacher() {
		return teacher;
	}
	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}
	public String getMaterial() {
		return material;
	}
	public void setMaterial(String material) {
		this.material = material;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	
	public String getUsernames() {
		return usernames;
	}
	public void setUsernames(String usernames) {
		this.usernames = usernames;
	}
	public String getIsdel() {
		return isdel;
	}
	public void setIsdel(String isdel) {
		this.isdel = isdel;
	}
	public Integer getRecordid() {
		return recordid;
	}
	public void setRecordid(Integer recordid) {
		this.recordid = recordid;
	}
	public String getRecordname() {
		return recordname;
	}
	public void setRecordname(String recordname) {
		this.recordname = recordname;
	}
	public String getRecordtime() {
		return recordtime;
	}
	public void setRecordtime(String recordtime) {
		this.recordtime = recordtime;
	}
	 
}