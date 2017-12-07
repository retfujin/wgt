package com.acec.wgt.service.wuliao;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.acec.core.orm.Page;
import com.acec.core.web.struts2.Struts2Utils;
import com.acec.wgt.models.wuliao.ProcurementDAO;
import com.acec.wgt.models.wuliao.ProcurementEO;

@Service
@Transactional
public class ProcurementManager {
    private final Logger logger = LoggerFactory.getLogger(ProcurementManager.class);

    @Autowired
    private ProcurementDAO procurementDao;
 
    public Page getPage(Page page, String where) {
		Page procurement = procurementDao.findPage(page, ("from ProcurementEO where isdel='N'" + where+" order by id desc"));	
		return procurement;
    }
    
    public List<ProcurementEO> getProcurementList(String where) {
    	return procurementDao.find("from ProcurementEO where isdel='N' " + where);
    }
 
    public ProcurementEO getById(int id) {
    	return procurementDao.get(id);
    }
    
    public void save(ProcurementEO entity) {
    	procurementDao.save(entity);
    }
    
    public int getNum(String userId){
    	StringBuffer sb = new StringBuffer();
    	String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
 		sb.append(" and areaId in (").append(areaIds).append(") ");
 		sb.append(" and (departid='").append(userId).append("' or departoneid='");
 		sb.append(userId).append("' or officeid='").append(userId).append("' or viceid='");
 		sb.append(userId).append("' or managerid='").append(userId).append("' )");
		List<ProcurementEO> list = getProcurementList(sb.toString());
    	int k = 0 ;
		for(ProcurementEO entity : list){
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
		    if("Y".equals(param))
		    	k++;
	    		 
		}
	    return k;
    }
}