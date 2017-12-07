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
import com.acec.wgt.models.administration.VacateDAO;
import com.acec.wgt.models.administration.VacateEO;

@Service
@Transactional
public class VacateManager {
    private final Logger logger = LoggerFactory.getLogger(VacateManager.class);

    @Autowired
    private VacateDAO vacateDao;
 
    public Page getPage(Page page, String where) {
		Page training = vacateDao.findPage(page, ("from VacateEO where isdel='N'" + where+" order by id desc"));	
		return training;
    }
    
    public List<VacateEO> getVacate(String where) {
    	return vacateDao.find("from VacateEO where isdel='N' " + where);
    }
 
    public VacateEO getById(int id) {
    	return vacateDao.get(id);
    }
    
    public void save(VacateEO entity) {
    	vacateDao.save(entity);
    }
    
    public int getNum(String userId){
    	StringBuffer sb = new StringBuffer();
    	String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
 		sb.append(" and areaId in (").append(areaIds).append(") ");
 		sb.append(" and (auditid ='").append(userId).append("' or approvalid ='").append(userId).append("' ) ");
		List<VacateEO> list = getVacate(sb.toString());
    	int k = 0 ;
		for(VacateEO entity : list){
	    	String param = "Y";//Y可以审核   N不可以审核
	    	if(entity.getAuditid()!=null && entity.getAuditid().equals(userId))
		    	if(null!=entity.getAuditname() && !"".equals(entity.getAuditname()))
		    		param = "N";
				else
				    param = "Y";
		    if(entity.getApprovalid()!=null && entity.getApprovalid().equals(userId))
		    	if(null!=entity.getApprovalname() && !"".equals(entity.getApprovalname()))
		    		param = "N";
				else
				    param = "Y";
		    if("Y".equals(param))
		    	k++;
	    		 
		}
	    return k;
    }
}