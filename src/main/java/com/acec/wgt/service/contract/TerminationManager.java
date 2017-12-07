package com.acec.wgt.service.contract;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.acec.core.orm.Page;
import com.acec.core.web.struts2.Struts2Utils;
import com.acec.wgt.models.contract.TerminationDAO;
import com.acec.wgt.models.contract.TerminationEO;

@Service
@Transactional
public class TerminationManager {
    private final Logger logger = LoggerFactory.getLogger(TerminationManager.class);

    @Autowired
    private TerminationDAO terminationDao;

    
    public Page getPage(Page page,String where) {
		return terminationDao.findPage(page, "from TerminationEO where isdel='N' "+where);
    }
 
    public List<TerminationEO> getTermination(String where) {
    	return terminationDao.find("from TerminationEO where isdel='N' " + where);
    }

    /**
     * 根据id获取
     * 
     * @param id
     * @return
     */
    public TerminationEO getById(int id) {
    	return terminationDao.get(id);
    }

    /**
     * 新增;修改;
     * 
     * @param department
     * @return
     */
    public void save(TerminationEO entity) {
    	terminationDao.save(entity);
    }

    public int getNum(String userId){
    	StringBuffer sb = new StringBuffer();
    	String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
 		sb.append(" and areaId in (").append(areaIds).append(") ");
 		sb.append(" and ( departid='").append(userId).append("' or departoneid ='").append(userId).append("' or officeid = '").append(userId)
 		.append("' or leadershipid = '").append(userId).append("') ");
		List<TerminationEO> list = getTermination(sb.toString());
    	int k = 0 ;
		for(TerminationEO entity : list){
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
		    if("Y".equals(param))
		    	k++;
	    		 
		}
	    return k;
    }
}
