package com.acec.wgt.service.sms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acec.wgt.models.baseData.HouseEO;
import com.acec.wgt.models.ser.IndividualSMReceived;
import com.acec.wgt.models.ser.MySendMsg;
import com.acec.wgt.models.ser.SMReceived1;
import com.acec.wgt.models.ser.SmsSendBak;
import com.acec.wgt.models.ser.SMReceived;
import com.acec.wgt.models.ser.SMSendTask;
import com.acec.wgt.models.ser.SmsSendEO;
import com.acec.commons.persist.HibernateEntityDao;
import com.acec.commons.util.PaginatorTag;
import com.acec.core.web.struts2.Struts2Utils;

@Repository
public class MessageManager extends HibernateEntityDao<SmsSendBak> {

	
	private String area;//有权限查看的小区，用，号分割
    
	@Autowired
	private SMSSendManager smsSendManager;
	private Map session;
	protected Logger log = LoggerFactory.getLogger(this.getClass());

	public void setSession(Map session){
		this.session = session;
		this.area = (String)session.get("areaIds");
	}
	
	public List getMobTel(String sql){
		
			return find(sql,null);
	}
	
	//保存
	public void save(SmsSendBak bak){
		super.save1(bak);
		//发送给审批人提醒短信
//		smsSendManager.sendTingDx("群发审批", "现有短信内容【"+bak.getSmContent()+"】需要发送;请您回复spy"+bak.getId()+"通过审批：spn"+bak.getId()+"否决审批");
		
	}
	
	public void saveSmsSend(SmsSendEO entity){
		super.save(entity);
	}
	
	//保存用户咨询回复
	public void saveCon(SmsSendBak bak){
		String companyId = (String)Struts2Utils.getSession().getAttribute("companyId");
		String companyName = (String)Struts2Utils.getSession().getAttribute("companyName");
		bak.setCompanyId(companyId);
		bak.setCompanyName(companyName);
		super.save(bak);
	}

	public void update(String id){
		createQuery("update SmsSendBak  set isDel=1 where id="+id, null).executeUpdate();
	}
	//删除我发送的短信
	public void delMySendMsg(String id){
		createQuery("update MySendMsg  set isDel=1 where id="+id, null).executeUpdate();
	}
	
	//删除我发送的短信 批量
	public void delMultMySendMsg(String[] tempId){
		
		StringBuffer whereStr = new StringBuffer();
		String sql="update MySendMsg  set isDel=1 where id ";
		if(tempId != null && tempId.length != 0)
        {
            whereStr.append(" in(");
            for(int i = 0; i < tempId.length; i++)
            {
                whereStr.append((String)tempId[i]);
                whereStr.append(",");
            }
            sql = (new StringBuilder(String.valueOf(sql))).append(whereStr.toString().substring(0, whereStr.length() - 1)).append(")").toString();
        }
		
		createQuery(sql, null).executeUpdate();
	}
	
	//删除我发送的短信 批量
	public void delMultMyRecvMsg(String[] tempId){
		
		StringBuffer whereStr = new StringBuffer();
		String sql="update IndividualSMReceived  set readed=1,droped=1 where id ";
		if(tempId != null && tempId.length != 0)
        {
            whereStr.append(" in(");
            for(int i = 0; i < tempId.length; i++)
            {
                whereStr.append((String)tempId[i]);
                whereStr.append(",");
            }
            sql = (new StringBuilder(String.valueOf(sql))).append(whereStr.toString().substring(0, whereStr.length() - 1)).append(")").toString();
        }
		
		createQuery(sql, null).executeUpdate();
	}
	
	public void updateMult(String[] tempId){
		
		StringBuffer whereStr = new StringBuffer();
		String sql="update SmsSendEO  set isDel=1 where id ";
		if(tempId != null && tempId.length != 0)
        {
            whereStr.append(" in(");
            for(int i = 0; i < tempId.length; i++)
            {
                whereStr.append((String)tempId[i]);
                whereStr.append(",");
            }
            sql = (new StringBuilder(String.valueOf(sql))).append(whereStr.toString().substring(0, whereStr.length() - 1)).append(")").toString();
        }else{
        	sql+="= '"+"'";
        }
		
		createQuery(sql, null).executeUpdate();
	}

	public List findlist1(String urla ){
		
		return find("select new com.acec.wgt.models.ser.TreesVO(d.id,d.areaName,'0',-1,'"+urla+"') from AreaEO d where d.id in("+area+")");
	}
	public List findlist2(String urlb){
		
		return find("select new com.acec.wgt.models.ser.TreesVO(e.id,e.edificeName,'1',e.area.id,'"+urlb+"') from EdificeEO e where e.area.id in ("+area+") order by e.area.id");
	}
	
