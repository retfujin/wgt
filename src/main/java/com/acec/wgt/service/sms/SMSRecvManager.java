/**
 * @author th
 * @version 1.0
 * @since 1.0
 */

package com.acec.wgt.service.sms;

import java.net.URLDecoder;
import java.util.List;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.acec.core.orm.hibernate.HibernateDao;
import com.acec.core.utils.Utils;
import com.acec.wgt.models.baseData.HouseDAO;
import com.acec.wgt.models.baseData.HouseEO; 
import com.acec.wgt.models.ser.SMSRecvDAO;
import com.acec.wgt.models.ser.SMSRecv; 
import com.acec.commons.util.PaginatorTag;
import com.acec.core.utils.SMSTools;

//Spring Bean的标识.
@Service
//默认将类中的所有函数纳入事务管理.
@Transactional
public class SMSRecvManager {

    @Autowired
    private SMSRecvDAO smsRecvDAO;
    @Autowired
    private HouseDAO houseDao;
      

    public void saveOrUpdate() {
		// 先从web接口得到MO
    	try{
			SMSServiceImpl impl = new SMSServiceImplService().getSMSServiceImplPort();
			List<String> ls = impl.recvSMS(SMSTools.getSubId(), SMSTools.getSubPass());
			for (int i = 0; i < ls.size(); i++) {
			    SMSRecv entity = new SMSRecv();
			    String recvStr = ls.get(i);
			    if (recvStr != null) {
				String[] recvmo = recvStr.split("\\|");
				if (recvmo.length == 5) {
				    String msgid = recvmo[0];
				    String sjh = recvmo[1];
				    String subid = recvmo[2];
				    String content = recvmo[3];
				    String recvtime = recvmo[4];
		
				    
	 
				    HouseEO h = houseDao.getHouseByMobile(sjh);
				    if(h!=null){
				    	entity.setUserName(h.getId());			    	
				    }			    
				    entity.setMsgId(msgid);
				    entity.setMobile(sjh);
				    entity.setSubid(subid);
				    content = URLDecoder.decode(content,"GBK");
				    entity.setContent(content);
				    entity.setRecvTime(Utils.strToMinterDate(recvtime));
				    smsRecvDAO.save(entity);
				}
			    }
			}
    	}catch (Exception e) {
    		throw new RuntimeException("日期类型转换错误！");
		}
    }

    public PaginatorTag findListByPage(int no , int i,String where) {
		
    	// 先从web接口中取出信息保存到表中
    	saveOrUpdate();
    	return smsRecvDAO.getListByPage(no, i, where);
	}
    
    
    
    
    
}