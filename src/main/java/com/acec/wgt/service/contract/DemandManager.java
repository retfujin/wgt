package com.acec.wgt.service.contract;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.acec.core.orm.Page;
import com.acec.core.web.struts2.Struts2Utils;
import com.acec.wgt.models.contract.DemandDAO;
import com.acec.wgt.models.contract.DemandEO;

@Service
@Transactional
public class DemandManager {
    private final Logger logger = LoggerFactory.getLogger(DemandManager.class);

    @Autowired
    private DemandDAO demandDao;

    
    public Page getPage(Page page,String where) {
		return demandDao.findPage(page, "from DemandEO where isdel='N' "+where);
    }
 
    public List<DemandEO> getDemand(String where) {
    	return demandDao.find("from DemandEO where isdel='N' " + where);
    }

    /**
     * 根据id获取
     * 
     * @param id
     * @return
     */
    public DemandEO getById(int id) {
    	return demandDao.get(id);
    }

    /**
     * 新增;修改;
     * 
     * @param department
     * @return
     */
    public void save(DemandEO entity) {
    	demandDao.save(entity);
    }

    public int getNum(String userId){
    	StringBuffer sb = new StringBuffer();
    	String areaIds=(String)Struts2Utils.getSession().getAttribute("areaIds");
 		sb.append(" and areaId in (").append(areaIds).append(") ");
 		sb.append(" and (officeid='").append(userId).append("' or leadershipid = '").append(userId).append("') ");
		List<DemandEO> list = getDemand(sb.toString());
    	int k = 0 ;
		for(DemandEO entity : list){
	    	String param = "Y";//Y可以审核   N不可以审核
	    	if(entity.getOfficeid()!=null && entity.getOfficeid().equals(userId))
		    	if(null!=entity.getOfficename() && !"".equals(entity.getOfficename()))
		    		param = "N";
			    else
			    	param = "Y";
		    if(entity.getLeadershipid()!=null && entity.getLeadershipid().equals(userId))
		    	if(null!=entity.getLeadershipname() && !"".equals(entity.getLeadershipname()))
		    		param = "N";
		    	else
		    		param = "Y";
		    if("Y".equals(param))
		    	k++;
	    		 
		}
	    return k;
    }
}
