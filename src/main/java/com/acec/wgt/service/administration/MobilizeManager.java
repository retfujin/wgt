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
import com.acec.wgt.models.administration.MobilizeDAO;
import com.acec.wgt.models.administration.MobilizeEO;

@Service
@Transactional
public class MobilizeManager {
    private final Logger logger = LoggerFactory.getLogger(MobilizeManager.class);

    @Autowired
    private MobilizeDAO mobilizeDao;
 
    public Page getPage(Page page, String where) {
		Page mobilize = mobilizeDao.findPage(page, ("from MobilizeEO where isdel='N'" + where+" order by id desc"));	
		return mobilize;
    }
    
    public List<MobilizeEO> getMobilezeList(String where) {
    	return mobilizeDao.find("from MobilizeEO where isdel='N' " + where);
    }
 
    public MobilizeEO getById(int id) {
    	return mobilizeDao.get(id);
    }
    
    public void save(MobilizeEO entity) {
    	mobilizeDao.save(entity);
    }
    
    public int getNum(String userId){
    	StringBuffer sb = new StringBuffer();
    	String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
 		sb.append(" and areaId in (").append(areaIds).append(") ");
 		sb.append(" and (applyid='").append(userId).append("' or departid='").append(userId).append("' or departoneid='");
 		sb.append(userId).append("' or officeid='").append(userId).append("' or viceid='");
 		sb.append(userId).append("' or managerid='").append(userId).append("' )");
		List<MobilizeEO> list = getMobilezeList(sb.toString());
    	int k = 0 ;
		for(MobilizeEO entity : list){
	    	String param = "Y";//Y可以审核   N不可以审核
	    	if(entity.getApplyid()!=null && entity.getApplyid().equals(userId))
		    	if(null!=entity.getApplyname() && !"".equals(entity.getApplyname()))
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
		    if("Y".equals(param))
		    	k++;
	    		 
		}
	    return k;
    }
}