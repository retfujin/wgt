package com.acec.wgt.service.administration;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.acec.core.orm.Page;
import com.acec.wgt.models.administration.ApplyDAO;
import com.acec.wgt.models.administration.ApplyEO;

@Service
@Transactional
public class ApplyManager {
    private final Logger logger = LoggerFactory.getLogger(ApplyManager.class);

    @Autowired
    private ApplyDAO applyDao;
 
    public Page getPage(Page page, String where) {
		Page apply = applyDao.findPage(page, ("from ApplyEO where isdel='N'" + where +" order by id desc"));	
		return apply;
    }
    
    public List<ApplyEO> getApply(String where) {
    	return applyDao.find("from ApplyEO where isdel='N' " + where);
    }
 
    public ApplyEO getById(int id) {
    	return applyDao.get(id);
    }
    
    public void save(ApplyEO entity) {
    	applyDao.save(entity);
    }
}