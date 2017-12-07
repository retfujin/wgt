package com.acec.wgt.service.sms;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.acec.commons.util.PaginatorTag;
import com.acec.core.web.struts2.Struts2Utils;
import com.acec.wgt.models.ser.SMSSend;
import com.acec.wgt.models.ser.SMSSendDAO;


//Spring Bean的标识.
@Service
//默认将类中的所有函数纳入事务管理.
@Transactional
public class SMSSendManager {
	
	@Autowired
	private SMSSendDAO smsSendDAO;
	
	 

	public PaginatorTag getListByPage(int no , int i,String where) {
		return smsSendDAO.getListByPage(no, i, where);	
	}
	
	public void save(SMSSend entity){
		smsSendDAO.save(entity);
	}
	
}
