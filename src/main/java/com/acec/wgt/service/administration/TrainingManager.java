package com.acec.wgt.service.administration;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.acec.core.orm.Page;
import com.acec.wgt.models.administration.TrainingDAO;
import com.acec.wgt.models.administration.TrainingEO;

@Service
@Transactional
public class TrainingManager {
    private final Logger logger = LoggerFactory.getLogger(TrainingManager.class);

    @Autowired
    private TrainingDAO trainingDao;
 
    public Page getPage(Page page, String where) {
		Page training = trainingDao.findPage(page, ("from TrainingEO where isdel='N'" + where+" order by id desc"));	
		return training;
    }
    
    public List<TrainingEO> getApply(String where) {
    	return trainingDao.find("from TrainingEO where isdel='N' " + where);
    }
 
    public TrainingEO getById(int id) {
    	return trainingDao.get(id);
    }
    
    public void save(TrainingEO entity) {
    	trainingDao.save(entity);
    }
}