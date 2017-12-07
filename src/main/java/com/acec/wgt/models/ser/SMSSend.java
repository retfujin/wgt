/**
 * @author th
 * @version 1.0
 * @since 1.0
 */

package com.acec.wgt.models.ser;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.acec.wgt.models.IdEntity;
import com.bestnet.base.vo.BaseVO;
import com.googlecode.jsonplugin.annotations.JSON;


@Entity
@Table(name = "tb_sms_send")
//宽松的缓存策略.

@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SMSSend extends IdEntity {
	
	
	//date formats
	public static final String FORMAT_SEND_TIME = DATE_TIME_FORMAT;
	
	//columns START
	private Integer id;
	private String subid;
	private java.util.Date sendTime;
	
	private String sendTimeString;//字符串日期
	
	private String sendName;
	private Integer receiveId;
	private String receiveName;
	private Integer unitId;
	private String unitName;
	private String mobilePhones;
	private Integer mobileNum;
	private String content;
	private Integer contentNum;
	private String type; //0:手机号  1 :学生   2 : 教师
	private String state;
	
	private String msgid;
	//columns END

	public SMSSend(){
	}

	public SMSSend(
		Integer id
	){
		this.id = id;
	}
 
	@Transient
	public String getSendTimeString() {
		return date2String(getSendTime(), DATE_TIME_FORMAT);
	}
	public void setSendTimeString(String sendTimeString) {
		setSendTime(string2Date(sendTimeString, DATE_TIME_FORMAT));
	}
	
	
	public void setSendTime(java.util.Date value) {
		this.sendTime = value;
	}
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public java.util.Date getSendTime() {
		return this.sendTime;
	}
	
	
	
	public void setSendName(String value) {
		this.sendName = value;
	}
	public String getSendName() {
		return this.sendName;
	}
	
	public Integer getReceiveId() {
		return receiveId;
	}

	public void setReceiveId(Integer receiveId) {
		this.receiveId = receiveId;
	}

	public String getReceiveName() {
		return receiveName;
	}

	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}

	public Integer getUnitId() {
		return unitId;
	}

	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public void setMobilePhones(String value) {
		this.mobilePhones = value;
	}
	public String getMobilePhones() {
		return this.mobilePhones;
	}
	public void setMobileNum(Integer value) {
		this.mobileNum = value;
	}
	public Integer getMobileNum() {
		return this.mobileNum;
	}
	public void setContent(String value) {
		this.content = value;
	}
	public String getContent() {
		return this.content;
	}
	public void setContentNum(Integer value) {
		this.contentNum = value;
	}
	public Integer getContentNum() {
		return this.contentNum;
	}
	public void setType(String value) {
		this.type = value;
	}
	public String getType() {
		return this.type;
	}
	public void setState(String value) {
		this.state = value;
	}
	public String getState() {
		return this.state;
	}
	public String getSubid() {
		return subid;
	}

	public void setSubid(String subid) {
		this.subid = subid;
	}

	public String getMsgid() {
		return msgid;
	}

	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}

	
	
	
}

