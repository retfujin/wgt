package com.acec.wgt.service.contract;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.acec.core.orm.Page;
import com.acec.core.web.struts2.Struts2Utils;
import com.acec.wgt.models.contract.AssessmentDAO;
import com.acec.wgt.models.contract.AssessmentEO;

@Service
@Transactional
public class AssessmentManager {
    private final Logger logger = LoggerFactory.getLogger(AssessmentManager.class);

    @Autowired
    private AssessmentDAO assessmentDao;

    
    public Page getPage(Page page,String where) {
		return assessmentDao.findPage(page, "from AssessmentEO where isdel='N' "+where);
    }
 
    public List<AssessmentEO> getAssessment(String where) {
    	return assessmentDao.find("from AssessmentEO where isdel='N' " + where);
    }

    /**
     * 根据id获取
     * 
     * @param id
     * @return
     */
    public AssessmentEO getById(int id) {
    	return assessmentDao.get(id);
    }

    /**
     * 新增;修改;
     * 
     * @param department
     * @return
     */
    public void save(AssessmentEO entity) {
    	assessmentDao.save(entity);
    }

    public int getNum(String userId){
    	StringBuffer sb = new StringBuffer();
    	String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
 		sb.append(" and areaId in (").append(areaIds).append(") ");
 		sb.append(" and (departid ='").append(userId).append("' or departoneid ='").append(userId).append("' or officeid ='")
 		.append(userId).append("' or viceid = '").append(userId).append("' or managerid ='").append(userId).append("' or mid ='")
 		.append(userId).append("') ");
		List<AssessmentEO> list = getAssessment(sb.toString());
    	int k = 0 ;
		for(AssessmentEO entity : list){
	    	String param = "Y";//Y可以审核   N不可以审核
	    	if(entity.getDepartid()!=null && entity.getDepartid().equals(userId))
		    	if(null!=entity.getDepartname() && !"".equals(entity.getDepartname()))
		    		param = "N";
			    else
			    	param = "Y";
		    if(entity.getDepartoneid()!=null && entity.getDepartoneid().equals(userId))
		    	if(null!=entity.getDepartonename() && !"".equals(entity.getDepartonename()))
		    		param = "N";
		    	else
		    		param = "Y";
		    if(entity.getOfficeid()!=null && entity.getOfficeid().equals(userId))
		    	if(null!=entity.getOfficename() && !"".equals(entity.getOfficename()))
		    		param = "N";
		    	else
		    		param = "Y";
		    if(entity.getViceid()!=null && entity.getViceid().equals(userId))
		    	if(null!=entity.getVicename() && !"".equals(entity.getVicename()))
		    		param = "N";
		    	else
		    		param = "Y";
		    if(entity.getManagerid()!=null && entity.getManagerid().equals(userId))
		    	if(null!=entity.getManagername() && !"".equals(entity.getManagername()))
		    		param = "N";
		    	else
		    		param = "Y";
		    if(entity.getMid()!=null && entity.getMid().equals(userId))
		    	if(null!=entity.getMname() && !"".equals(entity.getMname()))
		    		param = "N";
		    	else
		    		param = "Y";
		    if("Y".equals(param))
		    	k++;
	    		 
		}
	    return k;
    }
}