	public List findlist3(String areaId){
		
		return find("select e.id,e.edificeName,e.area.areaName from EdificeEO e where e.area.id="+areaId);
	
	}
	public List findlist4(String edificeId){
			
			return find("select o.id, o.id,o.houseName,o.ownerName,o.edificeName,o.areaName,o.houseName from HouseEO o where o.edificeId='"+edificeId+"'");
		
	}
	
	public List findlist5(String edificeId){
		
		return find("select id,houseName,buildnum,edificeName from HouseEO o where edificeId='"+edificeId+"'");
	
	}	
	public void sendSm(List<SMSendTask> listST)
	{
		
		getHibernateTemplate().saveOrUpdateAll(listST);
	}

	 
	 
	
	//修改回复状态
	public void updateReplayStatus(String smId){
		createQuery("update Consultation  set Readed = 1  where SM_ID="+smId+" ", null).executeUpdate();
	}
	  
	public PaginatorTag getListByPage(int no , int i,int smsType,String phone,String beginDate,String endDate) {
		//增加权限，发送人是自己
		String userName = (String)Struts2Utils.getSession().getAttribute("userName");
		String param = "";
		if(!"null".equals(phone) && !"".equals(phone))
			param = "and sendAddress='"+phone+"' ";
		if(!"null".equals(beginDate) && !"".equals(beginDate))
			param += "and date_format(sendTime,'%Y-%m-%d')>='"+beginDate+"' ";
		if(!"null".equals(endDate) && !"".equals(endDate))
			param += "and date_format(sendTime,'%Y-%m-%d')<='"+endDate+"' ";
		return pagedQuery("from SmsSendEO where userName='"+userName+"'and isDel=0 "+param+" order by sendTime desc",no,i);		
	}
	
	
	//从接收表取个人短信息列表
	public List<SMReceived> findmessger(String userCode) {
		return find("from SMReceived where Droped=0  and DestAddr=?",userCode);
	}
	
	//删除个人信息
	public void delmessger(int id) {
		createQuery("delete from  SMReceived  where SM_ID="+id).executeUpdate();
		
	}
	//删除我接收的信息
	public void delMyRecv(int id) {
		createQuery("update IndividualSMReceived set  droped=1 ,readed=1 where id="+id).executeUpdate();
		
	}
	//从个人信息表取信息
	public PaginatorTag getMyMsg(int no, int i, String creatorID) {
		return pagedQuery("from IndividualSMReceived i where i.Droped=0  and i.CreatorID='"+creatorID+"'",no,i);		
	}

	public PaginatorTag getListByPage(int no, int i, String creatorID) {
		
		return pagedQuery("from Questions where CreatorID='"+creatorID+"' and Question_type='30' and isDel=0 order by id desc",no,i);
	}

	public void delMySend(String id) {
		createQuery("update Questions q set q.isDel=1 where q.ID="+id+"").executeUpdate();
	}
	
	//我的发送记录
	public PaginatorTag getMySendMsg(int no , int i,String creatorID) {
		return pagedQuery("from MySendMsg where isDel=2 and smsType=5 and creatorID='"+creatorID+"' order by id desc",no,i);		
	}

	public void sendMyMsg(MySendMsg ms,String msgId) {
		List l1 = new ArrayList();
		String aa[] = ms.getDestAddr().split(",");
		for (int i = 0; i < aa.length; i++) {
			l1.add(aa[i]);
		}
		
		List l2 = new ArrayList();
		l2.add(ms.getSmContent());
		
//		smsSendManager.intsendSmtyp(l1,"", smsSendManager.getOrgAddr()+ms.getCreatorID(), ms.getCreatorID(),  l2);
		
		if(null!=msgId)
		{
			createQuery("update IndividualSMReceived set readed=1 where id="+msgId,null).executeUpdate();
		}
		
		save(ms);
	}

	public void setSmsSendManager(SMSSendManager smsSendManager) {
		this.smsSendManager = smsSendManager;
	}

	public List selectConsultation(String sjh) {
		// TODO Auto-generated method stub
		return find("from SmsSendBak where smsType='2' and destAddr='"+sjh+"'");
	}

	public void saveMyRecvMsg(IndividualSMReceived indSMRecv,int id) {
		save(indSMRecv);
		delmessger(id);
		
	}
	 
}