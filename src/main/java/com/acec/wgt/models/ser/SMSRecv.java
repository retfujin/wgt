/**
 * @author th
 * @version 1.0
 * @since 1.0
 */

package com.acec.wgt.models.ser;


import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import com.acec.wgt.models.IdEntity;
import com.googlecode.jsonplugin.annotations.JSON;

@Entity
@Table(name = "tb_sms_recv")
//宽松的缓存策略.
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SMSRecv extends IdEntity{
	
	
	//date formats
	public static final String FORMAT_RECV_TIME = DATE_TIME_FORMAT;
	
	//columns START
	private String msgId;
	private java.util.Date recvTime;
	private String recvTimeString;
	private String mobile;
	private String content;
	private Integer userId;
	private String userName;
	private String subid;
	private String replyState;
	//columns END

	@Transient
	public String getRecvTimeString() {
		return date2String(getRecvTime(), FORMAT_RECV_TIME);
	}
	public void setRecvTimeString(String value) {
		setRecvTime(string2Date(value, FORMAT_RECV_TIME));
	}
	
	public void setRecvTime(java.util.Date value) {
		this.recvTime = value;
	}
	@JSON(format="yyyy-MM-dd HH:mm:ss")
	public java.util.Date getRecvTime() {
		return this.recvTime;
	}
	public void setMobile(String value) {
		this.mobile = value;
	}
	public String getMobile() {
		return this.mobile;
	}
	public void setContent(String value) {
		this.content = value;
	}
	public String getContent() {
		return this.content;
	}	 
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public void setSubid(String value) {
		this.subid = value;
	}
	public String getSubid() {
		return this.subid;
	}
	public String getReplyState() {
		return replyState;
	}
	public void setReplyState(String replyState) {
		this.replyState = replyState;
	}
	public String getMsgId() {
		return msgId;
	}
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}	
}