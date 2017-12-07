package com.acec.wgt.service.administration;

import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.acec.core.orm.Page;
import com.acec.wgt.models.administration.TrainingdetailDAO;
import com.acec.wgt.models.administration.TrainingdetailEO;

@Service
@Transactional
public class TrainingdetailManager {
    private final Logger logger = LoggerFactory.getLogger(TrainingdetailManager.class);

    @Autowired
    private TrainingdetailDAO trainingdetailDao;
 
    public Page getPage(Page page, String where) {
		Page trainingdetail = trainingdetailDao.findPage(page, ("from TrainingdetailEO " + where));	
		return trainingdetail;
    }
    
    public List<TrainingdetailEO> getTrainingdetail(String where) {
    	return trainingdetailDao.find("from TrainingdetailEO " + where);
    }
 
    public TrainingdetailEO getById(int id) {
    	return trainingdetailDao.get(id);
    }
    
    
    
    public void save(TrainingdetailEO entity) {
    	trainingdetailDao.save(entity);
    }
}