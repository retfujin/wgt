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
import com.acec.wgt.models.administration.DepartureDAO;
import com.acec.wgt.models.administration.DepartureEO;

@Service
@Transactional
public class DepartureManager {
    private final Logger logger = LoggerFactory.getLogger(DepartureManager.class);

    @Autowired
    private DepartureDAO departureDao;
 
    public Page getPage(Page page, String where) {
		Page departure = departureDao.findPage(page, ("from DepartureEO where isdel='N'" + where+" order by id desc"));	
		return departure;
    }
    
    public List<DepartureEO> getDepartureList(String where) {
    	return departureDao.find("from DepartureEO where isdel='N' " + where);
    }
 
    public DepartureEO getById(int id) {
    	return departureDao.get(id);
    }
    
    public void save(DepartureEO entity) {
    	departureDao.save(entity);
    }
    
    public int getNum(String userId){
    	StringBuffer sb = new StringBuffer();
    	String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
 		sb.append(" and areaId in (").append(areaIds).append(") ");
 		sb.append(" and (contentid='").append(userId).append("' or departid='").append(userId).append("' or departoneid='");
 		sb.append(userId).append("' or officeid='").append(userId).append("' or viceid='");
 		sb.append(userId).append("' or managerid='").append(userId).append("' or workoneid='");
 		sb.append(userId).append("' or worktwoid='").append(userId).append("' or workthreeid='").append(userId).append("' )");
		List<DepartureEO> list = getDepartureList(sb.toString());
    	int k = 0 ;
		for(DepartureEO entity : list){
	    	String param = "Y";//Y可以审核   N不可以审核
	    	if(entity.getContentid()!=null && entity.getContentid().equals(userId))
		    	if(null!=entity.getContent() && !"".equals(entity.getContent()))
		    		param = "N";
			    else
			    	param = "Y";
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
		    if(entity.getWorkoneid()!=null && entity.getWorkoneid().equals(userId))
		    	if(null!=entity.getWorkmanagerone() && !"".equals(entity.getWorkmanagerone()))
		    		param = "N";
				else
				    param = "Y";
		    if(entity.getWorktwoid()!=null && entity.getWorktwoid().equals(userId))
		    	if(null!=entity.getWorkmanagertwo() && !"".equals(entity.getWorkmanagertwo()))
		    		param = "N";
				else
				    param = "Y";
		    if(entity.getWorkthreeid()!=null && entity.getWorkthreeid().equals(userId))
		    	if(null!=entity.getWorkmanagerthree() && !"".equals(entity.getWorkmanagerthree()))
		    		param = "N";
				else
				    param = "Y";
		    if("Y".equals(param))
		    	k++;
	    		 
		}
	    return k;
    }
}