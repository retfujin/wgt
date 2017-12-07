package com.acec.wgt.service.administration;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.acec.core.orm.Page;
import com.acec.core.web.struts2.Struts2Utils;
import com.acec.wgt.models.administration.RecruitmentDAO;
import com.acec.wgt.models.administration.RecruitmentEO;

@Service
@Transactional
public class RecruitmentManager {
    private final Logger logger = LoggerFactory.getLogger(RecruitmentManager.class);

    @Autowired
    private RecruitmentDAO recruitmentDao;
 
    public Page getPage(Page page, String where) {
		Page training = recruitmentDao.findPage(page, ("from RecruitmentEO where isdel='N'" + where+" order by id desc"));	
		return training;
    }
    
    public List<RecruitmentEO> getRecruitmentList(String where) {
    	return recruitmentDao.find("from RecruitmentEO where isdel='N' " + where);
    }
 
    public RecruitmentEO getById(int id) {
    	return recruitmentDao.get(id);
    }
    
    public void save(RecruitmentEO entity) {
    	recruitmentDao.save(entity);
    }
    
    public int getNum(String userId){
    	StringBuffer sb = new StringBuffer();
    	String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
 		sb.append(" and areaId in (").append(areaIds).append(") ");
 		sb.append("and departid='").append(userId).append("' ");
		List<RecruitmentEO> list = getRecruitmentList(sb.toString());
    	int k = 0 ;
		for(RecruitmentEO entity : list){
	    	String param = "Y";//Y可以审核   N不可以审核
		    if(entity.getDepartid()!=null && entity.getDepartid().equals(userId))
		    	if(null!=entity.getDepartname() && !"".equals(entity.getDepartname()))
		    		param = "N";
		    	else
		    		param = "Y";
		    if("Y".equals(param))
		    	k++;
	    		 
		}
	    return k;
    }
}